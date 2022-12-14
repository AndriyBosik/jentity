package com.jentity.core.configuration.impl;

import com.jentity.core.configuration.StartupConfigurator;
import com.jentity.core.exception.InitializationException;
import com.jentity.core.meta.ConfigProperty;
import com.jentity.core.meta.Regex;
import com.jentity.core.model.config.Configuration;
import com.jentity.core.model.config.DbProperties;
import com.jentity.core.model.config.SchemaProperties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApplicationPropertiesStartupConfigurator implements StartupConfigurator {
  private static final Pattern pattern = Pattern.compile(Regex.ENV.getPattern());

  @Override
  public Configuration configure() {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    Properties properties = new Properties();
    try (InputStream resourceStream = classLoader.getResourceAsStream("application.properties")) {
      properties.load(resourceStream);
      return getConfigurationFromProperties(properties);
    } catch (IOException exception) {
      throw new InitializationException("Failed to initialize configuration", exception);
    }
  }

  private Configuration getConfigurationFromProperties(Properties properties) {
    return new Configuration(
        new DbProperties(
            parsePropertyValue(properties, ConfigProperty.DB_DRIVER),
            parsePropertyValue(properties, ConfigProperty.DB_URL),
            parsePropertyValue(properties, ConfigProperty.DB_USERNAME),
            parsePropertyValue(properties, ConfigProperty.DB_PASSWORD)
        ),
        new SchemaProperties(
            parsePropertyValue(properties, ConfigProperty.SCHEMA_DEFINITION_PACKAGE)
        )
    );
  }

  private String parsePropertyValue(Properties properties, ConfigProperty property) {
    String propertyValue = properties.getProperty(property.getName());
    Matcher matcher = pattern.matcher(propertyValue);
    StringBuilder sb = new StringBuilder();
    while (matcher.find()) {
      String envValue = parseEnvValue(matcher.group(1));
      matcher.appendReplacement(sb, envValue);
    }
    matcher.appendTail(sb);
    return sb.toString();
  }

  private String parseEnvValue(String env) {
    int colonIndex = env.indexOf(":");
    String envName = colonIndex == -1 ? env : env.substring(0, colonIndex);
    String envValue = System.getenv(envName);
    if (envValue != null) {
      return envValue;
    }
    if (colonIndex == -1) {
      throw new InitializationException("Unable to find env: " + envName);
    }
    return env.substring(colonIndex + 1);
  }
}

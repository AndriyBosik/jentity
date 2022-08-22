package com.jentity.core;

import com.jentity.core.configuration.StartupConfigurator;
import com.jentity.core.context.JEntityContext;
import com.jentity.core.context.impl.DefaultJEntityContext;
import com.jentity.core.db.impl.DefaultDbManager;
import com.jentity.core.exception.InitializationException;
import com.jentity.core.model.config.Configuration;
import com.jentity.core.model.schema.SchemaDefinition;
import com.jentity.core.service.SchemaDefinitionReader;
import com.jentity.core.service.impl.DefaultPackageService;
import com.jentity.core.service.impl.DefaultSchemaDefinitionReader;

public enum JEntity {
  INSTANCE;

  public JEntityContext createContext(StartupConfigurator configurator) {
    Configuration configuration = configurator.configure();
    initializeApplication(configuration);
    DefaultPackageService packageService = new DefaultPackageService();
    SchemaDefinitionReader schemaDefinitionReader = new DefaultSchemaDefinitionReader(packageService);
    SchemaDefinition schemaDefinition = schemaDefinitionReader.readSchema(configuration.getSchemaProperties());

    return new DefaultJEntityContext(
        new DefaultDbManager(),
        schemaDefinition
    );
  }

  private void initializeApplication(Configuration configuration) {
    initializeDbDriver(configuration.getDbProperties().getDriver());
  }

  private void initializeDbDriver(String driverClassName) {
    try {
      Class.forName(driverClassName);
    } catch (ClassNotFoundException exception) {
      throw new InitializationException("Unable to load database driver class: " + driverClassName, exception);
    }
  }
}

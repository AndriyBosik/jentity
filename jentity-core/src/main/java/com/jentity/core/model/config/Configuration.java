package com.jentity.core.model.config;

public class Configuration {
  private final DbProperties dbProperties;
  private final SchemaProperties schemaProperties;

  public Configuration(
      DbProperties dbProperties,
      SchemaProperties schemaProperties
  ) {
    this.dbProperties = dbProperties;
    this.schemaProperties = schemaProperties;
  }

  public DbProperties getDbProperties() {
    return dbProperties;
  }

  public SchemaProperties getSchemaProperties() {
    return schemaProperties;
  }
}

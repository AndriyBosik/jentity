package com.jentity.orm.model;

public class Configuration {
  private final DbProperties dbProperties;

  public Configuration(
      DbProperties dbProperties
  ) {
    this.dbProperties = dbProperties;
  }

  public DbProperties getDbProperties() {
    return dbProperties;
  }
}

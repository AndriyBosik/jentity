package com.jentity.orm.meta;

public enum ConfigProperty {
  DB_DRIVER("jentity.db.driver"),
  DB_URL("jentity.db.url"),
  DB_USERNAME("jentity.db.username"),
  DB_PASSWORD("jentity.db.password");

  private final String name;

  ConfigProperty(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}

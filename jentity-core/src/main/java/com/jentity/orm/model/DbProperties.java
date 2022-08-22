package com.jentity.orm.model;

public class DbProperties {
  private final String driver;
  private final String url;
  private final String username;
  private final String password;

  public DbProperties(
      String driver,
      String url,
      String username,
      String password
  ) {
    this.driver = driver;
    this.url = url;
    this.username = username;
    this.password = password;
  }

  public String getDriver() {
    return driver;
  }

  public String getUsername() {
    return username;
  }

  public String getUrl() {
    return url;
  }

  public String getPassword() {
    return password;
  }
}

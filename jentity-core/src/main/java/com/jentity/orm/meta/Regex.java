package com.jentity.orm.meta;

public enum Regex {
  ENV("\\$\\{([a-zA-Z\\d:_/]+)\\}");

  private final String pattern;

  Regex(String pattern) {
    this.pattern = pattern;
  }

  public String getPattern() {
    return pattern;
  }
}

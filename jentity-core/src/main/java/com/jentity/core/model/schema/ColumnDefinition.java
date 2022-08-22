package com.jentity.core.model.schema;

public class ColumnDefinition {
  protected final String name;

  public ColumnDefinition(
      String name
  ) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}

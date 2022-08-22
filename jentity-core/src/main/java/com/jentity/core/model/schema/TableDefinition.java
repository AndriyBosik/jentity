package com.jentity.core.model.schema;

import java.util.List;

public class TableDefinition {
  private final String name;
  private final Class<?> mappedClass;
  private final List<ColumnDefinition> columnDefinitions;

  public TableDefinition(
      String name,
      Class<?> mappedClass,
      List<ColumnDefinition> columnDefinitions
  ) {
    this.name = name;
    this.mappedClass = mappedClass;
    this.columnDefinitions = columnDefinitions;
  }

  public String getName() {
    return name;
  }

  public Class<?> getMappedClass() {
    return mappedClass;
  }

  public List<ColumnDefinition> getColumnDefinitions() {
    return columnDefinitions;
  }
}

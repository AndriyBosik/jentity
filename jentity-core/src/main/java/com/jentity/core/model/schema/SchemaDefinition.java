package com.jentity.core.model.schema;

import java.util.List;

public class SchemaDefinition {
  private final List<TableDefinition> tableDefinitions;

  public SchemaDefinition(
      List<TableDefinition> tableDefinitions
  ) {
    this.tableDefinitions = tableDefinitions;
  }

  public List<TableDefinition> getTableDefinitions() {
    return tableDefinitions;
  }
}

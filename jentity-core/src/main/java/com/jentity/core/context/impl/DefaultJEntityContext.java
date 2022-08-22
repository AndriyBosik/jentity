package com.jentity.core.context.impl;

import com.jentity.core.context.JEntityContext;
import com.jentity.core.db.DbManager;
import com.jentity.core.model.schema.SchemaDefinition;

public class DefaultJEntityContext implements JEntityContext {
  private final DbManager dbManager;
  private final SchemaDefinition schemaDefinition;

  public DefaultJEntityContext(
      DbManager dbManager,
      SchemaDefinition schemaDefinition
  ) {
    this.dbManager = dbManager;
    this.schemaDefinition = schemaDefinition;
  }

  @Override
  public DbManager getDbManager() {
    return dbManager;
  }

  public SchemaDefinition getSchemaDefinition() {
    return schemaDefinition;
  }
}

package com.jentity.core.service;

import com.jentity.core.model.config.SchemaProperties;
import com.jentity.core.model.schema.SchemaDefinition;

public interface SchemaDefinitionReader {
  SchemaDefinition readSchema(SchemaProperties schemaProperties);
}

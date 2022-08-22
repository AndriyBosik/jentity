package com.jentity.core.model.config;

public class SchemaProperties {
  private final String definitionPackage;

  public SchemaProperties(
      String definitionPackage
  ) {
    this.definitionPackage = definitionPackage;
  }

  public String getDefinitionPackage() {
    return definitionPackage;
  }
}

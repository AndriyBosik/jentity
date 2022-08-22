package com.jentity.core.service.impl;

import com.jentity.core.model.config.SchemaProperties;
import com.jentity.core.model.schema.ColumnDefinition;
import com.jentity.core.model.schema.PrimaryKeyColumnDefinition;
import com.jentity.core.model.schema.SchemaDefinition;
import com.jentity.core.model.schema.TableDefinition;
import com.jentity.core.service.PackageService;
import com.jentity.core.service.SchemaDefinitionReader;
import com.jentity.schema.annotation.Column;
import com.jentity.schema.annotation.Table;
import com.jentity.schema.persistence.Field;
import com.jentity.schema.persistence.PersistentTable;
import com.jentity.schema.persistence.PrimaryKeyField;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultSchemaDefinitionReader implements SchemaDefinitionReader {
  private final PackageService packageService;

  public DefaultSchemaDefinitionReader(PackageService packageService) {
    this.packageService = packageService;
  }

  @Override
  public SchemaDefinition readSchema(SchemaProperties schemaProperties) {
    List<Class<?>> persistentClasses = packageService.getAllClasses(
        schemaProperties.getDefinitionPackage(),
        PersistentTable.class::isAssignableFrom);

    List<TableDefinition> tableDefinitions = persistentClasses.stream()
        .map(this::getTableDefinition)
        .collect(Collectors.toList());

    return new SchemaDefinition(tableDefinitions);
  }

  private TableDefinition getTableDefinition(Class<?> persistentClass) {
    Table annotation = persistentClass.getAnnotation(Table.class);
    String tableName = annotation == null ? persistentClass.getName() : annotation.name();

    List<ColumnDefinition> columnDefinitions = Arrays.stream(persistentClass.getDeclaredMethods())
        .filter(method -> Field.class.isAssignableFrom(method.getReturnType()))
        .map(this::getColumnDefinition)
        .collect(Collectors.toList());

    return new TableDefinition(
        tableName,
        persistentClass,
        columnDefinitions
    );
  }

  private ColumnDefinition getColumnDefinition(Method method) {
    Column annotation = method.getAnnotation(Column.class);
    String columnName = annotation == null ? method.getName() : annotation.name();
    Class<?> returnType = method.getReturnType();
    if (PrimaryKeyField.class.isAssignableFrom(returnType)) {
      return new PrimaryKeyColumnDefinition(columnName);
    }
    return new ColumnDefinition(columnName);
  }
}

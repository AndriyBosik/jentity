package com.example.jentity.table;

import com.jentity.schema.annotation.Table;
import com.jentity.schema.persistence.Field;
import com.jentity.schema.persistence.PersistentTable;
import com.jentity.schema.persistence.PrimaryKeyField;

@Table(name = "users")
public interface UserTable extends PersistentTable {
  PrimaryKeyField<Integer> id();

  Field<String> name();

  Field<String> surname();

  Field<Integer> age();
}

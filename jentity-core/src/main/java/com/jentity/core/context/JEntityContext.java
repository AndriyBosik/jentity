package com.jentity.core.context;

import com.jentity.core.db.DbManager;

public interface JEntityContext {
  DbManager getDbManager();
}

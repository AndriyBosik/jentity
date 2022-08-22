package com.jentity.orm;

import com.jentity.orm.configuration.StartupConfigurator;
import com.jentity.orm.context.JEntityContext;
import com.jentity.orm.context.impl.DefaultJEntityContext;
import com.jentity.orm.model.Configuration;

public enum JEntity {
  INSTANCE;

  public JEntityContext createContext(StartupConfigurator configurator) {
    Configuration configuration = configurator.configure();
    return new DefaultJEntityContext();
  }
}

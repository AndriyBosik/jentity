package com.example.jentity;

import com.jentity.orm.JEntity;
import com.jentity.orm.configuration.impl.ApplicationPropertiesStartupConfigurator;
import com.jentity.orm.context.JEntityContext;

public class Application {
  public static void main(String[] args) {
    ApplicationPropertiesStartupConfigurator configurator = new ApplicationPropertiesStartupConfigurator();
    JEntityContext context = JEntity.INSTANCE.createContext(configurator);
  }
}

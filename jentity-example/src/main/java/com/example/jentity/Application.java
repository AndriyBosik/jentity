package com.example.jentity;

import com.jentity.core.JEntity;
import com.jentity.core.configuration.impl.ApplicationPropertiesStartupConfigurator;
import com.jentity.core.context.JEntityContext;

public class Application {
  public static void main(String[] args) {
    ApplicationPropertiesStartupConfigurator configurator = new ApplicationPropertiesStartupConfigurator();
    JEntityContext context = JEntity.INSTANCE.createContext(configurator);
  }
}

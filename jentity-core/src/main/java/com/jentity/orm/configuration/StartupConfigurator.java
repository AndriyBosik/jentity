package com.jentity.orm.configuration;

import com.jentity.orm.model.Configuration;

public interface StartupConfigurator {
  Configuration configure();
}

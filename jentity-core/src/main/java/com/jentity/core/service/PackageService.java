package com.jentity.core.service;

import java.util.List;
import java.util.function.Predicate;

public interface PackageService {
  List<Class<?>> getAllClasses(String packageName, Predicate<Class<?>> predicate);
}

package com.jentity.core.service.impl;

import com.jentity.core.service.PackageService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DefaultPackageService implements PackageService {
  private static final String CLASS_EXTENSION = ".class";

  @Override
  public List<Class<?>> getAllClasses(String packageName, Predicate<Class<?>> predicate) {
    Enumeration<URL> resources = getResources(packageName);
    List<File> directories = new ArrayList<>();
    while (resources.hasMoreElements()) {
      URL resource = resources.nextElement();
      directories.add(new File(resource.getFile()));
    }
    return directories.stream()
        .map(directory -> findClasses(directory, packageName))
        .flatMap(List::stream)
        .filter(predicate)
        .collect(Collectors.toList());
  }

  private Enumeration<URL> getResources(String packageName) {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    String path = packageName.replace(".", "/");
    try {
      return classLoader.getResources(path);
    } catch (IOException exception) {
      throw new RuntimeException("Unable to get resources for " + path, exception);
    }
  }

  private List<Class<?>> findClasses(File directory, String packageName) {
    if (!directory.exists()) {
      return new ArrayList<>();
    }
    File[] files = directory.listFiles();
    if (files == null) {
      return new ArrayList<>();
    }
    return Arrays.stream(files)
        .map(file -> processFile(file, packageName))
        .flatMap(List::stream)
        .collect(Collectors.toList());
  }

  private List<Class<?>> processFile(File file, String packageName) {
    if (file.isDirectory()) {
      return findClasses(file, packageName);
    } else if (file.getName().endsWith(CLASS_EXTENSION)) {
      String fullFileName = file.getName();
      String fileName = fullFileName.substring(0, fullFileName.length() - CLASS_EXTENSION.length());
      String className = packageName + "." + fileName;
      return List.of(
          registerClass(className)
      );
    }
    return Collections.emptyList();
  }

  private Class<?> registerClass(String className) {
    try {
      return Class.forName(className);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException("Unable to load class: " + className);
    }
  }
}

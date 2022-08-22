package com.jentity.orm.exception;

public class InitializationException extends RuntimeException {
  public InitializationException(String message) {
    super(message);
  }

  public InitializationException(String message, Throwable cause) {
    super(message, cause);
  }
}
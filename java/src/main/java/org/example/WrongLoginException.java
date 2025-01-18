package org.example;

public class WrongLoginException extends RuntimeException {
    public WrongLoginException(String message) {
        super(message);
    }

  public WrongLoginException() {

  }
}

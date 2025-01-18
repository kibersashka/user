package org.example;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException(String message) {
        super(message);
    }
    public WrongPasswordException() {

    }
}

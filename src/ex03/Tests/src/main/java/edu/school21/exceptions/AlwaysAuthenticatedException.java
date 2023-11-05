package edu.school21.exceptions;

public class AlwaysAuthenticatedException extends RuntimeException{
    public AlwaysAuthenticatedException(String message) {
        super(message);
    }
}

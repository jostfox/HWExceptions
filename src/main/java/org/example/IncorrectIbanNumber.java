package org.example;

public class IncorrectIbanNumber extends RuntimeException {

    public IncorrectIbanNumber(String message) {
        super(message);
    }
}



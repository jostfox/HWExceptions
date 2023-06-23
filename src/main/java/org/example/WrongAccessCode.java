package org.example;

public class WrongAccessCode extends RuntimeException{

    public WrongAccessCode(String message) {
        super(message);
    }
}

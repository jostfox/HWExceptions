package org.example;

public class TransferLimitException extends RuntimeException{

    public TransferLimitException(String message) {
        super(message);
    }
}

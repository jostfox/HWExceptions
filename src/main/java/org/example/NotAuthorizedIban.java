package org.example;

public class NotAuthorizedIban extends RuntimeException{

    public NotAuthorizedIban(String message) {
        super(message);

    }
}

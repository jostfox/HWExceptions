package org.example;

public class NotEnoughFunds extends RuntimeException{
    private final String account;

    public NotEnoughFunds(String message, String account) {
        super(message);
        this.account = account;
    }

    @SuppressWarnings("unused")
    public String getAccount() {
        return account;
    }


}

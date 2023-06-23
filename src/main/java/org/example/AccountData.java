package org.example;

import java.util.Map;

public class AccountData {

    private final String iban = new AccountStorage().getIbanOne();
    private final String CODE = "249835";
    private final Map<String, String> ibanCodeVerification = Map.of(iban, CODE);

    public boolean accountService(String code) {
        return ibanCodeVerification.get(iban).equals(code);
    }
}

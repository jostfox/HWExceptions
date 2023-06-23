package org.example;

import java.util.HashMap;
import java.util.Map;

public class AccountStorage {

    private final String IBAN_ONE = "DE98549124934583473494";
    private final Double moneySumOne = 2458.48;
    private final String IBAN_TWO = "DE98245981246943458164";
    private final Double moneySumTwo = 4880.24;
    protected   Map<String, Double> accountCondition = new HashMap<>();

    protected void mapInitializing(){
        accountCondition.put(IBAN_ONE, moneySumOne);
        accountCondition.put(IBAN_TWO, moneySumTwo);
    }

    public String getIbanOne() {
        return IBAN_ONE;
    }

    public String getIbanTwo() {
        return IBAN_TWO;
    }
}

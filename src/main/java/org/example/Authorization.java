package org.example;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Authorization {
    private final String IBAN_REGEX = "([a-z]{2}\\d{13,32})";
    private final String APPROVE_CODE = "(\\d{6})";

    public void ibanVerification(Scanner scanner) {
        String iban = new DecoratorScanner(scanner).getString("Please, enter your IBAN: ");
        Pattern ibanPattern = Pattern.compile(IBAN_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = ibanPattern.matcher(iban);
        if (!matcher.matches()) {
            throw new IncorrectIbanNumber("\nWrong IBAN Exception! (" + iban + ")");
        }
        if (!iban.equals(new AccountStorage().getIbanOne())){
            throw new NotAuthorizedIban("\nNot authorized IBAN Exception (" + iban + ")");
        }
        System.out.println("\nIBAN accepted");
    }

    public void approveCodeVerification(Scanner scanner) {
        String code = new DecoratorScanner(scanner).getString("Please, enter verification code: ");
        Pattern codePattern = Pattern.compile(APPROVE_CODE);
        Matcher matcher = codePattern.matcher(code);
        if (!matcher.matches()){
            throw new IncorrectCodeNTemplate("\nWrong code template Exception (" + code + ")");
        }
        if (!new AccountData().accountService(code)){
            throw new WrongAccessCode("\nWrong access code Exception (" + code + ")");
        }
        System.out.println("Verification code accepted\n");
    }
}


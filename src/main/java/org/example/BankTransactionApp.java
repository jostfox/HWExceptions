package org.example;

import java.util.Scanner;

public class BankTransactionApp {
    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        Authorization authorization = new Authorization();
        checkIban(authorization);
        scanner.close();

    }
    // Authorized IBAN: DE98549124934583473494
    protected static void checkIban(Authorization authorization){
        while (true) {
            try {
                authorization.ibanVerification(scanner);
                break;
            } catch (IncorrectIbanNumber | NotAuthorizedIban e) {
                System.out.println(e.getMessage() +  " / " + e.getCause());
                System.out.println("You have entered incorrect IBAN.");
                while (true) {
                    String choise = new DecoratorScanner(scanner).getString("1 - Enter your IBAN again;\n" +
                            "2 - Close application\n");
                    if (choise.equals("1")) {
                        break;
                    } else if (choise.equals("2")) {
                        System.exit(0);
                    } else {
                        System.out.println("Please, choose  1 or 2");
                    }
                }
            }
        }
        checkCode(authorization);
    }

    //Authorized code: 249835
    protected static void checkCode(Authorization authorization){
        int counter = 0;
        while (counter < 3){
            try {
                authorization.approveCodeVerification(scanner);
                initOperational();
                break;
            }catch (IncorrectCodeNTemplate | WrongAccessCode e){
                System.out.println(e.getMessage() +  " / " + e.getCause());
                System.out.println("Wrong code");
                counter++;
            }
        }
        if (counter == 3){
            checkIban(authorization);
        }
    }

    public static void initOperational(){
        Transaction transaction = new Transaction();
        transaction.mapInitializing();
        transaction.checkBalanceAllAccounts();
        transaction.operational(scanner);
    }
}





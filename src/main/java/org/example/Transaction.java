package org.example;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Scanner;

public class Transaction extends AccountStorage {

    private final Double LIMIT = 500.0;

    public void operational(Scanner scanner) {
        while (true) try {
            switch (initialMenu(scanner)) {
                case "1":
                    while (true) {
                        try {
                            moneyTransfering(getIbanOne(), getIbanTwo(), scanner);
                            break;
                        } catch (NotEnoughFunds e) {
                            notEnoughFundsExceptionHandler(e);
                        } catch (TransferLimitException e){
                            transferLimitExceptionHandler(e);
                        }
                    }
                    break;
                case "2":
                    while (true) try {
                        moneyTransfering(getIbanTwo(), getIbanOne(), scanner);
                        break;
                    } catch (NotEnoughFunds e) {
                        notEnoughFundsExceptionHandler(e);
                    }catch (TransferLimitException e){
                        transferLimitExceptionHandler(e);
                    }
                    break;
                case "3":
                    while (true) {
                        String accountChoise = new DecoratorScanner(scanner)
                                .getString("Choose the account you want to check: \n" +
                                        "----------------------------\n" +
                                        "1 - " + getIbanOne() +
                                        "\n2 - " + getIbanTwo() +
                                        "\n3 - Both accounts" +
                                        "\n4 - Back to main menu\n");

                        switch (accountChoise) {
                            case "1":
                                checkBalanceOneAccount(getIbanOne());
                                break;
                            case "2":
                                checkBalanceOneAccount(getIbanTwo());
                                break;
                            case "3":
                                checkBalanceAllAccounts();
                                break;
                            case "4":
                                operational(scanner);
                        }
                    }
                case "4":
                    System.out.println("Good bye!!!");
                    System.exit(0);
            }
        } catch (IncorrectMenuItem e) {
            System.out.println(e.getMessage());
            System.out.println("Please, choose between 1 - 4\n");
        }
    }

    private void moneyTransfering(String from, String to, Scanner scanner) {
        Double sum = new DecoratorScanner(scanner)
                .getDouble("Enter the amount you want to transfer: ");
        if (accountCondition.get(from) < sum) {
            throw new NotEnoughFunds("\nNotEnoughFunds Exception", from);
        }
        if (sum > LIMIT){
            throw new TransferLimitException("\nTransferLimit Exception");
        }
        accountCondition.put(from, (accountCondition.get(from)) - sum);
        accountCondition.put(to, (accountCondition.get(to)) + sum);
        System.out.println("\nMoney has been sent...");
        System.out.println("-----------------");
        checkBalanceAllAccounts();
    }

    public void checkBalanceAllAccounts() {
        System.out.println("Your present balance:");
        System.out.println("================");
        System.out.println("Account 1 - " + getIbanOne() + ": " + accountCondition.get(getIbanOne()) + " Eur\n" +
                "Account 2 - " + getIbanTwo() + ": " + accountCondition.get(getIbanTwo()) + " Eur");
        System.out.println("-------------------------------");
    }

    public void checkBalanceOneAccount(String account) {
        System.out.println("Your present balance:");
        System.out.println("================");
        System.out.println(account + ": " + accountCondition.get(account) + " Eur\n");
        System.out.println("-------------------------------");
    }

    private String initialMenu(Scanner scanner) {
        System.out.println("Available operations:");
        System.out.println("-----------------------");
        String choice = new DecoratorScanner(scanner)
                .getString("1 - Transfer money from Accout 1 to Account 2;\n" +
                        "2 - Transfer money from Accout 2 to Account 1;\n" +
                        "3 - Check your balance;\n" +
                        "4 - Exit\n");
        System.out.println("-----------------------\n");
        String[] menuNumbers = {"1", "2", "3", "4"};
        if (!Arrays.asList(menuNumbers).contains(choice)) {
            throw new IncorrectMenuItem("Incorrect menu item Exception!");
        }
        return choice;
    }

    private void notEnoughFundsExceptionHandler(Exception e) {
        try {
            System.out.println(e.getMessage() + " / " + e.getClass().getDeclaredMethod("getAccount").invoke(e));
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
        System.out.println("\nThere is not enough funds on your account...");
        System.out.println("Please, choose another amount\n");
    }

    private void transferLimitExceptionHandler(Exception e){
        System.out.println(e.getMessage());
        System.out.println("Sorry, you have exceeded daily allowable limit of " + LIMIT + " Eur");
    }
}

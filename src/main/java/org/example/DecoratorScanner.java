package org.example;

import java.util.Scanner;

public class DecoratorScanner implements ScannerRequest{

    private final Scanner scanner;

    public DecoratorScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String getString(String text) {
        System.out.print(text);
        return scanner.next();
    }

    @Override
    public Double getDouble(String text) {
        System.out.print(text);
        return scanner.nextDouble();
    }
}

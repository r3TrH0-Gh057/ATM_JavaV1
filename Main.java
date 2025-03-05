package com.cajero;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Colores
        final String RESET = "\u001B[0m";
        final String GREEN = "\u001B[32m";
        final String RED = "\u001B[31m";
        final String WHITE = "\u001B[97m";

        // Variables y constantes
        final int PIN = 1234;
        double money = 50000;
        int try_counter = 0;
        boolean blocked_accounts = false;
        List<String> transaction_history = new ArrayList<>();

        // Autenticación
        while (try_counter < 3) {
            try {
                System.out.println("Write your pin");
                int pinin = scanner.nextInt();

                if (pinin == PIN) {
                    break;
                } else {
                    try_counter++;
                    System.out.println("Incorrect PIN. You have " + (3 - try_counter) + " attempts left.");
                }

                if (try_counter == 3) {
                    blocked_accounts = true;
                    System.out.println("Your account has been blocked. Contact your bank to unlock.");
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Limpia el buffer de entrada
            }
        }

        // Si la cuenta está bloqueada, el programa se cierra
        if (blocked_accounts) {
            scanner.close();
            return;
        }

        // Menú del cajero
        int option;
        do {
            System.out.println(GREEN + "Welcome to the ATM." + RESET);
            System.out.println("1. Check your money");
            System.out.println("2. Add money to your account");
            System.out.println("3. Withdraw money");
            System.out.println("4. Deposit money");
            System.out.println("5. Watch the history of the account");
            System.out.println("6. Exit");
            System.out.println("7. ???");
            System.out.println("Waiting for your answer");
            
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println(GREEN + "Your money is: $" + money + RESET);
                    break;
                case 2:
                    System.out.println("Insert the money you want to add to your account");
                    double money_in = scanner.nextDouble();
                    if (money_in > 0) {
                        money += money_in;
                        System.out.println(GREEN + "You have added successfully. Your amount is: $" + money + RESET);
                        transaction_history.add(GREEN + "You added to your account + $" + money_in + RESET);
                    } else {
                        System.out.println("Try with another number");
                    }
                    break;
                case 3:
                    System.out.println("Write the amount you want to withdraw:");
                    double amount = scanner.nextDouble();
                    if (amount > 0 && amount <= money) {
                        money -= amount;
                        System.out.println(GREEN + "You have withdrawn successfully. Your amount is: $" + money + RESET);
                        transaction_history.add(GREEN + "Withdraw - $" + amount + RESET);
                    } else {
                        System.out.println(RED + "Invalid amount. Try again with another amount" + RESET);
                    }
                    break;
                case 4:
                    System.out.println("How much money do you want to deposit?");
                    double deposit = scanner.nextDouble();
                    if (deposit > 0 && deposit <= money) {
                        System.out.println("Write the account number you want to deposit to");
                        int account_Number = scanner.nextInt();
                        money -= deposit;
                        System.out.println(GREEN + "You have deposited successfully. Your money is: $" + money + RESET);
                        transaction_history.add(GREEN + "Deposit - $" + deposit + RESET);
                    } else {
                        System.out.println(RED + "Invalid amount or you don't have enough money. Please try again." + RESET);
                    }
                    break;
                case 5:
                    if (transaction_history.isEmpty()) {
                        System.out.println(RED + "No transactions yet" + RESET);
                    } else {
                        System.out.println("Transaction history:");
                        for (String transaction : transaction_history) {
                            System.out.println(transaction);
                        }
                    }
                    break;
                case 6:
                    System.out.println(GREEN + "Thank you for using our ATM! 8(^O^)8 " + RESET);
                    break;
                case 7:
                    System.out.println(RED + "Your account has been hacked by DedSec" + RESET);
                    String[] calavera = {
                    "       ______",
                    "    .-'      `-.",
                    "   /            \\",
                    "  |              |",
                    "  |,  .-.  .-.  ,|",
                    "  | )(_o/  \\o_)( |",
                    "  |/     /\\     \\|",
                    "  (_     ^^     _)",
                    "   \\__|IIIIII|__/",
                    "    | \\IIIIII/ |",
                    "    \\          /",
                    "     `--------`"
                    };
                    for (String line : calavera) {
                        System.out.println(WHITE + line + RESET);
                    }
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        } while (option != 6);

        scanner.close();
    }
}

package Atm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ATM {

    Map<String, User> users;
    Map<String, Account> accounts;
    private Scanner sc;

    public ATM() {
        this.users = new HashMap<>();
        this.accounts = new HashMap<>();
        this.sc = new Scanner(System.in);
    }

    public void addUser() {
        System.out.print("Enter user ID: ");
        String id = sc.nextLine();
        System.out.print("Enter user PIN: ");
        String pin = sc.nextLine();

        if (id != null && !id.isEmpty()) {
            users.put(id, new User(id, pin));
            accounts.put(id, new Account());
            System.out.println("User  added successfully.");
        } else {
            System.out.println("Invalid ID. Please enter a non-empty ID.");
        }
    }

    public void run() {
        while (true) {
            System.out.println("1. Add User");
            System.out.println("2. Login");
            System.out.println("3. Quit");
            System.out.print("Choose an option: ");
            int option = Integer.parseInt(sc.nextLine());

            switch (option) {
                case 1:
                    addUser();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    System.out.println("Application Closed");
                    return;
                default:
                    System.out.println("Unexpected choice. Try again: " + option);
            }
        }
    }

    private void login() {
        System.out.print("Enter user ID: ");
        String userId = sc.nextLine();
        System.out.print("Enter user PIN: ");
        String userPin = sc.nextLine();

        if (authenticateUser (userId, userPin)) {
            System.out.println("Login Successful.");
            performTransaction(userId);
        } else {
            System.out.println("Invalid ID or PIN. Please try again to login.");
        }
    }

    private boolean authenticateUser (String userId, String userPin) {
        User user = users.get(userId);
        return user != null && user.getPin().equals(userPin);
    }

    private void performTransaction(String userId) {
        Account acc = accounts.get(userId);
        while (true) {
            System.out.println("1. Transactions History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Check Balance");
            System.out.println("6. Quit");
            System.out.print("Choose an option: ");
            int option = Integer.parseInt(sc.nextLine());

            switch (option) {
                case 1:
                    displayTransactions(acc);
                    break;
                case 2:
                    withdraw(acc);
                    break;
                case 3:
                    deposit(acc);
                    break;
                case 4:
                    transfer(acc);
                    break;
                case 5:
                    checkBalance(acc);
                    break;
                case 6:
                    System.out.println("Application Closed");
                    return;
                default:
                    System.out.println("Unexpected choice. Try again: " + option);
            }
        }
    }

    private void displayTransactions(Account acc) {
        List<String> transactions = acc.getTransactions();
        for (String transaction : transactions) {
            System.out.println(transaction);
        }
    }

    private void withdraw(Account acc) {
        System.out.print("Enter amount to withdraw: ");
        double amount = Double.parseDouble(sc.nextLine());
        acc.withdraw(amount);
    }

    private void deposit(Account acc) {
        System.out.print("Enter amount to deposit: ");
        double amount = Double.parseDouble(sc.nextLine());
        acc.deposit(amount);
    }

    private void transfer(Account acc) {
        System.out.print("Enter recipient's user ID: ");
        String recipientId = sc.nextLine();
        Account recipientAcc = accounts.get(recipientId);
        if (recipientAcc != null) {
            System.out.print("Enter amount to transfer: ");
            double amount = Double.parseDouble(sc.nextLine());
            acc.transfer(amount, recipientAcc);
        } else {
            System.out.println("Recipient's account not found.");
        }
    }

    private void checkBalance(Account acc) {
        System.out.println("Your balance is: " + acc.getBalance());
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.run();
    }
}
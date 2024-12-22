
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ATMInterface {

    private static class Account {
        private String accountNumber;
        private String pin;
        private double balance;

        public Account(String accountNumber, String pin, double balance) {
            this.accountNumber = accountNumber;
            this.pin = pin;
            this.balance = balance;
        }
@SuppressWarnings("unused")

        public String  getAccountNumber() {
            return accountNumber;
        }

        public String getPin() {
            return pin;
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                System.out.println("Successfully deposited : INR " + amount);
            } else {
                System.out.println("Invalid deposit amount.");
            }
        }

        public void withdraw(double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                
                if(balance<500){
                    System.out.println("Successfully withdrew : INR " + amount);
                    System.out.println("Please maintain minimum balance");
                }
            } else {
                System.out.println("Invalid withdrawal amount or insufficient balance.");
            }
        }
    }

    private static Map<String, Account> accounts = new HashMap<>();

    public static void main(String[] args) {
        initializeAccounts();
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the ATM!");

        System.out.print("Enter your account number: ");
        String accountNumber = sc.nextLine();

        System.out.print("Enter your PIN: ");
        String pin = sc.nextLine();
        
        Account account = authenticate(accountNumber, pin);
        if (account == null) {
            System.out.println("Invalid account number or PIN.");
            
        }

        boolean exit = false;
        while (!exit) {
            System.out.println("\nATM Menu:");
            System.out.println("Please withdraw cash in multiple of 100 ");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Your balance is: INR " + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = sc.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawalAmount = sc.nextDouble();
                    account.withdraw(withdrawalAmount);
                    break;
                case 4:
                    exit = true;
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        sc.close();
    }

    private static void initializeAccounts() {
        accounts.put("123456", new Account("123456", "1234", 50000.00));
        accounts.put("654321", new Account("654321", "6541", 30000.00));
        accounts.put("987654", new Account("987654", "9874", 10000.00));
    }

    private static Account authenticate(String accountNumber, String pin) {
        Account account = accounts.get(accountNumber);
        if (account != null && account.getPin().equals(pin)) {
            return account;
        }
        return null;
    }
}
  
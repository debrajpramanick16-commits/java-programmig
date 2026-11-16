
import java.util.Scanner;

// 1. Bank Interface (Defines banking rules and operations)
interface Bank {
    String BANK_NAME = "State Bank of India";
    double INTEREST_RATE = 4.5; // Annual interest rate in %

    void deposit(double amount);
    void withdraw(double amount);
    void calculateInterest();
    void displayDetails();
}

// 2. Customer Superclass (Holds personal profile attributes)
class Customer {
    protected String customerName;
    protected String customerId;
    protected String phoneNumber;

    public Customer(String customerName, String customerId, String phoneNumber) {
        this.customerName = customerName;
        this.customerId = customerId;
        this.phoneNumber = phoneNumber;
    }

    public void displayCustomerInfo() {
        System.out.println("Customer Name   : " + customerName);
        System.out.println("Customer ID     : " + customerId);
        System.out.println("Phone Number    : " + phoneNumber);
    }
}

// 3. Account Subclass (Achieves Multiple Inheritance via extends + implements)
class Account extends Customer implements Bank {
    private String accountNumber;
    private double balance;

    public Account(String customerName, String customerId, String phoneNumber, String accountNumber, double initialBalance) {
        // Call Customer superclass constructor
        super(customerName, customerId, phoneNumber);
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("Deposited: %.2f | New Balance: %.2f%n", amount, balance);
        } else {
            System.out.println("Error: Deposit amount must be greater than zero.");
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Error: Withdrawal amount must be greater than zero.");
        } else if (amount > balance) {
            System.out.println("Error: Insufficient funds.");
        } else {
            balance -= amount;
            System.out.printf("Withdrawn: %.2f | Remaining Balance: %.2f%n", amount, balance);
        }
    }

    @Override
    public void calculateInterest() {
        double interest = (balance * INTEREST_RATE) / 100.0;
        System.out.printf("Annual Interest (@ %.2f%%): %.2f%n", INTEREST_RATE, interest);
    }

    @Override
    public void displayDetails() {
        System.out.println("\n========================================");
        System.out.println("Bank Name       : " + BANK_NAME);
        // Calling inherited method from Customer class
        displayCustomerInfo();
        System.out.println("Account Number  : " + accountNumber);
        System.out.printf("Current Balance : %.2f%n", balance);
        System.out.println("========================================");
    }
}

// 4. Driver Class
public class MultipleInheritanceBank {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Open New Account ===");
        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Customer ID: ");
        String custId = sc.nextLine();

        System.out.print("Enter Phone Number: ");
        String phone = sc.nextLine();

        System.out.print("Enter Account Number: ");
        String accNo = sc.nextLine();

        System.out.print("Enter Initial Balance: ");
        double initBalance = Double.parseDouble(sc.nextLine());

        Account acc = new Account(name, custId, phone, accNo, initBalance);

        int choice;
        do {
            System.out.println("\n=== BANKING MENU ===");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Calculate Annual Interest");
            System.out.println("4. Display Account & Customer Details");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                choice = 0;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    acc.deposit(Double.parseDouble(sc.nextLine()));
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    acc.withdraw(Double.parseDouble(sc.nextLine()));
                    break;
                case 3:
                    acc.calculateInterest();
                    break;
                case 4:
                    acc.displayDetails();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Enter 1-5.");
            }
        } while (choice != 5);

        sc.close();
    }
}
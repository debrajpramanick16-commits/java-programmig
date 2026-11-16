
import java.util.Scanner;

// 1. Abstract Base Class
abstract class Accounts {
    protected double balance;
    protected String accountNumber;
    protected String accountHoldersName;
    protected String address;

    // Constructor to initialize common account details
    public Accounts(String accountNumber, String accountHoldersName, String address, double balance) {
        this.accountNumber = accountNumber;
        this.accountHoldersName = accountHoldersName;
        this.address = address;
        this.balance = balance;
    }

    // Abstract methods to be implemented by subclasses
    public abstract void withdrawl(double amount);
    public abstract void deposit(double amount);

    // Concrete method to display the balance of the account number
    public void display() {
        System.out.println("\n--- Account Details ---");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Account Holder : " + accountHoldersName);
        System.out.println("Address        : " + address);
        System.out.printf("Current Balance: %.2f%n", balance);
        System.out.println("-----------------------");
    }
}

// 2. Subclass extending Accounts
class SavingsAccount extends Accounts {
    private double rateOfInterest; // Annual interest rate in percentage

    public SavingsAccount(String accountNumber, String accountHoldersName, String address, double balance, double rateOfInterest) {
        super(accountNumber, accountHoldersName, address, balance);
        this.rateOfInterest = rateOfInterest;
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("Deposited: %.2f | Updated Balance: %.2f%n", amount, balance);
        } else {
            System.out.println("Error: Deposit amount must be positive.");
        }
    }

    @Override
    public void withdrawl(double amount) {
        if (amount <= 0) {
            System.out.println("Error: Withdrawal amount must be positive.");
        } else if (amount > balance) {
            System.out.println("Error: Insufficient balance for withdrawal.");
        } else {
            balance -= amount;
            System.out.printf("Withdrawn: %.2f | Remaining Balance: %.2f%n", amount, balance);
        }
    }

    // Calculates simple interest for a given time period (in years) and updates/returns total amount
    public double calculateAmount(int timeInYears) {
        double interest = (balance * rateOfInterest * timeInYears) / 100.0;
        double totalAmount = balance + interest;
        System.out.printf("Interest earned over %d year(s) at %.2f%%: %.2f%n", timeInYears, rateOfInterest, interest);
        System.out.printf("Total Maturity Amount: %.2f%n", totalAmount);
        return totalAmount;
    }
}

// 3. Driver Class
public class BankDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Create Savings Account ===");
        System.out.print("Enter Account Number: ");
        String accNo = sc.nextLine();

        System.out.print("Enter Account Holder's Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Address: ");
        String address = sc.nextLine();

        System.out.print("Enter Initial Balance: ");
        double initialBalance = Double.parseDouble(sc.nextLine());

        System.out.print("Enter Rate of Interest (%): ");
        double roi = Double.parseDouble(sc.nextLine());

        SavingsAccount sa = new SavingsAccount(accNo, name, address, initialBalance, roi);

        int choice;
        do {
            System.out.println("\n=== SAVINGS ACCOUNT MENU ===");
            System.out.println("1. Deposit");
            System.out.println("2. Withdrawal");
            System.out.println("3. Calculate Amount (with Interest)");
            System.out.println("4. Display Balance & Details");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                choice = 0;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    double depAmt = Double.parseDouble(sc.nextLine());
                    sa.deposit(depAmt);
                    break;

                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withAmt = Double.parseDouble(sc.nextLine());
                    sa.withdrawl(withAmt);
                    break;

                case 3:
                    System.out.print("Enter time period in years: ");
                    int years = Integer.parseInt(sc.nextLine());
                    sa.calculateAmount(years);
                    break;

                case 4:
                    sa.display();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice! Please select between 1 and 5.");
            }
        } while (choice != 5);

        sc.close();
    }
}
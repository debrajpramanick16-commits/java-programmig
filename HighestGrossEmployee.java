
import java.util.Scanner;

class Employee {
    private String emp_no;
    private String name;
    private String department;
    private double basic_pay;
    private double DA;
    private double HRA;
    private double gross_salary;

    // Standard allowance rates (can be adjusted as needed)
    private static final double DA_RATE = 0.40;  // 40% of basic_pay
    private static final double HRA_RATE = 0.20; // 20% of basic_pay

    // Method to accept input for an employee
    public void getData(Scanner sc) {
        System.out.print("Enter Employee Number : ");
        emp_no = sc.nextLine();

        System.out.print("Enter Employee Name   : ");
        name = sc.nextLine();

        System.out.print("Enter Department      : ");
        department = sc.nextLine();

        System.out.print("Enter Basic Pay       : ");
        basic_pay = Double.parseDouble(sc.nextLine());
    }

    // Method to calculate DA, HRA, and gross_salary
    public void calculateSalary() {
        DA = basic_pay * DA_RATE;
        HRA = basic_pay * HRA_RATE;
        gross_salary = basic_pay + DA + HRA;
    }

    public double getGrossSalary() {
        return gross_salary;
    }

    // Method to display complete employee details
    public void display() {
        System.out.println("\n==========================================");
        System.out.println("  EMPLOYEE WITH HIGHEST GROSS SALARY      ");
        System.out.println("==========================================");
        System.out.println("Employee Number : " + emp_no);
        System.out.println("Name            : " + name);
        System.out.println("Department      : " + department);
        System.out.printf("Basic Pay       : %.2f%n", basic_pay);
        System.out.printf("DA (40%%)        : %.2f%n", DA);
        System.out.printf("HRA (20%%)       : %.2f%n", HRA);
        System.out.println("------------------------------------------");
        System.out.printf("Gross Salary    : %.2f%n", gross_salary);
        System.out.println("==========================================");
    }
}

public class HighestGrossEmployee {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of employees: ");
        int n = Integer.parseInt(sc.nextLine());

        if (n <= 0) {
            System.out.println("Number of employees must be at least 1.");
            sc.close();
            return;
        }

        Employee[] employees = new Employee[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Enter Details for Employee " + (i + 1) + " ---");
            employees[i] = new Employee();
            employees[i].getData(sc);
            employees[i].calculateSalary();
        }

        // Find the employee with the highest gross salary
        Employee highestPaid = employees[0];
        for (int i = 1; i < n; i++) {
            if (employees[i].getGrossSalary() > highestPaid.getGrossSalary()) {
                highestPaid = employees[i];
            }
        }

        highestPaid.display();
        sc.close();
    }
}
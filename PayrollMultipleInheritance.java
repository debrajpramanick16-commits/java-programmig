
import java.util.Scanner;

// 1. Gross Interface (Defines allowance rates and gross salary calculation contract)
interface Gross {
    double DA_PERCENT = 40.0;   // Dearness Allowance: 40% of Basic
    double HRA_PERCENT = 20.0;  // House Rent Allowance: 20% of Basic

    double calculateGross();
}

// 2. Employee Superclass (Holds core employee identity attributes)
class Employee {
    protected String empId;
    protected String empName;
    protected String designation;

    public Employee(String empId, String empName, String designation) {
        this.empId = empId;
        this.empName = empName;
        this.designation = designation;
    }

    public void displayEmployee() {
        System.out.println("Employee ID     : " + empId);
        System.out.println("Employee Name   : " + empName);
        System.out.println("Designation     : " + designation);
    }
}

// 3. Salary Subclass (Achieves Multiple Inheritance via extends + implements)
class Salary extends Employee implements Gross {
    private double basicSalary;
    private double da;
    private double hra;
    private double grossSalary;

    public Salary(String empId, String empName, String designation, double basicSalary) {
        // Call Employee superclass constructor
        super(empId, empName, designation);
        this.basicSalary = basicSalary;
    }

    @Override
    public double calculateGross() {
        da = (basicSalary * DA_PERCENT) / 100.0;
        hra = (basicSalary * HRA_PERCENT) / 100.0;
        grossSalary = basicSalary + da + hra;
        return grossSalary;
    }

    public void displaySalarySlip() {
        calculateGross();
        System.out.println("\n==========================================");
        System.out.println("             EMPLOYEE PAYSLIP             ");
        System.out.println("==========================================");
        // Calling inherited method from Employee superclass
        displayEmployee();
        System.out.println("------------------------------------------");
        System.out.printf("Basic Salary    : %.2f%n", basicSalary);
        System.out.printf("DA (%.0f%%)        : %.2f%n", DA_PERCENT, da);
        System.out.printf("HRA (%.0f%%)       : %.2f%n", HRA_PERCENT, hra);
        System.out.println("------------------------------------------");
        System.out.printf("Gross Salary    : %.2f%n", grossSalary);
        System.out.println("==========================================");
    }
}

// 4. Driver Class
public class PayrollMultipleInheritance {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Enter Employee Payroll Details ===");
        System.out.print("Enter Employee ID: ");
        String id = sc.nextLine();

        System.out.print("Enter Employee Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Designation: ");
        String desig = sc.nextLine();

        System.out.print("Enter Basic Salary: ");
        double basic = Double.parseDouble(sc.nextLine());

        Salary empSalary = new Salary(id, name, desig, basic);
        empSalary.displaySalarySlip();

        sc.close();
    }
}
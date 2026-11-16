
class Employee {
    // Private instance data members
    private String name;
    private String department;
    private double salary;

    // Static data member shared by all instances of the class
    private static String companyName = "Apex Innovations";

    // Parameterized constructor to initialize instance members
    public Employee(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    // Static method to display or update the shared company name
    public static void setCompanyName(String newCompanyName) {
        companyName = newCompanyName;
    }

    public static String getCompanyName() {
        return companyName;
    }

    // Method to display individual employee information along with company name
    public void display() {
        System.out.printf("%-18s | %-18s | %10.2f | %s%n", name, department, salary, companyName);
    }
}

public class CompanyEmployeeDemo {
    public static void main(String[] args) {
        // Creating and initializing 5 Employee objects via constructor
        Employee[] employees = new Employee[5];
        employees[0] = new Employee("Aarav Sharma", "Engineering", 75000.00);
        employees[1] = new Employee("Priya Chatterjee", "Data Science", 82000.00);
        employees[2] = new Employee("Rohan Sen", "Cloud Operations", 68000.00);
        employees[3] = new Employee("Ananya Das", "Product Design", 71500.00);
        employees[4] = new Employee("Vikram Bose", "Cybersecurity", 79000.00);

        // Displaying the shared company name via static method
        System.out.println("==========================================================================");
        System.out.println("Company Name: " + Employee.getCompanyName());
        System.out.println("==========================================================================");
        System.out.printf("%-18s | %-18s | %10s | %s%n", "Employee Name", "Department", "Salary", "Company");
        System.out.println("--------------------------------------------------------------------------");

        for (Employee emp : employees) {
            emp.display();
        }
        System.out.println("==========================================================================");
    }
}
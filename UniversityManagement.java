
import java.util.ArrayList;
import java.util.Scanner;

// 1. Department Interface
interface Department {
    // Attributes in an interface are implicitly public, static, and final
    String deptName = "Computer Science & Engineering";
    String deptHead = "Dr. A. Sen";

    // Abstract methods for printing the attributes
    void printDeptName();
    void printDeptHead();
}

// 2. Hostel Base Class
class Hostel {
    protected String hostelName;
    protected String hostelLocation;
    protected int numberOfRooms;

    public void getHostelData(Scanner sc) {
        System.out.print("Enter Hostel Name: ");
        hostelName = sc.nextLine();
        System.out.print("Enter Hostel Location: ");
        hostelLocation = sc.nextLine();
        System.out.print("Enter Number of Rooms: ");
        numberOfRooms = Integer.parseInt(sc.nextLine());
    }

    public void printHostelData() {
        System.out.println("Hostel Name     : " + hostelName);
        System.out.println("Hostel Location : " + hostelLocation);
        System.out.println("Number of Rooms : " + numberOfRooms);
    }
}

// 3. Student Class extending Hostel and implementing Department
class Student extends Hostel implements Department {
    private String studentName;
    private String regdNo;
    private String electiveSubject;
    private double avgMarks;
    
    // Instance variables to hold student-specific department details
    private String assignedDeptName;
    private String assignedDeptHead;

    public String getRegdNo() {
        return regdNo;
    }

    public void getData(Scanner sc) {
        System.out.print("Enter Student Name: ");
        studentName = sc.nextLine();
        System.out.print("Enter Registration Number: ");
        regdNo = sc.nextLine();
        System.out.print("Enter Elective Subject: ");
        electiveSubject = sc.nextLine();
        System.out.print("Enter Average Marks: ");
        avgMarks = Double.parseDouble(sc.nextLine());

        System.out.print("Enter Department Name (or press Enter for default): ");
        String dName = sc.nextLine();
        assignedDeptName = dName.isEmpty() ? deptName : dName;

        System.out.print("Enter Department Head (or press Enter for default): ");
        String dHead = sc.nextLine();
        assignedDeptHead = dHead.isEmpty() ? deptHead : dHead;

        // Fetch Hostel details from superclass
        getHostelData(sc);
    }

    @Override
    public void printDeptName() {
        System.out.println("Department Name : " + assignedDeptName);
    }

    @Override
    public void printDeptHead() {
        System.out.println("Department Head : " + assignedDeptHead);
    }

    public void printData() {
        System.out.println("\n----------------------------------------");
        System.out.println("Student Name    : " + studentName);
        System.out.println("Registration No : " + regdNo);
        System.out.println("Elective Subject: " + electiveSubject);
        System.out.println("Average Marks   : " + avgMarks);
        printDeptName();
        printDeptHead();
        printHostelData();
        System.out.println("----------------------------------------");
    }
}

// 4. Driver Class
public class UniversityManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        int choice;

        do {
            System.out.println("\n=== UNIVERSITY STUDENT MENU ===");
            System.out.println("1. Admit new student");
            System.out.println("2. Migrate a student");
            System.out.println("3. Display details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                choice = 0;
            }

            switch (choice) {
                case 1:
                    Student s = new Student();
                    s.getData(sc);
                    students.add(s);
                    System.out.println("Student admitted successfully!");
                    break;

                case 2:
                    if (students.isEmpty()) {
                        System.out.println("No student records available.");
                        break;
                    }
                    System.out.print("Enter Registration Number of student to migrate: ");
                    String searchRegd = sc.nextLine();
                    boolean foundForMigration = false;

                    for (Student st : students) {
                        if (st.getRegdNo().equalsIgnoreCase(searchRegd)) {
                            System.out.println("Student found! Enter new hostel details for migration:");
                            st.getHostelData(sc);
                            System.out.println("Student migrated successfully!");
                            foundForMigration = true;
                            break;
                        }
                    }

                    if (!foundForMigration) {
                        System.out.println("Error: Student with Registration Number '" + searchRegd + "' not found.");
                    }
                    break;

                case 3:
                    if (students.isEmpty()) {
                        System.out.println("No student records to display.");
                        break;
                    }
                    System.out.print("Enter Registration Number (or type 'ALL' to display everyone): ");
                    String displayRegd = sc.nextLine();

                    if (displayRegd.equalsIgnoreCase("ALL")) {
                        for (Student st : students) {
                            st.printData();
                        }
                    } else {
                        boolean foundForDisplay = false;
                        for (Student st : students) {
                            if (st.getRegdNo().equalsIgnoreCase(displayRegd)) {
                                st.printData();
                                foundForDisplay = true;
                                break;
                            }
                        }
                        if (!foundForDisplay) {
                            System.out.println("Error: Student with Registration Number '" + displayRegd + "' not found.");
                        }
                    }
                    break;

                case 4:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice! Please select between 1 and 4.");
            }
        } while (choice != 4);

        sc.close();
    }
}
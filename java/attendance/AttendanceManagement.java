import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// **1. Abstraction**: Define an abstract class 'Person'
// We can equivalently define an Interface
abstract class Person {
    protected String name;
    protected String id;
    private final String password;  // **Encapsulation**: Password is private

    public Person(String name, String id, String password) {
        this.name = name;
        this.id = id;
        this.password = password;  // Password set only once
    }

    // **Encapsulation**: Check password securely
    protected boolean authenticate(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    // Abstract method (to be implemented in subclasses)
    public abstract boolean markAttendance(String password);
}

// **2. Inheritance**: Student class extends Person
class Student extends Person {
    private String course;
    private boolean isPresent;

    // Constructor
    public Student(String name, String id, String password, String course) {
        super(name, id, password);
        this.course = course;
        this.isPresent = false;
    }

    // **Encapsulation**: Only controlled access to attendance marking
    public boolean isPresent() {
        return isPresent;
    }

    // **Polymorphism**: Overriding markAttendance method with password check
    @Override
    public boolean markAttendance(String password) {
        if (authenticate(password)) {
            isPresent = true;
            System.out.println("✅ Attendance marked successfully for Student: " + name);
            return true;
        } else {
            System.out.println("❌ Incorrect password. Attendance not marked for " + name);
            return false;
        }
    }

   
    public void displayInfo() {
        System.out.println("Student: " + name + " | ID: " + id + " | Course: " + course + " | Present: " + isPresent);
    }
}

// **2. Inheritance**: Teacher class extends Person
class Teacher extends Person {
    private String subject;
    private boolean isPresent;

    // Constructor
    public Teacher(String name, String id, String password, String subject) {
        super(name, id, password);
        this.subject = subject;
        this.isPresent = false;
    }

    // **Encapsulation**: Attendance tracking only via methods
    public boolean isPresent() {
        return isPresent;
    }

    // **Polymorphism**: Overriding markAttendance method with password check
    @Override
    public boolean markAttendance(String password) {
        if (authenticate(password)) {
            isPresent = true;
            System.out.println("✅ Attendance marked successfully for Teacher: " + name);
            return true;
        } else {
            System.out.println("❌ Incorrect password. Attendance not marked for " + name);
            return false;
        }
    }

    // Display teacher details
    public void displayInfo() {
        System.out.println("Teacher: " + name + " | ID: " + id + " | Subject: " + subject + " | Present: " + isPresent);
    }
}

// **3. Attendance System to Manage Attendance for Everyone**
class AttendanceSystem {
    private List<Person> people;
    private Scanner scanner;

    public AttendanceSystem() {
        this.people = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    // Add a person (student or teacher)
    public void addPerson(Person person) {
        people.add(person);
    }

    // Allow a user to mark their attendance securely
    public void markAttendance() {
        System.out.print("\nEnter your ID: ");
        String inputId = scanner.next();
        System.out.print("Enter your password: ");
        String inputPassword = scanner.next();

        boolean found = false;
        for (Person person : people) {
            if (person.id.equals(inputId)) {
                found = true;
                person.markAttendance(inputPassword);
                break;
            }
        }

        if (!found) {
            System.out.println("❌ No record found with this ID.");
        }
    }

    // Display attendance records
    public void displayAttendance() {
        System.out.println("\n--- Attendance Record ---");
        for (Person person : people) {
            if (person instanceof Student) {
                ((Student) person).displayInfo();
            } else if (person instanceof Teacher) {
                ((Teacher) person).displayInfo();
            }
        }
    }
}

// **4. Main Class to Run the Program**
public class AttendanceManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AttendanceSystem attendanceSystem = new AttendanceSystem();

        // Creating Students and Teachers
        Student student1 = new Student("Alice", "S101", "pass123", "Computer Science");
        Student student2 = new Student("Bob", "S102", "math456", "Mathematics");
        Teacher teacher1 = new Teacher("Dr. Smith", "T201", "phy789", "Physics");
        Teacher teacher2 = new Teacher("Ms. Johnson", "T202", "eng321", "English");

        // Registering them in the system
        attendanceSystem.addPerson(student1);
        attendanceSystem.addPerson(student2);
        attendanceSystem.addPerson(teacher1);
        attendanceSystem.addPerson(teacher2);

        // Allow users to mark attendance
        System.out.println("\nMark Attendance:");
        for (int i = 0; i < 4; i++) {  // Allow 4 attempts
            attendanceSystem.markAttendance();
        }

        // Display attendance records
        attendanceSystem.displayAttendance();

        scanner.close();
    }
}

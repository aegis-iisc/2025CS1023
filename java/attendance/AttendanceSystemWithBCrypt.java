import java.sql.*;
import java.util.Scanner;
import org.mindrot.jbcrypt.BCrypt; // Import BCrypt for password hashing

public class AttendanceSystemWithBCrypt {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/studentdb";
    private static final String DB_USER = "root"; // Change to your MySQL username
    private static final String DB_PASSWORD = "xxxx"; // Change to your MySQL password

    private static Connection connection;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("✅ Database Connected Successfully!\n");

            while (true) {
                System.out.println("\n---- Attendance System ----");
                System.out.println("1. Register Student");
                System.out.println("2. Mark Attendance");
                System.out.println("3. View Attendance");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");
                
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        registerStudent();
                        break;
                    case 2:
                        markAttendance();
                        break;
                    case 3:
                        viewAttendance();
                        break;
                    case 4:
                        System.out.println("🔴 Exiting... Thank you!");
                        connection.close();
                        return;
                    default:
                        System.out.println("❌ Invalid choice. Try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // **1. Register Student with Encrypted Password**
    private static void registerStudent() throws SQLException {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        System.out.print("Set Password: ");
        String password = scanner.nextLine();
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt()); // Hash password

        String sql = "INSERT INTO students (id, name, password) VALUES (?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, id);
        pstmt.setString(2, name);
        pstmt.setString(3, hashedPassword);

        int rows = pstmt.executeUpdate();
        if (rows > 0) {
            System.out.println("✅ Student Registered Successfully!");
        } else {
            System.out.println("❌ Registration Failed.");
        }
    }

    // **2. Mark Attendance with Password Authentication**
    private static void markAttendance() throws SQLException {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        // Get stored hashed password from DB
        String authQuery = "SELECT password FROM students WHERE id = ?";
        PreparedStatement authStmt = connection.prepareStatement(authQuery);
        authStmt.setString(1, id);

        ResultSet resultSet = authStmt.executeQuery();
        if (resultSet.next()) {
            String storedHash = resultSet.getString("password");

            if (BCrypt.checkpw(password, storedHash)) { // Verify password
                System.out.println("✅ Authentication Successful!");
                String date = java.time.LocalDate.now().toString();

                // Check if attendance is already marked
                String checkQuery = "SELECT * FROM attendance WHERE student_id = ? AND date = ?";
                PreparedStatement checkStmt = connection.prepareStatement(checkQuery);
                checkStmt.setString(1, id);
                checkStmt.setString(2, date);
                ResultSet checkResult = checkStmt.executeQuery();

                if (checkResult.next()) {
                    System.out.println("⚠️ Attendance already marked for today.");
                    return;
                }

                // Mark attendance
                String sql = "INSERT INTO attendance (student_id, date, status) VALUES (?, ?, ?)";
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, id);
                pstmt.setString(2, date);
                pstmt.setBoolean(3, true);

                int rows = pstmt.executeUpdate();
                if (rows > 0) {
                    System.out.println("✅ Attendance Marked Successfully!");
                } else {
                    System.out.println("❌ Failed to Mark Attendance.");
                }
            } else {
                System.out.println("❌ Incorrect Password! Authentication Failed.");
            }
        } else {
            System.out.println("❌ Student Not Found.");
        }
    }

    // **3. View Attendance Record**
    private static void viewAttendance() throws SQLException {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();

        String sql = "SELECT a.date, a.status, s.name FROM attendance a JOIN students s ON a.student_id = s.id WHERE s.id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, id);

        ResultSet resultSet = pstmt.executeQuery();
        boolean hasRecords = false;

        System.out.println("\n📅 Attendance Records for Student ID: " + id);
        while (resultSet.next()) {
            hasRecords = true;
            String date = resultSet.getString("date");
            boolean status = resultSet.getBoolean("status");
            System.out.println("📌 Date: " + date + " | Status: " + (status ? "Present" : "Absent"));
        }

        if (!hasRecords) {
            System.out.println("❌ No Attendance Records Found.");
        }
    }
}

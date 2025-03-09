import java.sql.*;
import java.util.Scanner;

public class AttendanceDatabaseSystem {
    // Database Connection Details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/studentdb";
    private static final String DB_USER = "root"; //  MySQL username goes here
    private static final String DB_PASSWORD = "xxxx"; //  MySQL password

    private static Connection connection;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            // Establish Database Connection
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

    // **1. Register a New Student**
    private static void registerStudent() throws SQLException {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        System.out.print("Set Password: ");
        String password = scanner.nextLine();

        String sql = "INSERT INTO students (id, name, password) VALUES (?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, id);
        pstmt.setString(2, name);
        pstmt.setString(3, password);  // (In production, use hashed passwords!)
        
        int rows = pstmt.executeUpdate();
        if (rows > 0) {
            System.out.println("✅ Student Registered Successfully!");
        } else {
            System.out.println("❌ Registration Failed.");
        }
    }

    // **2. Mark Attendance (Authentication Required)**
    private static void markAttendance() throws SQLException {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        // Validate student credentials
        String authQuery = "SELECT * FROM students WHERE id = ? AND password = ?";
        PreparedStatement authStmt = connection.prepareStatement(authQuery);
        authStmt.setString(1, id);
        authStmt.setString(2, password);

        ResultSet resultSet = authStmt.executeQuery();
        if (resultSet.next()) {
            System.out.println("✅ Authentication Successful!");

            String date = java.time.LocalDate.now().toString(); // Get today's date

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

            // Insert attendance record
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
            System.out.println("❌ Authentication Failed! Invalid ID or Password.");
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


// Setup Required:

    // Install MySQL.
    // Create the database (studentdb).
    // Update the database credentials (DB_URL, DB_USER, DB_PASSWORD).
package task6;
import java.sql.*;

public class JdbcCrudExample {
	 // Database URL, Username, Password
    private static final String URL = "jdbc:mysql://localhost:3306/user_db";
    private static final String USER = "root";  // change as per your setup
    private static final String PASSWORD = "Amul2124@";
	
	

	public static void main(String[] args) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Step 1: Connect to DB
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connected to Database!");

            // Step 2: Insert Data
            String insertSQL = "INSERT INTO users (name, email) VALUES (?, ?)";
            pstmt = conn.prepareStatement(insertSQL);
            pstmt.setString(1, "Rahul Vanjare");
            pstmt.setString(2, "rahul@example.com");
            pstmt.executeUpdate();
            System.out.println("✅ User Inserted!");

            // Step 3: Fetch Data
            String selectSQL = "SELECT * FROM users";
            pstmt = conn.prepareStatement(selectSQL);
            rs = pstmt.executeQuery();

            System.out.println("📋 Users in DB:");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " + rs.getString("name") + " | " + rs.getString("email"));
            }

            // Step 4: Update Data
            String updateSQL = "UPDATE users SET email = ? WHERE name = ?";
            pstmt = conn.prepareStatement(updateSQL);
            pstmt.setString(1, "rahulvanjare@updated.com");
            pstmt.setString(2, "Rahul Vanjare");
            pstmt.executeUpdate();
            System.out.println("✅ User Updated!");

            // Step 5: Delete Data
            String deleteSQL = "DELETE FROM users WHERE name = ?";
            pstmt = conn.prepareStatement(deleteSQL);
            pstmt.setString(1, "Rahul Vanjare");
            pstmt.executeUpdate();
            System.out.println("✅ User Deleted!");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Step 6: Close Resources
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
                System.out.println("🔒 Resources Closed.");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

	}

}

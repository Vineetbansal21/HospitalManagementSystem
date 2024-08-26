package hospitalmanagement;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import hospitalmanagement.DatabaseConnection;

public class StaffDAO {
	 public void registerStaff(String name, String position, String contact, String schedule) throws SQLException {
	        String query = "INSERT INTO Staff (name, position, contact, schedule) VALUES (?, ?, ?, ?)";
	        try (Connection conn = DatabaseConnection.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(query)) {
	            pstmt.setString(1, name);
	            pstmt.setString(2, position);
	            pstmt.setString(3, contact);
	            pstmt.setString(4, schedule);
	            pstmt.executeUpdate();
	        }
	    }

	    public void getStaffDetails(int staffId) throws SQLException {
	        String query = "SELECT * FROM Staff WHERE id=?";
	        try (Connection conn = DatabaseConnection.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(query)) {
	            pstmt.setInt(1, staffId);
	            ResultSet rs = pstmt.executeQuery();
	            if (rs.next()) {
	                System.out.println("Name: " + rs.getString("name"));
	                System.out.println("Position: " + rs.getString("position"));
	                System.out.println("Contact: " + rs.getString("contact"));
	                System.out.println("Schedule: " + rs.getString("schedule"));
	            } else {
	                System.out.println("Staff member not found.");
	            }
	        }
	    }

}

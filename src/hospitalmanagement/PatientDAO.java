package hospitalmanagement;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import hospitalmanagement.DatabaseConnection;

public class PatientDAO {
    public void registerPatient(String name, String dob, String contact, String medicalHistory) throws SQLException {
        String query = "INSERT INTO Patients (name, dob, contact, medical_history) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setString(2, dob);
            pstmt.setString(3, contact);
            pstmt.setString(4, medicalHistory);
            pstmt.executeUpdate();
            
     //       int rowsAffected = pstmt.executeUpdate();
       //     if (rowsAffected > 0) {
         //       System.out.println("Patient registered successfully.");
           // } else {
             //   System.out.println("Failed to register patient.");
            //}

        } catch (SQLException e) {
            System.err.println("Error registering patient: " + e.getMessage());
        
        }
    }

    public void getPatientDetails(int patientId) throws SQLException {
        String query = "SELECT * FROM Patients WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, patientId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("DOB: " + rs.getString("dob"));
                System.out.println("Contact: " + rs.getString("contact"));
                System.out.println("Medical History: " + rs.getString("medical_history"));
            } else {
                System.out.println("Patient not found.");
            }
        }
    }
}

package hospitalmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import hospitalmanagement.DatabaseConnection;

public class MedicalRecordsDAO {
    public void createMedicalRecord(int patientId, String diagnosis, String treatment) throws SQLException {
        String query = "INSERT INTO MedicalRecords (patient_id, diagnosis, treatment) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, patientId);
            pstmt.setString(2, diagnosis);
            pstmt.setString(3, treatment);
            pstmt.executeUpdate();
        }
    }

    public void getMedicalRecord(int recordId) throws SQLException {
        String query = "SELECT * FROM MedicalRecords WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, recordId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Patient ID: " + rs.getInt("patient_id"));
                System.out.println("Diagnosis: " + rs.getString("diagnosis"));
                System.out.println("Treatment: " + rs.getString("treatment"));
            } else {
                System.out.println("Medical record not found.");
            }
        }
    }
}


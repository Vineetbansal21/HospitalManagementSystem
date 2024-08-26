package hospitalmanagement;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import hospitalmanagement.DatabaseConnection;

public class AppointmentDAO {
    public void scheduleAppointment(int patientId, int staffId, String appointmentDate) throws SQLException {
        String query = "INSERT INTO Appointments (patient_id, staff_id, appointment_date) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, patientId);
            pstmt.setInt(2, staffId);
            pstmt.setString(3, appointmentDate);
            pstmt.executeUpdate();
        }
    }

    public void getAppointmentDetails(int appointmentId) throws SQLException {
        String query = "SELECT * FROM Appointments WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, appointmentId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Patient ID: " + rs.getInt("patient_id"));
                System.out.println("Staff ID: " + rs.getInt("staff_id"));
                System.out.println("Appointment Date: " + rs.getString("appointment_date"));
            } else {
                System.out.println("Appointment not found.");
            }
        }
    }
}


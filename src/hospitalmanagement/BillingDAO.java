package hospitalmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import hospitalmanagement.DatabaseConnection;

public class BillingDAO {
    public void generateBill(int patientId, double amount, String paymentStatus) throws SQLException {
        String query = "INSERT INTO Billing (patient_id, amount, payment_status) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, patientId);
            pstmt.setDouble(2, amount);
            pstmt.setString(3, paymentStatus);
            pstmt.executeUpdate();
        }
    }

    public void getBill(int billId) throws SQLException {
        String query = "SELECT * FROM Billing WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, billId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Patient ID: " + rs.getInt("patient_id"));
                System.out.println("Amount: " + rs.getDouble("amount"));
                System.out.println("Payment Status: " + rs.getString("payment_status"));
            } else {
                System.out.println("Bill not found.");
            }
        }
    }
}
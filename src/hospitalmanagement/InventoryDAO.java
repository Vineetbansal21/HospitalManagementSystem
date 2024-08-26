package hospitalmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import hospitalmanagement.DatabaseConnection;

public class InventoryDAO {
    public void addItem(String itemName, int quantity) throws SQLException {
        String query = "INSERT INTO Inventory (item_name, quantity) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, itemName);
            pstmt.setInt(2, quantity);
            pstmt.executeUpdate();
        }
    }

    public void checkInventory() throws SQLException {
        String query = "SELECT * FROM Inventory";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Item: " + rs.getString("item_name"));
                System.out.println("Quantity: " + rs.getInt("quantity"));
            }
        }
    }
}


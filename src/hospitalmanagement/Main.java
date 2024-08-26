package hospitalmanagement;

import hospitalmanagement.PatientDAO;
import hospitalmanagement.StaffDAO;
import hospitalmanagement.InventoryDAO;
import hospitalmanagement.BillingDAO;
import hospitalmanagement.AppointmentDAO;
import hospitalmanagement.MedicalRecordsDAO;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PatientDAO patientDAO = new PatientDAO();
        StaffDAO staffDAO = new StaffDAO();
        InventoryDAO inventoryDAO = new InventoryDAO();
        BillingDAO billingDAO = new BillingDAO();
        AppointmentDAO appointmentDAO = new AppointmentDAO();
        MedicalRecordsDAO medicalRecordsDAO = new MedicalRecordsDAO();

        while (true) {
            System.out.println("\nHospital Management System");
            System.out.println("1. Register Patient");
            System.out.println("2. Get Patient Details");
            System.out.println("3. Register Staff");
            System.out.println("4. Get Staff Details");
            System.out.println("5. Add Inventory Item");
            System.out.println("6. Check Inventory");
            System.out.println("7. Generate Bill");
            System.out.println("8. Get Bill Details");
            System.out.println("9. Schedule Appointment");
            System.out.println("10. Get Appointment Details");
            System.out.println("11. Create Medical Record");
            System.out.println("12. Get Medical Record Details");
            System.out.println("13. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter patient name: ");
                        String patientName = scanner.nextLine();
                        System.out.print("Enter patient DOB (YYYY-MM-DD): ");
                        String patientDob = scanner.nextLine();
                        System.out.print("Enter patient contact: ");
                        String patientContact = scanner.nextLine();
                        System.out.print("Enter medical history: ");
                        String medicalHistory = scanner.nextLine();
                        patientDAO.registerPatient(patientName, patientDob, patientContact, medicalHistory);
                        System.out.println("Patient registered successfully.");
                        break;
                    case 2:
                        System.out.print("Enter patient ID: ");
                        int patientId = scanner.nextInt();
                        patientDAO.getPatientDetails(patientId);
                        break;
                    case 3:
                        System.out.print("Enter staff name: ");
                        String staffName = scanner.nextLine();
                        System.out.print("Enter staff position: ");
                        String staffPosition = scanner.nextLine();
                        System.out.print("Enter staff contact: ");
                        String staffContact = scanner.nextLine();
                        System.out.print("Enter staff schedule: ");
                        String staffSchedule = scanner.nextLine();
                        staffDAO.registerStaff(staffName, staffPosition, staffContact, staffSchedule);
                        System.out.println("Staff registered successfully.");
                        break;
                    case 4:
                        System.out.print("Enter staff ID: ");
                        int staffId = scanner.nextInt();
                        staffDAO.getStaffDetails(staffId);
                        break;
                    case 5:
                        System.out.print("Enter item name: ");
                        String itemName = scanner.nextLine();
                        System.out.print("Enter quantity: ");
                        int quantity = scanner.nextInt();
                        inventoryDAO.addItem(itemName, quantity);
                        System.out.println("Item added to inventory.");
                        break;
                    case 6:
                        inventoryDAO.checkInventory();
                        break;
                    case 7:
                        System.out.print("Enter patient ID for billing: ");
                        int billingPatientId = scanner.nextInt();
                        System.out.print("Enter amount: ");
                        double amount = scanner.nextDouble();
                        System.out.print("Enter payment status: ");
                        scanner.nextLine(); // Consume newline
                        String paymentStatus = scanner.nextLine();
                        billingDAO.generateBill(billingPatientId, amount, paymentStatus);
                        System.out.println("Bill generated successfully.");
                        break;
                    case 8:
                        System.out.print("Enter bill ID: ");
                        int billId = scanner.nextInt();
                        billingDAO.getBill(billId);
                        break;
                    case 9:
                        System.out.print("Enter patient ID: ");
                        int appointmentPatientId = scanner.nextInt();
                        System.out.print("Enter staff ID: ");
                        int appointmentStaffId = scanner.nextInt();
                        System.out.print("Enter appointment date (YYYY-MM-DD HH:MM:SS): ");
                        scanner.nextLine(); // Consume newline
                        String appointmentDate = scanner.nextLine();
                        appointmentDAO.scheduleAppointment(appointmentPatientId, appointmentStaffId, appointmentDate);
                        System.out.println("Appointment scheduled successfully.");
                        break;
                    case 10:
                        System.out.print("Enter appointment ID: ");
                        int appointmentId = scanner.nextInt();
                        appointmentDAO.getAppointmentDetails(appointmentId);
                        break;
                    case 11:
                        System.out.print("Enter patient ID: ");
                        int recordPatientId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter diagnosis: ");
                        String diagnosis = scanner.nextLine();
                        System.out.print("Enter treatment: ");
                        String treatment = scanner.nextLine();
                        medicalRecordsDAO.createMedicalRecord(recordPatientId, diagnosis, treatment);
                        System.out.println("Medical record created successfully.");
                        break;
                    case 12:
                        System.out.print("Enter medical record ID: ");
                        int recordId = scanner.nextInt();
                        medicalRecordsDAO.getMedicalRecord(recordId);
                        break;
                    case 13:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (SQLException e) {
                System.err.println("An error occurred: " + e.getMessage());
            }
        }
    }
}

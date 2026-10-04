package HospitalAppointment;
import java.util.*;
import java.time.*;
public class Main {
	// static scanner we can use for main mmethod as well as remaining methods
	static Scanner scan= new Scanner(System.in);
	static Map<Integer, Patient> patients = new HashMap<>();
    static Map<Integer, Doctor> doctors = new HashMap<>();
    static Map<String, Appointment> appointments = new HashMap<>();
	public static void main(String args[]) {
		int choice;
		do {
			System.out.println("\n======================================");
            System.out.println(" HOSPITAL APPOINTMENT MANAGEMENT SYSTEM");
            System.out.println("======================================");

            System.out.println("1. Add Doctor");
            System.out.println("2. Register Patient");
            System.out.println("3. View Doctors Available");
            System.out.println("4. View Available Slots");
            System.out.println("5. Book Appointment");
            System.out.println("6. View Doctor Schedule");
            System.out.println("7. View Patient History");
            System.out.println("8. Cancel Appointment");
            System.out.println("9. Exit");

            System.out.print("Enter your choice: ");
            try {
            	choice=scan.nextInt();
            	switch(choice) {
            	case 1:
                    addDoctor();
                    break;

                case 2:
                    registerPatient();
                    break;
                case 3:
                	viewDoctors();
                	break;
                case 4:
                    viewAvailableSlots();
                    break;

                case 5:
                    bookAppointment();
                    break;

                case 6:
                    viewDoctorSchedule();
                    break;

                case 7:
                    viewPatientHistory();
                    break;

                case 8:
                    cancelAppointment();
                    break;

                case 9:
                    System.out.println("Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice!");

            	}
            }
            catch(Exception e) {
            	System.out.println("Invalid Input");
            	choice=0;
            }
		}while(choice!=9);
		
		
	}
	static void addDoctor() {
		System.out.println("\n-----Add Doctor------");
		 System.out.print("Enter Doctor ID: ");
	        int id = scan.nextInt();
	        if(doctors.containsKey(id)) {
	        	System.out.println("Doctor ID already exists!");
	            return;
	        }
	        scan.nextLine();

	        System.out.print("Enter Doctor Name: ");
	        String name = scan.nextLine();

	        System.out.print("Enter Specialization: ");
	        String specialization = scan.nextLine();

	        Doctor doctor = new Doctor(id, name, specialization);
	        //Adding some time available time slots
	        doctor.addSlot("10:00");
	        doctor.addSlot("11:00");
	        doctor.addSlot("12:00");
	        doctor.addSlot("14:00");
	        doctor.addSlot("15:00");
	        doctors.put(id, doctor);
	        

	}
	 // 2. Register Patient
    static void registerPatient() {

        System.out.println("\n----- Register Patient -----");

        System.out.print("Enter Patient ID: ");
        int id = scan.nextInt();

        if (patients.containsKey(id)) {
            System.out.println("Patient ID already exists!");
            return;
        }

        scan.nextLine();

        System.out.print("Enter Patient Name: ");
        String name = scan.nextLine();

        System.out.print("Enter Age: ");
        int age = scan.nextInt();

        scan.nextLine();

        System.out.print("Enter Phone Number: ");
        String phone = scan.nextLine();

        Patient patient = new Patient(id, name, age, phone);

        patients.put(id, patient);

        System.out.println("Patient registered successfully!");
    }
//    // 3. View Doctors
    static void viewDoctors() {

        System.out.println("\n----- List of Doctors -----");

        if (doctors.isEmpty()) {
            System.out.println("No doctors available.");
            return;
        }

        for (Doctor doctor : doctors.values()) {

            System.out.println(
                    "Doctor ID: " + doctor.getDoctorId()
                    + " | Name: " + doctor.getName()
                    + " | Specialization: "
                    + doctor.getSpecialization()
            );
        }
    }
    // 4. View Available Slots
    static void viewAvailableSlots() {

        System.out.println("\n----- Available Slots -----");

        if (doctors.isEmpty()) {
            System.out.println("No doctors available.");
            return;
        }

        System.out.print("Enter Doctor ID: ");
        int doctorId = scan.nextInt();

        Doctor doctor = doctors.get(doctorId);

        if (doctor == null) {
            System.out.println("Doctor not found!");
            return;
        }

        System.out.println("\nDoctor Name: " + doctor.getName());
        System.out.println("Specialization: " + doctor.getSpecialization());

        System.out.println("Available Slots:");

        for (String slot : doctor.getSlots()) {

            boolean booked = false;

            for (Appointment appointment : appointments.values()) {

                if (appointment.getDoctorId() == doctorId &&
                    appointment.getTime().toString().equals(slot)) {

                    booked = true;
                    break;
                }
            }

            if (!booked) {
                System.out.println(slot);
            }
        }
    }

    // 5. Book Appointment
    static void bookAppointment() {

        System.out.println("\n----- Book Appointment -----");

        System.out.print("Enter Patient ID: ");
        int patientId = scan.nextInt();

        if (!patients.containsKey(patientId)) {
            System.out.println("Patient not found!");
            return;
        }

        System.out.print("Enter Doctor ID: ");
        int doctorId = scan.nextInt();

        if (!doctors.containsKey(doctorId)) {
            System.out.println("Doctor not found!");
            return;
        }

        System.out.print("Enter Date (YYYY-MM-DD): ");
        String dateInput = scan.next();

        System.out.print("Enter Time (HH:MM): ");
        String timeInput = scan.next();

        try {

            LocalDate date = LocalDate.parse(dateInput);
            LocalTime time = LocalTime.parse(timeInput);

            String key = doctorId + "-" + date + "-" + time;

            if (appointments.containsKey(key)) {

                System.out.println("This time slot is already booked!");

                return;
            }

            Appointment appointment =
                    new Appointment(patientId, doctorId, date, time);

            appointments.put(key, appointment);

            System.out.println("Appointment booked successfully!");

        } catch (Exception e) {

            System.out.println("Invalid date or time format!");

        }
    }

    // 6. View Doctor Schedule
    static void viewDoctorSchedule() {

        System.out.println("\n----- Doctor Schedule -----");

        System.out.print("Enter Doctor ID: ");
        int doctorId = scan.nextInt();

        Doctor doctor = doctors.get(doctorId);

        if (doctor == null) {
            System.out.println("Doctor not found!");
            return;
        }

        System.out.println("\nDoctor: " + doctor.getName());
        System.out.println("Specialization: " +
                           doctor.getSpecialization());

        boolean found = false;

        for (Appointment appointment : appointments.values()) {

            if (appointment.getDoctorId() == doctorId) {

                System.out.println(
                        "Date: " + appointment.getDate() +
                        " | Time: " + appointment.getTime() +
                        " | Patient ID: " + appointment.getPatientId()
                );

                found = true;
            }
        }

        if (!found) {
            System.out.println("No appointments found.");
        }
    }

    // 7. View Patient History
    static void viewPatientHistory() {

        System.out.println("\n----- Patient History -----");

        System.out.print("Enter Patient ID: ");
        int patientId = scan.nextInt();

        Patient patient = patients.get(patientId);

        if (patient == null) {
            System.out.println("Patient not found!");
            return;
        }

        System.out.println("\nPatient Name: " + patient.getName());

        boolean found = false;

        for (Appointment appointment : appointments.values()) {

            if (appointment.getPatientId() == patientId) {

                Doctor doctor =
                        doctors.get(appointment.getDoctorId());

                System.out.println(
                        "Doctor: " + doctor.getName() +
                        " | Date: " + appointment.getDate() +
                        " | Time: " + appointment.getTime()
                );

                found = true;
            }
        }

        if (!found) {
            System.out.println("No appointment history found.");
        }
    }

    // 8. Cancel Appointment
    static void cancelAppointment() {

        System.out.println("\n----- Cancel Appointment -----");

        System.out.print("Enter Patient ID: ");
        int patientId = scan.nextInt();

        System.out.print("Enter Doctor ID: ");
        int doctorId = scan.nextInt();

        System.out.print("Enter Date (YYYY-MM-DD): ");
        String dateInput = scan.next();

        System.out.print("Enter Time (HH:MM): ");
        String timeInput = scan.next();

        try {

            LocalDate date = LocalDate.parse(dateInput);
            LocalTime time = LocalTime.parse(timeInput);

            String key = doctorId + "-" + date + "-" + time;

            Appointment appointment = appointments.get(key);

            if (appointment == null) {

                System.out.println("Appointment not found!");
                return;
            }

            if (appointment.getPatientId() != patientId) {

                System.out.println(
                        "This appointment does not belong to this patient!"
                );

                return;
            }

            appointments.remove(key);

            System.out.println("Appointment cancelled successfully!");
        } 
        catch (Exception e) {

            System.out.println("Invalid date or time format!");

        }
    }
}

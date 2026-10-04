package HospitalAppointment;
import java.util.*;
//import java.time.*;

public class Doctor {
	private int id;
	private String name;
	private String specialization;
	private ArrayList<String> slots;
	public Doctor(int id,String name, String specialization) {
		this.id=id;
		this.name=name;
		this.specialization=specialization;
		this.slots=new ArrayList<>();
	}
	public int getDoctorId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public ArrayList<String> getSlots() {
        return slots;
    }

    public void addSlot(String slot) {
        slots.add(slot);
    }
}

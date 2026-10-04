package HospitalAppointment;
//import java.util.*;
//import java.time.*;
public class Patient {
	private  int id;
	private String name;
	private int age;
	private String phone;
	public Patient(int id, String name, int age,String phone) {
		this.id=id;
		this.name=name;
		this.age=age;
		this.phone=phone;
	}
	public int getPatientId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }
	
}

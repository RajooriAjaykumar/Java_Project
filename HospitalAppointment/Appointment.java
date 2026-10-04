package HospitalAppointment;
//import java.util.*;
import java.time.*;
public class Appointment {
	private int pId;
	private int dId;
	private LocalDate date;
	private LocalTime time;
	public Appointment(int pId, int dId,LocalDate date, LocalTime time) {
		this.pId=pId;
		this.dId=dId;
		this.date=date;
		this.time=time;
	}
	public int getDoctorId() {
		return dId;
	}
	public int getPatientId() {
		return pId;
	}
	public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }
	
	
	
}

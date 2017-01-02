package models;

public class SoccerAttendee {
	
	public String lastName  = "NA";
	public String firstName = "NA";

	public SoccerAttendee() { }
	
	public SoccerAttendee(String lname, String fname) {
		this.lastName = lname;
		this.firstName = fname;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String toString() {
		return (this.lastName + "," + this.firstName);
	}

}

package models.attendees;

import java.util.List;

import models.attendees.StarPlayers.SoccerAttendeesManager;

import com.google.inject.ImplementedBy;

@ImplementedBy(SoccerAttendeesManager.class)
public interface AttendeesManager {
	
	public List<String> getAll();
	public String       getBySurname(String surname);
	public Integer      attendeeCount();
	public Integer      addNewAttendee(String surname,String firstname);

}

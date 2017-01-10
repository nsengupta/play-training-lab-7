package models.attendees;

import java.util.List;
import java.util.concurrent.CompletionStage;

import models.attendees.StarPlayers.SoccerAttendeesManager;
import models.attendees.StarPlayers.SoccerAttendeesManagerWithPromise;

import com.google.inject.ImplementedBy;

@ImplementedBy(SoccerAttendeesManagerWithPromise.class)
public interface AttendeesManagerWithPromise {
	
	public CompletionStage<List<String>> getAll();
	
	// TODO: Change, as appropriate.
	// public String       getBySurname(String surname);
	// public Integer      attendeeCount();
	// public Integer      addNewAttendee(String surname,String firstname);

}

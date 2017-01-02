package models;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class AttendeesDB {
	
	public final Map<String,SoccerAttendee> attendees= new HashMap<String,SoccerAttendee>();
	
	public AttendeesDB () {
		
		attendees.put("Ronaldo",     new SoccerAttendee("Ronaldo","Christiano"));
		attendees.put("Messi",       new SoccerAttendee("Messi","Lionel"));
		attendees.put("Ozil",        new SoccerAttendee("Ozil","Mesit"));
		attendees.put("Sterling",    new SoccerAttendee("Sterling","Rehim"));
		attendees.put("Griezeman",   new SoccerAttendee("Griezeman","Antoine"));
		attendees.put("Lewandowski", new SoccerAttendee("Lewandowski","Robert"));
		attendees.put("Agueiro",     new SoccerAttendee("Agueiro","Sergio"));
		attendees.put("Keane",       new SoccerAttendee("Keane","Robby"));
		attendees.put("Gyan",        new SoccerAttendee("Gyan","Asamoa"));
		attendees.put("Speed",       new SoccerAttendee("Speed","Gary"));
		attendees.put("Kelly",       new SoccerAttendee("Kelly","Gary"));
	}
	
	public List<String> getAll() {
		
		List<String> players = new ArrayList<String>();
		
		for (Entry<String, SoccerAttendee> s: this.attendees.entrySet()) 
			players.add(s.getValue().toString());
		
		return (players);
	}
	
	public String getBySurname(String sname) {
		
		SoccerAttendee soccerAttendee = attendees.get(sname);
		if (soccerAttendee == null)
			return ("No player with " + sname+ ", found");
		else 
			return (soccerAttendee.toString());
	}
	
	public int attendeeCount() {
		return (attendees.size());
	}
	
	public Integer addNewAttendee(SoccerAttendee sa) {
		attendees.put(sa.lastName, sa);
		return (attendeeCount());
	}

}

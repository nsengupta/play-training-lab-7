package services.attendees.starPlayers;

import java.io.Serializable;

public class SoccerInfoMessageProtocol {
	
	public static class CountMessage { 
		public String toString() {
			return ("CountMessage");
		}
	}
	
	public static class GetAllMessage { 
		public String toString() {
			return ("GetAllMessage");
		}
	}
	
	public static class GetAllTournamentsMessage {
		public final String year;
		public GetAllTournamentsMessage(String year) {
			this.year = year;
		}
		public String toString() {
			return ("GetAllTournamentsMessage");
		}
	}
	

}

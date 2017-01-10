package services.attendees.starPlayers;

import java.io.Serializable;

public class SoccerInfoMessageProtocol {
	
	public static class CountMessage { 
		public String toString() {
			return ("PingMessage");
		}
	}
	
	public static class GetAllMessage { 
		public String toString() {
			return ("PongMessage");
		}
	}
	

}

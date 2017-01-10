package services.attendees.starPlayers;


import java.util.Arrays;
import java.util.List;

import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Creator;
import static services.attendees.starPlayers.SoccerInfoMessageProtocol.*;

public class SoccerAttendeesInfoActor extends UntypedActor {
	
	LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	
	private AttendeesDB attendeesDB = new AttendeesDB();
	

	@Override
	public void onReceive(Object arg0) throws Throwable {
		
		if (arg0 instanceof CountMessage) {
			Integer countFromDB = attendeesDB.attendeeCount();
			getSender().tell(countFromDB, getSelf());
		}
		else
	    if (arg0 instanceof GetAllMessage) {
	    	List<String> allPlayers = attendeesDB.getAll();
	    	getSender().tell(allPlayers,getSelf());
	    }
		
	}
	
	public static Props props = Props.create(SoccerAttendeesInfoActor.class);

}
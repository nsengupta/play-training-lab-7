package services.attendees.starPlayers;


import java.net.URI;
import java.util.Arrays;
import java.util.List;

import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Creator;
import scala.concurrent.duration.Duration;
import akka.japi.Function;
import scala.concurrent.duration.Duration;
import akka.japi.Function;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import static akka.dispatch.Futures.future;
import static java.util.concurrent.TimeUnit.SECONDS;
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
	    else
	    if (arg0 instanceof GetAllTournamentsMessage) {
	    	
	    	GetAllTournamentsMessage m = (GetAllTournamentsMessage) arg0;	
	    }	
	}
	
	public static Props props = Props.create(SoccerAttendeesInfoActor.class);

}
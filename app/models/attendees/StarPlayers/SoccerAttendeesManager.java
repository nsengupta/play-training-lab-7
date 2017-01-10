package models.attendees.StarPlayers;

import java.util.ArrayList;
import java.util.List;

import services.attendees.starPlayers.AttendeesDB;
import services.attendees.starPlayers.SoccerAttendeesInfoActor;
import play.mvc.*;
import akka.actor.ActorSystem;
import akka.actor.ActorRef;
import play.api.libs.concurrent.*;

import com.google.inject.Inject;

import models.attendees.AttendeesManager;

public class SoccerAttendeesManager  implements AttendeesManager {
	
	final ActorRef attendeeInfoActor;

    @Inject 
    public SoccerAttendeesManager(ActorSystem system) {
    	attendeeInfoActor = system.actorOf(SoccerAttendeesInfoActor.props);
    }

	@Override
	public List<String> getAll() { 
		
		return new ArrayList<String>(); 
		
	}

	@Override
	public String getBySurname(String surname) { return (surname); }
		
	@Override
	public Integer attendeeCount() { return (0); }

	@Override
	public Integer addNewAttendee(String surname, String firstname) {
		// TODO Auto-generated method stub
		return null;
	}

}
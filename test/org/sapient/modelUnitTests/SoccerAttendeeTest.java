package org.sapient.modelUnitTests;

import java.util.List;

import models.attendees.StarPlayers.SoccerAttendeesManager;

import org.junit.Before;
import org.junit.Test;

import services.attendees.AttendeesDB;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class SoccerAttendeeTest {
	
	private SoccerAttendeesManager manager;
	
	@Before
	public void setUp() {
	  manager = new SoccerAttendeesManager(new AttendeesDB());
	}
	
	@Test
	public void countTest() {
		
		
		int countOfPlayers = manager.attendeeCount();
		assertThat(countOfPlayers,equalTo(11));	
	}
	
	@Test
	public void allTest() {
		
		SoccerAttendeesManager manager = new SoccerAttendeesManager(new AttendeesDB());
		List<String> allSoccerPlayers = manager.getAll();
		
		assertThat(allSoccerPlayers.size(),equalTo(11));
		assertThat(allSoccerPlayers,hasItems("Christiano,Ronaldo"));
		assertThat(allSoccerPlayers,not(hasItems("Baichung,Bhutia")));
	}
}

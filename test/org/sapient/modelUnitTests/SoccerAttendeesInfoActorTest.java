package org.sapient.modelUnitTests;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import models.attendees.StarPlayers.SoccerAttendeesManager;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.JavaTestKit;
import akka.testkit.TestActor;
import akka.testkit.TestActor.Message;
import akka.testkit.TestActorRef;
import akka.testkit.TestProbe;
import scala.concurrent.duration.Duration;
import services.attendees.starPlayers.AttendeesDB;
import services.attendees.starPlayers.SoccerAttendeesInfoActor;
import services.attendees.starPlayers.SoccerInfoMessageProtocol;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertArrayEquals;

public class SoccerAttendeesInfoActorTest {
	
	private SoccerAttendeesManager manager;
	
	static ActorSystem system;
	
	@BeforeClass
	  public static void setup() {
	    system = ActorSystem.create("myAttendeesActorTestSystem");
	  }
	  
	  @AfterClass
	  public static void teardown() {
	    JavaTestKit.shutdownActorSystem(system);
	    system = null;
	  }
	
	@Test
	public void countTest() {
		System.out.println("CountTest running...");
		new JavaTestKit(system) {{
			
			final Props props = Props.create(SoccerAttendeesInfoActor.class);
			final TestActorRef<SoccerAttendeesInfoActor> attendeesRef = TestActorRef.create(system, props, "countTest");
			final SoccerAttendeesInfoActor actor = attendeesRef.underlyingActor();
			
			attendeesRef.tell(new SoccerInfoMessageProtocol.CountMessage(), getRef());		
			expectMsgEquals(duration("1 second"),2);
		}};
	}
	
	@Test
	public void allTest() {
		
        new JavaTestKit(system) {{
        	
        	String expectedPlayers[] = { "Christiano Ronaldo", "Lionel Messi" }; 
			
			final Props props = Props.create(SoccerAttendeesInfoActor.class);
			final TestActorRef<SoccerAttendeesInfoActor> attendeesRef = TestActorRef.create(system, props, "allTest");
			final SoccerAttendeesInfoActor actor = attendeesRef.underlyingActor();
			
			TestProbe testProbe = TestProbe.apply(system);
			
			attendeesRef.tell(new SoccerInfoMessageProtocol.GetAllMessage(), testProbe.ref());		
			testProbe.expectMsg(duration("1 second"),
					        Arrays.asList(expectedPlayers));
			
			Message responseMessage = testProbe.lastMessage();
			List<String> retrievedPlayers = (ArrayList<String>) responseMessage.msg();
			
			assertArrayEquals(retrievedPlayers.toArray(new String[0]),expectedPlayers);
		}};
		
	}
}

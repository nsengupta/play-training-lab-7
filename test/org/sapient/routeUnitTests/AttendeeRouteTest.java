package org.sapient.routeUnitTests;

import models.attendees.AttendeesManager;
import models.attendees.StarPlayers.SoccerAttendeesManager;

import org.junit.Test;

import controllers.AttendeesController;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import static play.inject.Bindings.bind;
import play.mvc.Result;
import play.test.WithApplication;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import play.mvc.Http.RequestBuilder;
import services.attendees.AttendeesDB;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;


public class AttendeeRouteTest extends WithApplication {
	
	@Override
	  protected Application provideApplication() {
	    Application application = new GuiceApplicationBuilder()
	    .overrides(bind(AttendeesManager.class).to(SoccerAttendeesManager.class))
	    .build();
	    
	    return (application);
	      
	  }
	
	@Test
	  public void testIndex() {
	    Result result = new AttendeesController(new SoccerAttendeesManager(new AttendeesDB())).index();
	    assertEquals(OK, result.status());
	    assertEquals("text/html", result.contentType().get());
	    assertEquals("utf-8", result.charset().get());
	    assertTrue(contentAsString(result).contains("Welcome"));
	  }

}

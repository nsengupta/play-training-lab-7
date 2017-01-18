package controllers;

import static akka.pattern.Patterns.ask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

import models.attendees.StarPlayers.SoccerAttendeeDataCarrier;
import play.data.Form;
import play.data.FormFactory;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;
import play.libs.ws.WSResponse;
import play.mvc.Controller;
import play.mvc.Result;
import scala.compat.java8.FutureConverters;
import services.attendees.starPlayers.SoccerAttendeesInfoActor;
import services.attendees.starPlayers.SoccerInfoMessageProtocol;
import views.html.index;
import views.html.attendees.list;
import views.html.attendees.count;
import views.html.attendees.external;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class AttendeesController extends Controller {
	/**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
	@Inject
    FormFactory formFactory;
	
	private final ActorRef soccerAttendeesInfoActor;
	
	@Inject WSClient ws;
	
	@Inject
    public AttendeesController(ActorSystem actorSystem) {
       this.soccerAttendeesInfoActor = actorSystem.actorOf(SoccerAttendeesInfoActor.props,"Soccer-Players-Actor");
    }
	
	
    public Result index() {
        return ok(index.render("Attendees Application is being readied."));
    }
    
public CompletionStage<Result> getTourneys(String year) {
    	
    	Function<Object,List<String>> fn = (r) -> {
    		List<String> s = (ArrayList<String>)r;
    		return (s);
    	};
    	
        return( FutureConverters.toJava(ask(soccerAttendeesInfoActor,
			    new SoccerInfoMessageProtocol.GetAllTournamentsMessage(year), 
				1000))
                .thenApply(fn)
                .thenApply(nameList -> ok(list.render(nameList))));
    }
    
    public CompletionStage<Result> getAll() {
    	
    	Function<Object,List<String>> fn = (r) -> {
    		List<String> s = (ArrayList<String>)r;
    		return (s);
    	};
    	
        return( FutureConverters.toJava(ask(soccerAttendeesInfoActor,
			    new SoccerInfoMessageProtocol.GetAllMessage(), 
				1000))
                .thenApply(fn)
                .thenApply(nameList -> ok(list.render(nameList))));
    }
    
    public CompletionStage<Result> externalWS() {
    	
    	WSRequest request = ws.url("http://example.com");
    	
    	Function<Object,WSResponse> fn = (o) -> {
    		WSResponse r = (WSResponse) o;
    		return (r);
    	};
    	
    	return (request
    			.get()
    			.thenApply(fn)
    	        .thenApply(r -> ok(external.render(r.getBody()))));
    	
    }
    
    public Result getBySurname(String surname) {
    	
    	return TODO;
    }
    
    public Result count() {
    	int attendeeCountAtPresent = 0; // this.attendeesManager.attendeeCount();
    	return TODO;
    }
    
    public Result addAttendee(String surname,String firstname) {
    	return TODO;
    }
    
    public Result addSoccerAttendeeThruForm() {
    	Form<SoccerAttendeeDataCarrier> attendeeForm = formFactory.form(SoccerAttendeeDataCarrier.class);
    	attendeeForm.fill(new SoccerAttendeeDataCarrier("LastName here","Firstname here"));
    	return TODO;
    	
    }
    
    public Result saveSoccerAttendeeThruForm() {
    	Form<SoccerAttendeeDataCarrier> attendeeForm = formFactory.form(SoccerAttendeeDataCarrier.class).bindFromRequest();
    	/*this.attendeesManager
    	    .addNewAttendee(
    	    			attendeeForm.apply("surname").value(), 
    	    			attendeeForm.apply("firstname").value()
    	    		);*/
    	ok(String
    			.format("New Soccer Player %s,%s added", 
    					 attendeeForm.apply("firstName").value(),
    					 attendeeForm.apply("lastName").value()
    				   )
    			 );
    	
    	return TODO;
    }

}

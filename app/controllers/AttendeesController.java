package controllers;

import java.util.List;

import com.google.inject.Inject;

import models.AttendeesDB;
import models.SoccerAttendee;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.attendees.bysname;
import views.html.attendees.list;
import views.html.attendees.count;
import views.html.attendees.newSoccerAttendeeDetails;
import views.html.index;

public class AttendeesController extends Controller {
	/**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
	@Inject
    FormFactory formFactory;
	
	private AttendeesDB attendeesDB = new AttendeesDB();
	
    public Result index() {
        return ok(index.render("Attendees Application is being readied."));
    }
    
    public Result getAll() {
    	List<String> all =  attendeesDB.getAll();
    	return ok(list.render(all));
    }
    
    public Result getBySurname(String surname) {
    	String retrievedName = attendeesDB.getBySurname(surname);
    	return ok(bysname.render(retrievedName));
    }
    
    public Result count() {
    	int attendeeCount = attendeesDB.attendeeCount();
    	return ok(count.render(attendeeCount));
    }
    
    public Result addAttendee(String surname,String firstname) {
    	return TODO;
    }
    
    public Result addSoccerAttendeeThruForm() {
    	Form<SoccerAttendee> attendeeForm = formFactory.form(SoccerAttendee.class);
    	attendeeForm.fill(new SoccerAttendee("LastName here","Firstname here"));
    	return ok(newSoccerAttendeeDetails.render(attendeeForm));
    	
    }
    
    public Result saveSoccerAttendeeThruForm() {
    	Form<SoccerAttendee> attendeeForm = formFactory.form(SoccerAttendee.class).bindFromRequest();
    	attendeesDB.addNewAttendee(attendeeForm.get());
    	return ok(String
    			.format("New Soccer Player %s,%s added", 
    					 attendeeForm.apply("firstName").value(),
    					 attendeeForm.apply("lastName").value()
    				   )
    			 );
    	
    }

}

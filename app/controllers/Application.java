package controllers;

import com.cricmania.models.Base;
import com.mongodb.util.JSON;

import play.*;
import play.libs.Json;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }
    
    public static Result saveBase() {
    	Base base = new Base();
    	base.setId("1");
    	base.setName("test");
    	base.save();
    	return ok(Json.toJson(base));
    }

}

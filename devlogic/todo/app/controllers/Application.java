package controllers;

import models.Task;
import play.*;
import play.mvc.*;

import views.html.*;

import java.util.List;

public class Application extends Controller {

    public Result index() {
        return ok(index.render("Tasks To Do"));
    }

}

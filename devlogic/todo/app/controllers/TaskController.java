package controllers;

import com.avaje.ebean.Ebean;
import models.Category;
import models.Task;
import play.Logger;
import play.data.Form;
import play.mvc.*;
import views.html.list;
import views.html.newTask;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Emina on 13.1.2016.
 */
public class TaskController extends Controller{

    private final Form<Task> taskForm = Form.form(Task.class);

    public Result viewTasks(){
        List<Task> taskList = Task.findAll();
        Logger.info(taskList.toString());
        return ok(list.render(taskList));
    }

    public Result saveTask() {
        Form<Task> boundForm = taskForm.bindFromRequest();
        if (boundForm.hasErrors()) {
            flash("warning", "Please correct form.");
            return redirect("tasks/new");
        }
        Long id = boundForm.get().getId();
        String title = boundForm.get().getTitle();
        Boolean status = boundForm.get().getIsCompleted();
        Date date  = boundForm.get().getDate();
        String categoryName = boundForm.bindFromRequest().field("category-id").value();
        Category category = Category.findCategoryByName(categoryName);

        Task task = boundForm.get();
        task.save();
        Ebean.save(task);
        flash("success", "You have successfully add a new task");

        return redirect(routes.TaskController.newTask());
    }

    public Result newTask() {
        List<Category>categories = Category.findAll();
        return ok(newTask.render(taskForm, categories));
    }

    public Result search(Long id) {
        List<Task> tasks = new ArrayList<Task>();
        tasks.add(Task.findTaskById(id));
        return ok(list.render(tasks));
    }


    public Result editTask(Long id){
        Task task = Task.findTaskById(id);
        Form<Task> boundform = taskForm.bindFromRequest();

        String title = boundform.field("taskTitle").value();
        task.setTitle(title);
        task.update();

        return redirect("/");

    }

    /**
     * Deletes selected task.
     * @param id - task id
     * @return If the deleting of the product was successful renders page where all product of user are listed.
     */
    public Result deleteTask(Long id) {

        Task task = Task.findTaskById(id);
        task.setIsCompleted(true);
        task.update();
        return redirect("/");
    }

    public Result updateTask(Long id){

        Task task= Task.findTaskById(id);
        Form<Task> boundform = taskForm.bindFromRequest();

        String name = boundform.field("task-title").value();
        task.setTitle(name);
        task.setIsCompleted(true);
        task.update();

        return redirect("/");

    }

}

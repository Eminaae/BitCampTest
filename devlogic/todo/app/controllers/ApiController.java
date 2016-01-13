package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import helpers.JSONHelper;
import models.Category;
import models.Task;
import org.json.JSONArray;
import org.json.JSONObject;
import play.mvc.Controller;
import play.libs.Json;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Emina on 13.1.2016.
 */
public class ApiController extends Controller {

    public Result categoryList(){
        List<Category> categories = Category.findAll();
        List<String> categoryName = new ArrayList<>();
        List<String> categoryId = new ArrayList<>();
        for(int i = 0; i < categories.size(); i++){
            categoryName.add(categories.get(i).getName());
            categoryId.add(categories.get(i).getId().toString());
        }

        JSONArray jsonArray  = new JSONArray();
        JSONObject jsonObjectId = new JSONObject();
        jsonObjectId.put("category-id", categoryId);

        JSONObject jsonObjectName = new JSONObject();
        jsonObjectName.put("category-name", categoryName);

        jsonArray.put(jsonObjectId);
        jsonArray.put(jsonObjectName);
        JsonNode jsonNode = Json.toJson(jsonArray);
        return ok(jsonNode);
    }

    public Result taskList(){
        List<Task> taskList = Task.findAll();
        List<String> taskTitle = new ArrayList<>();
        List<String> taskId = new ArrayList<>();
        List<String> taskCompleted = new ArrayList<>();
        for(int i = 0; i < taskList.size(); i++){
           taskId.add(taskList.get(i).getId().toString());
            taskTitle.add(taskList.get(i).getTitle());
            taskCompleted.add(taskList.get(i).getIsCompleted().toString());
        }

        JSONArray jsonArray  = new JSONArray();
        JSONObject jsonObjectId = new JSONObject();
        jsonObjectId.put("task-id", taskId);

        JSONObject jsonObjectTitle = new JSONObject();
        jsonObjectTitle.put("task-title", taskTitle);

        JSONObject jsonObjectStatus = new JSONObject();
        jsonObjectStatus.put("completed", taskCompleted);

        jsonArray.put(jsonObjectId);
        jsonArray.put(jsonObjectTitle);
        jsonArray.put(jsonObjectStatus);
        JsonNode jsonNode = Json.toJson(jsonArray);
        return ok(jsonNode);
    }

    public Result newTask(){

        JsonNode json = request().body().asJson();
        String title = json.findPath("title").textValue();

        Task task = new Task();
        task.setTitle(title);
        task.save();

        JSONObject object = new JSONObject();

        object.put("response","ok");
        JsonNode jsonNode = Json.toJson(object);
        return ok(jsonNode);

    }
}

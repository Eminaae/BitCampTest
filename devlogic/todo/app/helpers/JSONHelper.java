package helpers;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import models.Category;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Task;
import play.libs.Json;

import java.util.List;

/**
 * Created by Emina on 13.1.2016.
 */
public class JSONHelper {

    /**
     * Create category as json
     * @param c - category
     * @return category
     */
    public static ObjectNode jsonCategory(Category c){
        ObjectNode category = Json.newObject();
        category.put("category-id", c.getId());
        category.put("category-name", c.getName());
        return category;

    }

    /**
     * Create task as json
     * @param t - task
     * @return task
     */
    public static ObjectNode jsonTask(Task t){
        ObjectNode task = Json.newObject();
        task.put("task-id", t.getId());
        task.put("task-title", t.getTitle());
        task.put("task-status", t.getIsCompleted());
        return task;
    }

    /**
     * Getting list of categories as a json
     * @param categories - category list
     * @return jsonListCategories
     */
    public static ArrayNode jsonCategoryList(List<Category> categories){
        ArrayNode jsonListCategories = new ArrayNode(JsonNodeFactory.instance);
        for(Category category: categories){
            ObjectNode jsonCategory = jsonCategory(category);
            jsonListCategories.add(jsonCategory);
        }
        return jsonListCategories;
    }

    /**
     * Getting list of tasks as a json
     * @param tasks - task list
     * @return - jsonListTasks
     */
    public static ArrayNode jsonTaskList(List<Task> tasks){
        ArrayNode jsonListTasks = new ArrayNode(JsonNodeFactory.instance);
        for(Task task: tasks){
            ObjectNode jsonTask = jsonTask(task);
            jsonListTasks.add(jsonTask);
        }
        return jsonListTasks;
    }
}

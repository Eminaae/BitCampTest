package models;


import com.avaje.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Emina on 12.1.2016.
 */
@Entity
public class Task extends Model {

    @Id
    @Constraints.Min(10)
    private Long id;

    @Column(length = 255)
    @Constraints.Required
    private String title;

    @Column
    private Boolean isCompleted = true;

    @Formats.DateTime(pattern="dd/MM/yyyy")
    private Date date = new Date();

    @ManyToOne
    private Category category;

    private static Finder<Long, Task> taskFinder = new Finder<Long, Task>(Task.class);

    public Task() {
    }

    public Task(Long id, String title, Boolean isCompleted, Date date) {
        this.id = id;
        this.title = title;
        this.isCompleted = isCompleted;
        this.date = date;
    }

    public Task(Long id, String title, Boolean isCompleted, Date date, Category category) {
        this.id = id;
        this.title = title;
        this.isCompleted = isCompleted;
        this.date = date;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Method to find task by id
     * @param id - task id
     * @return - task if exist
     */
    public static Task findTaskById(Long id){
        Task task = taskFinder.byId(id);
        if(task != null){
            return task;
        }
        return null;
    }

    /**
     * Method to find task by name
     * @param taskTitle - task title
     * @return - task
     */
    public static Task findTaskByName(String taskTitle){
        return  taskFinder.where().eq("taskTitle", taskTitle).findUnique();
    }

    /**
     * Method to find tasks by task status
     * @return list
     */
    public static List<Task> findTaskByStatus(){
        return taskFinder.where().eq("completed", null).findList();
    }

    /**
     * Method findAll will return all tasks as a list
     * @return - tasks list
     */
    public static List<Task> findAll(){
        List<Task> taskList = taskFinder.findList();
        if(taskList.isEmpty()){
            taskList = new ArrayList<>();
        }
        return taskList;
    }
}

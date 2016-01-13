package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Emina on 13.1.2016.
 */
@Entity
public class Category extends Model{

    @Id
    private Long Id;

    @Column
    @Constraints.Required
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Task> tasks;
    public List<Category> categories = new ArrayList<>();

    public Category() {
    }

    public Category(Long id, String name) {
        Id = id;
        this.name = name;
    }

    public Category(Long id, String name, List<Task> tasks) {
        Id = id;
        this.name = name;
        this.tasks = tasks;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public static Finder<Long, Category> categoryFinder = new Finder<Long, Category>(Category.class);

    /**
     * Method to find task category by category name
     * @param name - category name
     * @return category searched for
     */
    public static Category findCategoryByName(String name){
        return categoryFinder.where().eq("category-name", name).findUnique();
    }


    /**
     * Method to find task category by category id
     * @param id - category id
     * @return category searched for
     */
    public static Category findCategoryById(Long id){
        return categoryFinder.where().eq("category-id", id).findUnique();
    }

    /**
     * This method is used to find all categories
     * @return - List of categories
     */
    public static List<Category> findAll() {
        List<Category> categories = categoryFinder.findList();
        if(categories.isEmpty()){
            categories = new ArrayList<>();
        }
        return categories;
    }
}

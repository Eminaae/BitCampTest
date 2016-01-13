package controllers;

import models.Category;
import models.Task;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.*;
import views.html.editCategory;
import views.html.index;
import views.html.listCategory;
import views.html.newCategory;

import java.util.List;


/**
 * Created by Emina on 13.1.2016.
 */
public class CategoryController extends Controller {

    private final Form<Category> categoryForm = Form.form(Category.class);

    /**
     * Method to create new category
     * @param id - category id
     * @return - renders new category
     */
    public Result newCategory(Long id){
        Task task = Task.findTaskById(id);
        return ok(newCategory.render(categoryForm,task));
    }

    public Result newCategory() {
        Task task = new Task();
        return ok(newCategory.render(categoryForm, task));
    }

    public Result saveCategory(Long id){
        Form <Category> boundForm = categoryForm.bindFromRequest();
        Task task = Task.findTaskById(id);
        Category category = new Category();
        String categoryName = boundForm.field("categoryName").value();
        category.setName(categoryName);
        category.save();
        flash("success", "Category added successfully");
        return redirect("/");

    }

    public Result editCategory(Long id){
        Category category = Category.findCategoryById(id);
        List<Category>categories = Category.findAll();
        return ok(editCategory.render(category, categories));
    }

    public Result updateCategory(Long id){
        Category category = Category.findCategoryById(id);
        Form<Category> boundForm = categoryForm.bindFromRequest();
        String categoryName = boundForm.field("categoryName").value();
        category.setName(categoryName);
        category.update();
        return redirect("/");
    }

    public Result deleteCategory(Long id){
        Category category = Category.findCategoryById(id);
        if(category == null){
            return badRequest(listCategory.render(Category.findAll()));
        }
        category.delete();
        return ok(listCategory.render(Category.findAll()));
    }

}

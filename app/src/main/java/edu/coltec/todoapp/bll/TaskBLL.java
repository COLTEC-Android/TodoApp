package edu.coltec.todoapp.bll;

import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import edu.coltec.todoapp.dao.AppDB;
import edu.coltec.todoapp.dao.Category;
import edu.coltec.todoapp.dao.CategoryDAO;
import edu.coltec.todoapp.dao.Task;
import edu.coltec.todoapp.dao.TaskDAO;

public class TaskBLL {

    private AppDB appDB;

    public TaskBLL(AppDB appDB) {
        this.appDB = appDB;
    }

    public boolean create(String name, String description, String categoryName) {
        TaskDAO taskDAO = new TaskDAO(this.appDB);
        CategoryDAO categoryDAO = new CategoryDAO(this.appDB);

        Category category = categoryDAO.getByName(new Category(categoryName));
        if (category == null) {
            categoryDAO.create(new Category(categoryName));
            category = categoryDAO.getByName(new Category(categoryName));
        }

        Task newTask = new Task(name, description, category);

        if (!newTask.isValid())
            return false;

        Task task = taskDAO.getByName(newTask);
        if (task != null)
            return false;

        boolean result = taskDAO.create(newTask);
        return result;
    }

    public List<Task> getAll() {
        List<Task> tasks = new ArrayList<>();



        return tasks;
    }
}

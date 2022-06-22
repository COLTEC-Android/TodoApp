package edu.coltec.todoapp.bll;

import android.database.sqlite.SQLiteOpenHelper;

import edu.coltec.todoapp.dao.AppDB;
import edu.coltec.todoapp.dao.Task;
import edu.coltec.todoapp.dao.TaskDAO;

public class TaskBLL {

    private AppDB appDB;

    public TaskBLL(AppDB appDB) {
        this.appDB = appDB;
    }

    public boolean create(String name, String description) {
        TaskDAO taskDAO = new TaskDAO(this.appDB);
        Task newTask = new Task(name, description);

        if (!newTask.isValid())
            return false;

        Task task = taskDAO.getByName(newTask);
        if (task != null)
            return false;

        boolean result = taskDAO.create(newTask);
        return result;
    }

}

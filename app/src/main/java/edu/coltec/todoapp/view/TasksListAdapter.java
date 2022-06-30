package edu.coltec.todoapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import edu.coltec.todoapp.R;
import edu.coltec.todoapp.bll.TaskBLL;
import edu.coltec.todoapp.dao.AppDB;
import edu.coltec.todoapp.dao.Task;

public class TasksListAdapter extends BaseAdapter {

    private Context context;
    private List<Task> tasks;

    public TasksListAdapter(Context context) {
        AppDB appDB = new AppDB(context);
        TaskBLL taskBLL = new TaskBLL(appDB);

        this.context = context;
        this.tasks = taskBLL.getAll();
    }

    @Override
    public int getCount() {
        return this.tasks.size();
    }

    @Override
    public Object getItem(int position) {
        return this.tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.tasks.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Task task = this.tasks.get(position);

        View taskView = LayoutInflater.from(this.context).inflate(R.layout.adapter_task, parent, false);
        TextView taskName = taskView.findViewById(R.id.adp_task_name);
        TextView taskCategory = taskView.findViewById(R.id.adp_task_category);

        taskName.setText(task.getName());
        taskCategory.setText(task.getCategory().getName());

        return taskView;
    }

    public void updateList() {
        AppDB appDB = new AppDB(context);
        TaskBLL taskBLL = new TaskBLL(appDB);
        this.tasks = taskBLL.getAll();
    }
}

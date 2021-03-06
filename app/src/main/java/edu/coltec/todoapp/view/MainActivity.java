package edu.coltec.todoapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import edu.coltec.todoapp.R;
import edu.coltec.todoapp.bll.TaskBLL;
import edu.coltec.todoapp.dao.AppDB;
import edu.coltec.todoapp.dao.Task;

public class MainActivity extends AppCompatActivity {

    TasksListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView tasksList = this.findViewById(R.id.tasks_list);
        listAdapter = new TasksListAdapter(this);
        tasksList.setAdapter(listAdapter);

        Button btnAddTask = this.findViewById(R.id.btn_add_task);
        btnAddTask.setOnClickListener((view) -> {
            Intent newTaskIntent = new Intent(MainActivity.this, NewTaskActivity.class);
            startActivity(newTaskIntent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // força atualização da lista após cadastro de nova tarefa
        listAdapter.updateList();
        listAdapter.notifyDataSetChanged();
    }
}
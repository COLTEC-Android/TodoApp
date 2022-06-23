package edu.coltec.todoapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.coltec.todoapp.R;
import edu.coltec.todoapp.bll.TaskBLL;
import edu.coltec.todoapp.dao.AppDB;

public class NewTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        AppDB appDB = new AppDB(this);

        EditText txtTaskName = this.findViewById(R.id.txt_task_name);
        EditText txtTaskDescription = this.findViewById(R.id.txt_task_description);
        EditText txtTaskCategory = this.findViewById(R.id.txt_task_category);
        Button btnNewTask = this.findViewById(R.id.btn_new_task);

        btnNewTask.setOnClickListener((view) -> {
            String taskName = txtTaskName.getText().toString();
            String taskDescription = txtTaskDescription.getText().toString();
            String taskCategory = txtTaskCategory.getText().toString();

            TaskBLL taskBll = new TaskBLL(appDB);
            boolean wasCreated = taskBll.create(taskName, taskDescription, taskCategory);

            if (wasCreated) {
                Toast.makeText(this, "Tarefa cadastrada!", Toast.LENGTH_SHORT).show();
                this.finish();
            } else {
                Toast.makeText(this, "Erro no cadastro da tarefa", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
package edu.coltec.todoapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import edu.coltec.todoapp.R;

public class NewTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        EditText txtTaskName = this.findViewById(R.id.txt_task_name);
        EditText txtTaskDescription = this.findViewById(R.id.txt_task_description);
        Button btnNewTask = this.findViewById(R.id.btn_new_task);

        btnNewTask.setOnClickListener((view) -> {
            String taskName = txtTaskName.getText().toString();
            String taskDescription = txtTaskDescription.getText().toString();
        });
    }
}
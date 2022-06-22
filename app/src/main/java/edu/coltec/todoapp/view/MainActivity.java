package edu.coltec.todoapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import edu.coltec.todoapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAddTask = this.findViewById(R.id.btn_add_task);
        btnAddTask.setOnClickListener((view) -> {
            Intent newTaskIntent = new Intent(MainActivity.this, NewTaskActivity.class);
            startActivity(newTaskIntent);
        });
    }
}
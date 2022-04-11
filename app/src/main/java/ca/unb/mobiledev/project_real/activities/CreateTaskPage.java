package ca.unb.mobiledev.project_real.activities;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import ca.unb.mobiledev.project_real.MainActivity;
import ca.unb.mobiledev.project_real.R;
import ca.unb.mobiledev.project_real.model.Task;

public class CreateTaskPage extends AppCompatActivity {

    TextInputLayout textInputLayout;
    Button finishButton;
    Button cancelButton;
    Task task;
    String taskName;
    char category = ' ';

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    AppCompatActivity parentActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_task);

        parentActivity = (AppCompatActivity) this.getParent();

        sharedPreferences = getSharedPreferences("", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        textInputLayout = (TextInputLayout)findViewById(R.id.outlinedTextField);
        finishButton = (Button) findViewById(R.id.task_finish);
        cancelButton = (Button) findViewById(R.id.task_cancel);

        finishButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        taskName = textInputLayout.getEditText().getText().toString();
                        int id = sharedPreferences.getInt("taskCounter",0);
                        task = new Task(id,taskName, category);
                        updateTaskCounter();

                        Intent intent = new Intent(CreateTaskPage.this, TaskListActivity.class);
                        intent.putExtra("new task", task);
                        startActivity(intent);

                    }
                }
        );
    }

    public void updateTaskCounter(){
        int current = sharedPreferences.getInt("taskCounter",0);
        editor.putInt("taskCounter",current+1);
        editor.apply();
        Log.i("CreateTask", "New Counter val: "+ sharedPreferences.getInt("taskCounter",0));

    }

//    public void addTaskToSharedPreferences(Task newTask){
//        editor.put("taskCounter",current+1);
//        editor.apply();
//    }

    public void codingClick(View v)
    {
        Toast.makeText(this, "Coding", Toast.LENGTH_LONG).show();
        category = 'c';
    }

    public void workClick(View v)
    {
        Toast.makeText(this, "Coding", Toast.LENGTH_LONG).show();
        category = 'w';
    }

    public void wellnessClick(View v)
    {
        Toast.makeText(this, "wellness", Toast.LENGTH_LONG).show();
        category = 'h';
    }

    public void readingClick(View v)
    {
        Toast.makeText(this, "reading", Toast.LENGTH_LONG).show();
        category = 'r';
    }


    public void workOutClick(View v)
    {
        Toast.makeText(this, "workout", Toast.LENGTH_LONG).show();
        category = 'o';
    }

}
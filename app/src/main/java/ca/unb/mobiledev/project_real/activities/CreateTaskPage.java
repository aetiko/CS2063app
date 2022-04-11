package ca.unb.mobiledev.project_real.activities;
import android.content.Intent;
import android.os.Bundle;
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
    Task.Builder task;
    String taskName;
    char category = ' ';


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_task);

        textInputLayout = (TextInputLayout)findViewById(R.id.outlinedTextField);
        finishButton = (Button) findViewById(R.id.task_finish);
        cancelButton = (Button) findViewById(R.id.task_cancel);

        finishButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        taskName = textInputLayout.toString();
                        task = new Task.Builder(taskName, category);
                        Task newTask = task.build();


                        Intent intent = new Intent(CreateTaskPage.this, TaskListActivity.class);
                        intent.putExtra("new task", newTask);
                        startActivity(intent);

                    }
                }
        );
    }

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
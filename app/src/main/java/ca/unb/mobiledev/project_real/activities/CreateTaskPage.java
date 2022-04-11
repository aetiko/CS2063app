package ca.unb.mobiledev.project_real.activities;
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
    String taskID;
    String taskName;


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

                    }
                }
        );
    }



    public void codingClick(View v)
    {
        Toast.makeText(this, "Coding clicked on.", Toast.LENGTH_LONG).show();
    }

    public void workClick(View v)
    {
        Toast.makeText(this, "Work clicked on", Toast.LENGTH_LONG).show();
    }

    public void wellnessClick(View v)
    {
        Toast.makeText(this, "Wellness clicked on", Toast.LENGTH_LONG).show();
    }

    public void readingClick(View v)
    {
        Toast.makeText(this, "Reading Clicked on", Toast.LENGTH_LONG).show();
    }


    public void workOutClick(View v)
    {
        Toast.makeText(this, "Work out clicked on.", Toast.LENGTH_LONG).show();
    }

}
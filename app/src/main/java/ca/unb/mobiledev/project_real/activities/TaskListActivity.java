package ca.unb.mobiledev.project_real.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import ca.unb.mobiledev.project_real.R;
import ca.unb.mobiledev.project_real.adapters.TaskAdapter;
import ca.unb.mobiledev.project_real.model.Task;

public class TaskListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_list_page);


        ArrayList<Task> taskList = generateTasks();
        Log.i("Main Activity", taskList.get(0).getName().toString());

        TaskAdapter adapter = new TaskAdapter(taskList, this);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNavigationView);

        recyclerView.setAdapter(adapter);

    }

    public ArrayList<Task> generateTasks(){
        Task.Builder taskBuilder;
        Task taskOne;
        Task taskTwo;
        Task taskThree;
        Task taskFive;
        Task taskSix;
        Task taskSeven;
        Task taskEight;

        ArrayList<Task> list = new ArrayList<Task>();

        ImageView oneImage = new ImageView(this);
        oneImage.setImageResource(R.drawable.ic_vector);
        ImageView twoImage = new ImageView(this);
        twoImage.setImageResource(R.drawable.ic_vector);
        ImageView threeImage = new ImageView(this);
        threeImage.setImageResource(R.drawable.ic_vector);

        taskBuilder = new Task.Builder("1","Do CS2026 Project",oneImage,"workout" );
        taskOne = taskBuilder.build();
        taskBuilder = new Task.Builder("2","Finish Song",twoImage,"code" );
        taskTwo = taskBuilder.build();
        taskBuilder = new Task.Builder("3","Smoke Weed",threeImage,"work" );
        taskThree = taskBuilder.build();

        taskBuilder = new Task.Builder("3","Smoke Weed",threeImage,"read" );
        taskFive = taskBuilder.build();
        taskBuilder = new Task.Builder("3","Smoke Weed",threeImage,"health" );
        taskSix = taskBuilder.build();

        list.add(taskOne);
        list.add(taskTwo);
        list.add(taskThree);
        list.add(taskFive);
        list.add(taskSix);

        return list;
    }
}

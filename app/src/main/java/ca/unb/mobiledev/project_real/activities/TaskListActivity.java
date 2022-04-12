package ca.unb.mobiledev.project_real.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Locale;

import ca.unb.mobiledev.project_real.R;
import ca.unb.mobiledev.project_real.adapters.TaskAdapter;
import ca.unb.mobiledev.project_real.model.Task;

public class TaskListActivity extends AppCompatActivity {
    Parcelable state;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static ArrayList<Task> taskList;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    TextView currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_list_page);
        Log.i("TaskList", "in create");
        currentTime = (TextView)findViewById(R.id.current_act_time);


        sharedPreferences = getSharedPreferences("", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

//        editor.remove("courses");
//
//
//        editor.putInt("taskCounter", 0);
//        editor.apply();

        loadData();


        Task newTask = (Task)getIntent().getSerializableExtra("new task");

        if(newTask!=null){
            int latestTime = sharedPreferences.getInt("currentTime",0);
            int hours = latestTime / 3600;
            int minutes = (latestTime % 3600) / 60;
            int secs = latestTime % 60;

            // Format the seconds into hours, minutes,
            // and seconds.
            String time
                    = String
                    .format(Locale.getDefault(),
                            "%d:%02d:%02d", hours,
                            minutes, secs);
            currentTime.setText(time);
            currentTime.setTextSize(TypedValue.COMPLEX_UNIT_SP,37);

            Log.i("TaskList", "Category: "+newTask.getCategory()+"");
            Log.i("TaskList", "Id: "+newTask.getId()+"");
            Log.i("TaskList", "Name: "+newTask.getName()+"");
            taskList.add(newTask);
        }
        else{
            currentTime.setText("No Activity Started");
            currentTime.setTextSize(TypedValue.COMPLEX_UNIT_SP,23);

        }

        TaskAdapter adapter = new TaskAdapter(taskList, this, sharedPreferences);

        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        FloatingActionButton floatingActionButton = findViewById(R.id.add_task_btn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                Intent intent = new Intent(TaskListActivity.this, CreateTaskPage.class);
                startActivity(intent);

            }
        });

    }

    public static ArrayList<Task> getTaskList(){
        return taskList;
    }

//        @Override
//    protected void onRestoreInstanceState( Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//
//        if(savedInstanceState!=null){
//            Log.i("TaskList", "not null");
//        }
//        else{
//            Log.i("TaskList", "null");
//        }
//    }
//
//    @Override
//    protected void onSaveInstanceState(Bundle savedStateInstance) {
//        super.onSaveInstanceState(savedStateInstance);
//        Log.i("TaskList", "saving");
//        saveData(savedStateInstance);
//    }


    private void saveData() {
        // method for saving the data in array list.
        // creating a variable for storing data in
        // shared preferences.
        SharedPreferences sharedPreferences = getSharedPreferences("", MODE_PRIVATE);

        // creating a variable for editor to
        // store data in shared preferences.
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // creating a new variable for gson.
        Gson gson = new Gson();

        // getting data from gson and storing it in a string.
        String json = gson.toJson(taskList);

        // below line is to save data in shared
        // prefs in the form of string.
        editor.putString("courses", json);

        // below line is to apply changes
        // and save data in shared prefs.
        editor.apply();

        // after saving data we are displaying a toast message.
//        Toast.makeText(this, "Saved Array List to Shared preferences. ", Toast.LENGTH_SHORT).show();
    }

    private void loadData() {
        // method to load arraylist from shared prefs
        // initializing our shared prefs with name as
        // shared preferences.
        SharedPreferences sharedPreferences = getSharedPreferences("", MODE_PRIVATE);

        // creating a variable for gson.
        Gson gson = new Gson();

        // below line is to get to string present from our
        // shared prefs if not present setting it as null.
        String json = sharedPreferences.getString("courses", null);

        // below line is to get the type of our array list.
        Type type = new TypeToken<ArrayList<Task>>() {}.getType();

        // in below line we are getting data from gson
        // and saving it to our array list
        taskList = gson.fromJson(json, type);

        // checking below if the array list is empty or not
        if (taskList == null) {
            // if the array list is empty
            // creating a new array list.
            taskList = new ArrayList<>();
        }
    }


//
//   public ArrayList<Task> generateTasks(){
//        Task.Builder taskBuilder;
//        Task taskOne;
//        Task taskTwo;
//        Task taskThree;
//        Task taskFive;
//        Task taskSix;
//
//        ArrayList<Task> list = new ArrayList<Task>();
//
//        ImageView oneImage = new ImageView(this);
//        oneImage.setImageResource(R.drawable.ic_vector);
//        ImageView twoImage = new ImageView(this);
//        twoImage.setImageResource(R.drawable.ic_vector);
//        ImageView threeImage = new ImageView(this);
//        threeImage.setImageResource(R.drawable.ic_vector);
//
//        taskBuilder = new Task.Builder("Do CS2026 Project",'e' );
//        taskOne = taskBuilder.build();
//        taskBuilder = new Task.Builder("Finish Song",'c' );
//        taskTwo = taskBuilder.build();
//        taskBuilder = new Task.Builder("Smoke Weed",'w' );
//        taskThree = taskBuilder.build();
//
//        taskBuilder = new Task.Builder("Smoke Weed",'r' );
//        taskFive = taskBuilder.build();
//        taskBuilder = new Task.Builder("Smoke Weed",'h' );
//        taskSix = taskBuilder.build();
//
//        list.add(taskOne);
//        list.add(taskTwo);
//        list.add(taskThree);
//        list.add(taskFive);
//        list.add(taskSix);
//
//        return list;
//    }

    //    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        Parcelable listState = recyclerView.getLayoutManager().onSaveInstanceState();
//
//        for(int i = 0; i < )
//        // putting recyclerview position
//        outState.putParcelable("state", listState);
//        // putting recyclerview items
//        outState.putParcelableArrayList("list",taskList);
//        super.onSaveInstanceState(outState);
//    }
//    @Override
//    protected void onPause() {
//        super.onPause();
//        state = recyclerView.getLayoutManager().onSaveInstanceState();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        recyclerView.getLayoutManager().onRestoreInstanceState(state);
//    }
}

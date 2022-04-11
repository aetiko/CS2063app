package ca.unb.mobiledev.project_real.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;

import ca.unb.mobiledev.project_real.R;
import ca.unb.mobiledev.project_real.adapters.TaskAdapter;
import ca.unb.mobiledev.project_real.model.Task;

public class TaskListActivity extends AppCompatActivity {
    Parcelable state;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ArrayList<Task> taskList = new ArrayList<Task>();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private static Bundle mBundleRecyclerViewState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_list_page);
        Log.i("TaskList", "in create");


        sharedPreferences = getSharedPreferences("", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        TaskAdapter adapter = new TaskAdapter(taskList, this, sharedPreferences);

        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
//        editor.putInt("taskCounter", 0);
//        editor.apply();

        if(sharedPreferences.getInt("taskCounter", -1)> 0){
            Task newTask = (Task)getIntent().getParcelableExtra("new task");
            Log.i("TaskList", "Category: "+newTask.getCategory()+"");
            Log.i("TaskList", "Id: "+newTask.getId()+"");
            Log.i("TaskList", "Name: "+newTask.getName()+"");


            if(newTask!=null){
                taskList.add(newTask);
            }
        }
        else{
            Log.i("TaskList", "Null saved state: ");
            editor.putInt("taskCounter", 0);
        }

        FloatingActionButton floatingActionButton = findViewById(R.id.add_task_btn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaskListActivity.this, CreateTaskPage.class);
                startActivity(intent);

            }
        });

    }

//    @Override
//    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
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
    @Override
    protected void onSaveInstanceState(Bundle savedStateInstance) {
        super.onSaveInstanceState(savedStateInstance);
        Log.i("TaskList", "saving");
        saveData(savedStateInstance);
    }

    public void saveData(Bundle savedStateInstance){
        super.onSaveInstanceState(savedStateInstance);
        Log.i("TaskList", "saving");
        if(!taskList.isEmpty()){
            for(int i = 0; i <  sharedPreferences.getInt("taskCounter",0);i++){
                Task task = taskList.get(i);
                String json = Gson().toJson(task);
                editor.putString(""+i, json).apply();
                savedStateInstance.putSerializable(""+i,task);
            }
        }
    }

//    public void getData(Bundle savedStateInstance){
//        taskList.clear();
//        for(int i = 0; i <  sharedPreferences.getInt("taskCounter",0);i++){
//            Gson gson = new Gson();
//            String json = sharedPreferences.getString(""+i, "");
//            Task newTask = (Task)gson.fromJson(json, Task.class);
//            taskList.add(newTask);
//        }
//    }
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
    @Override
    protected void onPause() {
        super.onPause();
        state = recyclerView.getLayoutManager().onSaveInstanceState();
    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerView.getLayoutManager().onRestoreInstanceState(state);
    }
}

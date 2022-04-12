package ca.unb.mobiledev.project_real.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Locale;
import android.widget.TextView;

import android.app.Activity;

import com.google.gson.Gson;

import ca.unb.mobiledev.project_real.R;
import ca.unb.mobiledev.project_real.model.Task;

public class TimerActivity extends Activity {

    // Use seconds, running and wasRunning respectively
    // to record the number of seconds passed,
    // whether the stopwatch is running and
    // whether the stopwatch was running
    // before the activity was paused.

    // Number of seconds displayed
    // on the stopwatch.
    private int seconds = 0;
    private Task task;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    ArrayList<Task> taskList;

    // Is the stopwatch running?
    private boolean running;

    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        running = true;
        setContentView(R.layout.timer_layout);
        sharedPreferences = getSharedPreferences("",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        task = (Task)this.getIntent().getSerializableExtra("task");
        seconds = sharedPreferences.getInt(task.getId()+"",0);

//        if (savedInstanceState != null) {
//
//            // Get the previous state of the stopwatch
//            // if the activity has been
//            // destroyed and recreated.
//            seconds
//                    = savedInstanceState
//                    .getInt("seconds");
//            running
//                    = savedInstanceState
//                    .getBoolean("running");
//            wasRunning
//                    = savedInstanceState
//                    .getBoolean("wasRunning");
//        }
        runTimer();
    }

    // Save the state of the stopwatch
    // if it's about to be destroyed.
//    @Override
//    public void onSaveInstanceState(
//            Bundle savedInstanceState)
//    {
//        savedInstanceState
//                .putInt("seconds", seconds);
//        savedInstanceState
//                .putBoolean("running", running);
//        savedInstanceState
//                .putBoolean("wasRunning", wasRunning);
//    }
//
//    // If the activity is paused,
//    // stop the stopwatch.
    @Override
    protected void onPause()
    {
        super.onPause();
        Log.i("Timer", "on pause");
        wasRunning = true;
        running = false;
    }
//
//    // If the activity is resumed,
//    // start the stopwatch
//    // again if it was running previously.
    @Override
    protected void onResume()
    {
        super.onResume();
        if (wasRunning) {
            Log.i("Timer", "on resume was running");
            running = true;
            wasRunning = false;
        }
    }

    // Start the stopwatch running
    // when the Start button is clicked.
    // Below method gets called
    // when the Start button is clicked.
    public void onClickStart(View view)
    {
        running = true;
    }

    // Stop the stopwatch running
    // when the Stop button is clicked.
    // Below method gets called
    // when the Stop button is clicked.
    public void onClickStop(View view)
    {
        running = false;
//        TaskListActivity.removeTask(task);
        saveData();
        finish();
    }

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

        editor.putInt("currentTime", seconds);

        // below line is to apply changes
        // and save data in shared prefs.
        editor.apply();

        // after saving data we are displaying a toast message.
//        Toast.makeText(this, "Saved Array List to Shared preferences. ", Toast.LENGTH_SHORT).show();
    }

    // Reset the stopwatch when
    // the Reset button is clicked.
    // Below method gets called
    // when the Reset button is clicked.
    public void onClickReset(View view)
    {
        running = false;
        seconds = 0;
    }

    // Sets the NUmber of seconds on the timer.
    // The runTimer() method uses a Handler
    // to increment the seconds and
    // update the text view.
    private void runTimer()
    {

        // Get the text view.
        final TextView timeView
                = (TextView)findViewById(
                R.id.time_view);

        // Creates a new Handler
        final Handler handler
                = new Handler();

        // Call the post() method,
        // passing in a new Runnable.
        // The post() method processes
        // code without a delay,
        // so the code in the Runnable
        // will run almost immediately.
        handler.post(new Runnable() {
            @Override

            public void run()
            {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                // Format the seconds into hours, minutes,
                // and seconds.
                String time
                        = String
                        .format(Locale.getDefault(),
                                "%d:%02d:%02d", hours,
                                minutes, secs);

                // Set the text view text.
                timeView.setText(time);

                // If running is true, increment the
                // seconds variable.
                if(running){
                    seconds++;
                    task.setSeconds(seconds);
                    editor.putInt(task.getId()+"", seconds);
                    editor.apply();
                }


                // Post the code again
                // with a delay of 1 second.
                handler.postDelayed(this, 1000);
            }
        });
    }
}

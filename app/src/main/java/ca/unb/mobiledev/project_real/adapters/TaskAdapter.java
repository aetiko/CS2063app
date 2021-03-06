package ca.unb.mobiledev.project_real.adapters;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

import ca.unb.mobiledev.project_real.R;
import ca.unb.mobiledev.project_real.activities.TimerActivity;
import ca.unb.mobiledev.project_real.model.Task;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private final static String TAG = "My Adapter";
    private final ArrayList<Task> mDataset;
    private final Activity parentActivity;
    private final SharedPreferences sharedPreferences;

    public TaskAdapter(ArrayList<Task> myDataset, Activity parentActivity, SharedPreferences sharedPreferences) {
        mDataset = myDataset;
        this.parentActivity = parentActivity;
        this.sharedPreferences = sharedPreferences;
    }

    // ViewHolder represents an individual item to display. In this case
    // it will just be a single TextView (displaying the title of a course)
    // but RecyclerView gives us the flexibility to do more complex things
    // (e.g., display an image and some text).
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView view;
        public TextView taskName;
        public TextView taskTimer;
        public ImageView icon;
        public ImageView pill;

        public ViewHolder(CardView v) {
            super(v);
            view = v;
            taskName = (TextView)v.findViewById(R.id.task_name);
            icon = (ImageView)v.findViewById(R.id.category_icon);
            pill = (ImageView)v.findViewById(R.id.pill);
            taskTimer = (TextView)v.findViewById(R.id.taskTime);

        }
    }

    // The inflate method of the LayoutInflater class can be used to obtain the
    // View object corresponding to an XML layout resource file. Here
    // onCreateViewHolder inflates the TextView corresponding to item_layout.xml
    // and uses it to instantiate a ViewHolder.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item_layout, parent, false);

        return new ViewHolder(v);
    }

    // onBindViewHolder binds a ViewHolder to the data at the specified
    // position in mDataset
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Task task = mDataset.get(position);
        holder.taskName.setText(task.getName());

        //time formatting
        int latestTime = task.getSeconds();
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
        holder.taskTimer.setText(time);

        switch (task.getCategory()){
            case 'w':
                holder.icon.setBackgroundResource(R.drawable.ic_monitor);
                holder.icon.setImageResource(R.drawable.ic_monitor);
                holder.pill.setImageResource(R.drawable.ic_work_pill);
                Log.i("Adapter","w");
                break;
            case 'r':
                holder.icon.setBackgroundResource(R.drawable.ic_book);
                holder.icon.setImageResource(R.drawable.ic_book);
                holder.pill.setImageResource(R.drawable.ic_reading_pill);
                Log.i("Adapter","r");
                break;
            case 'c':
                holder.icon.setBackgroundResource(R.drawable.ic_code);
                holder.icon.setImageResource(R.drawable.ic_code);
                holder.pill.setImageResource(R.drawable.ic_coding_pill);
                Log.i("Adapter","c");
                break;
            case 'h':
                holder.icon.setBackgroundResource(R.drawable.ic_heart);
                holder.icon.setImageResource(R.drawable.ic_heart);
                holder.pill.setImageResource(R.drawable.ic_wellness_pill);
                Log.i("Adapter","h");
                break;
            case 'e':
                holder.icon.setBackgroundResource(R.drawable.ic_barbell);
                holder.icon.setImageResource(R.drawable.ic_barbell);
                holder.pill.setImageResource(R.drawable.ic_workout_pill);
                Log.i("Adapter","e");
                break;
            default:
                Log.i("Adapter","default");
                break;
        }
//        holder.mTextView.setText(task.getName());
//        holder.mTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent = new Intent(parentActivity, DetailActivity.class);
////                intent.putExtra("Icon", task.getIcon());
////                intent.putExtra("Title", task.getName());
////                parentActivity.startActivity(intent);
//            }
//        });

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(parentActivity, TimerActivity.class);
                intent.putExtra("seconds", task.getSeconds());
                intent.putExtra("task", task);
                parentActivity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

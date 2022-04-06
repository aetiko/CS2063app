package ca.unb.mobiledev.project_real.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import ca.unb.mobiledev.project_real.MainActivity;

public class CreateTaskPage extends AppCompatActivity {

    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(CreateTaskPage.this, MainActivity.class));
                finish();
            }
        }, 5000);
    }
}

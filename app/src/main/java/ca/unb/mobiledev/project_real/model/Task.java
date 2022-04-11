package ca.unb.mobiledev.project_real.model;

import android.widget.ImageView;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Task implements Serializable {

    private final String name;
    private final int id;
    private final char category;

    public Task (int id, String name, char category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    // Only need to include getters
    public String getName() {
        return name;
    }


    public char getCategory() {
        return category;
    }

    public int getId() {
        return id;
    }
}


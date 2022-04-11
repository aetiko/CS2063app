package ca.unb.mobiledev.project_real.model;

import android.widget.ImageView;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Task implements Serializable {

    private final String name;
    private final char category;

    private Task (Builder builder) {
        this.name = builder.name;
        this.category = builder.category;
    }

    // Only need to include getters
    public String getName() {
        return name;
    }


    public char getCategory() {
        return category;
    }

    public static class Builder {
        private final String name;
        private final char category;

        public Builder(@NonNull String name, @NonNull char category) {
            this.name = name;
            this.category = category;
        }

        public Task build() {
            return new Task(this);
        }
    }
}


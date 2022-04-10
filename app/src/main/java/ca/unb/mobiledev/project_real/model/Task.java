package ca.unb.mobiledev.project_real.model;

import android.widget.ImageView;

import androidx.annotation.NonNull;

public class Task {

    private final String id;
    private final String name;
    private final String category;
    private final ImageView icon;

    private Task (Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.icon = builder.description;
        this.category = builder.category;
    }

    // Only need to include getters
    public String getName() {
        return name;
    }

    public ImageView getIcon() {
        return icon;
    }

    public static class Builder {
        private final String id;
        private final String name;
        private final ImageView description;
        private final String category;

        public Builder(@NonNull String id, @NonNull String name, @NonNull ImageView description, @NonNull String category) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.category = category;
        }

        public Task build() {
            return new Task(this);
        }
    }
}


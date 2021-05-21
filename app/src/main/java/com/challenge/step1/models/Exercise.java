package com.challenge.step1.models;

import java.util.Date;

public class Exercise {
    private String name;
    private String exerciseId;
    private int practicedAtBpm;

    public Exercise() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(String exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getPracticedAtBpm() {
        return practicedAtBpm;
    }

    public void setPracticedAtBpm(int practicedAtBpm) {
        this.practicedAtBpm = practicedAtBpm;
    }
}

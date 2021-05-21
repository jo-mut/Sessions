package com.challenge.step1.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Session {
    private String name;
    private String practicedOnDate;
    private List<Exercise> exercises = new ArrayList<>();


    public Session() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPracticedOnDate() {
        return practicedOnDate;
    }

    public void setPracticedOnDate(String practicedOnDate) {
        this.practicedOnDate = practicedOnDate;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }


}

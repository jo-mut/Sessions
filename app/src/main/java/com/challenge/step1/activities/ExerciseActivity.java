package com.challenge.step1.activities;

import android.os.Bundle;

import com.challenge.step1.LoadData;
import com.challenge.step1.adapters.ExercisesAdapter;
import com.challenge.step1.adapters.SessionsAdapter;
import com.challenge.step1.interfaces.LoadListener;
import com.challenge.step1.models.Exercise;
import com.challenge.step1.models.Session;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.challenge.step1.R;

import java.util.ArrayList;
import java.util.List;

public class ExerciseActivity extends AppCompatActivity {
    private RecyclerView mExercisesRecyclerView;
    private ExercisesAdapter mExercisesAdapter;
    private Toolbar mToolbar;
    private List<Exercise> mExercises = new ArrayList<>();
    private String mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        //support action bar
        setSupportActionBar(mToolbar);
        mExercisesRecyclerView = findViewById(R.id.exercisesRecyclerView);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.exercises);
        }

        if (getIntent().getExtras() != null) {
            mPosition = getIntent().getStringExtra("position");
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        // initialise load payments methods async task
        if (mExercises != null) {
            mExercises.clear();
        }

        mExercises = MainActivity.mSessions.get(Integer.parseInt(mPosition)).getExercises();
        inflateRecyclerView();
    }

    /**
     * populate the recycler view with payments methods
     * */
    private void inflateRecyclerView() {
        mExercisesAdapter = new ExercisesAdapter(this, mExercises);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mExercisesRecyclerView.setLayoutManager(linearLayoutManager);
        mExercisesRecyclerView.setHasFixedSize(true);
        mExercisesRecyclerView.setAdapter(mExercisesAdapter);
    }

}
package com.challenge.step1.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.challenge.step1.R;
import com.challenge.step1.models.Exercise;
import com.challenge.step1.models.Session;
import com.challenge.step1.view_holders.ExerciseViewHolder;
import com.challenge.step1.view_holders.SessionsViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ExercisesAdapter extends RecyclerView.Adapter<ExerciseViewHolder> {
    private Context mContext;
    private List<Exercise> mExercises = new ArrayList<>();

    public ExercisesAdapter(Context mContext, List<Exercise> exercises) {
        this.mContext = mContext;
        this.mExercises = exercises;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_excercise_item, parent, false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        Exercise exercise = mExercises.get(position);
        if (exercise != null) {
            holder.mExerciseNameTextView.setText(exercise.getName());
            holder.mPracticedTextView.setText(exercise.getPracticedAtBpm() + "");
        }

    }

    @Override
    public int getItemCount() {
        return mExercises.size();
    }
}

package com.challenge.step1.view_holders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.challenge.step1.R;

public class ExerciseViewHolder extends RecyclerView.ViewHolder {
    private Context mContext;
    private View mView;
    public TextView mExerciseNameTextView;
    public TextView mPracticedTextView;


    public ExerciseViewHolder(@NonNull View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        mExerciseNameTextView = mView.findViewById(R.id.exerciseNameTextView);
        mPracticedTextView = mView.findViewById(R.id.practicedTextView);

    }
}

package com.challenge.step1.view_holders;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.challenge.step1.R;

public class SessionsViewHolder extends RecyclerView.ViewHolder {
    public Context mContext;
    public View mView;
    public TextView mSessionNameTextView;
    public TextView mDatePracticedTextView;
    public TextView mExercisesTextView;
    public Button mViewButton;

    public SessionsViewHolder(@NonNull View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        mSessionNameTextView = mView.findViewById(R.id.sessionNameTextView);
        mDatePracticedTextView = mView.findViewById(R.id.datePracticedTextView);
        mExercisesTextView = mView.findViewById(R.id.exercisesTextView);
        mViewButton = mView.findViewById(R.id.viewButton);

    }
}

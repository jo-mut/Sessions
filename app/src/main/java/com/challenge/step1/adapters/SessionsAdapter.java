package com.challenge.step1.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.challenge.step1.R;
import com.challenge.step1.activities.ExerciseActivity;
import com.challenge.step1.models.Session;
import com.challenge.step1.view_holders.SessionsViewHolder;

import java.util.ArrayList;
import java.util.List;

public class SessionsAdapter extends RecyclerView.Adapter<SessionsViewHolder> {
    private Context mContext;
    private List<Session> mSessions = new ArrayList<>();

    public SessionsAdapter(Context mContext, List<Session> sessions) {
        this.mContext = mContext;
        this.mSessions = sessions;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public SessionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_session_item, parent, false);
        return new SessionsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SessionsViewHolder holder, int position) {
        Session session = mSessions.get(position);
        if (session != null) {
            holder.mSessionNameTextView.setText(session.getName());
            holder.mDatePracticedTextView.setText(session.getPracticedOnDate());
            holder.mExercisesTextView.setText("Exercises: " + session.getExercises().size());

            holder.mViewButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ExerciseActivity.class);
                    intent.putExtra("position", String.valueOf(position));
                    mContext.startActivity(intent);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mSessions.size();
    }
}

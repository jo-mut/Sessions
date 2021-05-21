package com.challenge.step1.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.challenge.step1.LoadData;
import com.challenge.step1.R;
import com.challenge.step1.adapters.SessionsAdapter;
import com.challenge.step1.interfaces.LoadListener;
import com.challenge.step1.models.Session;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static LoadListener mLoadListener;
    private RecyclerView mSessionsRecyclerView;
    private SessionsAdapter mSessionsAdapter;
    private Toolbar mToolbar;
    public static List<Session> mSessions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = findViewById(R.id.toolbar);
        //support action bar
        setSupportActionBar(mToolbar);
        mSessionsRecyclerView = findViewById(R.id.sessionsRecyclerView);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.sessions);
        }

        // initialise load payments listener
        mLoadListener = LoadData.setLoadListener(new LoadListener() {
            @Override
            public void load(List<Session> sessionList) {
                mSessions = sessionList;
                if (mSessions != null) {
                    inflateRecyclerView();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // initialise load payments methods async task
        if (mSessions != null) {
            mSessions.clear();
        }

        new LoadData.LoadDataAsyncTask(this).execute();
    }

    /**
     * populate the recycler view with payments methods
     * */
    private void inflateRecyclerView() {
        mSessionsAdapter = new SessionsAdapter(this, mSessions);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mSessionsRecyclerView.setLayoutManager(linearLayoutManager);
        mSessionsRecyclerView.setHasFixedSize(true);
        mSessionsRecyclerView.setAdapter(mSessionsAdapter);
    }

}
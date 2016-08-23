package com.thedeveloperworldisyours.sqlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private RateDataSource mDataSource;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private List<Rate> mValues;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDataSource = new RateDataSource(this);
        mDataSource.open();

        mValues = mDataSource.getAllRates();

        mRecyclerView = (RecyclerView) findViewById(R.id.activity_main_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new CustomRecyclerViewAdapter(mValues);
        mRecyclerView.setAdapter(mAdapter);
    }

    // Will be called via the onClick attribute
    // of the buttons in main.xml
    public void onClick(View view) {
        @SuppressWarnings("unchecked")

        Rate Rate = null;
        switch (view.getId()) {
            case R.id.add:
                String[] rates = new String[] { "jPY", "aUD", "bGN" };
                int nextInt = new Random().nextInt(3);
                Random randomno = new Random();
                // save the new Rate to the database
                Rate = mDataSource.createRate(rates[nextInt], randomno.nextDouble());
                mValues.add(Rate);
                break;
            case R.id.delete:
                if (mValues.size() > 0) {
                    Rate = (Rate) mValues.get(0);
                    mDataSource.deleteRate(Rate);
                    mValues.remove(Rate);
                }
                break;
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        mDataSource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mDataSource.close();
        super.onPause();
    }

}

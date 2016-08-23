package com.thedeveloperworldisyours.sqlite;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private RateDataSource datasource;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private List<Rate> mValues;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datasource = new RateDataSource(this);
        datasource.open();

        mValues = datasource.getAllRates();

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
                Rate = datasource.createRate(rates[nextInt], randomno.nextDouble());
                mValues.add(Rate);
                break;
            case R.id.delete:
                if (mValues.size() > 0) {
                    Rate = (Rate) mValues.get(0);
                    datasource.deleteRate(Rate);
                    mValues.remove(Rate);
                }
                break;
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }

}

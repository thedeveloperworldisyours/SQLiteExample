package com.thedeveloperworldisyours.sqlite;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by javierg on 23/08/16.
 */
public class CustomRecyclerViewAdapter extends RecyclerView
        .Adapter<CustomRecyclerViewAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private List<Rate> mDataSet;
    private static MyClickListener mMyClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView mLabel;
        TextView mDateTime;

        public DataObjectHolder(View itemView) {
            super(itemView);
            mLabel = (TextView) itemView.findViewById(R.id.item_list_view_text_view);
            mDateTime = (TextView) itemView.findViewById(R.id.item_list_view_text_view_two);
            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            mMyClickListener.onItemClick(getPosition(), v);
        }

    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.mMyClickListener = myClickListener;
    }

    public CustomRecyclerViewAdapter(List<Rate> myDataset) {
        mDataSet = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder viewHolder, int position) {
        viewHolder.mLabel.setText(mDataSet.get(position).getCoin());
        viewHolder.mDateTime.setText(String.valueOf(mDataSet.get(position).getValue()));
    }

    public void addItem(Rate dataObj, int index) {
        mDataSet.add(dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataSet.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}

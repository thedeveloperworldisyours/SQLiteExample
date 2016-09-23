package com.thedeveloperworldisyours.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by javierg on 23/08/16.
 */
public class RateDataSource {
    // Database fields
    private SQLiteDatabase mDatabase;
    private MySQLiteHelper mDbHelper;
    private String[] mAllColumns = {MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_COIN, MySQLiteHelper.COLUMN_VALUE};

    public RateDataSource(Context context) {
        mDbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }

    public Rate createRate(String coin, double value) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_COIN, coin);
        values.put(MySQLiteHelper.COLUMN_VALUE, value);
        long insertId = mDatabase.insert(MySQLiteHelper.TABLE_RATE, null,
                values);
        Cursor cursor = mDatabase.query(MySQLiteHelper.TABLE_RATE,
                mAllColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Rate newComment = cursorToRate(cursor);
        cursor.close();
        return newComment;
    }

    public void deleteRate(Rate comment) {
        long id = comment.getId();
        System.out.println("Rate deleted with id: " + id);
        mDatabase.delete(MySQLiteHelper.TABLE_RATE, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Rate> getAllRates() {
        List<Rate> rates = new ArrayList<Rate>();

        Cursor cursor = mDatabase.query(MySQLiteHelper.TABLE_RATE,
                mAllColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Rate rate = cursorToRate(cursor);
            rates.add(rate);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return rates;
    }

    private Rate cursorToRate(Cursor cursor) {
        Rate rate = new Rate();
        rate.setId(cursor.getLong(0));
        rate.setCoin(cursor.getString(1));
        rate.setValue(cursor.getDouble(2));
        return rate;
    }

    public void deleteAll() {

        mDatabase.delete(MySQLiteHelper.TABLE_RATE, null, null);
    }
}

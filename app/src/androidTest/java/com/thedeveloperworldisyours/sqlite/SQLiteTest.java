package com.thedeveloperworldisyours.sqlite;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by javierg on 07/09/16.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class SQLiteTest {

    private RateDataSource mDataSource;

    @Before
    public void setUp(){
        mDataSource = new RateDataSource(InstrumentationRegistry.getTargetContext());
        mDataSource.open();
    }

    @After
    public void finish() {
        mDataSource.close();
    }

    @Test
    public void testPreConditions() {
        assertNotNull(mDataSource);
    }

    @Test
    public void testShouldAddExpenseType() throws Exception {
        mDataSource.createRate("AUD", 1.2);
        List<Rate> rate = mDataSource.getAllRates();

        assertThat(rate.size(), is(1));
        assertTrue(rate.get(0).toString().equals("AUD"));
        assertTrue(rate.get(0).getValue().equals(1.2));
    }

    @Test
    public void testDeleteAll() {
        mDataSource.deleteAll();
        List<Rate> rate = mDataSource.getAllRates();

        assertThat(rate.size(), is(0));
    }

    @Test
    public void testDeleteOnlyOne() {
        mDataSource.createRate("AUD", 1.2);
        List<Rate> rate = mDataSource.getAllRates();

        assertThat(rate.size(), is(1));

        mDataSource.deleteRate(rate.get(0));
        rate = mDataSource.getAllRates();

        assertThat(rate.size(), is(0));
    }

    @Test
    public void testAddAndDelete() {
        mDataSource.deleteAll();
        mDataSource.createRate("AUD", 1.2);
        mDataSource.createRate("JPY", 1.993);
        mDataSource.createRate("BGN", 1.66);

        List<Rate> rate = mDataSource.getAllRates();
        assertThat(rate.size(), is(3));

        mDataSource.deleteRate(rate.get(0));
        mDataSource.deleteRate(rate.get(1));

        rate = mDataSource.getAllRates();
        assertThat(rate.size(), is(1));
    }
}

package com.thedeveloperworldisyours.sqlite;

import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by javierg on 26/09/2016.
 */

public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testAddedCurrency() {
        onView(withId(R.id.activity_main_add)).perform(click());

        SystemClock.sleep(1500);
        // Match the text in an item below the fold and check that it's displayed.

        String itemElementTextjPY = "JPY";
        onView(withText(itemElementTextjPY)).check(matches(isDisplayed()));

        String itemElementTextbGN = "BGN";
        onView(withText(itemElementTextbGN)).check(matches(isDisplayed()));

    }

}

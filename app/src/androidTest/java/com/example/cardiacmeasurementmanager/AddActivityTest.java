package com.example.cardiacmeasurementmanager;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.os.SystemClock;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
@LargeTest


public class AddActivityTest {

    @Rule
    //public ActivityScenarioRule<AddDetails> addActivityActivityScenarioRule = new ActivityScenarioRule<AddDetails>(AddDetails.class);

    @Test
    public void testInput() {


        onView(withId(R.id.dateValue)).perform(ViewActions.typeText("12.10.2021"));
        SystemClock.sleep(1000);
        onView(withId(R.id.timeValue)).perform(ViewActions.typeText("10.19"));
        SystemClock.sleep(1000);
        onView(withId(R.id.systolicValue)).perform(ViewActions.typeText("320"));
        SystemClock.sleep(1000);
        onView(withId(R.id.diastolicValue)).perform(ViewActions.typeText("210"));
        pressBack();
        SystemClock.sleep(1000);
        onView(withId(R.id.heartRateValue)).perform(ViewActions.typeText("151"));
        pressBack();
        SystemClock.sleep(1000);
        onView(withId(R.id.commentValue)).perform(ViewActions.typeText("UI test inserted data validation"));
        pressBack();
        SystemClock.sleep(1000);
        onView(withId(R.id.saveButton)).perform(click());
        SystemClock.sleep(1000);

        onView(withId(R.id.systolicValue)).check(matches(hasErrorText("Invalid data input")));
        SystemClock.sleep(1000);
        onView(withId(R.id.systolicValue)).perform(ViewActions.clearText());
        SystemClock.sleep(1000);
        onView(withId(R.id.systolicValue)).perform(ViewActions.typeText("120"));
        pressBack();
        SystemClock.sleep(1000);
        onView(withId(R.id.saveButton)).perform(click());
        SystemClock.sleep(1000);

        onView(withId(R.id.dateValue)).check(matches(hasErrorText("Please input in 'dd/mm/yyyy' format")));
        SystemClock.sleep(1000);
        onView(withId(R.id.dateValue)).perform(ViewActions.clearText());
        SystemClock.sleep(1000);
        onView(withId(R.id.dateValue)).perform(ViewActions.typeText("12/10/2021"));
        pressBack();
        SystemClock.sleep(1000);
        onView(withId(R.id.saveButton)).perform(click());
        SystemClock.sleep(1000);

        onView(withId(R.id.timeValue)).check(matches(hasErrorText("Please input in 'hh:mm' format")));
        SystemClock.sleep(1000);
        onView(withId(R.id.timeValue)).perform(ViewActions.clearText());
        SystemClock.sleep(1000);
        onView(withId(R.id.timeValue)).perform(ViewActions.typeText("10:19"));
        pressBack();
        SystemClock.sleep(1000);
        onView(withId(R.id.saveButton)).perform(click());
        SystemClock.sleep(1000);

        //onView(withId(R.id.aSystolicValue)).perform(ViewActions.clearText());

        onView(withId(R.id.diastolicValue)).check(matches(hasErrorText("Invalid data input")));
        SystemClock.sleep(1000);
        onView(withId(R.id.diastolicValue)).perform(ViewActions.clearText());
        SystemClock.sleep(1000);
        onView(withId(R.id.diastolicValue)).perform(ViewActions.typeText("80"));
        pressBack();
        SystemClock.sleep(1000);
        onView(withId(R.id.saveButton)).perform(click());
        SystemClock.sleep(1000);

        onView(withId(R.id.heartRateValue)).check(matches(hasErrorText("Invalid data input")));
        SystemClock.sleep(1000);
        onView(withId(R.id.heartRateValue)).perform(ViewActions.clearText());
        SystemClock.sleep(1000);
        onView(withId(R.id.heartRateValue)).perform(ViewActions.typeText("70"));
        pressBack();
        SystemClock.sleep(1000);

    }

}
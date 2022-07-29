package com.example.cardiacmeasurementmanager;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
@LargeTest

public class SplashScreenTest {

    @Rule
    public ActivityScenarioRule<SplashScreen> startingActivityActivityScenarioRule = new ActivityScenarioRule<SplashScreen>(SplashScreen.class);

    @Test
    public void testSplashScreen() {
        onView(withId(R.id.ic_logo)).check(matches(isDisplayed()));
        onView(withText("Cardiac Measurement Recorder")).check(matches(isDisplayed()));
    }

}

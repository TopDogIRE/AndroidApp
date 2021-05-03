package com.example.adapp;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {


    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<MainActivity>(MainActivity.class);
    private Date PickerActions;

    @Test
    public void canEnterNameAndSignUp() throws InterruptedException {
        onView(withId(R.id.name)).perform(typeText("Cathal Mullen"));
        onView(withId(R.id.email)).perform(typeText("cathal777@gmail.com"));
        onView(withId(R.id.username)).perform(typeText("cathal777"));
        onView(withId(R.id.btPickDate)).perform(scrollTo(),(click()));

        onView(withText("OK")).perform(click());
        onView(withId(R.id.submitButton)).perform(scrollTo(), (click()));
        onView(withId(R.id.name)).check(matches(withText("Cathal Mullen")));

    }


    @Test
    public void testDataOrChange() {
        onView(withId(R.id.name)).perform(typeText("Cathal Mullen"));
        onView(withId(R.id.email)).perform(typeText("cathal777@gmail.com"));
        onView(withId(R.id.username)).perform(typeText("cathal777"));

        onView(withId(R.id.name)).check(matches(withText("Cathal Mullen")));
        onView(withId(R.id.username)).check(matches(withText("cathal777")));
        onView(withId(R.id.email)).check(matches(withText("cathal777@gmail.com")));
    }

    @Test
    public void testValidEmail() {
        onView(withId(R.id.email)).perform(typeText("cathal777"));
        onView(withId(R.id.btPickDate)).perform(scrollTo(),(click()));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.submitButton)).perform(scrollTo(), (click()));
        onView(allOf(withId(R.id.email), hasErrorText("Email not valid!")));
    }

    @Test
    public void testEmailNotBlank() {
        onView(withId(R.id.email)).perform(typeText(""));
        onView(withId(R.id.btPickDate)).perform(scrollTo(),(click()));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.submitButton)).perform(scrollTo(), (click()));
        onView(allOf(withId(R.id.email), hasErrorText("Email not valid!")));
    }


    @Test
    public void testUsernameNotBlank() {
        onView(withId(R.id.name)).perform(typeText("Cathal"));
        onView(withId(R.id.email)).perform(typeText("cathal777@gmail.com"));
        onView(withId(R.id.username)).perform(typeText(""));

        onView(withId(R.id.btPickDate)).perform(scrollTo(),(click()));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.submitButton)).perform(scrollTo(), click());
        onView(allOf(withId(R.id.username), hasErrorText("Cannot Be Blank!")));

    }

}
package com.example.adapp;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {


    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void canEnterNameAndSignUp() throws InterruptedException {
        onView(withId(R.id.name)).perform(typeText("Cathal Mullen"));
        onView(withId(R.id.email)).perform(typeText("cathal777@gmail.com"));
        onView(withId(R.id.username)).perform(typeText("cathal777"));
        onView(withId(R.id.bio)).perform(typeText("I'm Irish"));
        onView(withId(R.id.occupation)).perform(typeText("Software Developer"));

        onView(withId(R.id.btPickDate)).perform(scrollTo(),(click()));

        onView(withClassName(Matchers.equalTo(android.widget.DatePicker.class.getName()))).perform(PickerActions.setDate(1999 , 6, 28));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.button2)).perform(scrollTo(), (click()));

        onView(allOf(withId(R.id.profile))).check(matches(withText("Your Profile!")));
        onView(allOf(withId(R.id.name))).check(matches(withText("Cathal Mullen")));
        onView(allOf(withId(R.id.bio))).check(matches(withText("I'm Irish")));
        onView(allOf(withId(R.id.occupation))).check(matches(withText("Software Developer")));
        onView(allOf(withId(R.id.age))).check((matches(withText("21 Years Old."))));

    }


    @Test
    public void dataResistOrientationChange() {
        onView(withId(R.id.name)).perform(typeText("Cathal Mullen"));
        onView(withId(R.id.email)).perform(typeText("cathal777@gmail.com"));
        onView(withId(R.id.username)).perform(typeText("cathal777"));
        onView(withId(R.id.bio)).perform(typeText("I'm Irish"));
        onView(withId(R.id.occupation)).perform(typeText("Software Developer"));

        TestUtils.rotateScreen(TestUtils.getActivity(activityScenarioRule));


        onView(withId(R.id.name)).check(matches(withText("Cathal Mullen")));
        onView(withId(R.id.bio)).check(matches(withText("I'm Irish")));
        onView(withId(R.id.occupation)).check(matches(withText("Software Developer")));
        onView(withId(R.id.username)).check(matches(withText("cathal777")));
        onView(withId(R.id.email)).check(matches(withText("cathal777@gmail.com")));
    }

    @Test
    public void checkValidEmail() {
        onView(withId(R.id.email)).perform(typeText("cathal777"));

        onView(withId(R.id.btPickDate)).perform(scrollTo(),(click()));

        onView(withClassName(Matchers.equalTo(android.widget.DatePicker.class.getName()))).perform(PickerActions.setDate(1999 , 6, 28));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.button2)).perform(scrollTo(), (click()));

        onView(allOf(withId(R.id.email), hasErrorText("Email not valid!")));

    }

    @Test
    public void checkEmailNotBlank() {
        onView(withId(R.id.email)).perform(typeText(""));

        onView(withId(R.id.btPickDate)).perform(scrollTo(),(click()));

        onView(withClassName(Matchers.equalTo(android.widget.DatePicker.class.getName()))).perform(PickerActions.setDate(1999 , 6, 28));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.button2)).perform(scrollTo(), (click()));

        onView(allOf(withId(R.id.email), hasErrorText("Email not valid!")));

    }

    @Test
    public void checkUsernameNotBlank() {
        onView(withId(R.id.name)).perform(typeText("Cathal"));
        onView(withId(R.id.email)).perform(typeText("cathal777@gmail.com"));
        onView(withId(R.id.username)).perform(typeText(""));

        onView(withId(R.id.btPickDate)).perform(scrollTo(),(click()));

        onView(withClassName(Matchers.equalTo(android.widget.DatePicker.class.getName()))).perform(PickerActions.setDate(1999 , 6, 28));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.button2)).perform(scrollTo(), click());

        onView(allOf(withId(R.id.username), hasErrorText("Cannot Be Blank!")));

    }

    @Test
    public void checkUnderEighteen() {

        onView(withId(R.id.btPickDate)).perform(scrollTo(),(click()));

        onView(withClassName(Matchers.equalTo(android.widget.DatePicker.class.getName()))).perform(PickerActions.setDate(2004 , 6, 28));
        onView(withText("OK")).perform(click());

        onView(allOf(withId(R.id.dob), hasErrorText("Must Be Older Than 18!")));

    }
}
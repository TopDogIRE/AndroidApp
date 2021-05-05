package com.example.adapp;

import androidx.test.espresso.ViewAction;
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
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    static String myName = "Cathal Mullen";
    static String myEmail = "cathal777@gmail.com";
    static String myUsername = "cathal777";
    static String myBio = "A National Cyclist";
    static String myOcc = "Director of Aran Glamping Holidays Ltd.";

    public ViewAction setTo(){
        return PickerActions.setDate(1999 , 06, 28);
    }

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);



    @Test
    public void checkValidEmail() {
        onView(withId(R.id.email)).perform(typeText(myUsername));

        onView(withId(R.id.btPickDate)).perform(scrollTo(),(click()));

        onView(withClassName(Matchers.equalTo(android.widget.DatePicker.class.getName()))).perform(setTo());
        onView(withText("OK")).perform(click());

        onView(withId(R.id.button2)).perform(scrollTo(), (click()));

        onView(allOf(withId(R.id.email), hasErrorText("Email not valid!")));

    }

    @Test
    public void checkEmailNotBlank() {
        onView(withId(R.id.email)).perform(typeText(""));

        onView(withId(R.id.btPickDate)).perform(scrollTo(),(click()));

        onView(withClassName(Matchers.equalTo(android.widget.DatePicker.class.getName()))).perform(setTo());
        onView(withText("OK")).perform(click());

        onView(withId(R.id.button2)).perform(scrollTo(), (click()));

        onView(allOf(withId(R.id.email), hasErrorText("Email not valid!")));

    }

    @Test
    public void checkNameNotBlank() {
        onView(withId(R.id.email)).perform(typeText(myEmail));
        onView(withId(R.id.name)).perform(typeText(""));

        onView(withId(R.id.btPickDate)).perform(scrollTo(),(click()));

        onView(withClassName(Matchers.equalTo(android.widget.DatePicker.class.getName()))).perform(setTo());
        onView(withText("OK")).perform(click());

        onView(withId(R.id.button2)).perform(scrollTo(), click());

        onView(allOf(withId(R.id.name), hasErrorText("Cannot Be Blank!")));

    }

    @Test
    public void checkUsernameNotBlank() {
        onView(withId(R.id.name)).perform(typeText("Cathal Mullen"));
        onView(withId(R.id.email)).perform(typeText("cathal777@gmail.com"));
        onView(withId(R.id.username)).perform(typeText(""));

        onView(withId(R.id.btPickDate)).perform(scrollTo(),(click()));

        onView(withClassName(Matchers.equalTo(android.widget.DatePicker.class.getName()))).perform(setTo());
        onView(withText("OK")).perform(click());

        onView(withId(R.id.button2)).perform(scrollTo(), click());

        onView(allOf(withId(R.id.username), hasErrorText("Cannot Be Blank!")));

    }

    @Test
    public void checkUnderEighteen() {

        onView(withId(R.id.btPickDate)).perform(scrollTo(),(click()));
        onView(withClassName(Matchers.equalTo(android.widget.DatePicker.class.getName()))).perform(PickerActions.setDate(2011 , 1,1));
        onView(withText("OK")).perform(click());

        onView(allOf(withId(R.id.age), hasErrorText("Must Be Older Than 18!")));

    }
}
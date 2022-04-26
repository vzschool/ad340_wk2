package com.example.ad340_wk2;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.widget.DatePicker;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityActivityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void test() {
        onView(withId(R.id.editTextName)).perform(typeText("Vladislav Zakharov"));
        onView(withId(R.id.editTextEmail)).perform(typeText("vlad@gmail.com"));
        onView(withId(R.id.editTextUsername)).perform(typeText("vlad"));
        onView(withId(R.id.buttonBirthday)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2000, 1, 1));
        onView(withId(R.id.buttonSubmit)).perform(click());
        onView(withId(R.id.textViewUsername)).check(matches(withText("vlad")));
    }
}

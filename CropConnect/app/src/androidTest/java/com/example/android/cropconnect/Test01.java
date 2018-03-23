package com.example.android.cropconnect;


import android.support.test.espresso.ViewInteraction;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class Test01 {



    @Test
    public void test01() {
        ViewInteraction editText = onView(
                allOf(withId(R.id.editText), isDisplayed()));
        editText.perform(click());

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.editText), isDisplayed()));
        editText2.perform(click());

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.editText), isDisplayed()));
        editText3.perform(replaceText("Sam Smith"), closeSoftKeyboard());

        pressBack();

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.editText2), isDisplayed()));
        editText4.perform(click());

        ViewInteraction editText5 = onView(
                allOf(withId(R.id.editText2), isDisplayed()));
        editText5.perform(replaceText("Sam99"), closeSoftKeyboard());

        pressBack();

        ViewInteraction button = onView(
                allOf(withId(R.id.button), withText("login"), isDisplayed()));
        button.perform(click());

        ViewInteraction button2 = onView(
                allOf(withId(R.id.button),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                7),
                        isDisplayed()));
        button2.check(doesNotExist());

        ViewInteraction button3 = onView(
                allOf(withId(R.id.regbutton), withText("Register"), isDisplayed()));
        button3.perform(click());

        ViewInteraction appCompatEditText = onView(
                withClassName(is("android.support.v7.widget.AppCompatEditText")));
        appCompatEditText.perform(scrollTo(), replaceText("Sam"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                withClassName(is("android.support.v7.widget.AppCompatEditText")));
        appCompatEditText2.perform(scrollTo(), replaceText("sam@gmail.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                withClassName(is("android.support.v7.widget.AppCompatEditText")));
        appCompatEditText3.perform(scrollTo(), replaceText("0799764309"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                withClassName(is("android.support.v7.widget.AppCompatEditText")));
        appCompatEditText4.perform(scrollTo(), replaceText("!st street Ilamo"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatRadioButton = onView(
                allOf(withId(R.id.radioFemale), withText("Customer"),
                        withParent(withId(R.id.custype))));
        appCompatRadioButton.perform(scrollTo(), click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.button4), withText("submit")));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.navigation_dashboard), withContentDescription("Dashboard"), isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction bottomNavigationItemView2 = onView(
                allOf(withId(R.id.navigation_dashboard), withContentDescription("Dashboard"), isDisplayed()));
        bottomNavigationItemView2.perform(click());

        ViewInteraction bottomNavigationItemView3 = onView(
                allOf(withId(R.id.navigation_notifications), withContentDescription("Notifications"), isDisplayed()));
        bottomNavigationItemView3.perform(click());

        ViewInteraction bottomNavigationItemView4 = onView(
                allOf(withId(R.id.navigation_home), withContentDescription("Home"), isDisplayed()));
        bottomNavigationItemView4.perform(click());

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        withParent(allOf(withId(R.id.action_bar),
                                withParent(withId(R.id.action_bar_container)))),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction button4 = onView(
                allOf(withId(R.id.button2), withText("Cancel"), isDisplayed()));
        button4.perform(click());

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}

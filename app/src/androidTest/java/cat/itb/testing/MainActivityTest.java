package cat.itb.testing;

import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {



    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    // Activity 2 - Simple Activity App Testing

    @Test
    public void view_elements_are_display() {
        onView(withId(R.id.title)).check(matches(isDisplayed()));
        onView(withId(R.id.button)).check(matches(isDisplayed()));
    }

    @Test
    public void text_of_view_elements_are_correct(){
        onView(withId(R.id.title)).check(matches(withText("Main Activity Title")));
        onView(withId(R.id.button)).check(matches(withText("Next")));
    }

    @Test
    public void button_is_clickable_and_change_the_text(){
        onView(withId(R.id.button)).check(matches(isClickable()));
        onView(withId(R.id.button)).perform(click()).check(matches(withText(R.string.back)));
    }

    // Activity 3 - Testing other kind of views

    private static final String USER_TO_BE_TYPED = "Username";
    private static final String PASS_TO_BE_TYPED = "Password";

    @Test
    public void login_form_behaviour(){
        onView(withId(R.id.usernameEditText)).perform(typeText(USER_TO_BE_TYPED)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.passwordEditText)).perform(typeText(PASS_TO_BE_TYPED)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.button)).perform(click()).check(matches(withText(R.string.logged)));
    }

    // Activity 4 - Testing an app with multiple activities

    @Test
    public void when_button1_is_clicked_change_activity(){
        onView(withId(R.id.button)).perform(click());
    }

    @Test
    public void when_button1_is_clicked_change_activity_and_check_activity_and_when_button2_is_clicked_change_activity_and_check_activity(){
        when_button1_is_clicked_change_activity();
        onView(withId(R.id.secondActivity)).check(matches(isDisplayed()));
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.mainActivity)).check(matches(isDisplayed()));
    }

    // Activity 5 - Large test function

    @Test
    public void large_test(){
        onView(withId(R.id.usernameEditText)).perform(typeText(USER_TO_BE_TYPED)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.passwordEditText)).perform(typeText(PASS_TO_BE_TYPED)).perform(ViewActions.closeSoftKeyboard());
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.secondActivity)).check(matches(isDisplayed()));
        onView(withId(R.id.title2)).check(matches(withText("Welcome Back " + USER_TO_BE_TYPED)));
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.mainActivity)).check(matches(isDisplayed()));
        onView(withId(R.id.usernameEditText)).check(matches(withText("")));
        onView(withId(R.id.passwordEditText)).check(matches(withText("")));
    }

}

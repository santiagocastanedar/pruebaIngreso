package co.com.ceiba.mobile.pruebadeingreso.view;


import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.pojo.Post;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TestEspresso {

    private String nameSearch;
    private String badName;
    private String nameFound;
    private String emailFound;
    private String phoneFound;
    private String title;
    private String body;
    private int firstPosition;


    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    public ActivityTestRule<PostActivity> mActivityPostTestRule = new ActivityTestRule<>(PostActivity.class);

    @Before
    public void setUp(){
        nameSearch = "leanne";
        nameFound = "Leanne Graham";
        emailFound = "Sincere@april.biz";
        phoneFound = "1-770-736-8031 x56442";
        badName = "Santiago";
        firstPosition = 0;

    }

    @Test
    public void testFilterUserName(){
        ViewInteraction editTextSearch = onView(
                allOf(withId(R.id.editTextSearch),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textInputLayoutSearch),
                                        0),
                                0),
                        isDisplayed()));
        editTextSearch.perform(replaceText(nameSearch), closeSoftKeyboard());

        ViewInteraction recyclerViewSearchResults = onView(
                allOf(withId(R.id.recyclerViewSearchResults),
                        childAtPosition(
                                allOf(withId(R.id.content),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                1),
                        isDisplayed()));
        recyclerViewSearchResults.check(matches(isDisplayed()));

        ViewInteraction textViewName = onView(
                allOf(withId(R.id.name), withText(nameFound),
                        childAtPosition(
                                allOf(withId(R.id.contentCard),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                                0)),
                                0),
                        isDisplayed()));
        textViewName.check(matches(isDisplayed()));
        textViewName.check(matches(withText(nameFound)));

        ViewInteraction textViewPhone = onView(
                allOf(withId(R.id.phone), withText(phoneFound),
                        childAtPosition(
                                allOf(withId(R.id.contentPhone),
                                        childAtPosition(
                                                withId(R.id.contentCard),
                                                1)),
                                1),
                        isDisplayed()));
        textViewPhone.check(matches(isDisplayed()));
        textViewPhone.check(matches(withText(phoneFound)));

        ViewInteraction textViewEmail = onView(
                allOf(withId(R.id.email), withText(emailFound),
                        childAtPosition(
                                allOf(withId(R.id.contentEmail),
                                        childAtPosition(
                                                withId(R.id.contentCard),
                                                2)),
                                1),
                        isDisplayed()));
        textViewEmail.check(matches(isDisplayed()));
        textViewEmail.check(matches(withText(emailFound)));

        ViewInteraction buttonViewPost = onView(
                allOf(withId(R.id.btn_view_post),
                        childAtPosition(
                                allOf(withId(R.id.contentBtnViewPost),
                                        childAtPosition(
                                                withId(R.id.contentCard),
                                                3)),
                                0),
                        isDisplayed()));
        buttonViewPost.check(matches(isDisplayed()));

    }

    @Test
    public void filterUserBadName(){

        ViewInteraction editTextSearch = onView(
                allOf(withId(R.id.editTextSearch),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textInputLayoutSearch),
                                        0),
                                0),
                        isDisplayed()));
        editTextSearch.perform(replaceText(badName), closeSoftKeyboard());
    }

    @Test
    public void viewPosts(){
        ViewInteraction recyclerViewSearchResults = onView(
                withId(R.id.recyclerViewSearchResults))
                .perform(
                        RecyclerViewActions.actionOnItemAtPosition(firstPosition, new ViewAction() {
                            @Override
                            public Matcher<View> getConstraints() {
                                return null;
                            }

                            @Override
                            public String getDescription() {
                                return null;
                            }

                            @Override
                            public void perform(UiController uiController, View view) {

                                View btnViewPost =  view.findViewById(R.id.btn_view_post);
                                btnViewPost.performClick();
                            }
                        })
                );

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

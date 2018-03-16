package morkovkin.espresso.example;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import example.ivi.ru.espressorecyclerview.R;
import morkovkin.espresso.example.utils.DrawableMatcher;
import morkovkin.espresso.example.utils.MyRecyclerViewActions;
import morkovkin.espresso.example.utils.RecyclerViewItemCountAssertion;
import morkovkin.espresso.example.utils.RecyclerViewItemSpecificityView;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class MainActivityTest {
	@Rule
	public ActivityTestRule activityRule = new ActivityTestRule<>(
		MainActivity.class);
	
	@Test
	public void checkLeoType() throws InterruptedException {
		onView(withId(R.id.rvAnimals)).perform(scrollToPosition(0))
			.check(new RecyclerViewItemSpecificityView(R.id.ivAnimalType,
				new DrawableMatcher(R.drawable.ic_sentiment_very_dissatisfied_black_24dp)));
	}
	
	@Test
	public void checkCapybaraFullFieldsTest() {
		final int capybaraPosition = 6;
		onView(withId(R.id.rvAnimals))
			.perform(scrollToPosition(capybaraPosition))
			.check(new RecyclerViewItemSpecificityView(R.id.tvName, withText("Капибара")))
			.check(new RecyclerViewItemSpecificityView(R.id.ivAnimalType, new DrawableMatcher(R.drawable.ic_sentiment_very_satisfied_black_24dp)))
			.check(new RecyclerViewItemSpecificityView(R.id.btnRemove,isDisplayed()));
	}
	
	@Test
	public void clickRemoveAnimalTest() throws InterruptedException {
		onView(withId(R.id.rvAnimals)).check(new RecyclerViewItemCountAssertion(8));
		onView(withId(R.id.rvAnimals)).perform(
			RecyclerViewActions.actionOnItemAtPosition(0, MyRecyclerViewActions.clickChildViewWithId(R.id.btnRemove)));
		onView(withId(R.id.rvAnimals)).check(new RecyclerViewItemCountAssertion(7));
	}
}
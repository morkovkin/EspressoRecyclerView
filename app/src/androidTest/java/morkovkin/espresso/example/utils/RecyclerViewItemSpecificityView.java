package morkovkin.espresso.example.utils;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

import static android.support.test.espresso.matcher.ViewMatchers.assertThat;

public class RecyclerViewItemSpecificityView implements ViewAssertion {
	
	private final int mSpecificallyId;
	private final Matcher<View> mMatcher;
	
	public RecyclerViewItemSpecificityView(final int specificallyId, final Matcher<View> matcher) {
		mSpecificallyId = specificallyId;
		mMatcher = matcher;
	}
	
	@Override
	public void check(final View view, final NoMatchingViewException noViewFoundException) {
		
		final StringDescription description = new StringDescription();
		description.appendText("'");
		mMatcher.describeTo(description);
		
		final ViewGroup itemRoot = (ViewGroup) view;
		final View typeIUsage = itemRoot.findViewById(mSpecificallyId);
		
		if (noViewFoundException != null) {
			description.appendText(
				String.format(
					"' check could not be performed because view with id '%s' was not found.\n",
					mSpecificallyId));
			Log.e("RecyclerViewItemSpecificityView", description.toString());
			throw noViewFoundException;
		} else {
			description.appendText("' doesn't match the selected view.");
			assertThat(description.toString(), typeIUsage, mMatcher);
		}
	}
}



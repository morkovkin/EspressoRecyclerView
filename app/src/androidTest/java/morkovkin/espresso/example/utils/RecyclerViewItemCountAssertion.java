package morkovkin.espresso.example.utils;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class RecyclerViewItemCountAssertion implements ViewAssertion {
	private final int expectedCount;
	
	public RecyclerViewItemCountAssertion(final int expectedCount) {
		this.expectedCount = expectedCount;
	}
	
	@Override
	public void check(final View view, final NoMatchingViewException noViewFoundException) {
		if (noViewFoundException != null) {
			throw noViewFoundException;
		}
		
		final RecyclerView recyclerView = (RecyclerView) view;
		final RecyclerView.Adapter adapter = recyclerView.getAdapter();
		assertThat(adapter.getItemCount(), is(expectedCount));
	}
}
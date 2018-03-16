package morkovkin.espresso.example.utils;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;

import org.hamcrest.Matcher;

public class MyRecyclerViewActions {
	
	public static ViewAction clickChildViewWithId(final int id) {
		return new ViewAction() {
			
			@Override
			public Matcher<View> getConstraints() {
				return null;
			}
			
			@Override
			public String getDescription() {
				return "Нажатие на элимент по специальному id";
			}
			
			@Override
			public void perform(final UiController uiController, final View view) {
				final View v = view.findViewById(id);
				v.performClick();
			}
		};
	}
}

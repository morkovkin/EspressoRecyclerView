package morkovkin.espresso.example.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class DrawableMatcher extends TypeSafeMatcher<View> {
	
	private final int mResDrawableId;
	private String resourceName;
	
	public DrawableMatcher(final int resourceId) {
		super(View.class);
		mResDrawableId = resourceId;
	}
	
	@Override
	protected boolean matchesSafely(final View target) {
		if (!(target instanceof ImageView)) {
			return false;
		}
		final ImageView imageView = (ImageView) target;
		if (mResDrawableId < 0) {
			return imageView.getBackground() == null;
		}
		final Resources resources = target.getContext().getResources();
		final Drawable expectedDrawable = resources.getDrawable(mResDrawableId);
		resourceName = resources.getResourceEntryName(mResDrawableId);
		if (expectedDrawable == null) {
			return false;
		}
		final Bitmap bitmap = getBitmap(imageView.getBackground());
		final Bitmap otherBitmap = getBitmap(expectedDrawable);
		return bitmap.sameAs(otherBitmap);
	}
	
	private static Bitmap getBitmap(final Drawable drawable) {
		final Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
			drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
		final Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
		drawable.draw(canvas);
		return bitmap;
	}
	
	@Override
	public void describeTo(Description description) {
		description.appendText("with drawable from resource id: ");
		description.appendValue(mResDrawableId);
		if (resourceName != null) {
			description.appendText("[");
			description.appendText(resourceName);
			description.appendText("]");
		}
	}
}
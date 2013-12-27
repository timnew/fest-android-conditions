package org.fest.assertions.conditions.android;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import org.fest.assertions.core.Condition;
import org.robolectric.shadows.ShadowImageView;

import static org.robolectric.Robolectric.application;
import static org.robolectric.Robolectric.shadowOf;

public class ImageViewSourceCondition extends Condition<View> {

    private Drawable expectedDrawable;

    public ImageViewSourceCondition(Drawable expectedDrawable) {
        super("ImageSource");

        this.expectedDrawable = expectedDrawable;
    }

    @Override
    public boolean matches(View view) {
        ImageView imageView = (ImageView) view;

        ShadowImageView shadowImageView = shadowOf(imageView);

        Drawable drawable = shadowImageView.getDrawable();

        return expectedDrawable.equals(drawable);
    }

    public static ImageViewSourceCondition imageSource(int resourceId) {
        return new ImageViewSourceCondition(application.getResources().getDrawable(resourceId));
    }
}

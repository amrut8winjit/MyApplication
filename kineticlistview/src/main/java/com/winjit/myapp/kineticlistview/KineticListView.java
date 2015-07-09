package com.winjit.myapp.kineticlistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

/**
 * Created by AmrutB on 30-06-2015.
 */
public class KineticListView extends ListView {
    public KineticListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {

        // Get the first and last visible positions.
        int first = getFirstVisiblePosition();
        int last = getLastVisiblePosition();

        // Snap delta to be a maximum of 60 on either sides.
        deltaY = deltaY > 60 ? 60 : deltaY;
        deltaY = deltaY < -60 ? -60 : deltaY;

        float factor = (float) (1/ (float) (last - first));

        // Rotate half the entries.
        if (deltaY > 0) {
            // Over-scrolled at the bottom.
            for (int i = last - (2 * first), j = 1; i <= last - first; i++, j++) {
                View item = getChildAt(i);
                if (item != null)
                    tilt(item, deltaY * j * factor);
            }
        } else {
            // Over-scrolled at the top.
            for (int i = first, j = 1; i <= ((int) (last/2)); i++, j++) {
                View item = getChildAt(i);
                if (item != null)
                    tilt(item, deltaY * (1 - (j * factor)));
            }
        }
        return true;
        //return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

      public void tilt(final View view, final float deg) {
        view.setRotationX(deg);

        // Reset the rotation.
        /*view.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setRotationX(0);
            }
        }, 200);*/
    }
}

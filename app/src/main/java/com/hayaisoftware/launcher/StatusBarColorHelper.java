
package com.hayaisoftware.launcher;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class StatusBarColorHelper {

    public static void setStatusBarColor(final Resources resources, final Activity activity,
                                         final int color) {
        
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return;

        final Window window = activity.getWindow();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            
            window.setStatusBarColor(color);
        } else {
            
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            final int statusBarHeight = getStatusBarHeight(resources);
            final View statusBarDummy = activity.findViewById(R.id.statusBarDummyView);
            statusBarDummy.getLayoutParams().height=statusBarHeight;
            statusBarDummy.setBackgroundColor(color);
        }
    }

    public static int getStatusBarHeight(final Resources resources) {

        final int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        return resourceId > 0 ? resources.getDimensionPixelSize(resourceId) : 0;
    }

    public static int getNavigationBarHeight(final Resources resources) {

        final int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        return resourceId > 0 ? resources.getDimensionPixelSize(resourceId) : 0;
    }

}

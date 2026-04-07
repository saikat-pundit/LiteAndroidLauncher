package com.hayaisoftware.launcher;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import com.hayaisoftware.launcher.threading.SimpleTaskConsumerManager;
import java.util.ArrayList;
public class LoadLaunchableActivityTask extends SimpleTaskConsumerManager.Task {
    private final ResolveInfo info;
    private final SharedData mSharedData;
    public LoadLaunchableActivityTask(final ResolveInfo info,
                                      final SharedData sharedData) {
        this.info = info;
        this.mSharedData = sharedData;
    }
    public boolean doTask() {
        final LaunchableActivity launchableActivity = new LaunchableActivity(
                info.activityInfo, info.activityInfo.loadLabel(mSharedData.mPackageManager).toString(), false);
        synchronized (mSharedData.launchablesFromResolve) {
            mSharedData.launchablesFromResolve.add(launchableActivity);
        }
        return true;
    }
    public static class SharedData {
        private final PackageManager mPackageManager;
        private final ArrayList<LaunchableActivity> launchablesFromResolve;
        public SharedData(final PackageManager packageManager,
                          final ArrayList<LaunchableActivity> launchablesFromResolve) {
            this.mPackageManager = packageManager;
            this.launchablesFromResolve=launchablesFromResolve;
        }
    }
}

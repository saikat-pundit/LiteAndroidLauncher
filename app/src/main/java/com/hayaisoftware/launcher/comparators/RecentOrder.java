
package com.hayaisoftware.launcher.comparators;

import com.hayaisoftware.launcher.LaunchableActivity;

import java.util.Comparator;

public class RecentOrder implements Comparator<LaunchableActivity>{

    @Override
    public int compare(LaunchableActivity lhs, LaunchableActivity rhs) {
        long lhsLaunchTime=lhs.getLaunchTime();
        long rhsLaunchTime = rhs.getLaunchTime();

        if (lhsLaunchTime > rhsLaunchTime)
            return -1;
        if (lhsLaunchTime < rhsLaunchTime)
            return 1;
        return 0;
    }
}

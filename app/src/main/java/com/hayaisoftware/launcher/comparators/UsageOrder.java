
package com.hayaisoftware.launcher.comparators;

import android.util.Log;

import com.hayaisoftware.launcher.LaunchableActivity;

import java.util.Comparator;

public class UsageOrder implements Comparator<LaunchableActivity>{

    @Override
    public int compare(LaunchableActivity lhs, LaunchableActivity rhs) {
        int lhsUsageQuantity = lhs.getusagesQuantity();
        int rhsUsageQuantity = rhs.getusagesQuantity();

        return rhsUsageQuantity - lhsUsageQuantity;
    }
}

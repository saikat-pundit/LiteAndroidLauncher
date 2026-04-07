
package com.hayaisoftware.launcher.comparators;

import com.hayaisoftware.launcher.LaunchableActivity;

import java.util.Comparator;

public class AlphabeticalOrder implements Comparator<LaunchableActivity> {
    @Override
    public int compare(LaunchableActivity lhs, LaunchableActivity rhs) {
        return lhs.getActivityLabel().compareToIgnoreCase(rhs.getActivityLabel());
    }
}

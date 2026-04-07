
package com.hayaisoftware.launcher.comparators;

import com.hayaisoftware.launcher.LaunchableActivity;

import java.util.Comparator;

public class PinToTop implements Comparator<LaunchableActivity>{

    @Override
    public int compare(LaunchableActivity lhs, LaunchableActivity rhs) {
        return rhs.getPriority()-lhs.getPriority();
    }
}

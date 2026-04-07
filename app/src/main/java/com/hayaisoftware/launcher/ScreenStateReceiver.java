package com.hayaisoftware.launcher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ScreenStateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction())) {
            // Screen turned off - start listening for fingerprint
            // You'd need to start a service here since activity is paused
        }
    }
}

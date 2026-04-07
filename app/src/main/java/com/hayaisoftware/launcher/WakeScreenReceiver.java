package com.hayaisoftware.launcher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.view.KeyEvent;

public class WakeScreenReceiver extends BroadcastReceiver {
    
    private static long lastTapTime = 0;
    private static final int DOUBLE_TAP_TIMEOUT = 1000;
    
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction())) {
            lastTapTime = 0;
        }
    }
    
    // This method would need to be called from a service that listens for power button
    // For true "double tap on screen" wake, you need special kernel support
    // Alternative: Use proximity sensor or power button detection
    public static void handleTap(Context context) {
        long currentTime = System.currentTimeMillis();
        
        if (currentTime - lastTapTime < DOUBLE_TAP_TIMEOUT) {
            // Double tap detected - wake screen
            wakeDevice(context);
            lastTapTime = 0;
        } else {
            lastTapTime = currentTime;
        }
    }
    
    private static void wakeDevice(Context context) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = pm.newWakeLock(
            PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP,
            "HayaiLauncher:WAKE_LOCK"
        );
        wakeLock.acquire(2000); // Release after 2 seconds
        wakeLock.release();
    }
}

package com.hayaisoftware.launcher;

import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

public class RebootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        DevicePolicyManager dpm = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        ComponentName adminComponent = new ComponentName(context, LauncherDeviceAdminReceiver.class);

        // The reboot API was introduced in Android Nougat (API 24)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if (dpm.isDeviceOwnerApp(context.getPackageName())) {
                try {
                    Log.d("HayaiReboot", "2:00 AM reached. Initiating Device Owner Reboot...");
                    dpm.reboot(adminComponent);
                } catch (Exception e) {
                    Log.e("HayaiReboot", "Reboot failed: " + e.getMessage());
                }
            }
        }
    }
}

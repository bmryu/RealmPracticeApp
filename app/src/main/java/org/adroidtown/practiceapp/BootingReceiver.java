package org.adroidtown.practiceapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootingReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Intent i = new Intent(context, TimeAlertService.class);
            context.startService(i);
        }


    }
}
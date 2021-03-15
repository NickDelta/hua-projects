package org.hua.tracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.widget.Toast;

public class BatteryStatusBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String status = intent.getAction();
        if(status.equals(Intent.ACTION_POWER_CONNECTED)) {
            Toast.makeText(context,"Power connected. Stopping service...",Toast.LENGTH_LONG).show();
            context.stopService(new Intent(context, LocationService.class));
        } else if(status.equals(Intent.ACTION_POWER_DISCONNECTED)){
            Toast.makeText(context,"Power disconnected. Starting service...",Toast.LENGTH_LONG).show();
            context.startService(new Intent(context, LocationService.class));
        }
    }

}



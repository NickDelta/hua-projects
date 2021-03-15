package org.hua.tracker;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private BatteryStatusBroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Check if app has location access permission
        if(ContextCompat.checkSelfPermission(
                getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //Since we have the permission, We can safely register our BroadcastReceiver
            registerBatteryBroadcastReceiver();
        }else {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(receiver);
        //See also the onDestroy() of the LocationService. The removeUpdates() happens there
        this.stopService(new Intent(getApplicationContext(), LocationService.class));
    }

    private void registerBatteryBroadcastReceiver() {
        this.receiver = new BatteryStatusBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        registerReceiver(receiver, intentFilter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 0:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Permission granted, so we can register our BroadcastReceiver
                    registerBatteryBroadcastReceiver();
                }  else {
                    //Show alert to the user, informing them about the consequences of denial
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage(
                            "Location permission is needed for this app to work. " +
                            "This app will close after you press OK. " +
                            "You may grand the permission next time you start this app " +
                            "unless you're on Android 11+. Starting in Android 11 (API level 30), " +
                            "if the user taps Deny for a specific permission more than once during " +
                            "your app's lifetime of installation on a device, the user doesn't see " +
                            "the system permissions dialog if your app requests that permission again.")
                           .setPositiveButton("OK", (dialog, id) -> finishAndRemoveTask());
                    builder.create().show();
                }
        }
    }
}

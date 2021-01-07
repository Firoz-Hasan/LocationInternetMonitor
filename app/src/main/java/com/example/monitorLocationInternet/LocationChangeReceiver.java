package com.example.monitorLocationInternet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.widget.Toast;

public class LocationChangeReceiver extends BroadcastReceiver {

    private static final String TAG = "LocationChangeReceiver";
    private Context context;

    public LocationChangeReceiver(Context context) {
        this.context = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        if (intent.getAction().matches("android.location.PROVIDERS_CHANGED")) {
            boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            if (gpsEnabled) {
                Toast.makeText(context, "GPS is enabled", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "GPS is disabled", Toast.LENGTH_SHORT).show();
            }
        }


    }

}
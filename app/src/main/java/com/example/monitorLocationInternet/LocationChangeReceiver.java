package com.example.monitorLocationInternet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class LocationChangeReceiver extends BroadcastReceiver {



    private static final String TAG = "LocationChangeReceiver";
    private Context context;

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date date = new Date();
    public LocationChangeReceiver(Context context) {
        this.context = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        DatabaseHandler db = new DatabaseHandler(context);
        if (intent.getAction().matches("android.location.PROVIDERS_CHANGED")) {
            boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            if (gpsEnabled) {
                db.addMonitor(new MonitorGpsInternet( "GPS", "true", formatter.format(date)));

                Toast.makeText(context, "GPS is enabled", Toast.LENGTH_SHORT).show();
            } else {
                db.addMonitor(new MonitorGpsInternet("GPS", "false", formatter.format(date)));

                Toast.makeText(context, "GPS is disabled", Toast.LENGTH_SHORT).show();
            }
        }


    }

}
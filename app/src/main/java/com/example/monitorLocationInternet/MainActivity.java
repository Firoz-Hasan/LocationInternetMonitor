package com.example.monitorLocationInternet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    NetworkChangeReceiver networkChangeReceiver;
    LocationChangeReceiver locationChangeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationChangeReceiver = new LocationChangeReceiver(this);
        networkChangeReceiver = new NetworkChangeReceiver(this);
        registerNetworkBroadcastReceiver();
        registerLocationBoradcastReceiver();

    }

    protected void registerLocationBoradcastReceiver() {
        registerReceiver(locationChangeReceiver, new IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION));

    }

    protected void registerNetworkBroadcastReceiver() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(networkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(networkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        }
    }

    protected void unregisterNetwork() {
        try {
            unregisterReceiver(networkChangeReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
    protected void unregisterLocation() {
        try {
            unregisterReceiver(locationChangeReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterNetwork();
        unregisterLocation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerLocationBoradcastReceiver();
    }


    @Override
    protected void onPause() {
        super.onPause();
        try {
            unregisterLocation();
            unregisterNetwork();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
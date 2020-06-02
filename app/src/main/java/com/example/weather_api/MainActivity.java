package com.example.weather_api;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getName();

    private static final int REQUEST_CODE_LOCATION_PERMISSION = 0;

    private LocationManager locationManager;

    private void chekAndRequestGetPermission(){
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        if(permissionCheck != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE_LOCATION_PERMISSION);
        }else { setupLocation();}
    }
    /**
     * Подписываемся на обновления гео
     */
    @SuppressLint("MissingPermission")
    private void setupLocation() {
        locationManager = (LocationManager) getSystemService( LOCATION_SERVICE);

        Criteria criteria = new Criteria();

        String bestProvider = locationManager.getBestProvider(criteria, true);
        Log.v(TAG, "Best provider: " + bestProvider);

        if (bestProvider != null) {
            Location lastKnowLocation = locationManager.getLastKnownLocation(bestProvider);

            Log.v(TAG,"Last location: "+ lastKnowLocation);

            locationManager.requestLocationUpdates(bestProvider,
                    0,
                    0,
                    locationListener);
        }
    }

@Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permission,@NonNull int[] grantResult ){
        super.onRequestPermissionsResult(requestCode,permission,grantResult);
        if (requestCode == REQUEST_CODE_LOCATION_PERMISSION){
            if (grantResult.length>0 && grantResult[0] == PackageManager.PERMISSION_GRANTED){
                setupLocation();
            }else {
                // non geo , show proz
                chekAndRequestGetPermission();
            }
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chekAndRequestGetPermission();
    }

    @Override
    protected void onDestroy() {
        if (locationManager != null) {locationManager.removeUpdates(locationListener);}
        super.onDestroy();
    }

    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Log.v(TAG,"LocationChanged" + location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.v(TAG,"StatusChanged"+provider+" "+status);
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.v(TAG,"ProviderEnabled"+provider);
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.v(TAG,"ProviderDisabled" + provider);
        }
    };
}

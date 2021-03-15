package org.hua.tracker;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.IBinder;
import android.widget.Toast;

import org.hua.tracker.entity.UserLocationContract;
import org.hua.tracker.UserLocationContentProvider;

public class LocationService extends Service implements LocationListener {

    private Context context;
    private static final long MIN_DISTANCE = 10; // 10 meters
    private static final long MIN_TIME = 1000 * 5; // 5 seconds
    protected LocationManager locationManager;

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = getApplicationContext();
        this.locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        initService();
    }

    @SuppressLint("MissingPermission")
    public void initService() {

        //Search for the best method to get location data based on the following criteria
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_LOW);

        String provider = locationManager.getBestProvider(criteria, true);

        //Start the LocationManager
        locationManager.requestLocationUpdates(
                provider,
                MIN_TIME,
                MIN_DISTANCE, this);

    }

    @Override
    public void onLocationChanged(Location location) {
        ContentValues values = new ContentValues();
        values.put(UserLocationContract.UserLocationEntry.COLUMN_NAME_LATITUDE, location.getLatitude());
        values.put(UserLocationContract.UserLocationEntry.COLUMN_NAME_LONGITUDE, location.getLongitude());

        //Use ContentResolver to make the save
        Uri uri = Uri.parse("content://" + UserLocationContentProvider.AUTHORITY + '/' + UserLocationContentProvider.LOCATIONS_PATH);
        getContentResolver().insert(uri,values);

        Toast.makeText(context,"Location Updated: Lat: " + location.getLatitude() + " Lng: " + location.getLongitude(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Stop receiving updates
        locationManager.removeUpdates(this);
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
}

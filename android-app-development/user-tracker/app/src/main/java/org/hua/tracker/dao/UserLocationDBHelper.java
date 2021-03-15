package org.hua.tracker.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.hua.tracker.entity.UserLocation;
import org.hua.tracker.entity.UserLocationContract;

import java.util.ArrayList;
import java.util.List;

import static org.hua.tracker.entity.UserLocationContract.UserLocationEntry;

public class UserLocationDBHelper extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "UserLocation.db";

    public UserLocationDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String SQL_CREATE_STATEMENT =
            "CREATE TABLE " + UserLocationEntry.TABLE_NAME + " (" +
                    UserLocationEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    UserLocationEntry.COLUMN_NAME_USER_ID + " VARCHAR(10)," +
                    UserLocationEntry.COLUMN_NAME_LONGITUDE + " FLOAT," +
                    UserLocationEntry.COLUMN_NAME_LATITUDE + " FLOAT," +
                    UserLocationEntry.COLUMN_NAME_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //No upgrades are intended
    }

    //Persists a location in the db.
    //Returns the row ID of the newly inserted row, or -1 if an error occurred.
    public long saveLocation(UserLocation location){

        SQLiteDatabase db = this.getWritableDatabase();

        //Timestamp is automatically saved at database level since the column's default is CURRENT_TIMESTAMP
        ContentValues values = new ContentValues();
        values.put(UserLocationEntry.COLUMN_NAME_USER_ID, location.getUserId());
        values.put(UserLocationEntry.COLUMN_NAME_LONGITUDE, location.getLongitude());
        values.put(UserLocationEntry.COLUMN_NAME_LATITUDE, location.getLatitude());


        return db.insert(UserLocationEntry.TABLE_NAME, null, values);

    }

    public List<UserLocation> searchLocations(String userId, String timestamp){
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = UserLocationContract.UserLocationEntry.COLUMN_NAME_USER_ID + "=? AND " + UserLocationContract.UserLocationEntry.COLUMN_NAME_TIMESTAMP + "=?";
        Cursor cursor = db.query(
                UserLocationContract.UserLocationEntry.TABLE_NAME,
                null,
                selection,
                new String[]{userId,timestamp},
                null,
                null,
                null);

        List<UserLocation> locations = new ArrayList<>();
        while(cursor.moveToNext()){
            locations.add(
                    new UserLocation(
                            cursor.getInt(cursor.getColumnIndexOrThrow(UserLocationContract.UserLocationEntry._ID)),
                            cursor.getString(cursor.getColumnIndexOrThrow(UserLocationContract.UserLocationEntry.COLUMN_NAME_USER_ID)),
                            cursor.getFloat(cursor.getColumnIndexOrThrow(UserLocationContract.UserLocationEntry.COLUMN_NAME_LONGITUDE)),
                            cursor.getFloat(cursor.getColumnIndexOrThrow(UserLocationContract.UserLocationEntry.COLUMN_NAME_LATITUDE)),
                            cursor.getString(cursor.getColumnIndexOrThrow(UserLocationContract.UserLocationEntry.COLUMN_NAME_TIMESTAMP))
                    )
            );
        }

        cursor.close();

        return locations;

    }

    public List<String> getTimestamps(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                UserLocationContract.UserLocationEntry.TABLE_NAME,
                new String[]{UserLocationContract.UserLocationEntry.COLUMN_NAME_TIMESTAMP}, //Fetch only timestamps
                null,
                null,
                null,
                null,
                null);

        List<String> timestamps = new ArrayList<>();
        while(cursor.moveToNext()){
            timestamps.add(cursor.getString(0));
        } //Add all timestamps in a list

        cursor.close();

        return timestamps;
    }
}

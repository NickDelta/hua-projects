package org.hua.tracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.hua.tracker.entity.UserLocation;
import org.hua.tracker.entity.UserLocationContract;

import static org.hua.tracker.entity.UserLocationContract.UserLocationEntry;

public class LocationDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Locations.db";

    public LocationDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String SQL_CREATE_STATEMENT =
            "CREATE TABLE " + UserLocationEntry.TABLE_NAME + " (" +
                    UserLocationEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    UserLocationEntry.COLUMN_NAME_LATITUDE + " FLOAT," +
                    UserLocationEntry.COLUMN_NAME_LONGITUDE + " FLOAT," +
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
        values.put(UserLocationEntry.COLUMN_NAME_LONGITUDE, location.getLongitude());
        values.put(UserLocationEntry.COLUMN_NAME_LATITUDE, location.getLatitude());

        return db.insert(UserLocationEntry.TABLE_NAME, null, values);

    }
}

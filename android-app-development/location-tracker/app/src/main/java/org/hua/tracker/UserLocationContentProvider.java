package org.hua.tracker;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.hua.tracker.entity.UserLocation;
import org.hua.tracker.entity.UserLocationContract;

public class UserLocationContentProvider extends ContentProvider {

    public static final String AUTHORITY = "org.hua.tracker.UserLocationContentProvider";
    public static final String LOCATIONS_PATH = "locations";

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private LocationDBHelper db;

    static {
        uriMatcher.addURI(AUTHORITY,LOCATIONS_PATH,1);
    }

    @Override
    public boolean onCreate() {
        db = new LocationDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection,
                        @Nullable String selection, @Nullable String[] selectionArgs,
                        @Nullable String sortOrder) {
        //This exercise does not require query operations
        throw new UnsupportedOperationException();
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            case 1:
                return "vnd.android.cursor.dir/vnd.tracker.locations";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        if(values == null)
            throw new IllegalArgumentException("values cannot be null");

        if(uriMatcher.match(uri) == 1){
            UserLocation location = new UserLocation(
                    values.getAsFloat(UserLocationContract.UserLocationEntry.COLUMN_NAME_LATITUDE),
                    values.getAsFloat(UserLocationContract.UserLocationEntry.COLUMN_NAME_LONGITUDE));
            long id = db.saveLocation(location);
            return Uri.parse(AUTHORITY + "/locations/" + id);
        } else {
            throw new IllegalArgumentException("URI not supported");
        }

    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        //This exercise does not require delete operations
        throw new UnsupportedOperationException();
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        //This exercise does not require update operations
        throw new UnsupportedOperationException();
    }
}

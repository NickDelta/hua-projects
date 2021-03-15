package org.hua.tracker.entity;

import android.provider.BaseColumns;

public final class UserLocationContract {

    private UserLocationContract() {}

    public static class UserLocationEntry implements BaseColumns {
        public static final String TABLE_NAME = "user_location";
        public static final String COLUMN_NAME_USER_ID = "userid";
        public static final String COLUMN_NAME_LONGITUDE = "longitude";
        public static final String COLUMN_NAME_LATITUDE = "latitude";
        public static final String COLUMN_NAME_TIMESTAMP = "dt";
    }
}

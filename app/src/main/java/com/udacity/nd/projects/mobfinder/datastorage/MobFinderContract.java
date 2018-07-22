package com.udacity.nd.projects.mobfinder.datastorage;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by noname on 7/22/18.
 */

public final class MobFinderContract {

    static final String AUTHORITY = "com.udacity.nd.projects.mobfinder";
    static final String PATHS_MOBILE = "mobiles";
    static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY).buildUpon().build();

    private MobFinderContract() {

    }

    public static final class MobileEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATHS_MOBILE)
                .build();


        static final String TABLE_NAME = "favorite_mobiles";
        public static final String COLUMN_MOBILE_NAME = "mobile_name";
        public static final String COLUMN_MOBILE_JSON = "mobile_json";
    }
}

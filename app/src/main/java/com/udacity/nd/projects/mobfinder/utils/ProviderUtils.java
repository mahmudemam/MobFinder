package com.udacity.nd.projects.mobfinder.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.udacity.nd.projects.mobfinder.data.Mobile;
import com.udacity.nd.projects.mobfinder.datastorage.MobFinderContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by noname on 7/22/18.
 */

public class ProviderUtils {
    public static boolean add(@NonNull Context context, @NonNull Mobile mobile) {
        String jsonStr = JsonUtils.mobileToJson(mobile);

        ContentValues cv = new ContentValues();
        cv.put(MobFinderContract.MobileEntry.COLUMN_MOBILE_NAME, mobile.getDeviceName());
        cv.put(MobFinderContract.MobileEntry.COLUMN_MOBILE_JSON, jsonStr);

        context.getContentResolver()
                .insert(MobFinderContract.MobileEntry.CONTENT_URI, cv);

        return true;
    }

    public static boolean remove(@NonNull Context context, @NonNull String mobileName) {
        Uri mobileUri = MobFinderContract.MobileEntry.CONTENT_URI
                .buildUpon()
                .appendPath(mobileName)
                .build();

        context.getContentResolver()
                .delete(mobileUri, null, null);

        return true;
    }

    public static boolean isStored(@NonNull Context context, @NonNull String mobileName) {
        Uri mobileUri = MobFinderContract.MobileEntry.CONTENT_URI
                .buildUpon()
                .appendPath(mobileName)
                .build();

        Cursor cursor = context.getContentResolver()
                .query(mobileUri, null, null, null, null);

        if (cursor != null && cursor.getCount() != 0) {
            cursor.close();
            return true;
        }

        return false;
    }

    public static List<Mobile> getAllMobiles(@NonNull Context context) {
        List<Mobile> mobiles = null;

        Cursor cursor = context.getContentResolver()
                .query(MobFinderContract.MobileEntry.CONTENT_URI, null, null, null, null);

        if (cursor != null && cursor.getCount() != 0) {
            mobiles = cursorToList(cursor);
        }

        return mobiles;
    }

    private static List<Mobile> cursorToList(Cursor cursor) {
        List<Mobile> mobiles = new ArrayList<>();

        while (cursor.moveToNext()) {
            mobiles.add(
                    JsonUtils.jsonToMobile(
                            cursor.getString(
                                    cursor.getColumnIndex(MobFinderContract.MobileEntry.COLUMN_MOBILE_JSON))
                    )
            );
        }

        cursor.close();

        return mobiles;
    }
}

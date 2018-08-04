package com.udacity.nd.projects.mobfinder.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;

import com.udacity.nd.projects.mobfinder.data.Mobile;
import com.udacity.nd.projects.mobfinder.datastorage.MobFinderContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by noname on 7/22/18.
 */

public class ProviderUtils {
    public interface ProviderCallbacks {
        void onMobilesLoaded(@Nullable List<Mobile> mobiles);
    }
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

    public static void getAllMobiles(@NonNull AppCompatActivity context, ProviderCallbacks callbacks) {
        /*List<Mobile> mobiles = null;*/

        ProviderLoader loader = new ProviderLoader(context, MobFinderContract.MobileEntry.CONTENT_URI, callbacks);

        context.getSupportLoaderManager()
                .initLoader(100, null, loader);

        /*Cursor cursor = context.getContentResolver()
                .query(MobFinderContract.MobileEntry.CONTENT_URI, null, null, null, null);*/

        /*if (cursor != null && cursor.getCount() != 0) {
            mobiles = cursorToList(cursor);
        }*/

        /*return mobiles;*/
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

    static class ProviderLoader implements LoaderManager.LoaderCallbacks<Cursor> {
        Context mContext;
        Uri mUri;
        ProviderCallbacks mCallbacks;

        ProviderLoader(Context context, Uri uri, ProviderCallbacks callbacks) {
            mContext = context;
            mUri = uri;
            mCallbacks = callbacks;
        }

        @Override
        public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
            return new CursorLoader(mContext, mUri, null, null, null, null);
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
            List<Mobile> mobiles = null;

            if (cursor != null && cursor.getCount() != 0) {
                mobiles = cursorToList(cursor);
            }

            mCallbacks.onMobilesLoaded(mobiles);
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {

        }
    }
}

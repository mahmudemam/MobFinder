package com.udacity.nd.projects.mobfinder.datastorage;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by noname on 7/22/18.
 */

public class MobFinderProvider extends ContentProvider {
    public static final int MOBILES = 100;
    public static final int MOBILES_WITH_NAME = 101;

    private MobFinderDB db;
    private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        MATCHER.addURI(MobFinderContract.AUTHORITY, MobFinderContract.PATHS_MOBILE, MOBILES);
        MATCHER.addURI(MobFinderContract.AUTHORITY, MobFinderContract.PATHS_MOBILE + "/*", MOBILES_WITH_NAME);
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        return super.bulkInsert(uri, values);
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor;

        int matchedID = MATCHER.match(uri);
        if (matchedID == MOBILES) {
            cursor = db.getReadableDatabase()
                    .query(MobFinderContract.MobileEntry.TABLE_NAME,
                            projection,
                            selection,
                            selectionArgs,
                            null,
                            null,
                            sortOrder);

        } else if (matchedID == MOBILES_WITH_NAME) {
            String mobileName = uri.getPathSegments().get(1);

            cursor = db.getReadableDatabase()
                    .query(MobFinderContract.MobileEntry.TABLE_NAME,
                            projection,
                            MobFinderContract.MobileEntry.COLUMN_MOBILE_NAME + "=?",
                            new String[]{mobileName},
                            null,
                            null,
                            sortOrder);
        } else {
            throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if (getContext() != null)
            cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        Uri insertedUri;

        int matchedID = MATCHER.match(uri);
        if (matchedID == MOBILES) {
            long rowID = db.getWritableDatabase()
                    .insert(MobFinderContract.MobileEntry.TABLE_NAME, null, contentValues);

            if (rowID > -1) {
                insertedUri = ContentUris.withAppendedId(MobFinderContract.MobileEntry.CONTENT_URI, rowID);
                uri = uri.buildUpon()
                        .appendPath(contentValues.getAsString(MobFinderContract.MobileEntry.COLUMN_MOBILE_NAME))
                        .build();
            } else {
                throw new android.database.SQLException("Failed to insert row into " + uri);
            }
        } else {
            throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if (getContext() != null) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return insertedUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int deletedRows;

        int matchedID = MATCHER.match(uri);
        if (matchedID == MOBILES_WITH_NAME) {
            String mobileName = uri.getPathSegments().get(1);

            deletedRows = db.getWritableDatabase()
                    .delete(
                            MobFinderContract.MobileEntry.TABLE_NAME,
                            MobFinderContract.MobileEntry.COLUMN_MOBILE_NAME + "=?",
                            new String[]{mobileName});
        } else {
            throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if (deletedRows != 0 && getContext() != null) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return deletedRows;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        throw new UnsupportedOperationException("Unsupported Operation for uri: " + uri);
    }

    @Override
    public boolean onCreate() {
        db = new MobFinderDB(getContext());
        return true;
    }
}

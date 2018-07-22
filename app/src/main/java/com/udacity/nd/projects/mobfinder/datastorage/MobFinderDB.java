package com.udacity.nd.projects.mobfinder.datastorage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by noname on 7/22/18.
 */

public class MobFinderDB extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "favoriteMobiles.db";
    private static final int DATABASE_VERSION = 1;

    public MobFinderDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_MOBILE_TABLE = "CREATE TABLE " + MobFinderContract.MobileEntry.TABLE_NAME + " (" +
                MobFinderContract.MobileEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MobFinderContract.MobileEntry.COLUMN_MOBILE_NAME + " TEXT NOT NULL, " +
                MobFinderContract.MobileEntry.COLUMN_MOBILE_JSON + " TEXT NOT NULL" +
                "); ";

        sqLiteDatabase.execSQL(SQL_CREATE_MOBILE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MobFinderContract.MobileEntry.TABLE_NAME);

        onCreate(sqLiteDatabase);
    }
}

package com.udacity.nd.projects.mobfinder.utils;

import android.content.Context;
import android.preference.PreferenceManager;

import com.udacity.nd.projects.mobfinder.R;

/**
 * Created by noname on 7/28/18.
 */

public class PreferencesUtils {
    public static int getBrand(Context context) {
        return Integer.valueOf(PreferenceManager.getDefaultSharedPreferences(context)
                .getString(context.getString(R.string.pref_key_brand), "-1"));
    }

    public static int getLimit(Context context) {
        return Integer.valueOf(PreferenceManager.getDefaultSharedPreferences(context)
                .getString(context.getString(R.string.pref_key_no_mobiles), "10"));
    }
}

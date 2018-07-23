package com.udacity.nd.projects.mobfinder.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

import com.udacity.nd.projects.mobfinder.R;
import com.udacity.nd.projects.mobfinder.data.Mobile;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by noname on 7/21/18.
 */

public class NetworkUtils {
    private static MobileAPI mobileAPI;
    private static final String MOBILE_BASE_API = "https://fonoapi.freshpixl.com/v1/";

    public static void getLatest(Callback<List<Mobile>> callback, String brand, int limit) {
        if (mobileAPI == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(MOBILE_BASE_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            mobileAPI = retrofit.create(MobileAPI.class);
        }

        mobileAPI.getLatestMobiles(brand, String.valueOf(limit)).enqueue(callback);
    }

    public static boolean isActive(Context context) {
        String operateOver = PreferenceManager.getDefaultSharedPreferences(context)
                .getString(context.getString(R.string.pref_key_network_type), context.getString(R.string.pref_default_network_type));

        if (operateOver.equals(context.getString(R.string.pref_default_network_type))) {
            if (NetworkUtils.isAnyActive(context)) {
                return true;
            }
        } else {
            if (NetworkUtils.isWifiActive(context)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isAnyActive(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo ni = cm.getActiveNetworkInfo();
            return ni != null && ni.isConnected();
        }

        return false;
    }

    private static boolean isWifiActive(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo ni = cm.getActiveNetworkInfo();

            return ni != null && ni.isConnected() && ni.getType() == ConnectivityManager.TYPE_WIFI;
        }

        return false;
    }
}

interface MobileAPI {
    @GET("getlatest?token=58579715ed9421e64b4aa57a8dc58de1e8b66d3791d0dec4")
    Call<List<Mobile>> getLatestMobiles(@Query("brand") String brand, @Query("limit") String limit);
}

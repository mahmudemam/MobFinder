package com.udacity.nd.projects.mobfinder.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.udacity.nd.projects.mobfinder.R;
import com.udacity.nd.projects.mobfinder.data.Mobile;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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

    public static List<Mobile> getLatest(Callback<List<Mobile>> callback, String brand, int limit) {
        getInstance();

        if (callback != null) {
            mobileAPI.getLatestMobiles(brand, String.valueOf(limit)).enqueue(callback);
            return null;
        } else {
            try {
                Response<List<Mobile>> response = mobileAPI.getLatestMobiles(brand, String.valueOf(limit)).execute();
                if (response.isSuccessful()) {
                    return response.body();
                } else {
                    return null;
                }
            } catch (IOException e) {
                return null;
            }
        }
    }

    private static void getInstance() {
        if (mobileAPI == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(MOBILE_BASE_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            mobileAPI = retrofit.create(MobileAPI.class);
        }
    }

    public interface NetworkCallbacks {
        void onMobileLoaded(List<Mobile> mobiles);
    }

    public static void getLatest(final NetworkCallbacks callbacks, final String brand, final int limit) {
        getInstance();


        new AsyncTask<Void, Void, List<Mobile>>() {
            @Override
            protected List<Mobile> doInBackground(Void... voids) {
                try {
                    Response<List<Mobile>> response = mobileAPI.getLatestMobiles(brand, String.valueOf(limit)).execute();
                    if (response.isSuccessful()) {
                        return response.body();
                    } else {
                        return null;
                    }
                } catch (IOException e) {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(List<Mobile> mobiles) {
                callbacks.onMobileLoaded(mobiles);
            }
        }.execute();
    }


    public static void loadImage(Context context, ImageView iv, Mobile mobile) {
        int placeHolder = R.drawable.ic_launcher_foreground;

        if (mobile.getBrand().equals(context.getString(R.string.vendor_google))) {
            placeHolder = R.drawable.ic_google_g_logo;
        } else if (mobile.getBrand().equals(context.getString(R.string.vendor_apple))) {
            placeHolder = R.drawable.ic_apple_logo;
        } else if (mobile.getBrand().equals(context.getString(R.string.vendor_htc))) {
            placeHolder = R.drawable.ic_htc_logo;
        } else if (mobile.getBrand().equals(context.getString(R.string.vendor_samsung))) {
            placeHolder = R.drawable.ic_samsung_logo;
        }

        Picasso.get()
                .load(mobile.getImageURL())
                .placeholder(placeHolder)
                .error(placeHolder)
                .into(iv);
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

package com.udacity.nd.projects.mobfinder;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.udacity.nd.projects.mobfinder.utils.NetworkUtils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String operateOver = PreferenceManager.getDefaultSharedPreferences(this)
                .getString(getString(R.string.pref_key_network_type), getString(R.string.pref_default_network_type));

        boolean online = false;
        if (operateOver.equals(getString(R.string.pref_default_network_type))) {
            if (NetworkUtils.isActive(this)) {
                online = true;
            }
        } else {
            if (NetworkUtils.isWifiActive(this)) {
                online = true;
            }
        }

        if (online) {
            MainActivity.start(this, MainActivity.OPERATE_MODE_ONLINE);
        } else {
            MainActivity.start(this, MainActivity.OPERATE_MODE_OFFLINE);
        }

        finish();
    }
}

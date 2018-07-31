package com.udacity.nd.projects.mobfinder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.ads.MobileAds;
import com.udacity.nd.projects.mobfinder.utils.NetworkUtils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

        if (NetworkUtils.isActive(this)) {
            MainActivity.start(this, MainActivity.OPERATE_MODE_ONLINE);
        } else {
            MainActivity.start(this, MainActivity.OPERATE_MODE_OFFLINE);
        }

        finish();
    }
}

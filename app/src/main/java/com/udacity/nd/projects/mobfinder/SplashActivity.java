package com.udacity.nd.projects.mobfinder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.udacity.nd.projects.mobfinder.utils.NetworkUtils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (NetworkUtils.isActive(this)) {
            MainActivity.start(this, MainActivity.OPERATE_MODE_ONLINE);
        } else {
            MainActivity.start(this, MainActivity.OPERATE_MODE_OFFLINE);
        }

        finish();
    }
}

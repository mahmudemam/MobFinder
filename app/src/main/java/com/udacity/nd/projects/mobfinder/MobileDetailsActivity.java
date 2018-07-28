package com.udacity.nd.projects.mobfinder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.udacity.nd.projects.mobfinder.data.Mobile;
import com.udacity.nd.projects.mobfinder.utils.NetworkUtils;

public class MobileDetailsActivity extends AppCompatActivity {
    private static final String INTENT_KEY_MOBILE = "mobile";

    private Mobile mobile;

    public static void start(Context context, Mobile mobile) {
        Intent intent = new Intent(context, MobileDetailsActivity.class);
        intent.putExtra(INTENT_KEY_MOBILE, mobile);

        context.startActivity(intent);
    }

    public static Intent getFillIntent(Mobile mobile) {
        Intent intent = new Intent();
        intent.putExtra(INTENT_KEY_MOBILE, mobile);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_details);

        Intent intent = getIntent();
        if (intent != null) {
            mobile = intent.getParcelableExtra(INTENT_KEY_MOBILE);
        } else if (savedInstanceState != null && savedInstanceState.containsKey(INTENT_KEY_MOBILE)) {
            mobile = savedInstanceState.getParcelable(INTENT_KEY_MOBILE);
        } else {
            throw new RuntimeException("Mobile is null");
        }

        loadViewByMobile();

        loadFAB();
    }

    private void loadFAB() {
        final FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareCompat.IntentBuilder.from(MobileDetailsActivity.this)
                        .setType("text/plain")
                        .setChooserTitle("Share A mobile")
                        .setText(mobile.toString())
                        .startChooser();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mobile != null)
            outState.putParcelable(INTENT_KEY_MOBILE, mobile);
    }

    private void loadViewByMobile() {
        setTitle(mobile.getDeviceName());

        NetworkUtils.loadImage(this, (ImageView) findViewById(R.id.iv_mobile), mobile);

        loadOS();

        loadDisplay();

        loadHardware();

        loadCamera();
    }

    private void loadOS() {
        String spec = mobile.getOs();
        String osType = spec.substring(0, spec.indexOf(' '));
        ((TextView) findViewById(R.id.tv_os_primary)).setText(osType);
        ((TextView) findViewById(R.id.tv_os_secondary)).setText(
                spec.substring(spec.indexOf(' ') + 1));
        if (!osType.equalsIgnoreCase("Android"))
            ((ImageView) findViewById(R.id.iv_os)).setImageResource(R.drawable.ic_ios_24dp);
    }

    private void loadDisplay() {
        String spec = mobile.getSize();
        String sizeInInches = spec.substring(0, spec.indexOf(' '));
        ((TextView) findViewById(R.id.tv_screen_primary)).setText(
                getString(R.string.display_size_header_format,
                        sizeInInches));
        ((TextView) findViewById(R.id.tv_physical_size_data)).setText(
                getString(R.string.display_size_content_format,
                        sizeInInches));

        spec = mobile.getResolution();
        String resolution = spec.substring(0, spec.indexOf(" pixels"));
        String ppi = spec.substring(spec.indexOf("~"), spec.indexOf("ppi") + "ppi".length());
        ((TextView) findViewById(R.id.tv_screen_secondary)).setText(
                resolution);
        ((TextView) findViewById(R.id.tv_resolution_data)).setText(
                getString(R.string.resolution_format, resolution));
        ((TextView) findViewById(R.id.tv_pixels_data)).setText(
                ppi);
    }

    private void loadHardware() {
        ((TextView) findViewById(R.id.tv_chipset_data)).setText(
                mobile.getChipset());

        ((TextView) findViewById(R.id.tv_graphics_data)).setText(
                mobile.getGpu());

        String spec = mobile.getInternal();
        ((TextView) findViewById(R.id.tv_memory_data)).setText(
                spec.substring(
                        spec.indexOf(", ") + ", ".length(),
                        spec.indexOf(" RAM")
                ));
    }

    private void loadCamera() {
        String spec = mobile.getPrimary();
        if (spec != null) {
            String primary = spec.substring(0, spec.indexOf(" MP") + " MP".length());
            ((TextView) findViewById(R.id.tv_camera_primary)).setText(primary);
            ((TextView) findViewById(R.id.tv_rear_camera_data)).setText(primary);

            spec = mobile.getSecondary();
            if (spec != null) {
                String secondary = spec.substring(0, spec.indexOf(" MP") + " MP".length());
                ((TextView) findViewById(R.id.tv_camera_secondary)).setText(
                        getString(R.string.front_camera_format, secondary));
                ((TextView) findViewById(R.id.tv_front_camera_data)).setText(secondary);
            } else {
                makeCameraHeaderGone(true);
            }
        } else {
            makeCameraHeaderGone(false);
        }
    }

    private void makeCameraHeaderGone(boolean secondaryOnly) {
        if (!secondaryOnly) {
            findViewById(R.id.tv_camera_primary).setVisibility(View.GONE);
            findViewById(R.id.iv_camera).setVisibility(View.GONE);
        }
        findViewById(R.id.tv_camera_secondary).setVisibility(View.GONE);
    }
}

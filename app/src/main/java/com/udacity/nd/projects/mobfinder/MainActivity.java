package com.udacity.nd.projects.mobfinder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.udacity.nd.projects.mobfinder.adapters.MobileAdapter;
import com.udacity.nd.projects.mobfinder.data.Mobile;
import com.udacity.nd.projects.mobfinder.settings.SettingsActivity;
import com.udacity.nd.projects.mobfinder.utils.NetworkUtils;
import com.udacity.nd.projects.mobfinder.utils.ProviderUtils;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<List<Mobile>>, SharedPreferences.OnSharedPreferenceChangeListener, MobileAdapter.MobileAdapterClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String INSTANCE_STATE_KEY_SPINNER = "KEY_SPINNER";
    private static final String INSTANCE_STATE_KEY_RV = "KEY_RV_POSITION";

    private static final String OPERATE_MODE_KEY = "operate_mode";
    public static final String OPERATE_MODE_ONLINE = "online";
    public static final String OPERATE_MODE_OFFLINE = "offline";

    private RecyclerView rv;
    private Parcelable rvPosition;
    private Spinner spinner;
    private ProgressBar progressBar;

    public static void start(Context context, String operateMode) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(OPERATE_MODE_KEY, operateMode);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.pb_loading_mobiles);
        spinner = findViewById(R.id.spinner_vendors);
        rv = findViewById(R.id.rv_mobiles);

        if (getIntent() != null) {
            String operateMode = getIntent().getStringExtra(OPERATE_MODE_KEY);
            if (operateMode.equals(OPERATE_MODE_ONLINE)) {
                workOnline();
            } else {
                workOffline();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (spinner != null)
            outState.putInt(INSTANCE_STATE_KEY_SPINNER, spinner.getSelectedItemPosition());

        if (rv != null && rv.getLayoutManager() != null)
            outState.putParcelable(INSTANCE_STATE_KEY_RV, rv.getLayoutManager().onSaveInstanceState());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState.containsKey(INSTANCE_STATE_KEY_SPINNER))
            spinner.setSelection(savedInstanceState.getInt(INSTANCE_STATE_KEY_SPINNER));

        if (savedInstanceState.containsKey(INSTANCE_STATE_KEY_RV))
            rvPosition = savedInstanceState.getParcelable(INSTANCE_STATE_KEY_RV);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);

        inflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        Log.d(TAG, "menuItem=" + item.getTitle().toString());

        switch (itemId) {
            case R.id.menu_settings:
                SettingsActivity.launch(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void workOnline() {
        PreferenceManager.getDefaultSharedPreferences(this)
                .registerOnSharedPreferenceChangeListener(this);

        setupSpinner();

        loadMobiles();
    }

    private void workOffline() {
        List<Mobile> mobiles = ProviderUtils.getAllMobiles(this);

        if (mobiles == null) {
            progressBar.setVisibility(View.GONE);
            spinner.setVisibility(View.GONE);
            rv.setVisibility(View.GONE);

            findViewById(R.id.iv_no_network).setVisibility(View.VISIBLE);
            findViewById(R.id.tv_no_network).setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
            spinner.setVisibility(View.GONE);

            loadRecyclerView(mobiles);
        }
    }

    private void setupSpinner() {
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.vendors, android.R.layout.simple_spinner_dropdown_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                String brand = adapterView.getAdapter().getItem(pos).toString();
                Log.d(TAG, brand);

                loadMobiles();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        updateSpinnerSelectionFromPreference();
    }

    private void updateSpinnerSelectionFromPreference() {
        int brand = getDefaultBrand();
        if (brand != -1)
            spinner.setSelection(brand);
    }

    private int getDefaultBrand() {
        return Integer.valueOf(PreferenceManager.getDefaultSharedPreferences(this)
                .getString(getString(R.string.pref_key_brand), "-1"));
    }

    private void loadMobiles() {
        Log.d(TAG, "loadMobiles: begin");
        progressBar.setVisibility(View.VISIBLE);
        rv.setVisibility(View.INVISIBLE);

        int limit = Integer.valueOf(PreferenceManager.getDefaultSharedPreferences(this)
                .getString(getString(R.string.pref_key_no_mobiles), "10"));

        NetworkUtils.getLatest(this, spinner.getSelectedItem().toString(), limit);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.pref_key_brand))) {
            updateSpinnerSelectionFromPreference();
        } else if (key.equals(getString(R.string.pref_key_no_mobiles))) {
            loadMobiles();
        }
    }

    @Override
    public void onResponse(@NonNull Call<List<Mobile>> call, @NonNull Response<List<Mobile>> response) {
        if (response.isSuccessful()) {
            List<Mobile> mobiles = response.body();
            Log.d(TAG, "mobiles: " + (mobiles != null ? mobiles.size() : 0));

            progressBar.setVisibility(View.INVISIBLE);
            rv.setVisibility(View.VISIBLE);

            loadRecyclerView(mobiles);
        } else {
            try {
                Log.e(TAG, response.errorBody().string());
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

    private void loadRecyclerView(List<Mobile> mobiles) {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(getResources().getInteger(R.integer.grid_count), StaggeredGridLayoutManager.VERTICAL);
        layoutManager.onRestoreInstanceState(rvPosition);
        rv.setLayoutManager(layoutManager);

        MobileAdapter adapter = new MobileAdapter(this, mobiles, this);
        rv.setAdapter(adapter);
    }

    @Override
    public void onFailure(@NonNull Call<List<Mobile>> call, @NonNull Throwable t) {
        Log.e(TAG, t.getMessage());
    }

    @Override
    public void onShareClicked(String text) {
        ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setChooserTitle("Share A mobile")
                .setText(text)
                .startChooser();
    }

    @Override
    public void onFavoriteClicked(Mobile mobile, boolean favorite) {
        if (favorite) {
            ProviderUtils.add(this, mobile);
        } else {
            ProviderUtils.remove(this, mobile.getDeviceName());
        }
    }
}

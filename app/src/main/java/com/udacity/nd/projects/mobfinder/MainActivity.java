package com.udacity.nd.projects.mobfinder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.udacity.nd.projects.mobfinder.adapters.MobileAdapter;
import com.udacity.nd.projects.mobfinder.data.Mobile;
import com.udacity.nd.projects.mobfinder.utils.NetworkUtils;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<List<Mobile>> {
    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView rv;
    private GridLayoutManager gridLayoutManager;
    private Spinner spinner;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupSpinner();
        progressBar = findViewById(R.id.pb_loading_mobiles);

        rv = findViewById(R.id.rv_mobiles);
        gridLayoutManager = new GridLayoutManager(this, getResources().getInteger(R.integer.grid_count));

        loadMobiles("Samsung");
    }

    private void setupSpinner() {
        spinner = findViewById(R.id.spinner_vendors);

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.vendors, android.R.layout.simple_spinner_dropdown_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                String brand = adapterView.getAdapter().getItem(pos).toString();
                Log.d(TAG, brand);

                loadMobiles(brand);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void loadMobiles(String brand) {
        progressBar.setVisibility(View.VISIBLE);
        rv.setVisibility(View.INVISIBLE);

        NetworkUtils.getLatest(this, brand, 10);
    }

    @Override
    public void onResponse(@NonNull Call<List<Mobile>> call, @NonNull Response<List<Mobile>> response) {
        if (response.isSuccessful()) {
            List<Mobile> mobiles = response.body();
            Log.d(TAG, "mobiles: " + (mobiles != null ? mobiles.size() : 0));

            MobileAdapter adapter = new MobileAdapter(this, mobiles);

            progressBar.setVisibility(View.INVISIBLE);
            rv.setVisibility(View.VISIBLE);

            rv.setLayoutManager(gridLayoutManager);
            rv.setAdapter(adapter);
        } else {
            try {
                Log.e(TAG, response.errorBody().string());
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

    @Override
    public void onFailure(@NonNull Call<List<Mobile>> call, @NonNull Throwable t) {
        Log.e(TAG, t.getMessage());
    }
}

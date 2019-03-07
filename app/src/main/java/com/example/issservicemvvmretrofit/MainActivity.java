package com.example.issservicemvvmretrofit;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.issservicemvvmretrofit.data.Response;
import com.example.issservicemvvmretrofit.home.HomeViewModel;
import com.example.issservicemvvmretrofit.home.ISSAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.btnGetResult);
        RecyclerView recyclerView = findViewById(R.id.rvData);

        final EditText etLatitude = findViewById(R.id.etLatitude);
        final EditText etLongtitude = findViewById(R.id.etLongitude);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration
                (this, linearLayoutManager.getOrientation()));
        final ISSAdapter issAdapter = new ISSAdapter();
        recyclerView.setAdapter(issAdapter);

        final HomeViewModel homeViewModel = new HomeViewModel();
        homeViewModel.getResponseLiveData().observe(this, new Observer<List<Response>>() {
            @Override
            public void onChanged(@Nullable List<Response> responses) {
                issAdapter.setData(responses);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeViewModel.getResponses(etLatitude.getText().toString(),
                        etLongtitude.getText().toString());
            }
        });
    }
}

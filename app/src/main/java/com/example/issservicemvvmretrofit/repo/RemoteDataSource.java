package com.example.issservicemvvmretrofit.repo;

import com.example.issservicemvvmretrofit.data.ISSRepo;
import com.example.issservicemvvmretrofit.net.Constants;
import com.example.issservicemvvmretrofit.net.ISSService;
//import com.example.issservicemvvmretrofit.data.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource extends Observable implements DataSource {

    private final ISSService issService;

    public RemoteDataSource() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        issService = retrofit.create(ISSService.class);
    }

    @Override
    public void getCoordinatesForLocation(String latitude, String longitude) {

        final List<com.example.issservicemvvmretrofit.data.Response> responseList = new ArrayList<>();
        issService.getCoordinates(latitude, longitude).enqueue(new Callback<ISSRepo>() {
            @Override
            public void onResponse(Call<ISSRepo> call, Response<ISSRepo> response) {
                if(response.isSuccessful() && response.body().getResponse() != null){
                    responseList.clear();
                    responseList.addAll(response.body().getResponse());
                    setChanged();
                    notifyObservers(responseList);
                }
            }

            @Override
            public void onFailure(Call<ISSRepo> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void setObserver(Observer observer) {
        addObserver(observer);
    }
}

package com.example.issservicemvvmretrofit.net;

import com.example.issservicemvvmretrofit.data.ISSRepo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ISSService {
    @GET(Constants.ENDPOINT)
    Call<ISSRepo> getCoordinates(@Query("lat") String lat, @Query("lon") String lon);
}

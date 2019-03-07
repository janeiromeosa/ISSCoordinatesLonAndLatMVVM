package com.example.issservicemvvmretrofit.repo;

import java.util.Observer;

public interface DataSource {
    void getCoordinatesForLocation(String latitude, String longitude);
    void setObserver(Observer observer);
}

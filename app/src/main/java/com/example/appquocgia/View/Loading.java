package com.example.appquocgia.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.appquocgia.API.CountryAPI;
import com.example.appquocgia.R;

public class Loading extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        callAPI();
    }

    private void callAPI() {
        CountryAPI api = new CountryAPI(this);
        api.execute("http://api.geonames.org/countryInfoJSON?formatted=true&lang=it&username=aporter&style=full");
    }
}
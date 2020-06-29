package com.example.appquocgia.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.appquocgia.API.CountryAPI;
import com.example.appquocgia.R;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Loading extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        if(checkInternetConnection()){
            callAPI();
        }
        else Toast.makeText(this,"Vui lòng kiểm tra lại kết nối mạng!",Toast.LENGTH_LONG).show();
    }

    private void callAPI() {
        CountryAPI api = new CountryAPI(this);
        api.execute("http://api.geonames.org/countryInfoJSON?formatted=true&lang=it&username=aporter&style=full");
    }

    private boolean checkInternetConnection() {
        ConnectivityManager connManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            Toast.makeText(this, "No default network is currently active", Toast.LENGTH_LONG).show();
            return false;
        }    if (!networkInfo.isConnected()) {
            Toast.makeText(this, "Network is not connected", Toast.LENGTH_LONG).show();
            return false;
        }   if (!networkInfo.isAvailable()) {
            Toast.makeText(this, "Network not available", Toast.LENGTH_LONG).show();
            return false;   }
        Toast.makeText(this, "Network OK", Toast.LENGTH_LONG).show();
        return true;}

}
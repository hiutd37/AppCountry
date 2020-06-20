package com.example.appquocgia.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.appquocgia.API.CountryAPI;
import com.example.appquocgia.API.Listening;
import com.example.appquocgia.API.Reload_API;
import com.example.appquocgia.Adapter.CountryAdapter;
import com.example.appquocgia.R;
import com.example.appquocgia.SingleTon.SingleTon;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements Listening {

    EditText editTextCountry;
    CountryAdapter countryAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    Button btnXacNhan;
    ListView listView;
    SingleTon singleTon=SingleTon.getInstance();
    ArrayList<JSONObject> arrayCountry=singleTon.getArrayListJson();
    ArrayList<JSONObject> filterCountry = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }
    public void shuffle(){
        Collections.shuffle(arrayCountry, new Random(System.currentTimeMillis()));
        countryAdapter.clear();
         //countryAdapter= new CountryAdapter(this, R.layout.item, arrayCountry);
        //countryAdapter.notifyDataSetChanged();
        //listView.setAdapter(countryAdapter);
        //Toast.makeText(this, arrayCountry.toString(), Toast.LENGTH_LONG).show();


    }
    private void addEvents() {
        editTextCountry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterCountry.clear();
                filterList(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private void addControls() {
        editTextCountry = findViewById(R.id.editTextCountry);
        btnXacNhan = findViewById(R.id.btnXacNhan);
        listView = findViewById(R.id.listView);
        mSwipeRefreshLayout = findViewById(R.id.swipeToRefresh);

        countryAdapter = new CountryAdapter(this,R.layout.item,arrayCountry);
        listView.setAdapter(countryAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Reload_API(MainActivity.this).execute("http://api.geonames.org/countryInfoJSON?formatted=true&lang=it&username=aporter&style=full");
                shuffle();
            }
        }
        );
    }
    public void LayGiaTri(View view){
            filterCountry.clear();
            String qr=editTextCountry.getText().toString();
            filterList(qr);
    }

    private void filterList(String qr) {
        for(JSONObject o:arrayCountry){
            try {
                if(o.getString("countryName").toLowerCase().contains(qr.toLowerCase())){
                    filterCountry.add(o);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
            refreshList(filterCountry);
    }

    @Override
    public void setChangeArrayList(ArrayList list) {
        arrayCountry=list;
        mSwipeRefreshLayout.setRefreshing(false);
       // Toast.makeText(MainActivity.this,list.toString(),Toast.LENGTH_LONG).show();

        refreshList(arrayCountry);
    }

    private void refreshList(ArrayList<JSONObject> list) {
        countryAdapter.clear();
        countryAdapter = new CountryAdapter(this,R.layout.item,list);
        listView.setAdapter(countryAdapter);
        countryAdapter.notifyDataSetChanged();
    }
}

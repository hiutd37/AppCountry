package com.example.appquocgia;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appquocgia.API.API;
import com.example.appquocgia.API.CountryAPI;
import com.example.appquocgia.Adapter.CountryAdapter;
import com.example.appquocgia.Entity.Country;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements API {

    EditText editTextCountry;
    CountryAdapter countryAdapter;
    Button btnXacNhan;
    ListView listView;

    ArrayList<JSONObject> arrayCountry=new ArrayList<>();
    ArrayList<Country>countries=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
        countries.add(new Country("A",1,1,"s"));
        countries.add(new Country("AB",1,1,"s"));
        countries.add(new Country("B",1,1,"s"));
        countries.add(new Country("BC",1,1,"s"));
        countries.add(new Country("C",1,1,"s"));
        countries.add(new Country("CD",1,1,"s"));
    }

    private void addEvents() {
        editTextCountry.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return false;
            }
        });
    }

    private void addControls() {
        editTextCountry = findViewById(R.id.editTextCountry);
        btnXacNhan = findViewById(R.id.btnXacNhan);
        listView = findViewById(R.id.listView);

        countryAdapter = new CountryAdapter(this,R.layout.item,arrayCountry);
        listView.setAdapter(countryAdapter);

        CountryAPI api = new CountryAPI(this);
        api.execute("http://api.geonames.org/countryInfoJSON?formatted=true&lang=it&username=aporter&style=full");
    }

    public void LayGiaTri(View view) {

    }



    @Override
    public void setArrayListJson(ArrayList arrayList) throws JSONException {
        this.arrayCountry.clear();
        this.arrayCountry.addAll(arrayList);
        //Toast.makeText(this,arrayCountry.toString(),Toast.LENGTH_LONG).show();
//        for(int i=0;i<arrayCountry.size();i++){
//            Toast.makeText(this,arrayCountry.get(i).getString("countryName"),Toast.LENGTH_LONG).show();
//        }
//        this.arrayCountry.add(new JSONObject());
          countryAdapter.notifyDataSetChanged();
    }
}

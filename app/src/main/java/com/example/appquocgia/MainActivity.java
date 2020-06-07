package com.example.appquocgia;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.appquocgia.API.API;
import com.example.appquocgia.API.CountryAPI;
import com.example.appquocgia.Entity.Country;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements API {

    private AutoCompleteTextView textCountry;
    AlertDialog dialog;
    ArrayAdapter countryAdapter;
    Button btnXacNhan;
    ListView listView;

    ArrayList<JSONObject> arrayCountry=new ArrayList();
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

    }

    private void addControls() {
        textCountry = findViewById(R.id.autoCompleteTextCountry);
        btnXacNhan = findViewById(R.id.btnXacNhan);
        listView = findViewById(R.id.listView);

        countryAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,countries);

        //listView.setAdapter(countryAdapter);
        textCountry.setAdapter(countryAdapter);
        textCountry.setAdapter(countryAdapter);
        textCountry.setThreshold(1);

        CountryAPI api = new CountryAPI(this);
        api.execute("http://api.geonames.org/countryInfoJSON?formatted=true&lang=it&username=aporter&style=full");
    }

    public void LayGiaTri(View view) {
        String text = "Ten quoc gia: " + this.textCountry.getText().toString();
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.diaglogcountry, null);
        builder.setView(v);
        dialog = builder.create();
        dialog.show();
    }


    @Override
    public void setArrayListJson(ArrayList arrayList) throws JSONException {
        this.arrayCountry=arrayList;
        //Toast.makeText(this,arrayCountry.toString(),Toast.LENGTH_LONG).show();
        countryAdapter.notifyDataSetChanged();
    }
}

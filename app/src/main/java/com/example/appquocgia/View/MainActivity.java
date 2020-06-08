package com.example.appquocgia.View;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.appquocgia.Adapter.CountryAdapter;
import com.example.appquocgia.R;
import com.example.appquocgia.SingleTon.SingleTon;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    EditText editTextCountry;
    CountryAdapter countryAdapter;
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

        countryAdapter = new CountryAdapter(this,R.layout.item,arrayCountry);
        listView.setAdapter(countryAdapter);

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
        countryAdapter = new CountryAdapter(this,R.layout.item,filterCountry);
        listView.setAdapter(countryAdapter);
        countryAdapter.notifyDataSetChanged();
    }

}

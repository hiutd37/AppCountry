package com.example.appquocgia;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView textCountry;
    AlertDialog dialog;
    private Button btnXacNhan;

    private String[] countries = {"Vietnam", "England", "Canada", "France", "Australia"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addEvents() {

    }

    private void addControls() {
        textCountry = findViewById(R.id.autoCompleteTextCountry);
        btnXacNhan = findViewById(R.id.btnXacNhan);

        ArrayAdapter adapterCountries = new ArrayAdapter(this, android.R.layout.simple_list_item_1, countries);
        textCountry.setAdapter(adapterCountries);

        textCountry.setThreshold(1);

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
}

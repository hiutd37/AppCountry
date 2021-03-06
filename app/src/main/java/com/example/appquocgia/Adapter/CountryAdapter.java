package com.example.appquocgia.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import com.example.appquocgia.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CountryAdapter extends ArrayAdapter<JSONObject> {
    Activity context;
    int resource;
    List objects;

    AlertDialog dialog;
    String url;

    public CountryAdapter(@NonNull Activity context, int resource, @NonNull ArrayList objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        View row=inflater.inflate(this.resource,null);
        TextView textViewCountry = row.findViewById(R.id.textViewCountry);
        ImageView imageViewFlag=row.findViewById(R.id.imageViewFlag);

        final JSONObject object= (JSONObject) this.objects.get(position);

        try {
            url="https://img.geonames.org/flags/l/"+object.getString("countryCode").toLowerCase()+".gif";
            Picasso.with(this.context).load(url).resize(150,100).into(imageViewFlag);
            textViewCountry.setText(object.getString("countryName"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        imageViewFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    callDialog(object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        textViewCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    callDialog(object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        return row;
    }

    private void callDialog(JSONObject object) throws JSONException {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        LayoutInflater inflater = this.context.getLayoutInflater();
        View v = inflater.inflate(R.layout.diaglogcountry, null);

        TextView textViewNameCountry = v.findViewById(R.id.textViewNameCountry);
        TextView textViewCountryName = v.findViewById(R.id.textViewCountryName);
        ImageView imageViewDialogFlag = v.findViewById(R.id.imageViewDialogFlag);
        TextView textViewPopulation = v.findViewById(R.id.textViewPopulation);
        TextView textViewSquareArea = v.findViewById(R.id.textViewSquareArea);
        Button buttonBack = v.findViewById(R.id.buttonBack);

        textViewCountryName.setText(object.getString("countryName"));
        textViewNameCountry.setText(object.getString("countryName"));
        url="https://img.geonames.org/flags/l/"+object.getString("countryCode").toLowerCase()+".gif";
        Picasso.with(this.context).load(url).resize(150,100).into(imageViewDialogFlag);
        textViewPopulation.setText(NumberFormat.getNumberInstance(Locale.getDefault()).format(Integer.parseInt(object.getString("population")))+" người");
        textViewSquareArea.setText(NumberFormat.getNumberInstance(Locale.getDefault()).format(Float.parseFloat(object.getString("areaInSqKm")))+ " km2");

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        builder.setView(v);
        dialog = builder.create();
        dialog.show();
    }
}

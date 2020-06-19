package com.example.appquocgia.API;

import android.app.Activity;
import android.app.ProgressDialog;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.appquocgia.Adapter.CountryAdapter;
import com.example.appquocgia.R;

import java.util.ArrayList;

public class Reload_API extends CountryAPI {
    ProgressDialog progressBar;

    public Reload_API(Activity context) {
        super(context);
}

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
//        progressBar = new ProgressDialog(this.context);
//        progressBar.setMessage("loading...");
//        progressBar.show();
    }

    @Override
    protected void onPostExecute(ArrayList arrayList) {

        singleTon.objects.clear();
        singleTon.setArrayListJson(arrayList);
//        if (progressBar.isShowing()) {
//            progressBar.dismiss();
//        }
//        CountryAdapter countryAdapter = new CountryAdapter(this.context, R.layout.item, arrayList);
//        lv.setAdapter(countryAdapter);
        //Toast.makeText(this.context, arrayList.toString(), Toast.LENGTH_SHORT).show();
    }
}

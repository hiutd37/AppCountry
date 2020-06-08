package com.example.appquocgia.API;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.appquocgia.MainActivity;
import com.example.appquocgia.SingleTon.SingleTon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class CountryAPI extends AsyncTask<String, String, ArrayList> {
    private Activity context;
    private JSONArray jsonArray;
    private  SingleTon singleTon;
    public CountryAPI(Activity context) {
        this.context=context;
        singleTon=SingleTon.getInstance();
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList arrayList) {
        super.onPostExecute(arrayList);
        singleTon.setArrayListJson(arrayList);
        callActivity();
    }

    private void callActivity() {
        Intent intent = new Intent(this.context, MainActivity.class);
        this.context.startActivity(intent);
        this.context.finish();
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected ArrayList doInBackground(String... urls) {

        try {
           JSONArray jsonArray =new JSONArray();
           jsonArray=getJSONData(urls[0]);
            return convertToArrayList(jsonArray);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private JSONArray getJSONData(String urls) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try{
            URL url = new URL(urls);
            connection = (HttpURLConnection) url.openConnection();

            InputStream stream = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer=new StringBuffer();

            String line="";

            while ((line=reader.readLine())!=null){
                buffer.append(line);
            }

            String data = buffer.toString();
            Log.i("JSON",data);
            JSONObject object=new JSONObject(data);//Lấy object của Json
            jsonArray=object.getJSONArray("geonames");

            return jsonArray;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList convertToArrayList(JSONArray jsonArray) throws JSONException {
        ArrayList<JSONObject> objectArrayList=new ArrayList<>();
        for (int i=0;i<jsonArray.length();i++){
            objectArrayList.add(jsonArray.getJSONObject(i));
        }
        return objectArrayList;
    }
}

package com.example.appquocgia.SingleTon;

import org.json.JSONObject;

import java.util.ArrayList;

public class SingleTon {
    private static SingleTon instance=null;
    public static ArrayList<JSONObject> objects;
    private SingleTon() {
        objects=new ArrayList<>();
    }

    public static SingleTon getInstance(){
        if(instance==null){
            instance=new SingleTon();
        }
        return instance;
    }
    public void setArrayListJson(ArrayList list){
        objects=list;
    }
    public ArrayList<JSONObject> getArrayListJson(){
        return objects;
    }
}

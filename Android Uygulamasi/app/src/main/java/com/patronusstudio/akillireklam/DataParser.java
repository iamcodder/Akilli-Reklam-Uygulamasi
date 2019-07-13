package com.patronusstudio.akillireklam;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//Json objesi olarak gelen dükkan bilgilerini dönüştürüyoruz
public class DataParser
{

    private HashMap<String,String > getSingleNearbyPlace(JSONObject googlePlacesJSON){

        HashMap<String ,String> googlePlaceMap=new HashMap<>();
        String PlaceID="-NA-";
        String NameOfPlace="-NA-";
        String vicinity="-NA-";
        String latitude="";
        String longitude="";
        String reference="";

        try {
            if(!googlePlacesJSON.isNull("place_id")){
                PlaceID=googlePlacesJSON.getString("place_id");
            }
            if(!googlePlacesJSON.isNull("name"))
            {
                NameOfPlace=googlePlacesJSON.getString("name");
            }

            if(!googlePlacesJSON.isNull("vicinity"))
            {
                vicinity=googlePlacesJSON.getString("vicinity");
            }
            latitude=googlePlacesJSON.getJSONObject("geometry").getJSONObject("location").getString("lat");
            longitude=googlePlacesJSON.getJSONObject("geometry").getJSONObject("location").getString("lng");
            reference=googlePlacesJSON.getString("reference");

            googlePlaceMap.put("place_id",PlaceID);
            googlePlaceMap.put("place_name",NameOfPlace);
            googlePlaceMap.put("vicinity",vicinity);
            googlePlaceMap.put("lat",latitude);
            googlePlaceMap.put("lng",longitude);
            googlePlaceMap.put("reference",reference);


        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return googlePlaceMap;

    }

    private List<HashMap<String ,String>> getAllNearbyPlaces(JSONArray jsonArray){

        int counter=jsonArray.length();

        List<HashMap<String ,String>> NearbyPlacesList=new ArrayList<>();

        HashMap<String,String > NearbyPlacesMap=null;

        for(int i=0;i<counter;i++){

            try {
                NearbyPlacesMap = getSingleNearbyPlace((JSONObject) jsonArray.get(i));
                NearbyPlacesList.add(NearbyPlacesMap);


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return NearbyPlacesList;
    }


    public List<HashMap<String,String>> parse(String jSONdata){

        JSONArray jsonArray=null;
        JSONObject jsonObject;

        try
        {
            jsonObject=new JSONObject(jSONdata);
            jsonArray=jsonObject.getJSONArray("results");
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return getAllNearbyPlaces(jsonArray);
    }

}

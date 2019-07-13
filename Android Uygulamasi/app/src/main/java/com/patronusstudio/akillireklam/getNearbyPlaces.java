package com.patronusstudio.akillireklam;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class getNearbyPlaces extends AsyncTask<Object,String ,String > {

    private String googlePlaceData,url;
    private GoogleMap mMap;

    private MagazaBilgileri magazaBilgileri;

    public getNearbyPlaces(MagazaBilgileri magazaBilgileri) {
        this.magazaBilgileri = magazaBilgileri;
    }

    @Override
    protected String doInBackground(Object... objects) {

        mMap=(GoogleMap) objects[0];
        url=(String) objects[1];

        downloadUrl downurl=new downloadUrl();
        try {
            googlePlaceData=downurl.ReadTheUrl(url);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return googlePlaceData;
    }

    @Override
    protected void onPostExecute(String s)
    {
        List<HashMap<String,String>>  nearbyPlacesList=null;
        DataParser dataParser=new DataParser();
        nearbyPlacesList=dataParser.parse(s);

        DisplayNearbyPlaces(nearbyPlacesList);

    }

    private void DisplayNearbyPlaces(List<HashMap<String,String>>  nearbyPlacesList){

        ArrayList<magazaModel> magaza_listsi=new ArrayList<>();

        for (int i=0;i<nearbyPlacesList.size();i++)
        {
            MarkerOptions markerOptions=new MarkerOptions();

            HashMap<String,String> googleNearbyPlace=nearbyPlacesList.get(i);
            String placeId=googleNearbyPlace.get("place_id");
            String nameOfPlace=googleNearbyPlace.get("place_name");
            String vicinity=googleNearbyPlace.get("vicinity");
            double latitude= Double.parseDouble(googleNearbyPlace.get("lat"));
            double longitude= Double.parseDouble(googleNearbyPlace.get("lng"));

            magaza_listsi.add(new magazaModel(placeId,nameOfPlace,latitude+"s"+longitude,"",""));

            LatLng latLng=new LatLng(latitude,longitude);

            markerOptions.position(latLng);
            markerOptions.title(nameOfPlace + " : "+vicinity);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));

            mMap.addMarker(markerOptions);

            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(14));
        }

        magazaBilgileri.magazaListesi(magaza_listsi);


    }
}

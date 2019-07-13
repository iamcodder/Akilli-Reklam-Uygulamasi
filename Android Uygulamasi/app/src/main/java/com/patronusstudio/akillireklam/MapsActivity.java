package com.patronusstudio.akillireklam;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.airbnb.lottie.L;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.patronusstudio.akillireklam.Activity.KampanyaActivity;
import com.patronusstudio.akillireklam.Activity.ProfileActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener
        , MagazaBilgileri, IFirebase.Database {

    private GoogleMap mMap;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Marker currentUserLocationMarker;
    private static final int Request_User_Location_Code = 99;
    private IFirebaseDatabase firebaseDatabase;
    private LocationManager locationManager;
    private boolean GpsStatus;
    private double latitude, longtitude;
    private int mesafe = 500;
    private ArrayList<magazaModel> kampanyali_magazalar , bulunan_magazalar;
    private Spinner spinner;
    private String magaza_listesi[];
    private EditText menzil, konum;
    private int spinner_tiklanan_oge=0;
    private BottomNavigationView bottomNavigationView;
    private MarkerOptions userMarkerOptions;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        firebaseDatabase = new IFirebaseDatabase(this);
        kampanyali_magazalar = new ArrayList<>();
        bulunan_magazalar=new ArrayList<>();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkUserLocationPermission();
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        magaza_listesi = getResources().getStringArray(R.array.magazalar_id);
        menzil = findViewById(R.id.edx_mesafe);
        konum = findViewById(R.id.location_search);
        bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_harita);
        progressBar=findViewById(R.id.maps_progress);
        progressBar.setVisibility(View.VISIBLE);


        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.magazalar_isimleri, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0) spinner_tiklanan_oge=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.bottom_kampanya:
                        Intent intent = new Intent(getApplicationContext(), KampanyaActivity.class);
                        intent.putParcelableArrayListExtra(Sabitler.KAMPANYALI_MAGAZALAR, kampanyali_magazalar);
                        startActivity(intent);
                        break;

                    case R.id.bottom_profile:
                        Intent intentt = new Intent(getApplicationContext(), ProfileActivity.class);
                        startActivity(intentt);
                        break;
                }

                return false;
            }
        });
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            mMap.setMyLocationEnabled(true);

            buildGoogleApiClient();

        }
    }

    public void checkUserLocationPermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, Request_User_Location_Code);
        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == Request_User_Location_Code &&
                grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                grantResults[1] == PackageManager.PERMISSION_GRANTED) {

            if ((ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) &&
                    (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {

                locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
                GpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
                Toast.makeText(this, "Lütfen konum hizmetlerini etkinleştirin", Toast.LENGTH_SHORT).show();

                if (googleApiClient == null) {
                    buildGoogleApiClient();
                }
                mMap.setMyLocationEnabled(true);
            }
        } else {
            Toast.makeText(this, "Konum iznini veriniz", Toast.LENGTH_SHORT).show();
        }

    }


    protected void buildGoogleApiClient() {
        progressBar.setVisibility(View.VISIBLE);
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        googleApiClient.connect();

        Toast.makeText(this, "Konum yükleniyor...", Toast.LENGTH_SHORT).show();

    }


    private String getUrl(double latitude, double longtitude, String nearByPlace) {

        return "https://maps.googleapis.com/maps/api/place/nearbysearch/json?" + "location=" + latitude + "," + longtitude +
                "&radius=" + mesafe +
                "&type=" + nearByPlace +
                "&sensor=true" +
                "&key=" + Google Clouddan Alınan Key;

    }

    @Override
    public void onLocationChanged(Location location) {
        progressBar.setVisibility(View.GONE);
        latitude = location.getLatitude();
        longtitude = location.getLongitude();


        //eğer kullanıcının bulunduğu yer boş değilse önceki markeri sil.(Marker:kullanıcının bulunduğu yeri gösteren kırmızı şey)
        if (currentUserLocationMarker != null) {

            currentUserLocationMarker.remove();

        }

        //kullanıcının geçerli bulunduğu konumları alıyoruz
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

        //Kullanıcının bulunduğu konumu marker nesnesinin konumuna atıyoruz.
        // (önce pozisyon,sonra başlık,sonra marker rengi özelleştirmeleri ypılıyor)
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Bulunduğunuz Konum");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));

        //son olarakta kullanıcının bulunduğu konumu son konumla değiştiriyoruz.
        //Mesela arabayla giderken bulunduğumuz yer sürekli değişiyor.Burada da bunu yapıyoruz.Hareket halinde olunca
        //eski yerimizi siliyoruz yeni yerimizi ekliyoruz.
        currentUserLocationMarker = mMap.addMarker(markerOptions);

        //Harita açıldığı zaman ve hareket halindeyken aynı şekilde haritanında zoomlanmasını ve hareket etmesini istiyoruz.
        //Bu işlemleri camera ile burada ayarlıyoruz.
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(14));

        //son olarak yukarıda son konumla yer değiştirme işleminin son aşaması burada.
        //hareket ettiysek önceki bulunduğumuz konumu sil diyoruz burada.
        if (googleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
        }

    }

    //
    @Override
    public void onConnected(@Nullable Bundle bundle) {

        locationRequest = new LocationRequest();
        locationRequest.setInterval(1100);
        locationRequest.setFastestInterval(1100);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }
    //
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this,connectionResult.getErrorMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void magazaListesi(List<magazaModel> magazaListesi) {
        progressBar.setVisibility(View.GONE);

        if (magazaListesi.size() > 0) {
            firebaseDatabase.magazalariKarsilastir(magazaListesi);

            firebaseDatabase.IDyeGoreMagazalariCek(magazaListesi);
        } else {
            Toast.makeText(this, "Aramanıza uygun mağaza bulunamadı...", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void cevredeki_magazalar(List<magazaModel> cevredeki_magazalar) {

        kampanyali_magazalar.clear();

        if (cevredeki_magazalar.size() > 0) {

            for (int i = 0; i < cevredeki_magazalar.size(); i++) {


                if (!cevredeki_magazalar.get(i).getKampanyaIcerik().equals("")) {
                    kampanyali_magazalar.add(cevredeki_magazalar.get(i));
                }
            }


        } else {
            Toast.makeText(this, "Aradığınız kriterlere uygun mağaza bulunamadı.", Toast.LENGTH_SHORT).show();
        }
    }


    public void aramaYap(View view) {
        progressBar.setVisibility(View.VISIBLE);
        String adres = konum.getText().toString();

        progressBar.setVisibility(View.VISIBLE);

        //Address nesnesi android studio ile gelen bir nesne
        //isim posta kodu vsher türlü bilgiyi tutuyor
        List<Address> addressList;
        userMarkerOptions = new MarkerOptions();

        if(currentUserLocationMarker!=null ){
            currentUserLocationMarker.remove();

        }

        if (!TextUtils.isEmpty(adres)) {
            Geocoder geocoder = new Geocoder(this);

            try {
                addressList = geocoder.getFromLocationName(adres, 6);

                if (addressList != null) {
                    for (int i = 0; i < addressList.size(); i++) {

                        Address userAddress = addressList.get(i);
                        LatLng latLng = new LatLng(userAddress.getLatitude(), userAddress.getLongitude());

                        latitude=userAddress.getLatitude();
                        longtitude=userAddress.getLongitude();

                        userMarkerOptions.position(latLng);
                        userMarkerOptions.title(adres);

                        userMarkerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));


                        currentUserLocationMarker = mMap.addMarker(userMarkerOptions);

                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                        mMap.animateCamera(CameraUpdateFactory.zoomTo(14));
                    }
                } else {
                    Toast.makeText(this, "Adres bulunamadı", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "Adres gir", Toast.LENGTH_SHORT).show();
        }
        progressBar.setVisibility(View.GONE);
    }

    public void aramaYap2(View view) {
        bulunan_magazalar.clear();

        String mesafe=menzil.getText().toString();

        if(!mesafe.equals("") && spinner_tiklanan_oge!=0){
            progressBar.setVisibility(View.VISIBLE);
            String magaza_id = magaza_listesi[spinner_tiklanan_oge];
            this.mesafe=Integer.parseInt(mesafe);
            Object[] transferData = new Object[2];

            getNearbyPlaces getNearbyPlaces = new getNearbyPlaces(this);

            mMap.clear();

            String url = getUrl(latitude, longtitude, magaza_id);
            transferData[0] = mMap;
            transferData[1] = url;

            getNearbyPlaces.execute(transferData);
            Toast.makeText(this, "Aranıyor...", Toast.LENGTH_SHORT).show();

            if(userMarkerOptions!=null && currentUserLocationMarker!=null){
                userMarkerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));

                currentUserLocationMarker = mMap.addMarker(userMarkerOptions);



            }
        }
    }

    @Override
    public void onBackPressed() {

    }
}

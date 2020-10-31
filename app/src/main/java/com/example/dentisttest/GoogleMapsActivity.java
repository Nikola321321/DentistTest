package com.example.dentisttest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.PendingResult;
import com.google.maps.model.DirectionsResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GoogleMapsActivity extends AppCompatActivity {

    public static final int PERMISSIONS_REQUEST_FINE_LOCATION = 9001;

    private static final String TAG = "GoogleMapsActivity";
    private GoogleMap map;
    private ImageButton ibGetDirections;
    private Location origin;
    Address address;


    private Boolean locationPermissonGranted = false;
    FusedLocationProviderClient fusedLocationProviderClient;

    GeoApiContext geoApiContext = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps);
        ibGetDirections = findViewById(R.id.ib_get_directions);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        initMap();

        ibGetDirections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (geoApiContext == null){
                    geoApiContext = new GeoApiContext.Builder().apiKey(getString(R.string.maps_api_key))
                            .build();
                }

                try {
                    address = getAddressLocation();
                    updateLocation();
                    getDeviceLocation(address);


                } catch (IOException e) {
                    e.printStackTrace();
                }




            }
        });
    }

    private Address getAddressLocation() throws IOException {
        Geocoder geocoder = new Geocoder(this.getApplicationContext());
        List<Address> list = new ArrayList<>();
        String address = getIntent().getStringExtra("address");
        Log.i(TAG, "getAddressLocation: " + address);
        list = geocoder.getFromLocationName(address, 1);
        Log.i(TAG, "getAddressLocation: " + list.size());
        for (int i = 0; i < list.size(); i++) {
            Log.i(TAG, "getAddressLocation: " + list.get(i));
        }

        if (list.size() > 0) {
            Address address1 = list.get(0);
            return address1;
        }
        return null;
    }

    private void moveCamera(LatLng latLng) {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f));
        MarkerOptions markerOptions = new MarkerOptions().position(latLng);
        map.addMarker(markerOptions);
    }

    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                try {
                    moveCamera(new LatLng(getAddressLocation().getLatitude(), getAddressLocation().getLongitude()));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void getLocationPermisson() {

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationPermissonGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        locationPermissonGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_FINE_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    locationPermissonGranted = true;
                }
            }
        }
    }

    private void updateLocation() {
        if (map == null) {
            return;
        }
        try {
            if (locationPermissonGranted) {
                map.setMyLocationEnabled(true);
                map.getUiSettings().setMyLocationButtonEnabled(false);
            } else {
                map.setMyLocationEnabled(false);
                map.getUiSettings().setMyLocationButtonEnabled(false);
                getLocationPermisson();
            }
        } catch (SecurityException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    private void getDeviceLocation(Address address) {
        if (locationPermissonGranted) {

            Task locationResult = fusedLocationProviderClient.getLastLocation();
            locationResult.addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if (task.isSuccessful()) {
                        origin = (Location) task.getResult();
                        calculateDirections(address);
                    }

                }
            });
        }
    }

    private void calculateDirections (Address address) {


        com.google.maps.model.LatLng destination = new com.google.maps.model.LatLng(address.getLatitude(),address.getLongitude());

        DirectionsApiRequest directions = new DirectionsApiRequest(geoApiContext);

        directions.alternatives(false);

        directions.origin(new com.google.maps.model.LatLng(origin.getLatitude(),origin.getLongitude()));

        directions.destination(destination).setCallback(new PendingResult.Callback<DirectionsResult>() {
            @Override
            public void onResult(DirectionsResult result) {
                Log.i(TAG, "onResult: info" + result.routes[0].toString());
            }

            @Override
            public void onFailure(Throwable e) {

            }
        });

    }
}

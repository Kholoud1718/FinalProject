package com.example.finalproject.activities;


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.finalproject.R;
import com.example.finalproject.callbacks.EventCallback;
import com.example.finalproject.controllers.EventController;
import com.example.finalproject.controllers.UserEventsController;
import com.example.finalproject.model.EventModel;
import com.example.finalproject.utils.SharedData;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.CameraUpdateFactory;

import java.util.ArrayList;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationClient;
    private Location currentLocation;

    private final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    ArrayList<EventModel> allEvents = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng defaultLocation = new LatLng(-37.79501631, 144.9423195);

        // Define the target zoom level you want
        float zoomLevel = 13.0f;
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(defaultLocation)
                .zoom(zoomLevel)
                .build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


        // Check for permission to access the location
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

            // Request the permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
            return;
        }

        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationButtonClickListener(() -> {
            requestLocationUpdates();
            return true;
        });
        requestLocationUpdates();
        mMap.setOnMarkerClickListener(this);
    }

    private void setEventOnMap() {
        new EventController().getEvents(new EventCallback() {
            @Override
            public void onSuccess(ArrayList<EventModel> events) {
                allEvents = events;
                for (EventModel e : events) {
                    Location eLocation = new Location("");
                    eLocation.setLatitude(e.getLatitude());
                    eLocation.setLongitude(e.getLongitude());
                    double distance = Double.MAX_VALUE;
                    if (currentLocation != null)
                        distance = currentLocation.distanceTo(eLocation) / 1000;

                    if (SharedData.selectedSports.stream().anyMatch(c -> c.getName().equals(e.getCategoryName()))
                            || SharedData.selectedSports.stream().anyMatch(c -> c.getName().equals("Any"))) {
                        if (distance <= SharedData.max_radius && distance >= SharedData.min_radius) {
                            if (e.getDate().after(SharedData.from_date) && e.getDate().before(SharedData.to_date)) {
                                mMap.addMarker(new MarkerOptions()
                                        .position(new LatLng(e.getLatitude(), e.getLongitude()))
                                        .title(e.getName())
                                        .snippet(e.getCategoryName())

                                        .icon(BitmapFromVector(
                                                getApplicationContext(),
                                                SharedData.categories_icons.get(e.getCategoryName().trim()) == null ?
                                                        R.drawable.baseline_360_24 : SharedData.categories_icons.get(e.getCategoryName().trim()))));

                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(MapsActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void requestLocationUpdates() {
        // Request location updates
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Location permission not granted", Toast.LENGTH_LONG).show();
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, location -> {
                    if (location != null) {
                        // Save current location
                        currentLocation = location;
                        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13.0f));
                        setEventOnMap();
                    } else {
                        setEventOnMap();
                        Toast.makeText(this, "Location not available", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, enable the My Location layer
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED ||
                        ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                                == PackageManager.PERMISSION_GRANTED) {
                    mMap.setMyLocationEnabled(true);
                }
            }
        }
    }
    private BitmapDescriptor BitmapFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(
                context, vectorResId);

        vectorDrawable.setBounds(
                0, 0, vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight());

        Bitmap bitmap = Bitmap.createBitmap(
                vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        String eventName = marker.getTitle();
        String eventCategory = marker.getSnippet();

        AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
        builder.setTitle("Register for " + eventCategory + " Event");
        builder.setMessage("Do you want to register for " + eventName + "?");

        builder.setPositiveButton("Yes", (dialog, which) -> {
            EventModel selectedEvent =
                    allEvents.stream().filter(event ->
                            event.getName().equals(eventName)).findFirst().orElse(null);
            if (selectedEvent != null) {
                new UserEventsController().save(selectedEvent, new EventCallback() {
                    @Override
                    public void onSuccess(ArrayList<EventModel> events) {
                        Toast.makeText(MapsActivity.this, "Event Registered successfully!", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(String message) {
                        Toast.makeText(MapsActivity.this, message, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        builder.setNegativeButton("No", (dialog, which) -> {
            dialog.dismiss();
        });
        builder.show();
        return false;
    }
}
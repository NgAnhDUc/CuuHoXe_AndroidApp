package com.example.appcuuhoxe.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appcuuhoxe.MainActivity;
import com.example.appcuuhoxe.R;
import com.example.appcuuhoxe.models.LocatonModel;
import com.example.appcuuhoxe.models.UserModel;
import com.example.appcuuhoxe.userView.InfoUserActivity;
import com.example.appcuuhoxe.utils.AndroidUtils;
import com.example.appcuuhoxe.utils.FireBaseUtils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsFragment extends Fragment implements GoogleMap.OnMarkerClickListener {
    private final int FINE_PERMISION_CODE = 1;
    private GoogleMap mMap;
    Location currentLocation;
    String address;
    TextView tv_address;
    LocatonModel locatonModel;
    FusedLocationProviderClient fusedLocationProviderClient;
    private OnMapReadyCallback callback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);
            LatLng allocation = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(allocation,15f));

            LatLng Doi1 = new LatLng(16.021360,108.209220);
            mMap.addMarker(new MarkerOptions().position(Doi1).title("Đội Cứu Hộ Quận Cẩm Lệ"));

            LatLng Doi2 = new LatLng(16.053840,108.211540);
            mMap.addMarker(new MarkerOptions().position(Doi2).title("Đội Cứu Hộ Quận Hải Châu"));

            LatLng Doi3 = new LatLng(16.099440,108.138700);
            mMap.addMarker(new MarkerOptions().position(Doi3).title("Đội Cứu Hộ Quận Liên Chiểu"));

            LatLng Doi4 = new LatLng(16.010970,108.256560);
            mMap.addMarker(new MarkerOptions().position(Doi4).title("Đội Cứu Hộ Quận Ngũ Hành Sơn"));

            LatLng Doi5 = new LatLng(16.077700,108.232240);
            mMap.addMarker(new MarkerOptions().position(Doi5).title("Đội Cứu Hộ Quận Sơn Trà"));

            LatLng Doi6 = new LatLng(16.071290,108.195310);
            mMap.addMarker(new MarkerOptions().position(Doi6).title("Đội Cứu Hộ Quận Thanh Khê"));
        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_address = view.findViewById(R.id.tv_address);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        getLastLocation();
        getLocationUser();
    }
    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},FINE_PERMISION_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location !=null){
                    Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
                    try {
                        List<Address> addresses =  geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                        address = addresses.get(0).getAddressLine(0);
//                        AndroidUtils.showToast(getContext(),address);
                        tv_address.setText(address);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    currentLocation = location;
                    SupportMapFragment mapFragment =
                            (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
                    mapFragment.getMapAsync(callback);
                }
            }
        });
    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        return false;
    }

    private void getLocationUser(){
        FireBaseUtils.myLocationDetail().get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    locatonModel = task.getResult().toObject(LocatonModel.class);
                    setLocationUser();
                }
            }
        });
    }
    private void setLocationUser(){
        double lat = currentLocation.getLatitude();
        double longtDouble = currentLocation.getLongitude();
        String addressStr = address;
        if(locatonModel !=null){
            locatonModel.setLatitude(lat);
            locatonModel.setLongtitude(longtDouble);
            locatonModel.setAdrress(addressStr);
        }else {
            locatonModel = new LocatonModel(lat,longtDouble,addressStr);
        }
        FireBaseUtils.myLocationDetail().set(locatonModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    AndroidUtils.showToast(getContext(),"locationUpdate");
                }
            }
        });
    }
}
package com.example.appcuuhoxe;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.appcuuhoxe.models.LocatonModel;
import com.example.appcuuhoxe.utils.AndroidUtils;
import com.example.appcuuhoxe.utils.FireBaseUtils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.appcuuhoxe.databinding.ActivityRecuseChooseBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class RecuseChooseActivity extends FragmentActivity implements GoogleMap.OnMarkerClickListener {
    private final int FINE_PERMISION_CODE = 1;
    private GoogleMap mMap;
    Location currentLocation;
    private ActivityRecuseChooseBinding binding;
    String address;
    String madoncuuho;
    String provide;
    RadioGroup radio_gr;
    LinearLayout btn_tienmat;
    LinearLayout btn_the;
    LocatonModel locatonModel;
    String serviceType;
    TextView tv_madoncuuho;
    Button btn_datcuuho;
    RadioButton radio_btn_1,radio_btn_2,radio_btn_3,radio_btn_4,radio_btn_5,radio_btn_6,radio_btn_7,radio_btn_8,radio_btn_9,radio_btn_10;;
    FusedLocationProviderClient fusedLocationProviderClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRecuseChooseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        radio_gr = findViewById(R.id.radio_gr);
        btn_tienmat = findViewById(R.id.btn_tienmat);
        btn_the = findViewById(R.id.btn_the);
        tv_madoncuuho = findViewById(R.id.tv_madoncuuho);
        radio_btn_1 = findViewById(R.id.radio_btn_1);
        radio_btn_2 = findViewById(R.id.radio_btn_2);
        radio_btn_3 = findViewById(R.id.radio_btn_3);
        radio_btn_4 = findViewById(R.id.radio_btn_4);
        radio_btn_5 = findViewById(R.id.radio_btn_5);
        radio_btn_6 = findViewById(R.id.radio_btn_6);
        radio_btn_7 = findViewById(R.id.radio_btn_7);
        radio_btn_8 = findViewById(R.id.radio_btn_8);
        radio_btn_9 = findViewById(R.id.radio_btn_9);
        radio_btn_10 = findViewById(R.id.radio_btn_10);
        btn_datcuuho = findViewById(R.id.btn_datcuuho);
        radio_btn_1.setOnCheckedChangeListener(listener);
        radio_btn_2.setOnCheckedChangeListener(listener);
        radio_btn_3.setOnCheckedChangeListener(listener);
        radio_btn_4.setOnCheckedChangeListener(listener);
        radio_btn_5.setOnCheckedChangeListener(listener);
        radio_btn_6.setOnCheckedChangeListener(listener);
        radio_btn_7.setOnCheckedChangeListener(listener);
        radio_btn_8.setOnCheckedChangeListener(listener);
        radio_btn_9.setOnCheckedChangeListener(listener);
        radio_btn_10.setOnCheckedChangeListener(listener);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();
        btn_tienmat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecuseChooseActivity.this,PaymentTypeActivity.class);
                intent.putExtra("paymentType","1");
                startActivity(intent);
            }
        });
        Random random = new Random();
        madoncuuho = "DCH" + random.nextInt(10000) +"D"+random.nextInt(10000);
        tv_madoncuuho.setText(madoncuuho);
        btn_the.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecuseChooseActivity.this,PaymentTypeActivity.class);
                intent.putExtra("paymentType","2");
                startActivity(intent);
            }
        });
        btn_datcuuho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(serviceType == null){
                    AndroidUtils.showToast(getApplicationContext(),"Vui lòng chọn dịch vụ cứu hộ");
                    return;
                }
                Intent intent = new Intent(RecuseChooseActivity.this,BillActivity.class);
                intent.putExtra("address",address);
                intent.putExtra("serviceType",serviceType);
                intent.putExtra("provide",provide);
                intent.putExtra("madoncuuho",madoncuuho);
                startActivity(intent);
            }
        });
    }
    CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            serviceType =buttonView.getText().toString();
        }
    };
    private OnMapReadyCallback callback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            LatLng allocation = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
            mMap.addMarker(new MarkerOptions().position(allocation).title("Điểm cứu hộ:"+address));

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(allocation, 15f));
        }
    };
    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},FINE_PERMISION_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location !=null){
                    Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                    try {
                        List<Address> addresses =  geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                        address = addresses.get(0).getAddressLine(0);
                        provide = addresses.get(0).getSubAdminArea();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    currentLocation = location;
                    SupportMapFragment mapFragment =
                            (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                    mapFragment.getMapAsync(callback);
                }
            }
        });
    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        return false;
    }
}

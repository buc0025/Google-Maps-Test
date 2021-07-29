package com.example.googlemapstest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private Button createUserBtn;
    private EditText userName;
    private EditText userEmail;
    private EditText userPhone;

    private EditText zipCode;
    private Button geoCodeBtn;
    private TextView latLong;

//    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//    private DatabaseReference root = firebaseDatabase.getReference().child("Users");
//    private DatabaseReference root2 = firebaseDatabase.getReference().child("Users").child("orders");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zipCode = findViewById(R.id.zipCode);
        geoCodeBtn = findViewById(R.id.geoCodeBtn);
        latLong = findViewById(R.id.latLong);

        geoCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = zipCode.getText().toString();
                GeoLocation geoLocation = new GeoLocation();
                geoLocation.getAddress(address, getApplicationContext(), new GeoHandler());
            }
        });



//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
//                startActivity(intent);
//            }
//        });

    }

    private class GeoHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            String address;
            switch (msg.what) {
                case 1:
                    Bundle bundle = msg.getData();
                    address = bundle.getString("address");
                    break;
                default:
                    address = null;
            }
            latLong.setText(address);
        }
    }
}
package com.example.shubham.googlemap_contacts;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String[][] word = new String[5][4];
    int count =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        //TextView tv = (TextView) findViewById(R.id.text); //Find the view by its id

        File sdcard = Environment.getExternalStorageDirectory(); //Find the directory for the SD Card using the API

        File file = new File(sdcard, "contacts.txt");   //Get the text file

        if (file.exists()) {
            StringBuilder text = new StringBuilder();   //Read text from file

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {

                    word[count]= line.split("\\s");
                    text.append(line);
                    text.append('\n');

                    Intent contactIntent = new Intent(ContactsContract.Intents.Insert.ACTION);
                    contactIntent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

                    contactIntent
                            .putExtra(ContactsContract.Intents.Insert.NAME,word[count][0])
                            .putExtra(ContactsContract.Intents.Insert.EMAIL, word[count][1])
                            .putExtra(ContactsContract.Intents.Insert.SECONDARY_PHONE,word[count][2])
                            .putExtra(ContactsContract.Intents.Insert.PHONE, word[count][3]);
                    startActivityForResult(contactIntent, 1);
                    count=count+1;
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(MapsActivity.this, "Error reading file", Toast.LENGTH_SHORT).show();
            }

        } else {
           // tv.setText("Sorry file doesn't exist");
        }
        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
      /*  double a=-34.2;
        double b=151;
        LatLng sydney = new LatLng(a, b);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Hi Shubham"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/

        double a = Double.parseDouble(word[0][2]);
        double b = Double.parseDouble(word[0][3]);
        a=a/1000000;
        b=b/1000000;
        LatLng p1 = new LatLng(a,b);
        mMap.addMarker(new MarkerOptions().position(p1).title(word[0][0]));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(p1));

        a = Double.parseDouble(word[1][2]);
        b = Double.parseDouble(word[1][3]);
        a=a/1000000;
        b=b/1000000;
        LatLng p2 = new LatLng(a,b);
        mMap.addMarker(new MarkerOptions().position(p2).title(word[1][0]));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(p2));

        a = Double.parseDouble(word[2][2]);
        b = Double.parseDouble(word[2][3]);
        a=a/1000000;
        b=b/1000000;
        LatLng p3 = new LatLng(a,b);
        mMap.addMarker(new MarkerOptions().position(p3).title(word[2][0]));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(p3));

        a = Double.parseDouble(word[3][2]);
        b = Double.parseDouble(word[3][3]);
        a=a/1000000;
        b=b/1000000;
        LatLng p4 = new LatLng(a,b);
        mMap.addMarker(new MarkerOptions().position(p4).title(word[3][0]));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(p4));
        a = Double.parseDouble(word[4][2]);
        b = Double.parseDouble(word[4][3]);
        a=a/1000000;
        b=b/1000000;
        LatLng p5 = new LatLng(a,b);
        mMap.addMarker(new MarkerOptions().position(p5).title(word[4][0]));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(p5));
    }
}

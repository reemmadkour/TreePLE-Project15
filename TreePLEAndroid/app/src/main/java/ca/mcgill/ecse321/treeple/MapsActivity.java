package ca.mcgill.ecse321.treeple;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
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

       // final EditText longText = (EditText)findViewById(R.id.longitude);
        //final EditText latText = (EditText)findViewById(R.id.latitude);
       // String longitude = longText.getText().toString(); // Same
      //  Double lng  = Double.parseDouble(longitude);

        //String latitude = latText.getText().toString();
       // Double lat = Double.parseDouble(latitude);
       // LatLng tree = new LatLng(lat, lng);

        Intent i = getIntent();
        Double lat = i.getDoubleExtra("latitude", 0);
        Double lng = i.getDoubleExtra("longitude", 0);

        LatLng tree = new LatLng(lat, lng);
      mMap.addMarker(new MarkerOptions().position(tree).icon(BitmapDescriptorFactory.fromResource(R.drawable.tree)));

        // Add a marker in Sydney and move the camera
       //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney").icon(BitmapDescriptorFactory.fromResource(R.drawable.tree)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(tree));
    }



   /* @Override
    public boolean onMarkerClick(Marker marker) {
        marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.tree));
        return false;
    }*/




}

package ca.mcgill.ecse321.treeple;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


public class MapsActivity extends AppCompatActivity implements GoogleMap.OnMarkerClickListener,OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker Marker1;
    private Marker Marker2;
    private Marker Marker3;
    private Marker Marker4;
    private Marker Marker5;
    private Marker Marker6;
    private Marker Marker8;
    private Marker Marker9;
    private Marker Marker10;


    private String error = null;
    




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

        //getting latitude longitude input from plant tree page
        Intent i = getIntent();
        Double lat = i.getDoubleExtra("latitude", 0);
        Double lng = i.getDoubleExtra("longitude", 0);

        LatLng tree = new LatLng(lat, lng);

        //creating a new marker at that position, with the tree icon
        Marker1 = mMap.addMarker(new MarkerOptions().position(tree).icon(BitmapDescriptorFactory.fromResource(R.drawable.tree)).title("Willow"));


        //on marker click, display marker position, tree status and tree species
        onMarkerClick(Marker1);

        //TODO


       /* Intent i2 = getIntent();
        Double lat_cut = i2.getDoubleExtra("latitude_cut", 0);
        Double lgt_cut = i2.getDoubleExtra("longitude_cut", 0);

        LatLng tree_cut = new LatLng(lat_cut, lgt_cut);

        mMap.addMarker((new MarkerOptions().position(tree_cut).icon(BitmapDescriptorFactory.fromResource(R.drawable.trunk1))));*/



        /*planted_markers.add(Marker1);

        for(Marker marker : planted_markers){
            mMap.addMarker((new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.tree))));
        }*/







        // ** HARDCODIND **

        //Montreal: 45.5017° N, 73.5673°
        /*LatLng tree1 = new LatLng(45.5017, -73.5673);
        Marker2 = mMap.addMarker(new MarkerOptions().position(tree1).icon(BitmapDescriptorFactory.fromResource((R.drawable.tree))).title("Pine").snippet("Planted"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(tree1));

        onMarkerClick(Marker2);*/



        mMap.setOnMarkerClickListener(this);

    }




    @Override
    public boolean onMarkerClick(final Marker marker) {

        if (marker.equals(marker)) {
            //handle click here
            Toast.makeText(this, "Position:  "  +  marker.getPosition()+ "\n"  + "Species:  " + marker.getTitle() + "\n" + "Status:  " + marker.getSnippet(),
                    Toast.LENGTH_SHORT).show();
            return true;

        }
        else{
            return false;
    }


    }


    private void refreshErrorMessage() {
        // set the error message
        TextView tvError = (TextView) findViewById(R.id.error);
        tvError.setText(error);

        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }

    }





}







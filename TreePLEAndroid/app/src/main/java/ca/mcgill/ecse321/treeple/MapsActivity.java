package ca.mcgill.ecse321.treeple;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.List;


public class MapsActivity extends FragmentActivity implements GoogleMap.OnMarkerClickListener,OnMapReadyCallback {

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
        Marker1 = mMap.addMarker(new MarkerOptions().position(tree).icon(BitmapDescriptorFactory.fromResource(R.drawable.tree)).title("Willow"));

        onMarkerClick(Marker1);

        //Montreal: 45.5017° N, 73.5673°
        LatLng tree1 = new LatLng(45.5017, -73.5673);
        Marker2 = mMap.addMarker(new MarkerOptions().position(tree1).icon(BitmapDescriptorFactory.fromResource((R.drawable.tree))).title("Pine").snippet("Planted"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(tree1));

        onMarkerClick(Marker2);

        LatLng tree7 = new LatLng(43.7517, -71.9673);
        Marker8 = mMap.addMarker(new MarkerOptions().position(tree7).icon(BitmapDescriptorFactory.fromResource((R.drawable.tree))).title("Pine").snippet("Planted"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(tree1));

        onMarkerClick(Marker8);

        LatLng tree8 = new LatLng(44.2017, -73.8673);
        Marker9 = mMap.addMarker(new MarkerOptions().position(tree8).icon(BitmapDescriptorFactory.fromResource((R.drawable.trunk1))).title("Pine").snippet("To be Cut Down"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(tree1));

        onMarkerClick(Marker9);

        LatLng tree9 = new LatLng(42.3017, -73.3573);
        Marker10 = mMap.addMarker(new MarkerOptions().position(tree9).icon(BitmapDescriptorFactory.fromResource((R.drawable.trunk1))).title("Willow").snippet("To be Cut Down"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(tree1));

        onMarkerClick(Marker10);



        LatLng tree2 = new LatLng(50.5017, -70.5673);
        Marker3 = mMap.addMarker(new MarkerOptions().position(tree2).icon(BitmapDescriptorFactory.fromResource((R.drawable.tree))).title("Willow").snippet("Planted"));
        onMarkerClick(Marker3);

        LatLng tree3 = new LatLng(47.9017, -72.9673);
        Marker4 = mMap.addMarker(new MarkerOptions().position(tree3).icon(BitmapDescriptorFactory.fromResource((R.drawable.tree))).title("Willow").snippet("Planted"));
        onMarkerClick(Marker4);

        LatLng tree4 = new LatLng(46.603, -69.6549);
        Marker5 = mMap.addMarker(new MarkerOptions().position(tree4).icon(BitmapDescriptorFactory.fromResource((R.drawable.trunk1))).title("Willow").snippet("To be Cut Down"));
        onMarkerClick(Marker5);

        LatLng tree5 = new LatLng(49.5017, -69.6693);
        Marker6 = mMap.addMarker(new MarkerOptions().position(tree5).icon(BitmapDescriptorFactory.fromResource((R.drawable.diseased))).title("Cedar").snippet("Diseased"));
        onMarkerClick(Marker6);

        mMap.setOnMarkerClickListener(this);
        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney").icon(BitmapDescriptorFactory.fromResource(R.drawable.tree)));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(tree));
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

    /** Called when the user clicks a marker. */
   /* @Override
    public boolean onMarkerClick(final Marker marker) {

        // Retrieve the data from the marker.
        Integer clickCount = (Integer) marker.getTag();

        // Check if a click count was set, then display the click count.
        if (clickCount != null) {
            clickCount = clickCount + 1;
            marker.setTag(clickCount);
            Toast.makeText(this,
                    marker.getPosition() +
                            " has been clicked " + clickCount + " times.",
                    Toast.LENGTH_SHORT).show();
        }

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;*/
    }




}




   /* @Override
    public boolean onMarkerClick(Marker marker) {
        marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.tree));
        return false;
    }*/






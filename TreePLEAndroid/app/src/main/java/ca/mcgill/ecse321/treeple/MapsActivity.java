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
import android.widget.Spinner;
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

    private List<Marker> planted;


    private String error = null;


    private List<Double> l = new ArrayList<>();
    private List<Double> l2 = new ArrayList<>();

    List<LatLng> position = new ArrayList<>();


    private List<String> munName = new ArrayList<>();
    private ArrayAdapter<String> munAdapter;

    TextView msg;






    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Spinner munSpinner = (Spinner) findViewById(R.id.spinnerl);

        msg = (TextView)findViewById(R.id.error_m);

        munAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, munName);
        munAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        munSpinner.setAdapter(munAdapter);

        refreshListLat(munAdapter ,munName, "trees");
        refreshListLgt(munAdapter ,munName, "trees");
        position = ListTreeByLocation();






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


    public void refreshMap(View view) {
        //refreshListLat(munAdapter ,munName, "trees");
        //refreshListLgt(munAdapter ,munName, "trees");


        int i=0;
        i++;
        position = ListTreeByLocation();
        System.out.println("l" + l);
        System.out.println("l2" + l2);
        System.out.println("On CLICK" + position);

        if(i<=1) {
            for (LatLng l : position) {
                Marker m = mMap.addMarker(new MarkerOptions().position(l).icon(BitmapDescriptorFactory.fromResource(R.drawable.tree)));
                onMarkerClick(m);
            }
        }

        mMap.setOnMarkerClickListener(this);

    }

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
        TextView tvError = (TextView) findViewById(R.id.error_m);
        tvError.setText(error);

        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }

    }


    private void refreshListLat(final ArrayAdapter<String> adapter, final List<String> names,String restFunctionName) {
        HttpUtils.get(restFunctionName, new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                names.clear();
                names.add("Please select...");
                for( int i = 0; i < response.length(); i++){
                    //JSONObject mun = new JSONObject();
                    try {
                        //names.add(response.getJSONObject(i).getString("name"));
                        names.add(response.getJSONObject(i).getString("latitude"));

                        Double latitude = Double.parseDouble(names.get(i));
                        if(response.getJSONObject(i).getJSONObject("currentStatus").getString("treeState").matches("Planted")) {
                            l.add(latitude);
                        }

                        //Double.parseDouble(aString)

                    } catch (Exception e) {
                        error += e.getMessage();

                    }
                    refreshErrorMessage();
                }
                System.out.println(names);
                System.out.println("list " + l);

                adapter.notifyDataSetChanged();
                //adapter2.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }
        });
    }


    private void refreshListLgt(final ArrayAdapter<String> adapter, final List<String> names,String restFunctionName) {
        HttpUtils.get(restFunctionName, new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                names.clear();
                names.add("Please select...");
                for( int i = 0; i < response.length(); i++){
                    //JSONObject mun = new JSONObject();
                    try {

                        //names.add(response.getJSONObject(i).getString("name"));
                        names.add(response.getJSONObject(i).getString("longitude"));

                        Double longitude = Double.parseDouble(names.get(i));

                        if(response.getJSONObject(i).getJSONObject("currentStatus").getString("treeState").matches("Planted")) {
                            l2.add(longitude);
                        }




                        //Double.parseDouble(aString)

                    } catch (Exception e) {
                        error += e.getMessage();

                    }
                    refreshErrorMessage();
                }
                System.out.println(names);
                System.out.println("list " + l2);





                adapter.notifyDataSetChanged();
                //adapter2.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }
        });
    }








    private List<LatLng> ListTreeByLocation(){
        if(position.size()>0) {
            position.clear();
        }

        for(int i=0; i<l.size(); i++){
            LatLng p = new LatLng(l.get(i), l2.get(i));
            position.add(p);
        }


        return position;
    }






}







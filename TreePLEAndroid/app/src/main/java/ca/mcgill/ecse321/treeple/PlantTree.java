package ca.mcgill.ecse321.treeple;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by student on 07/03/18.
 */


import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


public class PlantTree extends AppCompatActivity {

    private String error ;
    Button ok;
    Button mapsv;
    Button mainPlant;
    Button back, help;
    EditText diameter, height, longitude, latitude, userName;
    private GoogleMap mMap;

    TextView message;

    private List<String> municipalities = new ArrayList<>();
    private ArrayAdapter<String> munAdapter;



    private List<String> species = new ArrayList<>();
    private ArrayAdapter<String> speciesAdapter;

    private List<String> landType = new ArrayList<>();
    private ArrayAdapter<String> landAdapter;

    Spinner speciesSpinner, landSpinner , munSpinner;
    private double aDouble;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.planttree);

        //maps view button takes you to google maps

        mapsv = (Button) findViewById(R.id.viewmaps);
        ok = (Button)findViewById(R.id.plantTree);
        back = (Button)findViewById(R.id.back);
        help = (Button)findViewById(R.id.help);


        help.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(PlantTree.this, HelpPlant.class);
                startActivity(intent);

            }

        });


        mapsv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

               // Intent sendingIntent = new Intent(PlantTree.this, MapsActivity.class);
                //Intent i = new Intent(PlantTree.this, MapsActivity.class);
                Intent intent = new Intent(PlantTree.this, MapsActivity.class);

                startActivity(intent);

            }

        });

        mainPlant = (Button)findViewById(R.id.PlantTreeTitle);

        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(PlantTree.this, Options.class);
                startActivity(intent);

            }

        });

        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(PlantTree.this, AfterPlant.class);
                startActivity(intent);

            }

        });



        // set species Spinner

        species.add("Please Select");
        species.add("Willow");
        //species.add("Cedar");
        speciesAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, species);
        speciesAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );


        speciesSpinner = (Spinner)findViewById(R.id.species);
        speciesSpinner.setAdapter(speciesAdapter);


        // set landtype spinner
        landType.add("Please Select");
        landType.add("Institutional");

        landAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, landType);
        landAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        landSpinner = (Spinner)findViewById(R.id.landtype);
        landSpinner.setAdapter(landAdapter);

        //set Municipalities Spinner
        municipalities.add("Please Select");
        municipalities.add("Montreal");
        municipalities.add("Laval");
        munSpinner = (Spinner) findViewById(R.id.municipality);

        munAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, municipalities);
        munAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        munSpinner.setAdapter(munAdapter);



        diameter = (EditText)findViewById(R.id.diameter);
        height= (EditText)findViewById(R.id.height);

        longitude = (EditText)findViewById(R.id.longitude);
        latitude = (EditText)findViewById(R.id.latitude);


    }




    private void refreshList(final ArrayAdapter<String> adapter,final  List<String> names, String restFunctionName) {
        HttpUtils.get(restFunctionName, new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                names.clear();
                names.add("Please select...");
                for( int i = 0; i < response.length(); i++){
                    try {
                        names.add(response.getJSONObject(i).getString("name"));
                    } catch (Exception e) {
                        error += e.getMessage();
                    }
                    refreshErrorMessage();
                }
                adapter.notifyDataSetChanged();
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



    //refresh error messge to display error message on screen
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




    //addTrees
    public void addTree(View v) {


        //municipality spinner

        munSpinner = (Spinner) findViewById(R.id.municipality);

       // munSpinner.setText(i.getStringExtra("municipality"));
        error = "";
        RequestParams rp = new RequestParams();

        rp.add("municipality", munSpinner.getSelectedItem().toString());


        speciesSpinner = (Spinner)findViewById(R.id.species);
        landSpinner = (Spinner)findViewById(R.id.landtype);

        diameter= (EditText)findViewById(R.id.diameter);
        height = (EditText)findViewById(R.id.height);

        longitude = (EditText)findViewById(R.id.longitude);
        latitude = (EditText)findViewById(R.id.latitude);

        Intent intent = new Intent(PlantTree.this, MapsActivity.class);
        intent.putExtra("longitude", longitude.getText().toString());
        intent.putExtra("latitude", latitude.getText().toString());

        message = (TextView)findViewById(R.id.textView5) ;

        // take username data input from welcomePage
        Intent i = getIntent();
       String userName = i.getStringExtra("userName");

       // double height1 = Double.parseDouble(height.getText().toString());


        //adding to request parameters

        rp.add("species", speciesSpinner.getSelectedItem().toString());
        rp.add("landType", landSpinner.getSelectedItem().toString());
        rp.add("height",height.getText().toString() );
        rp.add("diameter", diameter.getText().toString());
        rp.add("longitude", longitude.getText().toString());
        rp.add("latitude", latitude.getText().toString());
        //rp.add("userName", userName);

        HttpUtils.post("PlantTree/" + userName , rp, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                refreshErrorMessage();
                ((Spinner) findViewById(R.id.municipality)).getSelectedItem();
                ((Spinner) findViewById(R.id.species)).getSelectedItem();
                ((Spinner) findViewById(R.id.landtype)).getSelectedItem();
                message.setVisibility(View.VISIBLE);

                //TODO: Test this pop up dialog!
                /*ok = (Button) findViewById(R.id.button);
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "Congatulations! you have successfully planted a tree!", Toast.LENGTH_LONG);
                    }
                });*/


                if(((Spinner) findViewById(R.id.municipality)).getSelectedItem()== "Please Select" || ((Spinner) findViewById(R.id.species)).getSelectedItem() == "Please Select" ||((Spinner) findViewById(R.id.landtype)).getSelectedItem()=="Please Select" ){
                    error = "Invalid Selection. Please pick an option!";
                }
                diameter.setText("");
                height.setText("");
                longitude.setText("");
                latitude.setText("");
                //userName.setText("");
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

        longitude.setText("");
        latitude.setText("");
        height.setText("");
        diameter.setText("");
        munSpinner.setSelection(0);
        speciesSpinner.setSelection(0);
        landSpinner.setSelection(0);

       refreshErrorMessage();
    }




}






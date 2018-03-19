package ca.mcgill.ecse321.treeple;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
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



public class PlantTree extends AppCompatActivity{

    private GoogleMap mMap;
    private String error ;
    Button button;
    private List<String> municipalities = new ArrayList<>();
    private ArrayAdapter<String> munAdapter;

    private List<String> trees = new ArrayList<>();
    private ArrayAdapter<String> treeAdapter;



        //When click on viewmaps (View Maps) it takes you to google maps
        public void addListenerOnButton() {

            final Context context = this;

          button = (Button) findViewById(R.id.viewmaps);

            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {

                    Intent intent = new Intent(context, MapsActivity.class);
                    startActivity(intent);

                }

            });

        }






    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.planttree);

        addListenerOnButton();


        //userType spinner
        List userType = new ArrayList<String>();
        userType.add("Please Select");
        userType.add("Local User");
        userType.add("Scientist");

        ArrayAdapter<String> userArrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, userType);
        userArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );


        //Municipalities Spinner
        municipalities.add("Please Select");
        municipalities.add("Montreal");
        municipalities.add("Laval");
        final Spinner munSpinner = (Spinner) findViewById(R.id.municipality);

        munAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, municipalities);
        munAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        munSpinner.setAdapter(munAdapter);


        //species Spinner
        List species = new ArrayList<String>();
        species.add("Please Select");
        species.add("Willow");
        //species.add("Cedar");
        ArrayAdapter<String> speciesArrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, species);
        speciesArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );


        final Spinner speciesSpinner = (Spinner)findViewById(R.id.species);
        speciesSpinner.setAdapter(speciesArrayAdapter);


        //landtype spinner
        List landType = new ArrayList<String>();
        landType.add("Please Select");
        landType.add("Institutional");

        ArrayAdapter<String> landArrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, landType);
        landArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );


        final Spinner landSpinner = (Spinner)findViewById(R.id.landtype);
        landSpinner.setAdapter(landArrayAdapter);

        final EditText diameterText = (EditText)findViewById(R.id.diameter);
        final EditText heightText = (EditText)findViewById(R.id.height);

        final EditText longitudeText = (EditText)findViewById(R.id.longitude);
        final EditText latitudeText = (EditText)findViewById(R.id.latitude);
        final EditText usernameText = (EditText)findViewById(R.id.username_entry);

        // Get initial
        refreshLists(this.getCurrentFocus());
    }

    public void refreshLists(View view) {

        refreshList(munAdapter, municipalities, "municipalities");
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
        final Spinner munSpinner = (Spinner) findViewById(R.id.municipality);
        error = "";
        RequestParams rp = new RequestParams();
        rp.add("municipality", munSpinner.getSelectedItem().toString());


        final Spinner speciesSpinner = (Spinner)findViewById(R.id.species);

        final Spinner landSpinner = (Spinner)findViewById(R.id.landtype);

        final EditText diameterText = (EditText)findViewById(R.id.diameter);
        final EditText heightText = (EditText)findViewById(R.id.height);

        final EditText longText = (EditText)findViewById(R.id.longitude);
        final EditText latText = (EditText)findViewById(R.id.latitude);
        final EditText usernameText = (EditText)findViewById(R.id.username_entry);



        //adding to request parameters

        rp.add("species", speciesSpinner.getSelectedItem().toString());
        rp.add("landType", landSpinner.getSelectedItem().toString());
        rp.add("height", heightText.toString());
        rp.add("diameter", diameterText.toString());
        rp.add("longitude", longText.toString());
        rp.add("latitude", latText.toString());
        rp.add("userName", usernameText.toString());

        HttpUtils.post("PlantTree/" , rp, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                refreshErrorMessage();
                ((Spinner) findViewById(R.id.municipality)).getSelectedItem();
                ((Spinner) findViewById(R.id.species)).getSelectedItem();
                ((Spinner) findViewById(R.id.landtype)).getSelectedItem();
                if(((Spinner) findViewById(R.id.municipality)).getSelectedItem()== "Please Select" || ((Spinner) findViewById(R.id.species)).getSelectedItem() == "Please Select" ||((Spinner) findViewById(R.id.landtype)).getSelectedItem()=="Please Select" ){
                    error = "Invalid Selection. Please pick an option!";
                }
                diameterText.setText("");
                heightText.setText("");
                longText.setText("");
                latText.setText("");
                usernameText.setText("");
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

        munSpinner.setSelection(0);
        speciesSpinner.setSelection(0);
        landSpinner.setSelection(0);

        refreshErrorMessage();
    }




        /*
        public void addMunicipality(View v){

            error="";
            final TextView tv = (TextView) findViewById(R.id.newMun_name);

            HttpUtils.post("municipality/" + tv.getText().toString(), new RequestParams(), new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    refreshErrorMessage();
                    tv.setText("");
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
        }*/



}






package ca.mcgill.ecse321.treeple;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
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



    public class MainActivity  extends AppCompatActivity{

        private GoogleMap mMap;
        private String error ;
        Button button;
        private List<String> municipalities = new ArrayList<>();
        private ArrayAdapter<String> munAdapter;



        // When click on button1 (View Maps) it takes you to google maps
        public void addListenerOnButton() {

            final Context context = this;

            button = (Button) findViewById(R.id.button1);

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
            setContentView(R.layout.main_activity);

            addListenerOnButton();
            Spinner munSpinner = (Spinner) findViewById(R.id.munspinner);



            munAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, municipalities);
            munAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            munSpinner.setAdapter(munAdapter);

            //height and diameter population
            List numbers = new ArrayList<Integer>();
            for (int i = 0; i <= 100; i++) {
                numbers.add(Integer.toString(i));
            }

            ArrayAdapter<Integer> spinnerArrayAdapter = new ArrayAdapter<Integer>(
                    this, android.R.layout.simple_spinner_item, numbers);
            spinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );

            //height spinner
            final Spinner heightSpinner = (Spinner)findViewById(R.id.heightspinner);
            heightSpinner.setAdapter(spinnerArrayAdapter);
            //diameter spinner
            final Spinner diameterSpinner = (Spinner) findViewById(R.id.diameterspinner);
            diameterSpinner.setAdapter(spinnerArrayAdapter);

            //species Spinner
            List species = new ArrayList<String>();
            species.add("Willow");
            //species.add("Cedar");
            ArrayAdapter<String> speciesArrayAdapter = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, species);
            speciesArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );


            final Spinner speciesSpinner = (Spinner)findViewById(R.id.speciesspinner);
            speciesSpinner.setAdapter(speciesArrayAdapter);


            //landtype spinner
            List landType = new ArrayList<String>();
            landType.add("Institutional");

            ArrayAdapter<String> landArrayAdapter = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, landType);
            landArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );


            final Spinner landSpinner = (Spinner)findViewById(R.id.landspinner);
            landSpinner.setAdapter(landArrayAdapter);


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
            final Spinner munSpinner = (Spinner) findViewById(R.id.munspinner);
            error = "";
            RequestParams rp = new RequestParams();
            rp.add("municipality", munSpinner.getSelectedItem().toString());

            //height spinner
            final Spinner heightSpinner = (Spinner)findViewById(R.id.heightspinner);

            //diameter spinner
            final Spinner diameterSpinner = (Spinner) findViewById(R.id.diameterspinner);

            final Spinner speciesSpinner = (Spinner)findViewById(R.id.speciesspinner);

            final Spinner landSpinner = (Spinner)findViewById(R.id.landspinner);


            //adding to request parameters
            rp.add("height", heightSpinner.getSelectedItem().toString());
            rp.add("diameter", diameterSpinner.getSelectedItem().toString());
            rp.add("species", speciesSpinner.getSelectedItem().toString());
            rp.add("landType", landSpinner.getSelectedItem().toString());

            HttpUtils.post("PlantTree/" , rp, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    refreshErrorMessage();
                    ((Spinner) findViewById(R.id.munspinner)).getSelectedItem();
                    ((Spinner) findViewById(R.id.diameterspinner)).getSelectedItem();
                    ((Spinner) findViewById(R.id.heightspinner)).getSelectedItem();
                    ((Spinner) findViewById(R.id.speciesspinner)).getSelectedItem();
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
            heightSpinner.setSelection(0);
            diameterSpinner.setSelection(0);
            speciesSpinner.setSelection(0);

            refreshErrorMessage();
        }




        //add trees
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
        }



    }






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

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
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


import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
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

//import cz.msebera.android.httpclient.entity.mime.Header;

    public class MainActivity  extends AppCompatActivity{

        private GoogleMap mMap;
        private String error ;
        Button button;
        private List<String> municipalities = new ArrayList<>();
        private ArrayAdapter<String> munAdapter;




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
            final Spinner munSpinner = (Spinner) findViewById(R.id.munspinner);
            error = "";
            RequestParams rp = new RequestParams();
            rp.add("municipality", munSpinner.getSelectedItem().toString());

            final TextView tv = (TextView) findViewById(R.id.AddTree);
            HttpUtils.post("plantTree/" + tv.getText().toString(), rp, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    refreshErrorMessage();
                    tv.setText("");
                    ((Spinner) findViewById(R.id.munspinner)).getSelectedItem();

                }
                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    try {
                        error += errorResponse.get("message").toString();
                    } catch (JSONException e) {
                        error += e.getMessage();
                    }
                    munSpinner.setSelection(0);
                    refreshErrorMessage();
                }
            });
        }




        //add trees
        public void addMunicipality(View v){
            TextView tv = (TextView) findViewById(R.id.newMun_name);
            String name = tv.getText().toString();
            RequestParams rp = new RequestParams();

            HttpUtils.post("municipalities/" + name, rp, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    refreshErrorMessage();
                    ((TextView) findViewById(R.id.newMun_name)).setText("");
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






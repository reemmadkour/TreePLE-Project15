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



    public class MainActivity  extends AppCompatActivity{

        private GoogleMap mMap;
        private String error ;
        Button button;
        private List<String> municipalities = new ArrayList<>();
        private ArrayAdapter<String> munAdapter;

        private List<String> userType = new ArrayList<>();
        private ArrayAdapter<String> userAdapter;

        private List<String> trees = new ArrayList<>();
        private ArrayAdapter<String> treeAdapter;



        /* When click on viewmaps (View Maps) it takes you to google maps
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

        }*/



        // When click on ok it takes you to options
        public void addListenerOnButton2() {

            final Context context = this;

            Button options  = (Button) findViewById(R.id.ok_1);

            options.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {

                    setContentView(R.layout.options);
                }

            });

        }







        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.welcomepage);

           // addListenerOnButton();
            addListenerOnButton2();
           // addListenerOnButton3();
          //  addListenerOnButton4();
            //addListenerOnButton5();

            //userType spinner

            //userType.add("Please Select");
            userType.add("Local User");
            userType.add("Scientist");
            final Spinner userSpinner = (Spinner) findViewById(R.id.userType);

            userAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, userType);
            userAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
            userSpinner.setAdapter(userAdapter);


            //Municipalities Spinner
           // municipalities.add("Please Select");
            municipalities.add("Montreal");
            municipalities.add("Laval");
            final Spinner munSpinner = (Spinner) findViewById(R.id.municipality);

            munAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, municipalities);
            munAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            munSpinner.setAdapter(munAdapter);



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






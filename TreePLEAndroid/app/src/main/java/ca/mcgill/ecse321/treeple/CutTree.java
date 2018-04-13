package ca.mcgill.ecse321.treeple;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


/**
 * This class implements a method that lets a user cut down a tree
 * Created by leaakkari on 2018-03-22.
 */

public class CutTree extends AppCompatActivity {

    Button  ok;
    private String error = null;

    TextView message, invalid;

    EditText lgt, lat;

    /**
     * On Create
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.cut);

       lgt = (EditText)findViewById(R.id.lgt_cut);
        lat = (EditText)findViewById(R.id.ltd_cut);

        //takes you back to options page


        invalid = (TextView)findViewById((R.id.invalid_cut));


        ok = (Button) findViewById(R.id.ok_cut);


    }


    /**
     * refresh error message, displays error on screen
     */
    private void refreshErrorMessage() {
        // set the error message
        TextView tvError = (TextView) findViewById(R.id.success);
        tvError.setText(error);

        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }

    }


    /**
     * Method that lets the user cut down a tree
     * @param v
     */
   public void cut(View v){

        Intent i = getIntent();
        String userName = i.getStringExtra("userName");

        lgt = (EditText)findViewById(R.id.lgt_cut);
        lat = (EditText)findViewById(R.id.ltd_cut);

        message = (TextView)findViewById(R.id.success);

        HttpUtils.post("cutDownTree/"  + lat.getText().toString() + "/"+ lgt.getText().toString() +"/"+ userName ,  new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                refreshErrorMessage();
               lgt.setText("");
               lat.setText("");

                //going to Bob's page
                Intent intent = new Intent(CutTree.this, AfterCut.class);
                startActivity(intent);

                //System.out.println("Success");

            }
           @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                //System.out.println("Fail");

                refreshErrorMessage();

            }
        });


    }

//@PostMapping(value = { "/MarktoBeCutDown/{latitude}/{longitude}/{userName}" })
    public void MarkCut(View v){

        Intent i = getIntent();
        String userName = i.getStringExtra("userName");

        lgt = (EditText)findViewById(R.id.lgt_cut);
        lat = (EditText)findViewById(R.id.ltd_cut);

        message = (TextView)findViewById(R.id.success);

        HttpUtils.post("MarktoBeCutDown/"  + lat.getText().toString() + "/"+ lgt.getText().toString() +"/"+ userName ,  new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                refreshErrorMessage();
                lgt.setText("");
                lat.setText("");

                //going to Bob's page
                Intent intent = new Intent(CutTree.this, AfterCut.class);
                startActivity(intent);

                //System.out.println("Success");

            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                //System.out.println("Fail");

                refreshErrorMessage();

            }
        });


    }








}

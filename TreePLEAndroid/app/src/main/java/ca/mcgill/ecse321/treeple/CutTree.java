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

    Button back, ok;
    private String error = null;

    TextView message, invalid;

    EditText lgt, lat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.cut);

       lgt = (EditText)findViewById(R.id.lgt_cut);
        lat = (EditText)findViewById(R.id.ltd_cut);

        //takes you back to options page
        back = (Button) findViewById(R.id.back_c);

        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(CutTree.this, Options.class);
                startActivity(intent);

            }

        });

        invalid = (TextView)findViewById((R.id.invalid_cut));

        //ok
        /*ok = (Button) findViewById(R.id.ok_cut);
        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                if(lgt.getText().toString().matches("") || lat.getText().toString().matches("")){
                    invalid.setText("Please input longitude and latitude!");
                    invalid.setVisibility(View.VISIBLE);
                }
                else {

                    Intent intent = new Intent(CutTree.this, AfterCut.class);
                    startActivity(intent);
                }

            }

        });*/





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
        TextView tvError = (TextView) findViewById(R.id.success);
        tvError.setText(error);

        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }

    }

    /*@PostMapping(value = { "/cutDownTree/{latitude}/{longitude}/{userName}" })


	public TreeDto cutDownTree(
			@PathVariable("latitude") double latitude,
			@PathVariable("longitude") double longitude,
			@PathVariable("userName") String userName)
			throws InvalidInputException {

	Tree t = service.getPlantedTreeByLocation(latitude,longitude);

	service.cutDownTree(t,userName);
		return convertToDto(t);
	}*/




   public void cut(View v){

        Intent i = getIntent();
        String userName = i.getStringExtra("userName");

        lgt = (EditText)findViewById(R.id.lgt_cut);
        lat = (EditText)findViewById(R.id.ltd_cut);

        message = (TextView)findViewById(R.id.success);

        RequestParams rp = new RequestParams();




        HttpUtils.post("/cutDownTree/"  + lat.getText() + lgt.getText() + userName ,  new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //refreshErrorMessage();
               lgt.setText("");
               lat.setText("");
               message.setVisibility(View.VISIBLE);

            }
           @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                message.setText("FAIL");
               message.setVisibility(View.VISIBLE);
                refreshErrorMessage();

            }
        });


    }








}

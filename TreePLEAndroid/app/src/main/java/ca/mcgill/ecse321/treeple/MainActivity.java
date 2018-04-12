package ca.mcgill.ecse321.treeple;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 07/03/18.
 */


import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//import cz.msebera.android.httpclient.entity.mime.Header;
import ca.mcgill.ecse321.TreePLE.dto.MunicipalityDto;
import cz.msebera.android.httpclient.Header;

/**
 * This class implements the first login page. user has to input his username and pick his role.
 */


public class MainActivity  extends AppCompatActivity {

        EditText username;
        TextView welcome, entername, entertype,errorMsg;


        private String error;

        Button ok_b, back_first;


        private List<String> userType = new ArrayList<>();
        private ArrayAdapter<String> userAdapter;

        private List<Double> l = new ArrayList<>();
        private List<Double> l2 = new ArrayList<>();

         List<LatLng> position = new ArrayList<>();

    List<LatLng> position_planted = new ArrayList<>();
    List<LatLng> position_diseased= new ArrayList<>();
    List<LatLng> position_cut = new ArrayList<>();
    List<LatLng> position_MarkedCut = new ArrayList<>();


    private List<String> munName = new ArrayList<>();
    private ArrayAdapter<String> munAdapter;



        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.welcomepage);


            welcome = (TextView) findViewById(R.id.welcome);

            username = (EditText) findViewById(R.id.username_entry);

            entername = (TextView) findViewById(R.id.textView2);
            Spinner munSpinner = (Spinner) findViewById(R.id.munspinner);

            munAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, munName);
            munAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            munSpinner.setAdapter(munAdapter);

            entertype = (TextView) findViewById(R.id.textView4);
            errorMsg = (TextView) findViewById(R.id.error);

            userType.add("Please Select");
            userType.add("Local User");
            userType.add("Scientist");
            final Spinner userSpinner = (Spinner) findViewById(R.id.userType);

            userAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, userType);
            userAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            userSpinner.setAdapter(userAdapter);


            //Go to Options
            ok_b = (Button) findViewById(R.id.ok_1);
            //String chosenValue = userSpinner.getSelectedItem().toString();


                ok_b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {

                        if(username.getText().toString().matches("")){
                           error = "You did not enter a Username!";
                            errorMsg.setText(error);
                            errorMsg.setVisibility(View.VISIBLE);
                        }

                        else if(userSpinner.getSelectedItem().toString().equals("Local User")){



                        Intent i = new Intent(MainActivity.this, BeforeOptions.class);
                        i.putExtra("userName", username.getText().toString());
                        startActivity(i);
                    }

                        else if(userSpinner.getSelectedItem().toString().equals("Scientist")){
                            Intent i = new Intent(MainActivity.this, ScientistOptions.class);
                            i.putExtra("userName", username.getText().toString());
                            startActivity(i);
                        }else if(userSpinner.getSelectedItem().toString().equals("Please Select") ){
                            error = "Please Select Your Role!";

                            errorMsg.setText(error);
                            errorMsg.setVisibility(View.VISIBLE);


                        }



                    }

                    });


            //Go back to first page
            back_first = (Button) findViewById(R.id.b_first);

            back_first.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {


                    Intent i = new Intent(MainActivity.this, FirstPage.class);

                    startActivity(i);
                }

            });



            refreshListLat(munAdapter ,munName, "trees");
            refreshListLgt(munAdapter ,munName, "trees");


            //final List<LatLng> position = ListTreeByLocation();

           // System.out.println("PSITION " + position);



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


    /*public void refreshLists(View view) {
        refreshList(munAdapter ,munName, "mun");

    }

    private void refreshList(final ArrayAdapter<String> adapter, final List<String> names, String restFunctionName) {
        HttpUtils.get(restFunctionName, new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                names.clear();
                names.add("Please select...");
                for( int i = 0; i < response.length(); i++){
                    //JSONObject mun = new JSONObject();
                    try {
                        //names.add(response.getJSONObject(i).getString("name"));
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
    }*/


    public void refreshLists(View view) {
        //refreshListLat(munAdapter ,munName, "trees");
        //refreshListLgt(munAdapter ,munName, "trees");



        System.out.println("l" + l);
        System.out.println("l2" + l2);
        position = ListTreeByLocation();
        System.out.println("On CLICK" + position);
    }

   /* private void refreshListLat(final ArrayAdapter<String> adapter, final List<String> names,String restFunctionName) {
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
                        l.add(latitude);

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
                        l2.add(longitude);



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
    }*/


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






package ca.mcgill.ecse321.treeple;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by leaakkari on 2018-03-19.
 */



public class ScientistOptions extends AppCompatActivity {

    Button plant, cut, maps, diseased;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_scientist);

        //plant tree button
        plant= (Button) findViewById(R.id.plant_s);
        plant.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(ScientistOptions.this, PlantTree.class);
                startActivity(intent);
            }

        });


        // View Maps Button
        maps = (Button) findViewById(R.id.maps_s);

        maps.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(ScientistOptions.this, MapsActivity.class);
                startActivity(i);

            }

        });

        //cut Tree Button
        cut  = (Button) findViewById(R.id.cut_s);

        cut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                //setContentView(R.layout.cut);
                Intent i = new Intent(ScientistOptions.this, CutTree.class);
                startActivity(i);
            }

        });

        diseased = (Button) findViewById(R.id.diseased_s);
        diseased.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                //setContentView(R.layout.cut);
                Intent i = new Intent(ScientistOptions.this, DiseasedTree.class);
                startActivity(i);
            }

        });

    }











}

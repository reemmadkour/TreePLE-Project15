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



public class Options extends AppCompatActivity {

    Button plant, cut, maps, back_b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.options);

        //plant tree button
        plant= (Button) findViewById(R.id.planttree_b);
        plant.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(Options.this, PlantTree.class);
                startActivity(intent);
            }

        });


       // View Maps Button
        maps = (Button) findViewById(R.id.viewmaps_b);

        maps.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(Options.this, MapsActivity.class);
                startActivity(i);

            }

        });

        //cut Tree Button
        cut  = (Button) findViewById(R.id.cuttree_b);

        cut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                //setContentView(R.layout.cut);
                Intent i = new Intent(Options.this, CutTree.class);
                startActivity(i);
            }

        });

        //cut Tree Button
        back_b  = (Button) findViewById(R.id.back_ic);

        back_b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                //setContentView(R.layout.cut);
                Intent i = new Intent(Options.this, BeforeOptions.class);
                startActivity(i);
            }

        });


    }











}

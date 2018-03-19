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

    // When click on button1 (View Maps) it takes you to google maps
    public void addListenerOnButton5() {

        final Context context = this;

        Button viewMaps = (Button) findViewById(R.id.viewmaps_b);

        viewMaps.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, MapsActivity.class);
                startActivity(intent);

            }

        });

    }



    // When click on plant tree  it takes you to plant tree page
    public void addListenerOnButton3() {

        final Context context = this;

        Button plantTree  = (Button) findViewById(R.id.planttree_b);

        plantTree.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, PlantTree.class);
                startActivity(intent);
            }

        });

    }

    // When click on cut tree  it takes you to cut tree page
    public void addListenerOnButton4() {

        final Context context = this;

        Button cut  = (Button) findViewById(R.id.cuttree_b);

        cut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                setContentView(R.layout.cut);
            }

        });

    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.options);


        addListenerOnButton3();
        addListenerOnButton4();
        addListenerOnButton5();


    }











}

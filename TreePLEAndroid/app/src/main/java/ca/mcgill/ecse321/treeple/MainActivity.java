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



    public class MainActivity  extends AppCompatActivity {

        EditText username;
        TextView welcome, entername, entertype;

        private String error;

        Button ok_b, back_first;


        private List<String> userType = new ArrayList<>();
        private ArrayAdapter<String> userAdapter;


        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.welcomepage);

            welcome = (TextView) findViewById(R.id.welcome);

            username = (EditText) findViewById(R.id.username_entry);

            entername = (TextView) findViewById(R.id.textView2);

            entertype = (TextView) findViewById(R.id.textView4);

            userType.add("Please Select");
            userType.add("Local User");
            userType.add("Scientist");
            final Spinner userSpinner = (Spinner) findViewById(R.id.userType);

            userAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, userType);
            userAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            userSpinner.setAdapter(userAdapter);


            //Go to before options Options
            ok_b = (Button) findViewById(R.id.ok_1);

            ok_b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {

                        //setContentView(R.layout.options);

                    Intent i = new Intent(MainActivity.this, BeforeOptions.class);
                    i.putExtra("userName", username.getText().toString());
                    startActivity(i);
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





        }
    }






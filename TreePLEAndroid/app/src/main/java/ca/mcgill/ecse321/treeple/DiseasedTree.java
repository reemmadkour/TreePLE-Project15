package ca.mcgill.ecse321.treeple;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * This class allows the scientist to mark tree as diseased
 * Created by leaakkari on 2018-03-22.
 */


public class DiseasedTree extends AppCompatActivity {

    EditText lat, lng;
    TextView title, thanks;
    Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.diseased);

        lat = (EditText)findViewById(R.id.lat_dis);
        lng = (EditText) findViewById(R.id.lgt_d);

        title = (TextView)findViewById(R.id.title_diseased);
        thanks = (TextView)findViewById(R.id.thanks);

        ok = (Button)findViewById(R.id.ok_d);

        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                thanks.setVisibility(View.VISIBLE);

            }

        });





    }
}

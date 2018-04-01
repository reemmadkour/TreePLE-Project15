package ca.mcgill.ecse321.treeple;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by leaakkari on 2018-04-01.
 */

public class BeforeOptions extends AppCompatActivity{

    Button b, b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.before_options);


        //Go to Options
        b = (Button) findViewById(R.id.next_options);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                //setContentView(R.layout.options);

                Intent i = new Intent(BeforeOptions.this, Options.class);
                startActivity(i);
            }

        });

        //Go to before options Options
        b1 = (Button) findViewById(R.id.back_main);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                //setContentView(R.layout.options);

                Intent i = new Intent(BeforeOptions.this, MainActivity.class);
                startActivity(i);
            }

        });

    }
}

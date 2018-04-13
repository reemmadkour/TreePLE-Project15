package ca.mcgill.ecse321.treeple;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by leaakkari on 2018-04-06.
 */

public class HelpDiameter extends AppCompatActivity {

    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_diameter);

        //Go to back to help page
        back = (Button) findViewById(R.id.b_help2);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                Intent intent = getIntent();
                String userName = intent.getStringExtra("userName");
                String userType = intent.getStringExtra("userType");

                Intent i = new Intent(HelpDiameter.this, HelpPlant.class);
                i.putExtra("userName",userName);
                i.putExtra("userType",userType);
                startActivity(i);
            }

        });


    }

}

package ca.mcgill.ecse321.treeple;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by leaakkari on 2018-04-02.
 */

public class AfterCut extends AppCompatActivity {

    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.aftercut);



        //Go to Options
        back = (Button) findViewById(R.id.back_to_cut);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                //setContentView(R.layout.options);

                Intent i = new Intent(AfterCut.this, CutTree.class);
                startActivity(i);
            }

        });




    }
}

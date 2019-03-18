package com.example.sonia.uvapp.Fototipo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sonia.uvapp.Info_fototipo_single;
import com.example.sonia.uvapp.R;

public class Fototipo_result extends AppCompatActivity {

    TextView txf;
    Button ver_caracteristic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fototipo_result);

        txf= (TextView)findViewById( R.id.fr_fps_num);
        ver_caracteristic= (Button) findViewById( R.id.fr_features_button);

        final int fototipo = getIntent().getIntExtra("fototipo", 0);
        txf.setText( String.valueOf(fototipo)   );
        ver_caracteristic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Info_fototipo_single.class);
                intent.putExtra("indice", fototipo - 1 );

                startActivity( intent );
            }
        });


    }




}

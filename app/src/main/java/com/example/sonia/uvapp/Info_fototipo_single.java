package com.example.sonia.uvapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Info_fototipo_single extends AppCompatActivity {


    TextView tx_title, tx_features, tx_sun_effects, tx_melanin;
    Button b_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_fototipo_single);


        tx_title= (TextView) findViewById( R.id.ifs_title);
        tx_features= (TextView) findViewById( R.id.ifs_features);
        tx_sun_effects= (TextView) findViewById( R.id.ifs_sun_effects);
        tx_melanin= (TextView) findViewById( R.id.ifs_melanin);
        b_home= (Button) findViewById( R.id.ifs_home_button);

        b_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( getApplicationContext(), Inicio.class));
            }
        });

        show_data();
    }


    void show_data(){

        int indice= getIntent().getIntExtra("indice", 0);
        String titulo= getResources().getStringArray( R.array.fototipos_title )[indice];
        String caract= getResources().getStringArray( R.array.fototipos_features )[indice];
        String sun_ef= getResources().getStringArray( R.array.fototipos_sun_effects )[indice];
        String melani= getResources().getStringArray( R.array.fototipos_melanin)[indice];

        tx_title.setText( titulo);
        tx_features.setText( caract);
        tx_sun_effects.setText( sun_ef);
        tx_melanin.setText( melani);

    }
}

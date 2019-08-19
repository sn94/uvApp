package com.example.sonia.uvapp.Info;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sonia.uvapp.Inicio;
import com.example.sonia.uvapp.R;

public class Info_fototipo_single extends AppCompatActivity {


    TextView tx_title, tx_features, tx_sun_effects, tx_melanin;
    ImageView im1;
    Button b_backward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_fototipo_single);


        im1= (ImageView) findViewById(R.id.if_img_single);
        tx_title= (TextView) findViewById( R.id.ifs_title);
        tx_features= (TextView) findViewById( R.id.ifs_features);
        tx_sun_effects= (TextView) findViewById( R.id.ifs_sun_effects);
        tx_melanin= (TextView) findViewById( R.id.ifs_melanin);
        b_backward = (Button) findViewById( R.id.ifs_home_button);

        b_backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        show_data();
    }


    int imagen_fototipo( int index){
        int ar= 0;
        switch (index){
            case 0: ar= R.drawable.fototipo1;break;
            case 1: ar= R.drawable.fototipo2;break;
            case 2: ar= R.drawable.fototipo3;break;
            case 3: ar= R.drawable.fototipo4;break;
            case 4: ar= R.drawable.fototipo5;break;
            case 5: ar= R.drawable.fototipo6;break;
        }  return  ar;
    }



    void show_data(){

        int indice= getIntent().getIntExtra("indice", 0);
        String titulo= getResources().getStringArray( R.array.fototipos_title )[indice];
        String caract= getResources().getStringArray( R.array.fototipos_features )[indice];
        String sun_ef= getResources().getStringArray( R.array.fototipos_sun_effects )[indice];
        String melani= getResources().getStringArray( R.array.fototipos_melanin)[indice];

        im1.setImageResource( imagen_fototipo(  indice  ));
        tx_title.setText( titulo);
        tx_features.setText( caract);
        tx_sun_effects.setText( sun_ef);
        tx_melanin.setText( melani);

    }
}

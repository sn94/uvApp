package com.example.sonia.uvapp.Fototipo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sonia.uvapp.R;

public class Fototipo_main extends AppCompatActivity {

    Button fm_button_test, fm_button_camera;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fototipo_main);


        //boton para hacer el Test de Fitzpatrick
        fm_button_test= (Button) findViewById( R.id.fm_button_test);
        //boton para hacer el test de fototipo con foto
        fm_button_camera= (Button) findViewById( R.id.fm_take_photo);

        fm_button_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext(), Fototipo_questions.class);
                startActivity(intent);
            }
        });

        fm_button_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { take_photo(); }
        });
    }








    void take_photo(){
        startActivity( new Intent( this, Fototipo_photo.class) );
    }






}

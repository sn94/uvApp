package com.example.sonia.uvapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sonia.uvapp.Fototipo.Fototipo_main;
import com.example.sonia.uvapp.Info.Info_fototipo;
import com.example.sonia.uvapp.Info.Info_fps;
import com.example.sonia.uvapp.Info.Info_iuv;

public class Inicio extends AppCompatActivity {


    Context This= null;
    Button i_nuevo_perfil, i_info_iuv, i_info_foto, i_info_fps=  null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        This= this;
        i_nuevo_perfil= (Button) findViewById(R.id.i_nuevo_perfil);
        i_info_iuv=   (Button) findViewById(R.id.i_info_iuv);
        i_info_foto= (Button) findViewById( R.id.i_info_foto);
        i_info_fps= (Button) findViewById( R.id.i_info_fps);


        i_nuevo_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(This, Fototipo_main.class);
                startActivity(intent);
            }
        });
        i_info_iuv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(This, Info_iuv.class);
                startActivity(intent);
            }
        });
        i_info_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(This, Info_fototipo.class);
                startActivity(intent);
            }
        });

        i_info_fps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(This, Info_fps.class);
                startActivity(intent);
            }
        });


    }




}

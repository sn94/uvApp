package com.example.sonia.uvapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sonia.uvapp.Adapters.info_fps_adapter;

import java.util.ArrayList;

public class Info_fps extends AppCompatActivity {


    TextView ifps_title;
    ListView ifps_list_fps;

    Button ant, sig;
    int[] res;
    int indice= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_fps);
        ifps_title= (TextView) findViewById(R.id.ifps_iuv_title);
        ifps_list_fps= (ListView) findViewById( R.id.ifps_list_fps);
        create_buttons();
        res= new int[]{ R.array.recommended_fps_iuv1, R.array.recommended_fps_iuv2, R.array.recommended_fps_iuv3,
                R.array.recommended_fps_iuv4, R.array.recommended_fps_iuv5, R.array.recommended_fps_iuv6,
                R.array.recommended_fps_iuv7, R.array.recommended_fps_iuv8, R.array.recommended_fps_iuv9,
                R.array.recommended_fps_iuv10, R.array.recommended_fps_iuv11 };

        show_data( );

    }




    void create_buttons(){
        ant= (Button) findViewById( R.id.ifps_ant);
        sig= (Button) findViewById( R.id.ifps_sig);

        ant.setVisibility( View.INVISIBLE);//estado inicial
        ant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( indice > 0) indice= indice -1;
                show_data( );
            }
        });

        sig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( indice <10)
                    indice= indice +1;
                show_data( );
            }
        });
    }
    ArrayList convertir(String[] a){
        ArrayList<String> l= new ArrayList<String>();
        for(String ar: a){
            l.add( ar);
        }  return l;
    }
    void show_data( ){
        if( indice== 0) ant.setVisibility( View.INVISIBLE);
        if( indice == 1) ant.setVisibility( View.VISIBLE);
        if( indice == 10) sig.setVisibility( View.INVISIBLE);
        if( indice < 10 ) sig.setVisibility( View.VISIBLE);

         ifps_title.setText("IUV "+ (indice+1));
        String[] stringArray1 = getResources().getStringArray( res[indice] );
        ArrayList<String> convertir = convertir(stringArray1);
        info_fps_adapter info_fps_adapter = new info_fps_adapter(this, R.layout.info_fps_row_layout, convertir);
        ifps_list_fps.setAdapter( info_fps_adapter);

    }

}

package com.example.sonia.uvapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Alerta extends AppCompatActivity {

    TextView iuv,  iuv_des,  fps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerta);
        iuv= (TextView) findViewById( R.id.a_iuv);
        iuv_des= (TextView) findViewById( R.id.a_iuv_text);
        fps = (TextView) findViewById( R.id.a_fps);



        try{
            Bundle dts= getIntent().getExtras();
            iuv.setText(  dts.getString("body") );
            iuv_des.setText( getResources().getStringArray( R.array.labels_iuv )[ Integer.parseInt( iuv.getText().toString()) ] );
            //obtener fototipo de usuario
            SharedPreferences uvapp = getSharedPreferences("uvapp", Context.MODE_PRIVATE);
            int iuv_= Integer.parseInt( dts.getString("body") );
            int fototipoUser=  Integer.parseInt( uvapp.getString("fototipo", "1")) ;
            //fps min
            fps.setText( getCustomFps( iuv_, fototipoUser ));
            //fps proteccion maxima
            Log.i(  "iuv", getIntent().getExtras().getString("title"));

        }catch (Exception e) {
            Log.i("error", e.toString());
        }

    }




    String getCustomFps( int iuv, int fototipo){
        String list_fpsByIuv[]= {};
        switch ( iuv ){
            case 1:  list_fpsByIuv= getResources().getStringArray( R.array.recommended_fps_iuv1);break;
            case 2:  list_fpsByIuv= getResources().getStringArray( R.array.recommended_fps_iuv1);break;
            case 3:  list_fpsByIuv= getResources().getStringArray( R.array.recommended_fps_iuv1);break;
            case 4:  list_fpsByIuv= getResources().getStringArray( R.array.recommended_fps_iuv1);break;
            case 5:  list_fpsByIuv= getResources().getStringArray( R.array.recommended_fps_iuv1);break;
            case 6:  list_fpsByIuv= getResources().getStringArray( R.array.recommended_fps_iuv1);break;
            case 7:  list_fpsByIuv= getResources().getStringArray( R.array.recommended_fps_iuv1);break;
            case 8:  list_fpsByIuv= getResources().getStringArray( R.array.recommended_fps_iuv1);break;
            case 9:  list_fpsByIuv= getResources().getStringArray( R.array.recommended_fps_iuv1);break;
            case 10:  list_fpsByIuv= getResources().getStringArray( R.array.recommended_fps_iuv1);break;
            case 11:  list_fpsByIuv= getResources().getStringArray( R.array.recommended_fps_iuv1);break;
        }
        String max_prot, min_prot="";
        String fps_Especifico= list_fpsByIuv[ fototipo ];
        String[] split = fps_Especifico.split("-");
        if( split.length == 1)
            max_prot=  split[0];
        else{
            min_prot=   split[0]  ;
            max_prot=  split[1];
        }
return  split.length ==1 ? "+"+max_prot   : "FPS min.: "+min_prot+", FPS max. prot.: "+max_prot;

    }
}

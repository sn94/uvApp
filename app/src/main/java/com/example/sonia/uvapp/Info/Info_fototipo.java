package com.example.sonia.uvapp.Info;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sonia.uvapp.R;

public class Info_fototipo extends AppCompatActivity {


    int CurrentIndexFragment= 0;


    //https://developer.android.com/training/animation/screen-slide
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_fototipo);
        update_data();

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
    void update_data(){

        TextView t0 = (TextView) findViewById(R.id.if_text0);
        TextView t1 = (TextView) findViewById(R.id.if_text1);
        TextView t2 = (TextView) findViewById(R.id.if_text2);
        TextView t3 = (TextView) findViewById(R.id.if_text3);
        ImageView im1= (ImageView) findViewById(R.id.if_img);

        t0.setText(  getResources().getStringArray( R.array.fototipos_title) [ CurrentIndexFragment] );
        t1.setText(  getResources().getStringArray( R.array.fototipos_features) [ CurrentIndexFragment]);
        t2.setText(  getResources().getStringArray( R.array.fototipos_sun_effects) [CurrentIndexFragment]);
        t3.setText(  getResources().getStringArray( R.array.fototipos_melanin) [CurrentIndexFragment]);
        im1.setImageResource(  imagen_fototipo(  CurrentIndexFragment  ));

    }

    void show_fragments( ){


        //info_fototipo_fragment = new Info_fototipo_fragment();
        Bundle paramsForFragment=  new Bundle();
        paramsForFragment.putInt( "indice", CurrentIndexFragment );
        //info_fototipo_fragment.setArguments( paramsForFragment );

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
       // fragmentTransaction.replace( R.id.if_container,  info_fototipo_fragment );
        fragmentTransaction.commit();
    }


    void update_fragments(){
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.popBackStack();
    }

}

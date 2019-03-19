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

    Button b1 , b2 =  null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_fototipo);


        b1 = (Button) findViewById(R.id.if_ant);
        b2 = (Button) findViewById(R.id.if_sig);
        buttons();
        update_data();

    }

    void update_data(){
        if(CurrentIndexFragment == 0) {
            b1.setVisibility( View.INVISIBLE);
            b2.setVisibility( View.VISIBLE);
        } else{
            b1.setVisibility( View.VISIBLE);
            if( CurrentIndexFragment == 5) b2.setVisibility( View.INVISIBLE);
            else b2.setVisibility( View.VISIBLE);
        }

        TextView t0 = (TextView) findViewById(R.id.if_text0);
        TextView t1 = (TextView) findViewById(R.id.if_text1);
        TextView t2 = (TextView) findViewById(R.id.if_text2);
        TextView t3 = (TextView) findViewById(R.id.if_text3);
        ImageView im1= (ImageView) findViewById(R.id.if_img);

        t0.setText(  getResources().getStringArray( R.array.fototipos_title) [ CurrentIndexFragment] );
        t1.setText(  getResources().getStringArray( R.array.fototipos_features) [ CurrentIndexFragment]);
        t2.setText(  getResources().getStringArray( R.array.fototipos_sun_effects) [CurrentIndexFragment]);
        t3.setText(  getResources().getStringArray( R.array.fototipos_melanin) [CurrentIndexFragment]);

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

    void buttons(){
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( CurrentIndexFragment >= 1){
                    CurrentIndexFragment= CurrentIndexFragment-1;
                    //actualizar fragmento
                    update_data();
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(  getResources().getStringArray( R.array.fototipos_features).length > CurrentIndexFragment ){
                    CurrentIndexFragment= CurrentIndexFragment+1;
                    //actualizar fragmento
                    update_data();
              }
            }
        });
    }
}

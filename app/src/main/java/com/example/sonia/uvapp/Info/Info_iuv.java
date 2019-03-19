package com.example.sonia.uvapp.Info;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.sonia.uvapp.Adapters.info_iuv_adapter;
import com.example.sonia.uvapp.R;

import java.util.ArrayList;

public class Info_iuv extends AppCompatActivity {


    ListView ii_tabla= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_iuv);


        ii_tabla= (ListView) findViewById( R.id.ii_tabla);
        show_data();

    }




    void show_data(){
        //Obtener labels de iuv
        String[] stringArray = getResources().getStringArray(R.array.labels_iuv);
        ArrayList lista= new ArrayList();
        for (String ar:stringArray
             ) {
            lista.add( ar);
        }
        info_iuv_adapter info_iuv_adapter = new info_iuv_adapter(this, R.layout.info_iuv_row_layout, lista);

        ii_tabla.setAdapter(info_iuv_adapter);
    }
}

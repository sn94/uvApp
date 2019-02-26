package com.example.sonia.uvapp.Adapters;

import android.content.Context;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sonia.uvapp.R;

import java.util.ArrayList;

public class info_iuv_adapter extends ArrayAdapter {


    ArrayList niveles_iuv= null;
    int[] colores_iuv= null;
    int layout;

    public info_iuv_adapter(@NonNull Context context, int resource, @NonNull ArrayList objects) {
        super(context, resource, objects);
        niveles_iuv=  objects;
        layout= resource;
        colores_iuv= context.getResources().getIntArray(R.array.colors_iuv);

    }



    @NonNull
    @Override
    public  View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v= convertView;

        LayoutInflater inflador= (LayoutInflater) getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE);
        v= inflador.inflate( layout, null);

        TextView t_color= (TextView) v.findViewById( R.id.iirl_color);
        int i = position + 1;
        t_color.setText(  String.valueOf( i)  );
        t_color.setBackgroundColor( colores_iuv[position]);

        TextView t_text= (TextView) v.findViewById( R.id.iirl_text);
        t_text.setText( niveles_iuv.get( position ).toString());


        return v;
    }
}

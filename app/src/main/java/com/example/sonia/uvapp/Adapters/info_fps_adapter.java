package com.example.sonia.uvapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sonia.uvapp.R;

import java.util.ArrayList;

public class info_fps_adapter extends ArrayAdapter {


    ArrayList<String> fps_list= null;

    int layout;
    public info_fps_adapter(@NonNull Context context, int resource, @NonNull ArrayList objects) {
        super(context, resource, objects);
        layout= resource;
        fps_list=  objects;

    }


    @NonNull
    @Override
    public  View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v= convertView;

        LayoutInflater inflador= (LayoutInflater) getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE);
        v= inflador.inflate( layout, null);

        TextView t_text= (TextView) v.findViewById( R.id.ifpsrl_name);
        TextView t_text2= (TextView) v.findViewById( R.id.ifpsrl_min);
        TextView t_text3= (TextView) v.findViewById( R.id.ifpsrl_max);
        t_text.setText( "FOTOTIPO "+ (position+1) );
        String[] split = fps_list.get(position).toString().split("-");
        if( split.length == 1)
        t_text3.setText( fps_list.get( position ).toString().split("-")[0]);
        else{
            t_text2.setText( fps_list.get( position ).toString().split("-")[0]);
            t_text3.setText( fps_list.get( position ).toString().split("-")[1]);
        }


        return v;
    }
}

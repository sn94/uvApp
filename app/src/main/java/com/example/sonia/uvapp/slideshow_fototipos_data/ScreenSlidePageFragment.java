package com.example.sonia.uvapp.slideshow_fototipos_data;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sonia.uvapp.R;

public class ScreenSlidePageFragment extends Fragment {


    public static ScreenSlidePageFragment obtener_fragmento( Bundle args){
        ScreenSlidePageFragment sspf= new ScreenSlidePageFragment();
        if( args != null ){  sspf.setArguments( args); }
        return sspf;
    }


    //el constructor es necesario
    public ScreenSlidePageFragment(){}




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.activity_info_fototipo, container, false);
        //Asignar valores
        ImageView img= (ImageView) rootView.findViewById( R.id.if_img);
        TextView title= (TextView) rootView.findViewById( R.id.if_text0);
        TextView text1= (TextView) rootView.findViewById( R.id.if_text1);
        TextView text2= (TextView) rootView.findViewById( R.id.if_text2);
        TextView text3= (TextView) rootView.findViewById( R.id.if_text3);


        Bundle arguments = getArguments();
        if(arguments !=null){
            title.setText( arguments.getString("title"));
            text1.setText( arguments.getString("text1"));
            text2.setText( arguments.getString("text2"));
            text3.setText( arguments.getString("text3"));
            img.setImageResource(  arguments.getInt( "fototipo"));
        }




        return rootView;
    }




}

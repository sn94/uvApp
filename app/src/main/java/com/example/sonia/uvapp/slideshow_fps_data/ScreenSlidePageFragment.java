package com.example.sonia.uvapp.slideshow_fps_data;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sonia.uvapp.Adapters.info_fps_adapter;
import com.example.sonia.uvapp.R;

import java.util.ArrayList;

public class ScreenSlidePageFragment extends Fragment {


    int res[]= new int[]{ R.array.recommended_fps_iuv1, R.array.recommended_fps_iuv2, R.array.recommended_fps_iuv3,
            R.array.recommended_fps_iuv4, R.array.recommended_fps_iuv5, R.array.recommended_fps_iuv6,
            R.array.recommended_fps_iuv7, R.array.recommended_fps_iuv8, R.array.recommended_fps_iuv9,
            R.array.recommended_fps_iuv10, R.array.recommended_fps_iuv11 };


    public static ScreenSlidePageFragment obtener_fragmento(Bundle args){
        ScreenSlidePageFragment sspf= new ScreenSlidePageFragment();
        if( args != null ){  sspf.setArguments( args); }
        return sspf;
    }



    ArrayList convertToArraylist(String[] a){
        ArrayList<String> l= new ArrayList<String>();
        for(String ar: a){
            l.add( ar);
        }  return l;
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
                R.layout.fragment_screen_slide_page_fps, container, false);
        //Asignar valores

        ListView lista= (ListView) rootView.findViewById( R.id.ifps_list_fps);


        Bundle arguments = getArguments();
        if(arguments !=null){
            //carga lista, de fps para cada fototipo segun el iuv
            String[] stringArray1 = getResources().getStringArray( res[   arguments.getInt("pos") ]  );
            //convertir array a arraylist
            ArrayList<String> arr = convertToArraylist(stringArray1);
            //instanciar adaptador de lista
            info_fps_adapter info_fps_adapter = new info_fps_adapter(getActivity(), R.layout.info_fps_row_layout, arr);
            //poblar lista
            lista.setAdapter( info_fps_adapter);
        }

        return rootView;
    }




}

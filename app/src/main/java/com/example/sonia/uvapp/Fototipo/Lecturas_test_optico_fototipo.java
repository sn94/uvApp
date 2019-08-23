package com.example.sonia.uvapp.Fototipo;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sonia.uvapp.R;

public class Lecturas_test_optico_fototipo extends Fragment {


    TextView progress_test_label,progress_test_fototipo,progress_test_rgb= null;
    Button progress_test_skin= null;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View greatview= inflater.inflate(R.layout.fototipo_visual_test_onprogress, container, false);

        return  greatview;
    }





    public void update_data( String fototipo, String r, String g, String b){
        //label
        progress_test_label = (TextView) getView().findViewById(R.id.fvt_testing_label);
        progress_test_label.setText(R.string.Fototipo_visual_test_text1);
        //fototipo
        progress_test_fototipo = (TextView) getView().findViewById(R.id.fvt_testing_fototipo);
        //rgb
        progress_test_rgb = (TextView) getView().findViewById(R.id.fvt_testing_rgb);
        //color
        progress_test_skin = (Button) getView().findViewById(R.id.fvt_testing_skin);

        progress_test_fototipo.setText( fototipo );
        progress_test_rgb.setText(r+","+g+","+b);
        progress_test_skin.setBackgroundColor( Color.rgb( Integer.parseInt( r), Integer.parseInt(g) , Integer.parseInt(b) ) );
    }
    public void update_data( String fototipo, int r, int g, int b){
        progress_test_fototipo.setText( fototipo );
        progress_test_rgb.setText(r+","+g+","+b);
        progress_test_skin.setBackgroundColor( Color.rgb( r,g,b) );
    }



}

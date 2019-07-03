package com.example.sonia.uvapp.Fototipo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sonia.uvapp.R;

public class Fototipo_main extends AppCompatActivity {

    EditText usernick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fototipo_main);
        usernick= (EditText) findViewById( R.id.fm_nick);
    }




    public void test_visual( View v){

        Intent intent = new Intent( getApplicationContext(), Fototipo_visual_test.class);
        intent.putExtra("nick",  usernick.getText().toString());
        startActivity(intent);

    }

    public void test_fitzpatrick( View v){
        Intent intent = new Intent( getApplicationContext(), Fototipo_questions.class);
        intent.putExtra("nick",  usernick.getText().toString());
        startActivity(intent);
    }




}

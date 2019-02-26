package com.example.sonia.uvapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Fototipo_main extends AppCompatActivity {

    Button fm_button_test= null;

    Context This;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fototipo_main);
        This= this;

        fm_button_test= (Button) findViewById( R.id.fm_button_test);
        fm_button_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(This, Info_fps.class);
                startActivity(intent);
            }
        });
    }
}

package com.example.sonia.uvapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sonia.uvapp.retrofits.Cliente;

public class config_conexion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_conexion);
    }



    public void settear(View v){
        EditText host= (EditText) findViewById( R.id.txtHost);
        EditText port= (EditText) findViewById( R.id.txtPort);
        Cliente.host= host.getText().toString();
        Cliente.puerto= port.getText().toString();
        Toast.makeText( getApplicationContext(), "Parametros de configuracion seteados!", Toast.LENGTH_LONG).show();
    }
}

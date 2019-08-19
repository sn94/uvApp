package com.example.sonia.uvapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sonia.uvapp.retrofits.Cliente;

public class config_conexion extends AppCompatActivity {

    EditText host, port;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_conexion);
        host= (EditText) findViewById( R.id.txtHost);
        port= (EditText) findViewById( R.id.txtPort);
        host.setText( Cliente.host);
        port.setText( Cliente.puerto);
    }



    public void settear(View v){
        Cliente.host= host.getText().toString();
        Cliente.puerto= port.getText().toString();
        Toast.makeText( getApplicationContext(), "Parametros de configuracion seteados!", Toast.LENGTH_LONG).show();
        finish();
    }
}

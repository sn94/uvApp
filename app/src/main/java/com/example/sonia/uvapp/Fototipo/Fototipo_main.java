package com.example.sonia.uvapp.Fototipo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sonia.uvapp.Inicio;
import com.example.sonia.uvapp.R;
import com.example.sonia.uvapp.config_conexion;

public class Fototipo_main extends AppCompatActivity {

    EditText usernick;
    Button b_help;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fototipo_main);
        usernick= (EditText) findViewById( R.id.fm_nick);
        b_help= (Button) findViewById( R.id.fm_help_visual_test);
        Toolbar toolbar= (Toolbar) findViewById( R.id.my_toolbar_main_test_opc);
        setSupportActionBar(toolbar);
    }




    void configurar_conexion(){
        startActivity( new Intent( this, config_conexion.class));
    }

    void go_home(){
        startActivity( new Intent( this, Inicio.class));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate( R.menu.my_appbar, menu) ;
         return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch ( item.getItemId()){
            case R.id.menu_config:  configurar_conexion(); break;
            case R.id.menu_home:  go_home();break;
        }
        return super.onOptionsItemSelected(item);
    }


    boolean userInput(){
        if( usernick.getText().toString().equals("") ){
            new AlertDialog.Builder(this)
                    .setTitle("Antes de seguir")
                    .setMessage("Ingresa un nick por favor.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Log.d("MainActivity", "Sending atomic bombs to Jupiter");
                        }
                    })
                    .show();
            return false;
        }  return true;
    }

    public void test_visual( View v){

        if( userInput())
        {
            Intent intent = new Intent( getApplicationContext(), Fototipo_visual_test.class);
            intent.putExtra("nick",  usernick.getText().toString());
            startActivity(intent);

        }
    }




    public void test_fitzpatrick( View v){
        if( userInput()){
            Intent intent = new Intent( getApplicationContext(), Fototipo_questions.class);
            intent.putExtra("nick",  usernick.getText().toString());
            startActivity(intent);
        }
    }




    public void show_help(View v){
        startActivity( new Intent( getBaseContext(), Fototipo_help.class));
    }

}

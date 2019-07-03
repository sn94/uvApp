package com.example.sonia.uvapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sonia.uvapp.Fototipo.Fototipo_main;
import com.example.sonia.uvapp.Info.Info_fototipo;
import com.example.sonia.uvapp.Info.Info_fps;
import com.example.sonia.uvapp.Info.Info_iuv;
import com.example.sonia.uvapp.retrofits.Cliente;
import com.example.sonia.uvapp.retrofits.UvappInterface;
import com.example.sonia.uvapp.retrofits.UvappResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Inicio extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if( existe_userdata()){
            setContentView(R.layout.activity_inicio_con_auth);
            show_user_data();
        }else{
            setContentView(R.layout.activity_inicio_sin_auth);
        }



    }


    void show_user_data(){
        SharedPreferences preferences = getSharedPreferences( "uvapp", Context.MODE_PRIVATE);
        TextView user= (TextView)findViewById( R.id.inicio_nick_name);
        TextView phot= (TextView)findViewById( R.id.inicio_user_phototype);
        user.setText( preferences.getString("nick", "") );
        phot.setText( "FOTOTIPO "+ preferences.getString( "fototipo", ""));
    }

    boolean existe_userdata(){
        SharedPreferences preferences = getSharedPreferences("uvapp", Context.MODE_PRIVATE);
        String f= preferences.getString("fototipo", "");
        String n= preferences.getString("nick", "");
        return !f.equals("") && !n.equals("") ;
    }

    public void nuevo_perfil( View v){
        Intent intent = new Intent( this, Fototipo_main.class);
        startActivity(intent);
    }

    public void borrar_perfil(View v){
        SharedPreferences preferences = getSharedPreferences("uvapp", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.clear();
        edit.apply();
        Toast.makeText( getBaseContext(), "Perfil borrado!", Toast.LENGTH_SHORT).show();
        Intent i= new Intent( this, Inicio.class);
        startActivity( i);
        finish();
    }



    public void info_iuv(View v){
        Intent intent = new Intent( this, Info_iuv.class);
        startActivity(intent);
    }

    public void info_fototipos(View v){
        Intent intent = new Intent( this, Info_fototipo.class);
        startActivity(intent);
    }

    public void info_fps( View v){
        Intent intent = new Intent( this, Info_fps.class);
        startActivity(intent);
    }










}

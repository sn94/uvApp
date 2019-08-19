package com.example.sonia.uvapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sonia.uvapp.Fototipo.Fototipo_main;
import com.example.sonia.uvapp.Info.Info_iuv;
import com.example.sonia.uvapp.retrofits.Cliente;
import com.example.sonia.uvapp.retrofits.Response;
import com.example.sonia.uvapp.retrofits.webapi;
import com.example.sonia.uvapp.slideshow_fototipos_data.ScreenSlidePagerActivity;

import retrofit2.Call;
import retrofit2.Callback;

public class Inicio extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if( existe_userdata()){
            setContentView(R.layout.activity_inicio_con_auth);
            Toolbar toolbar= (Toolbar) findViewById( R.id.my_toolbar2);
            setSupportActionBar(toolbar);
            show_user_data();
        }else{
            setContentView(R.layout.activity_inicio_sin_auth);
            Toolbar toolbar= (Toolbar) findViewById( R.id.my_toolbar);
            setSupportActionBar(toolbar);

        }

        /*NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationView navView = findViewById(R.id.nav_view);
        NavigationUI.setupWithNavController(navView, navController);

        DrawerLayout drawerLayout= (DrawerLayout)findViewById(R.id.drawer_layout);
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(navController.getGraph())
                        .setDrawerLayout(drawerLayout)
                        .build();

*/

    }


    void show_user_data(){
        SharedPreferences preferences = getSharedPreferences( "uvapp", Context.MODE_PRIVATE);
        TextView user= (TextView)findViewById( R.id.inicio_nick_name);
        TextView phot= (TextView)findViewById( R.id.inicio_user_phototype);
        user.setText( preferences.getString("nick", "") );
        phot.setText( "FOTOTIPO "+ preferences.getString( "fototipo", ""));
    }


    String getNick(){
        SharedPreferences preferences = getSharedPreferences("uvapp", Context.MODE_PRIVATE);
        return preferences.getString("nick", "");
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


    void deleteFromSharedPref(){

        SharedPreferences preferences = getSharedPreferences("uvapp", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.clear();
        edit.apply();
        Toast.makeText( getBaseContext(), "Perfil borrado!", Toast.LENGTH_SHORT).show();
    }
    public void borrar_perfil(View v){
        //borrar del servidor
        webapi w= Cliente.buildService( webapi.class);
        Call<Response> responseCall = w.deleteUser(  getNick()  );
        responseCall.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Response body = response.body();
                if(body != null ){
                    if(  body.getEstado() != 200 ){
                        Toast.makeText( getBaseContext(), body.getMsg(), Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText( getBaseContext(), body.getMsg(), Toast.LENGTH_LONG).show();
                        deleteFromSharedPref();// datos de usuario en preferencias compartidas
                        //abrir inicio con autenticacion
                        startActivity(  new Intent( getApplicationContext(), Inicio.class) );
                        finish();
                    }

                }else{
                    Toast.makeText( getBaseContext(), "No hay respuesta del servidor", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText( getBaseContext(), "HUBO UN ERROR DURANTE LA TRANSACCION", Toast.LENGTH_SHORT).show();

            }
        });


    }



    public void info_iuv(View v){
        Intent intent = new Intent( this, Info_iuv.class);
        startActivity(intent);
    }

    public void info_fototipos(View v){
       // Intent intent = new Intent( this, Info_fototipo.class);
        //startActivity(intent);
        Intent intent = new Intent( this, ScreenSlidePagerActivity.class);
        startActivity(intent);
    }

    public void info_fps( View v){
        Intent intent = new Intent( this, com.example.sonia.uvapp.slideshow_fps_data.ScreenSlidePagerActivity.class);
        startActivity(intent);
        //Intent intent = new Intent( this, Info_fps.class);
        //startActivity(intent);
    }





    void configurar_conexion(){
        startActivity( new Intent( this, config_conexion.class));
    }

    void go_home(){
        startActivity( new Intent( this, Inicio.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if( existe_userdata())
            getMenuInflater().inflate( R.menu.my_appbar, menu) ;
        else
            getMenuInflater().inflate( R.menu.my_appbar_inicial, menu) ;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch ( item.getItemId()){
            case R.id.menu_config:  configurar_conexion(); break;
            case R.id.menu_home:  go_home();break;
        }
        return super.onOptionsItemSelected(item);
    }
}

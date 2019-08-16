package com.example.sonia.uvapp.Fototipo;

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

import com.example.sonia.uvapp.Info.Info_fototipo_single;
import com.example.sonia.uvapp.Inicio;
import com.example.sonia.uvapp.R;
import com.example.sonia.uvapp.retrofits.Cliente;
import com.example.sonia.uvapp.retrofits.Data;
import com.example.sonia.uvapp.retrofits.Response;
import com.example.sonia.uvapp.retrofits.webapi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import retrofit2.Call;
import retrofit2.Callback;

public class Fototipo_result extends AppCompatActivity {


    String nick_="",  foto="";
    String token= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fototipo_result);


        obtener_token();

        nick_= getIntent().getExtras().getString("nick");
        TextView txt_nick= (TextView)findViewById( R.id.fr_nickname); txt_nick.setText( nick_);

        foto= getIntent().getExtras().getString("fototipo");
        TextView txt_foto= (TextView)findViewById( R.id.fr_fototipo); txt_foto.setText( foto);
        determ_fps();
    }

    //clave de servidor  AAAAJI-qXfU:APA91bGsyhz1yVUw45K-nNq3jyK-VFsC-ORkItNkUpHut_Bd0g6H122CHPwejKlrFngiihRb8MwmmoWS-TPUGzC3S8iB_DdzzXVO7TCtxOeXOwep_lvJOIhzDfFzuEwBaCuNe4ZxUYnZ
    //api web key  AIzaSyCdRbuLe2MckTBa_9XDLveUY-bTgEI6Ff0
//cnZDJimhpGs:APA91bEHk3uW91hRhoUArS9bmszHBVegLqTUjmbocuKd6R_2LBWLmJ1bt8L4J6fl-pzXsfWiHFFdw0ImY1j1Oib5-nem97iVmrnqn-k2yG4wDe4LquvBJd0C_WL2bnCESm5zEn0GNcY0
    void obtener_token(){
//Esto es asincrono
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w( "fallado", "getInstanceId failed", task.getException());

                        }

                        // Get new Instance ID token
                           token = task.getResult().getToken();

                        // Log and toast
                        Log.d("TOKEN",  token);
                    }
                });
    }

    private void guardar_userdata_locally(){
        SharedPreferences preferences = getSharedPreferences("uvapp", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString( "fototipo", foto);
        edit.putString("nick",  nick_);
        edit.commit();
    }

    public void guardar_userdata(View v){
        /**PETICION CON RETROFIT***************/

        webapi webapi=  new Cliente().buildService(com.example.sonia.uvapp.retrofits.webapi.class );
        /** REQUEST BODY */
        Data datos= new Data();
        datos.setNick( nick_ );
        datos.setToken(  token );
        /*** REQUEST **/
        Call<Response> respuesta=  webapi.signup( datos );
        //guardar el perfil en firestore
        respuesta.enqueue( new Callback<Response>(){

            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Response body = response.body();
                if(body != null ){
                    Toast.makeText( getBaseContext(), body.getMsg(), Toast.LENGTH_SHORT).show();
                    guardar_userdata_locally();// datos de usuario en preferencias compartidas
                    //abrir inicio con autenticacion
                    startActivity(  new Intent( getApplicationContext(), Inicio.class) );
                    finish();
                }else{
                    Toast.makeText( getBaseContext(), "No hay respuesta del servidor", Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText( getBaseContext(), "HUBO UN ERROR DURANTE LA TRANSACCION", Toast.LENGTH_SHORT).show();
            }
        }
        );
        /**************************************/
    }



    void determ_fps(){

        String des= getResources().getStringArray( R.array.recommended_fps_iuv1 )[ Integer.parseInt( foto )];
        TextView txt= (TextView)findViewById( R.id.fr_fps);
        txt.setText( des);
        //FPS MIN       FPS MAX

    }

public void ver_caracteristicas_fototipo(  View v){

    Intent intent = new Intent(getApplicationContext(), Info_fototipo_single.class);
    intent.putExtra("indice", Integer.parseInt( foto ) - 1 );
    startActivity( intent );
}

}

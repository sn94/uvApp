package com.example.sonia.uvapp.Fototipo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.sonia.uvapp.Helpers;
import com.example.sonia.uvapp.R;
import com.example.sonia.uvapp.retrofits.webapi;

import java.io.File;
import java.io.IOException;

public class Fototipo_phototype extends AppCompatActivity {



    //ViEWS
    ImageView taken_photo;
    Button button_take_photo, button_evaluate, button_open, test;
    Helpers help;


    //CAMERA
    String currentPhotoPath;
    Uri photoUri;
    File imageFile= null;
    Intent intent_for_take_photo;
    static final int REQUEST_TAKE_PHOTO = 2, READ_REQUEST_CODE= 3;


    webapi uvappInterface=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fototipo_photo);
        help= new Helpers();
        create_views();
    }


    void create_views(){

        taken_photo= (ImageView) findViewById( R.id.fp_taken_photo);//only a view for display the photo
        button_take_photo= (Button) findViewById( R.id.fp_take_photo_button); //for taking a photo
        button_evaluate= (Button) findViewById( R.id.fp_evaluate);  // for send the photo to the server
        button_open= (Button) findViewById( R.id.fp_open_photo_button);//open a photo
    }







    public void open_photo(View v ){
        //Abrir explorador de archivos
        //https://developer.android.com/guide/topics/providers/document-provider
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            startActivityForResult(intent, READ_REQUEST_CODE);
    }

    /**
     * Metodo para enviar peticiones HTTP
     */






/**************************************************************************************************/
    void take_photo( View v){
        intent_for_take_photo = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        append_uri_data_to_intent();
        if(  intent_for_take_photo.resolveActivity( getPackageManager())  != null)
            startActivityForResult(  intent_for_take_photo,   REQUEST_TAKE_PHOTO );
    }

    void append_uri_data_to_intent(){
        /***********Append URI to intent*************************************/
        //Proporcionar un archivo para guardar la foto
        imageFile= null;
        /*********Obtener instancia de archivo para la foto ********/
        try {
            imageFile = help.createImageFile_Container( getApplicationContext());
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = imageFile.getAbsolutePath();
        } catch (IOException e) {  e.printStackTrace();  }
        /**********************************************************/
        if( imageFile != null){
            photoUri=  FileProvider.getUriForFile(this, "com.example.sonia.uvapp.fileprovider", imageFile);
            intent_for_take_photo.putExtra( MediaStore.EXTRA_OUTPUT,  photoUri);//Se proporciona la URI  de archivo a la app de la Camara
        }
        /*********************************************************************/

    }





















    /*****************************************************************************************/


    /**
     *
     * Methods for showing the photo
     *
     *
     */


    void show_photo_full_size(){

       Bitmap foto_bitmap= null;
        try { foto_bitmap = help.getBitmapFromUri( getApplicationContext(),  photoUri);
        } catch (IOException e) {  e.printStackTrace(); }
        taken_photo.setImageBitmap( foto_bitmap);
    }


    void show_mini_photo(Bundle data){

        Bundle extras =  data;
        Bitmap imageBitmap = (Bitmap) extras.get("data");
        taken_photo.setImageBitmap(imageBitmap);
    }
    /*****************************************************/




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /*** AL TOMAR UNA FOTO  *********/
        if(  requestCode == REQUEST_TAKE_PHOTO  &&  resultCode== RESULT_OK ){//foto recien tomada
             show_photo_full_size();
            //show_mini_photo( data.getExtras());
            //Object data1 = data.getExtras().get("data");
            //taken_photo.setImageBitmap( (Bitmap)  data1);
        }//end request type check



        /***  AL ABRIR UNA FOTO  **************/
        if( requestCode == READ_REQUEST_CODE && resultCode== RESULT_OK){//Despues de abrir la foto
            Uri uri = null;
            if (data != null) {
                uri = data.getData();
                photoUri= uri;
                currentPhotoPath= "";
                show_photo_full_size();
            }

        }



        if(resultCode == 3535){

            //Este seria el bitmap de nuestra imagen cortada.
            Bitmap imagenCortada = (Bitmap) data.getExtras().get("return-data");
            taken_photo.setImageBitmap( imagenCortada );
        }


    }




}

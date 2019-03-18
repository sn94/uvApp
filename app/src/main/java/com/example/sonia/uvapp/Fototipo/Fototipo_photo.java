package com.example.sonia.uvapp.Fototipo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.sonia.uvapp.R;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Fototipo_photo extends AppCompatActivity {



    //ViEWS
    ImageView taken_photo;
    Button button_take_photo, button_evaluate;



    //CAMERA
    String currentPhotoPath;
    Uri photoUri;
    Intent intent_for_take_photo;
    static final int REQUEST_TAKE_PHOTO = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fototipo_photo);
        create_views();

        //Bitmap foto_bitmap=  show_image_by_uri();
        //taken_photo.setImageBitmap( foto_bitmap );

    }


    void create_views(){

        taken_photo= (ImageView) findViewById( R.id.fp_taken_photo);
        button_take_photo= (Button) findViewById( R.id.fp_take_photo_button);
        button_evaluate= (Button) findViewById( R.id.fp_evaluate);

        button_take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                take_photo();
            }
        });

    }


    /***
     *
     * Methods for receiving intent result
     * @return
     */
    Bitmap show_image_by_bundle(){

        Object o = getIntent().getBundleExtra("foto").get("data");
        Bitmap imageBitmap = (Bitmap)   o;
        return imageBitmap;
    }


    Bitmap show_image_by_uri( ){
        Uri extras = getIntent().getParcelableExtra("foto");
        Bitmap foto_bitmap= null;
        try {
            foto_bitmap = getBitmapFromUri( extras);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return foto_bitmap ;
    }
    private Bitmap getBitmapFromUri ( Uri uri ) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor = getContentResolver().openFileDescriptor( uri , "r" );
        FileDescriptor fileDescriptor = parcelFileDescriptor . getFileDescriptor ();
        Bitmap image = BitmapFactory. decodeFileDescriptor ( fileDescriptor );
        parcelFileDescriptor . close ();
        return image ;
    }

/*****************************************************/








    /***
     *
     *
     * METHODS FOR TAKING A FULL SIZE PHOTO
     * @return
     * @throws IOException
     */

    private File createImageFile_Container() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);//DIRECTORIO PRIVADO PARA LOS ARCHIVOS
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    void append_uri_data_to_intent(){
        /************************************************/
        //Proporcionar un archivo para guardar la foto
        File imageFile= null;
        try {
            imageFile = createImageFile_Container();
        } catch (IOException e) {
            e.printStackTrace();
        }/***********************************************/

        if( imageFile != null){

            photoUri=  FileProvider.getUriForFile(this, "com.example.sonia.uvapp.fileprovider", imageFile);

            intent_for_take_photo.putExtra( MediaStore.EXTRA_OUTPUT,  photoUri);//Se proporciona la URI  de archivo a la app de la Camara
            //Utilizara el archivo para almacenar la foto
        }
    }


    void take_photo(){
        intent_for_take_photo = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        append_uri_data_to_intent();
        if(  intent_for_take_photo.resolveActivity( getPackageManager())  != null)
            startActivityForResult(  intent_for_take_photo,   REQUEST_TAKE_PHOTO );
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
        try {
            foto_bitmap = getBitmapFromUri( photoUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        taken_photo.setImageBitmap( foto_bitmap);
        /**Para enviar a otra actividad
        //Ejecutar la actividad de vista previa de la foto
        Intent show_photo=  new Intent(  getApplicationContext(), Fototipo_photo.class);
        show_photo.putExtra("foto" ,  photoUri);
        //Cerrar actividad actual
        finish();
        startActivity( show_photo );
         */
    }



    void show_mini_photo(Bundle data){

        Bundle extras =  data;
        Bitmap imageBitmap = (Bitmap) extras.get("data");
        taken_photo.setImageBitmap(imageBitmap);

        /**Para enviar a otra actividad
        Bundle extras =  data;
        //Bitmap imageBitmap = (Bitmap) extras.get("data");
        //imageView.setImageBitmap(imageBitmap);

        //Ejecutar la actividad de vista previa de la foto
        Intent show_photo=  new Intent(  getApplicationContext(), Fototipo_photo.class);
        show_photo.putExtra("foto" ,  extras);
        //Cerrar actividad actual
        finish();
        startActivity( show_photo );
         **/
    }
    /*****************************************************/




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(  requestCode == REQUEST_TAKE_PHOTO  &&  resultCode== RESULT_OK ){




            show_photo_full_size();
            //show_mini_photo( data.getExtras());




            //Object data1 = data.getExtras().get("data");
            //taken_photo.setImageBitmap( (Bitmap)  data1);


        }//end request type check
    }




}

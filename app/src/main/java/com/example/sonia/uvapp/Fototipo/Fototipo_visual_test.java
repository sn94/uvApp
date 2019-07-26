package com.example.sonia.uvapp.Fototipo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sonia.uvapp.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class Fototipo_visual_test extends AppCompatActivity {




    String nick_="";

    TextView progress_test_label, progress_test_fototipo,progress_test_rgb;
    Button progress_test_skin, fvt_guardar;

    String FototipoValue="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fototipo_visual_test);
        nick_= getIntent().getExtras().getString("nick");
        fvt_guardar= (Button) findViewById( R.id.fvt_guardar);

        start_prototype_by_bluetooth();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver( receiver );
        //desactivar bluetooth
        bluetoothAdapter.disable();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if( requestCode == REQUEST_ENABLE_BT  &&  resultCode == RESULT_OK  ){
            Log.i("Bluetooth","enabled");
            discoverDevices();
        }
    }




    void ini_analysis_progress() {
        progress_test_label = (TextView) findViewById(R.id.fvt_testing_label);
        progress_test_label.setText(R.string.Fototipo_visual_test_text1);
        //fototipo
        progress_test_fototipo = (TextView)  findViewById(R.id.fvt_testing_fototipo);
        //rgb
        progress_test_rgb = (TextView)  findViewById(R.id.fvt_testing_rgb);
        //color
        progress_test_skin = (Button)  findViewById(R.id.fvt_testing_skin);

    }




    public void remove_analysis_progress(){

    }

    public   void show_result(View v){
        String f=  FototipoValue;//1-6
        Intent i= new Intent( this, Fototipo_result.class);
        i.putExtra("nick", nick_);
        i.putExtra("fototipo",   f );
        startActivity( i );
        finish();
    }










    BluetoothAdapter bluetoothAdapter = null;
    int REQUEST_ENABLE_BT= 5;

    // Create a BroadcastReceiver for ACTION_FOUND.
    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Discovery has found a device. Get the BluetoothDevice
                // object and its info from the Intent.
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); // MAC address
                Log.i("device name",   deviceName);
                Log.i("device mac add",  deviceHardwareAddress);//00:18:96:B0:08:20
                if( deviceName.equals("HC05"))
                    new PhototypeBTDevice( device).execute();
                    //new ConnectThread( device ).run();
            }
        }
    };



    boolean isBluetoothSupported( ) {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        return bluetoothAdapter != null;
    }


    boolean isBluetoothEnabled(){  return bluetoothAdapter.isEnabled(); }


    void discoverDevices(){

        // Register for broadcasts when a device is discovered.
            IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);//accion Dispositivo encontrado
            registerReceiver( receiver, filter );
            bluetoothAdapter.startDiscovery();
            //Register ya creado

    }



      void start_prototype_by_bluetooth(  ){
        if( isBluetoothSupported()){
            if(  isBluetoothEnabled()){
                discoverDevices();
            }else{
                //SOLICITA ACTIVACION DE BLUETOOTH
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        }
    }











    class PhototypeBTDevice extends  AsyncTask<Void, Void, Void>{

        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;
        private InputStream _inStream;
        private OutputStream _outStream;


        String fototipoDetected,r,g,b;


        public  PhototypeBTDevice( BluetoothDevice device){
            // Use a temporary object that is later assigned to mmSocket
            // because mmSocket is final.
            BluetoothSocket tmp = null;
            mmDevice = device;

            try {
                // Get a BluetoothSocket to connect with the given BluetoothDevice.
                // MY_UUID is the app's UUID string, also used in the server code.
                tmp = device.createRfcommSocketToServiceRecord( mmDevice.getUuids()[0].getUuid());
            } catch (IOException e) {  Log.e("socket", "Socket's create() method failed", e); }
            mmSocket = tmp;

        }





        void recibirDatos(){
            try {
                _inStream = mmSocket.getInputStream();
                byte[] buffer = new byte[1024];  // buffer (our data)
                int bytesCount=0; // amount of read bytes
                String messageFromBluetooth= "";
                while (  true) {
                    try {
                        //reading data from input stream
                        bytesCount = _inStream.read(buffer);
                        if(buffer != null && bytesCount > 0)
                        {
                            if(buffer[ bytesCount-1] == '*'){
                                //INTERPRETAR la lectura actual
                                String[] alldata= messageFromBluetooth.split(",");
                                if( alldata.length >3 ){
                                    String Fototipo= alldata[0];
                                    String canal_R= alldata[1];String canal_G= alldata[2];String canal_B= alldata[3];
                                    fototipoDetected= Fototipo;
                                    r= canal_R; g= canal_G;  b=  canal_B;
                                    //mostrar datos leidos
                                    Log.i("Fototipo", Fototipo);
                                    Log.i("rgb", canal_R+"-"+canal_G+"-"+canal_B);
                                    publishProgress();
                                }

                                //RESET CADENA
                                messageFromBluetooth= "";
                            }else{
                                if( buffer[ bytesCount-1] == 'X'){
                                    //remove_analysis_progress();
                                    break;
                                }else{  messageFromBluetooth+= new String( buffer, 0, bytesCount ); }
                            }
                        }else{ break;  }
                    } catch (IOException e) {
                        Log.e("Recibir datos,", e.toString());
                    }
                }

            } catch (IOException e) {   Log.e("Err al rec fluj de ent ", e.toString());  }
        }


        void enviarDatos( byte[]  bytes){
            try {
                _outStream = mmSocket.getOutputStream();
                _outStream.write(bytes);
            } catch (IOException e) {
                Log.e("Enviar datos,", e.toString());
            }
        }





    void ejecutar(){
        bluetoothAdapter.cancelDiscovery();
        try {
            // Connect to the remote device through the socket. This call blocks
            // until it succeeds or throws an exception.
            mmSocket.connect();
            enviarDatos( "1".getBytes());
            recibirDatos();
            cancel( true) ;

        } catch (IOException connectException) {
            // Unable to connect; close the socket and return.
            try {
                mmSocket.close();//00:21:13:03:11:25

            } catch (IOException closeException) {
                Log.e("socket", "Could not close the client socket", closeException);
            }
        }
    }


        @Override
        protected void onPreExecute() {
            ini_analysis_progress();
        }

        @Override
        protected void onCancelled() {
            try {
                mmSocket.close();
                progress_test_label.setText("");
                FototipoValue= fototipoDetected;
                fvt_guardar.setEnabled( true);
            } catch (IOException e) {
                Log.e("socket", "Could not close the client socket", e);
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            progress_test_fototipo.setText( fototipoDetected );
            String colorRgb=  "("+r+","+g+","+b+")";
            progress_test_rgb.setText( colorRgb);
            int rojo,verde, azul= 0;

            try{
                rojo= Integer.parseInt( r);  verde=Integer.parseInt(g);  azul= Integer.parseInt(b);
                progress_test_skin.setBackgroundColor( Color.rgb(rojo, verde , azul )  );
            }catch (NumberFormatException ex){ }
        }

        @Override
        protected Void doInBackground(Void... voids) {
            ejecutar();
            return null;
        }
    }





    private class ConnectThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;
        private InputStream _inStream;
        private OutputStream _outStream;


        public ConnectThread(BluetoothDevice device) {
            // Use a temporary object that is later assigned to mmSocket
            // because mmSocket is final.
            BluetoothSocket tmp = null;
            mmDevice = device;

            try {
                // Get a BluetoothSocket to connect with the given BluetoothDevice.
                // MY_UUID is the app's UUID string, also used in the server code.
                tmp = device.createRfcommSocketToServiceRecord( mmDevice.getUuids()[0].getUuid());
            } catch (IOException e) {
                Log.e("socket", "Socket's create() method failed", e);
            }
            mmSocket = tmp;
        }



        void recibirDatos(){
            try {
                _inStream = mmSocket.getInputStream();
                byte[] buffer = new byte[1024];  // buffer (our data)
                int bytesCount=0; // amount of read bytes
                String messageFromBluetooth= "";
                ini_analysis_progress();
                while (  true) {
                    try {
                        //reading data from input stream
                        bytesCount = _inStream.read(buffer);
                        if(buffer != null && bytesCount > 0)
                        {
                            if(buffer[ bytesCount-1] == '*'){
                                //INTERPRETAR la lectura actual
                                String[] alldata= messageFromBluetooth.split(",");
                                if( alldata.length >3 ){
                                    String Fototipo= alldata[0];
                                    String canal_R= alldata[1];String canal_G= alldata[2];String canal_B= alldata[3];
                                   // fototipoDetected= Fototipo;
                                    //r= canal_R; g= canal_G;  b=  canal_B;
                                    //mostrar datos leidos
                                    Log.i("Fototipo", Fototipo);
                                    Log.i("rgb", canal_R+"-"+canal_G+"-"+canal_B);
                                }
                                //update_analysis_progress( );
                                //RESET CADENA
                                messageFromBluetooth= "";
                            }else{
                                if( buffer[ bytesCount-1] == 'X'){
                                    remove_analysis_progress();
                                    break;
                                }else{  messageFromBluetooth+= new String( buffer, 0, bytesCount ); }
                            }
                        }else{ break;  }
                    } catch (IOException e) {
                        Log.e("Recibir datos,", e.toString());
                    }
                }

            } catch (IOException e) {   Log.e("Err al rec fluj de ent ", e.toString());  }
        }


        void enviarDatos( byte[]  bytes){
            try {
                _outStream = mmSocket.getOutputStream();
                _outStream.write(bytes);
            } catch (IOException e) {
                Log.e("Enviar datos,", e.toString());
            }
        }

        public void run() {
            // Cancel discovery because it otherwise slows down the connection.
            bluetoothAdapter.cancelDiscovery();

            try {
                // Connect to the remote device through the socket. This call blocks
                // until it succeeds or throws an exception.
                mmSocket.connect();
                enviarDatos( "1".getBytes());
                recibirDatos();
                //remove_analysis_progress();
                cancel();

            } catch (IOException connectException) {
                // Unable to connect; close the socket and return.
                try {
                    mmSocket.close();//00:21:13:03:11:25

                } catch (IOException closeException) {
                    Log.e("socket", "Could not close the client socket", closeException);
                }
                return;
            }

            // The connection attempt succeeded. Perform work associated with
            // the connection in a separate thread.
          //  manageMyConnectedSocket(mmSocket);
        }

        // Closes the client socket and causes the thread to finish.
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
                Log.e("socket", "Could not close the client socket", e);
            }
        }
    }














}

package com.example.sonia.uvapp.Fototipo;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.sonia.uvapp.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class Fototipo_visual_test extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fototipo_visual_test);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver( receiver );
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if( requestCode == REQUEST_ENABLE_BT  &&  resultCode == RESULT_OK  ){
            Log.i("Bluetooth","enabled");
            discoverDevices();
        }
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
                Log.i("device mac add",  deviceHardwareAddress);
                new ConnectThread( device ).run();
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
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(receiver, filter);
        bluetoothAdapter.startDiscovery();
    }



    void start_prototype_by_bluetooth(){
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








    public void iniciar_test(View w){
        start_prototype_by_bluetooth();
        /*
        //activar dispositivo
        Log.i("ACTIVANDO DISPOSITIVO", "ACTIVANDO");

        int f=  1;//1-6

        Intent i= new Intent( this, Fototipo_result.class);
        i.putExtra("nick", nick_);
        i.putExtra("fototipo", String.valueOf(  f ));
        startActivity( i );
        finish();*/
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

                do {
                    try {
                        //reading data from input stream
                        bytesCount = _inStream.read(buffer);
                        if(buffer != null && bytesCount > 0)
                        {
                            //Parse received bytes
                            Log.i("Recibido", "datos");
                        }
                    } catch (IOException e) {
                        //Error
                    }
                }while (  true);

            } catch (IOException e) {
                //Error
            }
        }


        void enviarDatos( byte[]  bytes){
            try {
                _outStream = mmSocket.getOutputStream();
                _outStream.write(bytes);
            } catch (IOException e) {
                //Error
            }
        }

        public void run() {
            // Cancel discovery because it otherwise slows down the connection.
            bluetoothAdapter.cancelDiscovery();

            try {
                // Connect to the remote device through the socket. This call blocks
                // until it succeeds or throws an exception.
                mmSocket.connect();
                enviarDatos( "Hola Ardu, Soy Andry".getBytes());
                recibirDatos();
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

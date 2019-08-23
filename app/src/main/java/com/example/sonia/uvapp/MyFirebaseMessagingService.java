package com.example.sonia.uvapp;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import static android.content.ContentValues.TAG;

public class MyFirebaseMessagingService  extends FirebaseMessagingService {




    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    @Override
    public void onNewToken(String token) {
        Log.d( "Refreshed token", "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
       // sendRegistrationToServer(token);
    }


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {//aqui se manejan los mensajes de datos
                                                                //las notificaciones son manejadas automaticamente por el sdk de FCM
        Log.d("recibido", "si");

         // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Map<String,String> dt= remoteMessage.getData();
            String titulo= dt.get("title");
            String msg= dt.get("body");
            showNotification( titulo, msg);


        }/****/

            // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
                Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

                // Also if you intend on generating your own notifications as a result of a received FCM
                // message, here is where that should be initiated. See sendNotification method below.
        }






        void showNotification(String titulo, String msg){
            // Create an explicit intent for an Activity in your app
            Intent intent = new Intent(this, Alerta.class);
            intent.setFlags( Intent.FLAG_ACTIVITY_SINGLE_TOP);
           // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            Bundle bundle= new Bundle(); bundle.putString( "title", titulo);bundle.putString( "body", msg );
            intent.putExtras( bundle);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.uvapp_logo)
                    .setContentTitle( titulo)
                    .setContentText( msg)
                    .setContentIntent( pendingIntent )
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel( true );
            //Show the notification
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            // notificationId is a unique int for each notification that you must define
            notificationManager.notify( 1994, builder.build());


        }

}

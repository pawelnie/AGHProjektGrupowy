package com.example.wydarzenia;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;

public class MessagingServices extends FirebaseMessagingService {
    public final String FCM = "FCM";
    //public MessagingServices() {
    //}

//    @Override
//    public IBinder onBind(Intent intent) {
//        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
//    }


    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    @Override
    public void onNewToken(String token) {
        Log.d(FCM, "Refreshed token: " + token);


        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        // TODO: sendRegistrationToServer(token);
    }
}

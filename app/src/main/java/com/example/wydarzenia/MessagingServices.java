package com.example.wydarzenia;

import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MessagingServices extends FirebaseMessagingService {
    private final String EID_KEY = "eid";
    String eid;
    //public MessagingServices() {
    //}

//    @Override
//    public IBinder onBind(Intent intent) {
//        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
//    }


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        eid = remoteMessage.getData().get("event");

        Intent intent = new Intent(this, EventEntryActivity.class);
        intent.putExtra(EID_KEY, eid);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
        stackBuilder.addNextIntentWithParentStack(intent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "reminder")
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle(remoteMessage.getData().get("title"))
                .setContentText(remoteMessage.getData().get("text"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        notificationManager.notify(1, builder.build());


    }

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    @Override
    public void onNewToken(String token) {
        Log.d("FCM", "Refreshed token: " + token);


        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        // TODO: sendRegistrationToServer(token);
    }
}

package ingenieriatrading.myapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by LGerardo-PC on 28/02/2017.
 */

public class MiFirebaseMessagingService extends FirebaseMessagingService{
    public static final String TAG = "Notificación: ";

    public MiFirebaseMessagingService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //Checa si el mensaje contiene datos
        if(remoteMessage.getData().size()>0)
        {
            Log.d(TAG, "Mensaje recibido: "+ remoteMessage.getData());
        }
        //Checa si el mensaje contiene notificacion
        if(remoteMessage.getNotification() != null){
            String title = remoteMessage.getNotification().getTitle();
            String message = remoteMessage.getNotification().getBody();

            Log.d(TAG, "Titulo del mensaje de notificación: "+ title);
            Log.d(TAG, "Cuerpo del mensaje de notificación: "+message);

            mostrarNotificacion(title, message);
        }

        if(remoteMessage.getData() != null) {
            Log.d("FIREBASE", "DATOS RECIBIDOS");
            Log.d("FIREBASE", "Usuario: " + remoteMessage.getData().get("usuario"));
            Log.d("FIREBASE", "Estado: " + remoteMessage.getData().get("estado"));
        }

        //super.onMessageReceived(remoteMessage);
        /*String from =  remoteMessage.getFrom();
        String title = remoteMessage.getNotification().getTitle();
        String message = remoteMessage.getNotification().getBody();
        Log.d(TAG, "Mensaje Recibido de: "+ from + "\nTitulo:"+title+" Mensaje: "+message);
        mostrarNotificacion(title, message);*/
        /*if(remoteMessage.getNotification()!=null)
        {
            Log.d(TAG, "Notificacion: "+ remoteMessage.getNotification().getBody());
            //mostrarNotificacion(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
        }
        if(remoteMessage.getData().size()>0)
        {
            Log.d(TAG, "data: "+ remoteMessage.getData());
        }*/
    }

    /**
     * Metodo encargado de mostrar la notificacion en nustro dispositivo
     * @param title titulo de la notificacion
     * @param body cuerpo de la notificacion
     */
    private void mostrarNotificacion(String title, String body)
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(soundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0/*ID de l notificación*/, notificationBuilder.build());
    }
}

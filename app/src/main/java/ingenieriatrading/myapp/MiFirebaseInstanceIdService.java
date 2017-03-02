package ingenieriatrading.myapp;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by LGerardo-PC on 28/02/2017.
 */

public class MiFirebaseInstanceIdService extends FirebaseInstanceIdService {
    public static final String TAG = "Noticias";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "TOKEN: "+token);
    }
}

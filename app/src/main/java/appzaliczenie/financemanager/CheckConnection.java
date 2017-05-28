package appzaliczenie.financemanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by User on 28.05.2017.
 */

public class CheckConnection {

    private Context context;
    public CheckConnection(Context context){
        this.context = context;
    }
    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo == null) {
            // There are no active networks.
            return false;
        } else
            return true;
    }
}

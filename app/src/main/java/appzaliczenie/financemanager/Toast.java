package appzaliczenie.financemanager;

import android.content.Context;
import android.view.Gravity;

public class Toast {
    public Toast(String komunikat, Context context){
        int duration = android.widget.Toast.LENGTH_SHORT;
        android.widget.Toast toast = android.widget.Toast.makeText(context, komunikat, duration);
        toast.setGravity(Gravity.CENTER_HORIZONTAL,0,0);
        toast.show();
    }
}
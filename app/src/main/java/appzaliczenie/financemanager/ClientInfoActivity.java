package appzaliczenie.financemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ClientInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Informacje o Kliencie");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_info);
    }
}

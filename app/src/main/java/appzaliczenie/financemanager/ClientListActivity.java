package appzaliczenie.financemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ClientListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Lista Klientow");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_list);
    }
}

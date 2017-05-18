package appzaliczenie.financemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainWindowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Okno po zalogowniu");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_window);
    }
}

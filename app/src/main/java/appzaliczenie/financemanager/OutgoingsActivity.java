package appzaliczenie.financemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class OutgoingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Wydatki");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outgoings);
    }
}

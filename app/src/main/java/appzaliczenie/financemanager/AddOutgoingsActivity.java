package appzaliczenie.financemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AddOutgoingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Okno dodawnia wypłat");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_outgoings);
    }
}

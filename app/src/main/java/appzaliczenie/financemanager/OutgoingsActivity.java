package appzaliczenie.financemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class OutgoingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Wydatki");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outgoings);
    }

    public void addOutgoingsItem(View view) {
        Intent intent = new Intent(this, AddOutgoingsActivity.class);
        startActivity(intent);
    }
}

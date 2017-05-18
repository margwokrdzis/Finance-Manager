package appzaliczenie.financemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AddIncomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Okno dodawania wpłat");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);
    }
}

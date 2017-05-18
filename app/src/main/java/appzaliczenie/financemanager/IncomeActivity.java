package appzaliczenie.financemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class IncomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Przychód");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
    }

    public void addIncomeItem(View view) {
        Intent intent = new Intent(this, AddIncomeActivity.class);
        startActivity(intent);
    }
}

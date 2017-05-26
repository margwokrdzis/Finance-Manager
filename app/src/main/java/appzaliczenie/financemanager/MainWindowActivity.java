package appzaliczenie.financemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainWindowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Okno po zalogowniu takie o");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_window);
    }

    public void clientList(View view) {
        Intent intent = new Intent(this, ClientListActivity.class);
        startActivity(intent);
    }

    public void incomeList(View view) {
        Intent intent = new Intent(this, IncomeActivity.class);
        startActivity(intent);
    }

    public void outgoingsList(View view) {
        Intent intent = new Intent(this, OutgoingsActivity.class);
        startActivity(intent);
    }

    public void createInvoice(View view) {
        Intent intent = new Intent(this, CreateInvoiceActivity.class);
        startActivity(intent);
    }

    public void monthlyStatistics(View view) {
        Intent intent = new Intent(this, MonthlyStatisticsActivity.class);
        startActivity(intent);
    }

    public void updateProfile(View view){
        Intent intent = new Intent(this, MyProfileActivity.class);
        startActivity(intent);
    }

    public void logout(View view) {

    }

}

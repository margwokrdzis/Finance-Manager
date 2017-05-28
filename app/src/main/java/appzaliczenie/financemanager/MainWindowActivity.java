package appzaliczenie.financemanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainWindowActivity extends AppCompatActivity {

    private SharedPreferences sp;
    private CheckConnection cc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Okno po zalogowniu takie o");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_window);
        cc = new CheckConnection(this);
    }

    public void onClientList(View view) {
        if(!cc.isNetworkConnected()){
            new Toast("Brak polaczenia z internetem", this);
        }else {
            Intent intent = new Intent(this, ClientListActivity.class);
            startActivity(intent);
        }
    }

    public void onIncomeList(View view) {
        if(!cc.isNetworkConnected()){
            new Toast("Brak polaczenia z internetem", this);
        }else {
            Intent intent = new Intent(this, IncomeActivity.class);
            startActivity(intent);
        }
    }

    public void onOutgoingsList(View view) {
        if(!cc.isNetworkConnected()){
            new Toast("Brak polaczenia z internetem", this);
        }else {
            Intent intent = new Intent(this, OutgoingsActivity.class);
            startActivity(intent);
        }
    }

    public void onCreateInvoice(View view) {
        if(!cc.isNetworkConnected()){
            new Toast("Brak polaczenia z internetem", this);
        }else {
            Intent intent = new Intent(this, CreateInvoiceActivity.class);
            startActivity(intent);
        }
    }

    public void onMonthlyStatistics(View view) {
        if(!cc.isNetworkConnected()){
            new Toast("Brak polaczenia z internetem", this);
        }else {
            Intent intent = new Intent(this, MonthlyStatisticsActivity.class);
            startActivity(intent);
        }
    }

    public void onUpdateProfile(View view){
        if(!cc.isNetworkConnected()){
            new Toast("Brak polaczenia z internetem", this);
        }else {
            Intent intent = new Intent(this, UpdateCompanyActivity.class);
            startActivity(intent);
        }
    }

    public void onLogout(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void onCalculator(View view){
        Intent intent = new Intent(this, CalculatorActivity.class);
        startActivity(intent);
    }

}

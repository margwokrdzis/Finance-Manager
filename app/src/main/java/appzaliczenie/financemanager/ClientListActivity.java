package appzaliczenie.financemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ClientListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Lista Klientow");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_list);


        ListView listView = (ListView) findViewById(R.id.clientListView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

            }
        });

    }



    public void onAddNewClient (View view){
            Intent intent = new Intent(this, AddNewClientActivity.class) ;
            startActivity(intent);
    }

}

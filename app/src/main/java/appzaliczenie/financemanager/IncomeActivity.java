package appzaliczenie.financemanager;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IncomeActivity extends ListActivity implements DatabaseOperations{

    JSONParser jParser = new JSONParser();
    ArrayList<HashMap<String, String>> incomingsList;
    JSONArray incomings = null;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Przychód");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        sp = getSharedPreferences("appzaliczenie.financemanager", Context.MODE_PRIVATE);
        incomingsList = new ArrayList<>();
        new LoadIncomings().execute();
        ListView lv = getListView();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // getting values from selected ListItem
                String incomeID = ((TextView) view.findViewById(R.id.incomeIdTV)).getText().toString();

                // Starting new intent
               // Intent in = new Intent(getApplicationContext(), EditProductActivity.class);
                // sending pid to next activity
               // in.putExtra(TAG_PID, pid);

                // starting new activity and expecting some response back
              //  startActivityForResult(in, 100);
            }
        });
    }

    // Response from Edit Product Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // if result code 100
        if (resultCode == 100) {
            // if result code 100 is received
            // means user edited/deleted product
            // reload this screen again
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }

    }

    class LoadIncomings extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... args) {
            // Building Parameters
            String id_company = sp.getString("id_company","");
            List<NameValuePair> list = new ArrayList<>();
            list.add(new BasicNameValuePair("id_company", id_company));
            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(GET_INCOME_LIST_SERVICE, "GET", list);

            try {
                int success = json.getInt(SUCCESS_TAG);

                if (success == 1) {
                    incomings = json.getJSONArray(INCOMING_TAG);

                    // looping through All Products
                    for (int i = 0; i < incomings.length(); i++) {
                        JSONObject c = incomings.getJSONObject(i);

                        // Storing each json item in variable
                        String id = c.getString(ID_INCOMING_TAG);
                        String name = c.getString(TAG_NAME);
                        String ammount = c.getString(TAG_AMMOUNT);
                        String date = c.getString(TAG_DATE);

                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<>();

                        // adding each child node to HashMap key => value
                        map.put(ID_INCOMING_TAG, id);
                        map.put(TAG_NAME, name);
                        map.put(TAG_AMMOUNT, ammount);
                        map.put(TAG_DATE, date);

                        // adding HashList to ArrayList
                        incomingsList.add(map);
                    }
                } else {

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(String file_url) {
            runOnUiThread(new Runnable() {
                public void run() {
                    ListAdapter adapter = new SimpleAdapter(IncomeActivity.this, incomingsList,
                            R.layout.adapter_ic_view_layout, new String[] { ID_INCOMING_TAG, TAG_NAME, TAG_AMMOUNT, TAG_DATE},
                            new int[] { R.id.incomeIdTV, R.id.incNameTV , R.id.incAmmountTV, R.id.incDateTV });

                    setListAdapter(adapter);
                }
            });

        }
    }

    public void addIncomeItem(View view) {
        Intent intent = new Intent(this, AddIncomeActivity.class);
        startActivity(intent);
    }
}

package appzaliczenie.financemanager;

import android.app.ListActivity;
import android.app.ProgressDialog;
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

public class OutgoingsActivity extends ListActivity implements DatabaseOperations {
    private JSONParser jParser = new JSONParser();
    private ArrayList<HashMap<String, String>> outgoingsList;
    private JSONArray outgoings = null;
    private SharedPreferences sp;
    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Wydatki");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outgoings);

        sp = getSharedPreferences("appzaliczenie.financemanager", Context.MODE_PRIVATE);
        outgoingsList = new ArrayList<>();
        new OutgoingsActivity.LoadOutgoings().execute();
        ListView lv = getListView();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // getting values from selected ListItem
                String outgoingID = ((TextView) view.findViewById(R.id.outgoingIdTV)).getText().toString();

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

    class LoadOutgoings extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(OutgoingsActivity.this);
            pDialog.setMessage("Operation in progress...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        protected String doInBackground(String... args) {
            String id_company = sp.getString("id_company","");
            List<NameValuePair> list = new ArrayList<>();
            list.add(new BasicNameValuePair("id_company", id_company));

            JSONObject json = jParser.makeHttpRequest(GET_OUTGOINGS_LIST_SERVICE, "GET", list);

            try {
                int success = json.getInt(SUCCESS_TAG);

                if (success == 1) {
                    outgoings = json.getJSONArray(OUTGOING_TAG);
                    HashMap<String, String> mapTag = new HashMap<>();
                    mapTag.put(ID_OUTGOINGS_TAG, "Bilans miesieczny");
                    mapTag.put(TAG_NAME, "Za co");
                    mapTag.put(TAG_AMMOUNT, "Kwota");
                    mapTag.put(TAG_DATE, "Data");
                    outgoingsList.add(mapTag);

                    for (int i = 0; i < outgoings.length(); i++) {
                        JSONObject c = outgoings.getJSONObject(i);

                        String id = c.getString(ID_OUTGOINGS_TAG);
                        String name = c.getString(TAG_NAME);
                        String ammount = c.getString(TAG_AMMOUNT);
                        String date = c.getString(TAG_DATE);

                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<>();

                        // adding each child node to HashMap key => value
                        map.put(ID_OUTGOINGS_TAG, id);
                        map.put(TAG_NAME, name);
                        map.put(TAG_AMMOUNT, ammount);
                        map.put(TAG_DATE, date);

                        // adding HashList to ArrayList
                        outgoingsList.add(map);
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
                    ListAdapter adapter = new SimpleAdapter(OutgoingsActivity.this, outgoingsList,
                            R.layout.adapter_ic_view_layout, new String[] { ID_INCOMING_TAG, TAG_NAME, TAG_AMMOUNT, TAG_DATE},
                            new int[] { R.id.incomeIdTV, R.id.incNameTV , R.id.incAmmountTV, R.id.incDateTV });

                    setListAdapter(adapter);
                }
            });
            pDialog.dismiss();
        }
    }
    public void addOutgoingsItem(View view) {
        Intent intent = new Intent(this, AddOutgoingsActivity.class);
        startActivity(intent);
    }
}

package appzaliczenie.financemanager;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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

public class ClientListActivity extends ListActivity implements DatabaseOperations{
    private JSONParser jParser = new JSONParser();
    private ArrayList<HashMap<String, String>> clientsList;
    private JSONArray clients = null;
    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Lista Klientow");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_list);
        sp = getSharedPreferences("appzaliczenie.financemanager", Context.MODE_PRIVATE);
        clientsList = new ArrayList<>();
        jParser = new JSONParser();

        ListView lv = getListView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String id_client = ((TextView) view.findViewById(R.id.clientIdTV)).getText().toString();
                System.out.println("aaaaaaaaaaaa" + id_client);
                Intent intent = new Intent(ClientListActivity.this, ClientInfoActivity.class);
                intent.putExtra(TAG_ID_CLIENT, id_client);
                startActivity(intent);
                finish();
            }
        });

        new ClientListActivity.LoadClients().execute();
    }
    public void onAddNewClient (View view){
            Intent intent = new Intent(this, AddNewClientActivity.class) ;
            startActivity(intent);
    }

    class LoadClients extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... args) {
            // Building Parameters
            String id_company = sp.getString(ID_COMPANY_TAG,"");
            List<NameValuePair> list = new ArrayList<>();
            list.add(new BasicNameValuePair(ID_COMPANY_TAG, id_company));
            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(GET_CLIENT_LIST_SERVICE, "GET", list);

            try {
                int success = json.getInt(SUCCESS_TAG);

                if (success == 1) {
                    clients = json.getJSONArray(TAG_CLIENT);

                    for (int i = 0; i < clients.length(); i++) {
                        JSONObject c = clients.getJSONObject(i);

                        String id = c.getString(TAG_ID_CLIENT);
                        String name = c.getString(TAG_NAME);
                        String phoneNumber = c.getString(TAG_PHONE_NUMBER);
                        String Email = c.getString(TAG_EMAIL);

                        HashMap<String, String> map = new HashMap<>();

                        map.put(TAG_ID_CLIENT, id);
                        map.put(TAG_NAME, name);
                        map.put(TAG_PHONE_NUMBER, phoneNumber);
                        map.put(TAG_EMAIL, Email);

                        clientsList.add(map);
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
                    ListAdapter adapter = new SimpleAdapter(ClientListActivity.this, clientsList,
                            R.layout.adapter_cl_view_layout, new String[] { TAG_ID_CLIENT, TAG_NAME, TAG_PHONE_NUMBER, TAG_EMAIL},
                            new int[] { R.id.clientIdTV, R.id.clientNameTV , R.id.clientPhoneNumbTV, R.id.clientEmailTV });

                    setListAdapter(adapter);
                }
            });

        }
    }
}

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

public class IncomeActivity extends ListActivity implements DatabaseOperations{

    private JSONParser jParser = new JSONParser();
    private ArrayList<HashMap<String, String>> incomingsList;
    private JSONArray incomings = null;
    private SharedPreferences sp;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Przychód");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        sp = getSharedPreferences("appzaliczenie.financemanager", Context.MODE_PRIVATE);
        incomingsList = new ArrayList<>();
        new LoadIncomings().execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 100) {
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }

    }

    class LoadIncomings extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(IncomeActivity.this);
            pDialog.setMessage("Operation in progress...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        protected String doInBackground(String... args) {
            // Building Parameters
            String id_company = sp.getString(ID_COMPANY_TAG,"");
            List<NameValuePair> list = new ArrayList<>();
            list.add(new BasicNameValuePair(ID_COMPANY_TAG, id_company));
            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(GET_INCOME_LIST_SERVICE, "GET", list);

            try {
                int success = json.getInt(SUCCESS_TAG);

                HashMap<String, String> mapTag = new HashMap<>();
                mapTag.put(ID_INCOMING_TAG, "Bilans miesieczny");
                mapTag.put(TAG_NAME, "Za co");
                mapTag.put(TAG_AMMOUNT, "Kwota");
                mapTag.put(TAG_DATE, "Data");
                incomingsList.add(mapTag);

                if (success == 1) {
                    incomings = json.getJSONArray(INCOMING_TAG);

                    for (int i = 0; i < incomings.length(); i++) {
                        JSONObject c = incomings.getJSONObject(i);

                        String id = c.getString(ID_INCOMING_TAG);
                        String name = c.getString(TAG_NAME);
                        String ammount = c.getString(TAG_AMMOUNT);
                        String date = c.getString(TAG_DATE);

                        HashMap<String, String> map = new HashMap<>();

                        map.put(ID_INCOMING_TAG, id);
                        map.put(TAG_NAME, name);
                        map.put(TAG_AMMOUNT, ammount);
                        map.put(TAG_DATE, date);

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
            pDialog.dismiss();

        }
    }

    public void addIncomeItem(View view) {
        Intent intent = new Intent(this, AddIncomeActivity.class);
        startActivity(intent);
    }
}

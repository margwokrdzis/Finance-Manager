package appzaliczenie.financemanager;

import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MonthlyStatisticsActivity extends ListActivity implements DatabaseOperations{
    private JSONParser jParser = new JSONParser();
    private ArrayList<HashMap<String, String>> incomingsList;
    private JSONArray incomes = null;
    private JSONArray outgoings = null;
    private SharedPreferences sp;
    private int year;
    private List monthList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_statistics);
        setTitle("Miesieczne statystyki");

        sp = getSharedPreferences("appzaliczenie.financemanager", Context.MODE_PRIVATE);
        incomingsList = new ArrayList<>();
        jParser = new JSONParser();

        new MonthlyStatisticsActivity.GetStatisitcsData().execute();
        year = Calendar.getInstance().get(Calendar.YEAR);
        monthList = new ArrayList();

    }

    class GetStatisitcsData extends AsyncTask<String, String, String> {

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
            JSONObject json = jParser.makeHttpRequest(GET_SUM_INCOME_SERVICE, "GET", list);

            try {
                int success = json.getInt(SUCCESS_TAG);

                if (success == 1) {
                    incomes = json.getJSONArray(TAG_SUMMARY);
                    HashMap<String, String> mapTag = new HashMap<>();

                    mapTag.put(TAG_AMMOUNT, "Bilans miesieczny");
                    mapTag.put(TAG_DATE, "Miesiac");

                    incomingsList.add(mapTag);
                    for (int i = 0; i < incomes.length(); i++) {
                        JSONObject c = incomes.getJSONObject(i);

                        String ammount = c.getString(TAG_AMMOUNT);
                        String year = c.getString(TAG_YEAR);
                        String month = c.getString(TAG_MONTH);

                        String date = year + "/" + month;

                        HashMap<String, String> map = new HashMap<>();

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
                    ListAdapter adapter = new SimpleAdapter(MonthlyStatisticsActivity.this, incomingsList,
                            R.layout.adapter_ms_view_layout, new String[] {TAG_AMMOUNT, TAG_DATE},
                            new int[] {R.id.sumTV, R.id.monthTV });

                    setListAdapter(adapter);
                }
            });

        }


    }
}


package appzaliczenie.financemanager;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UpdateCompanyActivity extends AppCompatActivity implements DatabaseOperations{

    private EditText nameET, emailET, phoneNumberET, nipET, cityET, streetET, buildingNumberET, doorNumberET, postalCodeET;
    private SharedPreferences sp;
    private ProgressDialog pDialog;
    private String id_company;
    private JSONParser jsonParser;
    private JSONObject json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_company);

        nameET = (EditText) findViewById(R.id.ucompanyNameET);
        emailET = (EditText) findViewById(R.id.uemailET);
        phoneNumberET = (EditText) findViewById(R.id.uphoneET);
        nipET = (EditText) findViewById(R.id.unipET);
        cityET = (EditText) findViewById(R.id.ucityET);
        streetET = (EditText) findViewById(R.id.ustreetET);
        buildingNumberET = (EditText) findViewById(R.id.ubuildingNumET);
        doorNumberET = (EditText) findViewById(R.id.udoorNumET);
        postalCodeET = (EditText) findViewById(R.id.upostalCodeET);

        sp = getSharedPreferences("appzaliczenie.financemanager", Context.MODE_PRIVATE);
        id_company = sp.getString("id_company", "");
        jsonParser = new JSONParser();
        new UpdateCompanyActivity.GetCompanyInfo().execute();
    }

    public void updateInfo(View view) {
        String companyName = nameET.getText().toString();
        String companyEmail = emailET.getText().toString();
        String companyPhoneNumber = phoneNumberET.getText().toString();
        String companyNIP = nipET.getText().toString();
        String companyCity = cityET.getText().toString();
        String companyPostalCode = postalCodeET.getText().toString();
        String companyBuildingNumber = buildingNumberET.getText().toString();
        String companyDoorNumber = doorNumberET.getText().toString();
        String companyStreet = streetET.getText().toString();

        if (companyName.equals("") || companyEmail.equals("") || companyPhoneNumber.equals("") ||
                companyNIP.equals("") || companyCity.equals("") || companyPostalCode.equals("") ||
                companyBuildingNumber.equals("") || companyStreet.equals("")) {
            new Toast("Podaj wszystkie dane", this);
        } else {
            BackgroundWorker loginWorker = new BackgroundWorker(this);
            loginWorker.execute(UPDATE_COMPANY_DATA, id_company, companyName, companyEmail, companyPhoneNumber, companyNIP,
                    companyCity, companyPostalCode, companyStreet, companyBuildingNumber, companyDoorNumber);
        }
    }

    class GetCompanyInfo extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(UpdateCompanyActivity.this);
            pDialog.setMessage("Wczytuje dane firmy...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        protected String doInBackground(String... params) {
            try {
                int success;
                List<NameValuePair> list = new ArrayList<>();
                list.add(new BasicNameValuePair("id_company", id_company));
                json = jsonParser.makeHttpRequest(GET_COMPANY_INFO_SERVICE, "GET", list);
                success = json.getInt(SUCCESS_TAG);

                if (success == 1) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            try {
                                JSONArray companyObj = json.getJSONArray(TAG_COMPANY);
                                JSONObject company = companyObj.getJSONObject(0);
                                nameET.setText(company.getString(TAG_COMPANY_NAME));
                                emailET.setText(company.getString(TAG_COMPANY_EMAIL));
                                phoneNumberET.setText(company.getString(TAG_COMPANY_PHONE_NUMBER));
                                nipET.setText(company.getString(TAG_COMPANY_NIP));
                                cityET.setText(company.getString(TAG_COMPANY_CITY));
                                streetET.setText(company.getString(TAG_COMPANY_STREET));
                                buildingNumberET.setText(company.getString(TAG_COMPANY_BUILDING_NUMBER));
                                doorNumberET.setText(company.getString(TAG_COMPANY_DOOR_NUMBER));
                                postalCodeET.setText(company.getString(TAG_COMPANY_POSTAL_CODE));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }return null;}

        protected void onPostExecute(String file_url) {
            pDialog.dismiss();
        }
    }
}

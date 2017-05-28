package appzaliczenie.financemanager;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ClientInfoActivity extends AppCompatActivity implements DatabaseOperations{
    private String id_client;
    private JSONParser jsonParser;
    private JSONObject json;
    private TextView clientName, clientEmail, clientNip, clientPhoneNumber, clientPostalCode,
            clientCity, clientStreet, clientBuildningNumber, clientDoorNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Informacje o Kliencie");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_info);
        Intent intent = getIntent();
        id_client = intent.getStringExtra(TAG_ID_CLIENT);

        clientName = (TextView) findViewById(R.id.clientNameInfoTV);
        clientEmail = (TextView) findViewById(R.id.clientEmailInfoTV);
        clientNip = (TextView) findViewById(R.id.clientNipInfoTV);
        clientPhoneNumber = (TextView) findViewById(R.id.clientPhoneNumbInfoTV);
        clientPostalCode = (TextView) findViewById(R.id.clientPostalCodeInfoTV);
        clientStreet = (TextView) findViewById(R.id.clientStreetInfoTV);
        clientCity = (TextView) findViewById(R.id.clientCityInfoTV);
        clientBuildningNumber = (TextView) findViewById(R.id.clientBuildingNumbInfoTV);
        clientDoorNumber = (TextView) findViewById(R.id.clientDoorNumbInfoTV);

        jsonParser = new JSONParser();
        new ClientInfoActivity.GetClientInfo().execute();
    }

    class GetClientInfo extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
        }

        protected String doInBackground(String... params) {
            try {
                int success;
                List<NameValuePair> list = new ArrayList<>();
                list.add(new BasicNameValuePair(TAG_ID_CLIENT, id_client));
                json = jsonParser.makeHttpRequest(GET_CLIENT_INFO_SERVICE, "GET", list);
                success = json.getInt(SUCCESS_TAG);

                if (success == 1) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            try {
                                JSONArray clientArr = json.getJSONArray(TAG_CLIENT);
                                JSONObject client = clientArr.getJSONObject(0);
                                clientName.setText(client.getString(TAG_NAME));
                                clientEmail.setText(client.getString(TAG_EMAIL));
                                clientNip.setText(client.getString(TAG_NIP));
                                clientPhoneNumber.setText(client.getString(TAG_PHONE_NUMBER));
                                clientPostalCode.setText(client.getString(TAG_POSTAL_CODE));
                                clientStreet.setText(client.getString(TAG_STREET));
                                clientCity.setText(client.getString(TAG_CITY));
                                clientBuildningNumber.setText(client.getString(TAG_BUILDING_NUMBER));
                                clientDoorNumber.setText(client.getString(TAG_DOOR_NUMBER));

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
        }
    }

    public void onClientDelete(View view){
        final BackgroundWorker deleteClientWorker = new BackgroundWorker(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Czy chcesz zlikiwdowac klienta? :)")
                .setCancelable(false)
                .setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        deleteClientWorker.execute(DELETE_CLIENT, id_client);
                    }
                })
                .setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }
}

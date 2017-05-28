package appzaliczenie.financemanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.ListAdapter;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import android.widget.SimpleAdapter;


public class BackgroundWorker extends AsyncTask<String, String, String> implements DatabaseOperations{

    private Context context;
    private String operationType;
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;
    private String id_company = null;
    private ProgressDialog pDialog;

    public BackgroundWorker(Context context){
        this.context = context;
        sp = context.getSharedPreferences("appzaliczenie.financemanager", Context.MODE_PRIVATE);
        edit = sp.edit();
    }


    @Override
    protected String doInBackground(String... params) {

        JSONParser jsonParser = new JSONParser();
        operationType = params[0];
        if(operationType.equals(LOGIN)) {
            loginDatabaseOperation(LOGIN_SERVICE, jsonParser, params);
        }
        else if(operationType.equals(CREATE_ACCOUNT)){
            loginDatabaseOperation(CREATE_ACCOUNT_SERVICE, jsonParser, params);
        }
        else if(operationType.equals(CREATE_COMPANY)){
            createProfile(CREATE_COMPANY_SERVICE, jsonParser, params);
        }
        else if(operationType.equals(ADD_INCOME)){
            addRevenueAndExpensesOperation(ADD_INCOME_SERVICE, jsonParser, params);
        }
        else if(operationType.equals(ADD_OUTGOING)){
            addRevenueAndExpensesOperation(ADD_OUTGOING_SERVICE, jsonParser, params);
        }
        else if(operationType.equals(CREATE_CLIENT)){
            createProfile(CREATE_CLIENT_SERVICE, jsonParser, params);
        }
        else if(operationType.equals(DELETE_CLIENT)){
            deleteClient(DELETE_CLIENT_SERVICE, jsonParser, params);
        }
        else if(operationType.equals(UPDATE_COMPANY_DATA)){
            updateCompanyData(UPDATE_COMPANY_DATA_SERVICE, jsonParser, params);
        }

        return null;
    }
    private void updateCompanyData(String type, JSONParser jsonParser, String... params){
        String id_company = params[1];
        String name = params[2];
        String email = params[3];
        String phoneNumber = params[4];
        String nip = params[5];
        String city = params[6];
        String postalCode = params[7];
        String street = params[8];
        String building_number = params[9];
        String door_number = params[10];

        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("id_company", id_company));
        list.add(new BasicNameValuePair("name", name));
        list.add(new BasicNameValuePair("email", email));
        list.add(new BasicNameValuePair("phoneNumber", phoneNumber));
        list.add(new BasicNameValuePair("nip", nip));
        list.add(new BasicNameValuePair("city", city));
        list.add(new BasicNameValuePair("postalCode", postalCode));
        list.add(new BasicNameValuePair("street", street));
        list.add(new BasicNameValuePair("building_number", building_number));
        list.add(new BasicNameValuePair("door_number", door_number));

        // sending modified data through http request
        // Notice that update product url accepts POST method
        JSONObject json = jsonParser.makeHttpRequest(type, "POST", list);

        // check json success tag
        try {
            int success = json.getInt(SUCCESS_TAG);

            if (success == 1) {
                ((Activity) context).finish();
            } else {
                // failed to update product
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void deleteClient(String type, JSONParser jsonParser, String... params){
        int success;
        try {
            String id_client = params[1];
            List<NameValuePair> list = new ArrayList<>();
            list.add(new BasicNameValuePair("id_client", id_client));

            JSONObject json = jsonParser.makeHttpRequest(type, "POST", list);

            success = json.getInt(SUCCESS_TAG);
            if (success == 1) {
                Intent intent = new Intent(context, ClientListActivity.class);
                context.startActivity(intent);
                ((Activity) context).finish();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Operation in progress...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
    }
    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        if(operationType.equals(UPDATE_COMPANY_DATA)){
            new Toast("Dane zaktualizowane", context);
        }
        pDialog.dismiss();
    }

    private void addRevenueAndExpensesOperation(String type, JSONParser jsonParser, String... params){
        id_company = sp.getString("id_company", "");

        String name = params[1];
        String ammount = params[2];
        String date = params[3];

        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("id_company", id_company));
        list.add(new BasicNameValuePair("name", name));
        list.add(new BasicNameValuePair("ammount", ammount));
        list.add(new BasicNameValuePair("date", date));
        JSONObject json = jsonParser.makeHttpRequest(type, "POST", list);

        try {
            int success = json.getInt(SUCCESS_TAG);

            if (success == 1) {
                Intent intent = new Intent(context, MainWindowActivity.class);
                context.startActivity(intent);

            } else {
                //blad
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void loginDatabaseOperation(String type, JSONParser jsonParser, String... params){

        String userName = params[1];
        String password = params[2];

        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("userName", userName));
        list.add(new BasicNameValuePair("password", password));
        JSONObject json = jsonParser.makeHttpRequest(type, "POST", list);

        try {
            int success = json.getInt(SUCCESS_TAG);

            if (success == 1) {
                int idGetSuccess;
                try {
                    // Building Parameters
                    List<NameValuePair> idList = new ArrayList<>();
                    idList.add(new BasicNameValuePair("userName", userName));

                    JSONObject jsonID = jsonParser.makeHttpRequest(GET_COMPANY_ID_SERVICE, "GET", idList);


                    // json success tag
                    idGetSuccess = json.getInt(SUCCESS_TAG);
                    if (idGetSuccess == 1) {
                        JSONArray idObj = jsonID.getJSONArray(ID_COMPANY_TAG); // JSON Array

                        JSONObject id = idObj.getJSONObject(0);


                        id_company =  id.getString(ID_COMPANY_TAG);

                    }else{
                        // product with pid not found
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                edit.putString("id_company", id_company);
                edit.commit();
                if(operationType.equals(LOGIN)) {
                    Intent intent = new Intent(context, MainWindowActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);
                }else{
                    Intent intent = new Intent(context, MyProfileActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);
                }

            } else {
                //blad
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void createProfile(String type, JSONParser jsonParser, String... params){
        String id_company = params[1];
        String name = params[2];
        String email = params[3];
        String phoneNumber = params[4];
        String nip = params[5];
        String city = params[6];
        String postalCode = params[7];
        String street = params[8];
        String building_number = params[9];
        String door_number = params[10];

        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("id_company", id_company));
        list.add(new BasicNameValuePair("name", name));
        list.add(new BasicNameValuePair("email", email));
        list.add(new BasicNameValuePair("phoneNumber", phoneNumber));
        list.add(new BasicNameValuePair("nip", nip));
        list.add(new BasicNameValuePair("city", city));
        list.add(new BasicNameValuePair("postalCode", postalCode));
        list.add(new BasicNameValuePair("street", street));
        list.add(new BasicNameValuePair("building_number", building_number));
        list.add(new BasicNameValuePair("door_number", door_number));

        JSONObject json = jsonParser.makeHttpRequest(type, "POST", list);

        try {
            int success = json.getInt(SUCCESS_TAG);

            if (success == 1) {
                if(operationType.equals(CREATE_CLIENT)) {
                    Intent intent = new Intent(context, ClientListActivity.class);
                    context.startActivity(intent);
                    ((Activity) context).finish();
                }
                if(operationType.equals(CREATE_COMPANY)){
                    Intent intent = new Intent(context, MainWindowActivity.class);
                    context.startActivity(intent);
                    ((Activity) context).finish();
                }

            } else {
                //blad
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
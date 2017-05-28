package appzaliczenie.financemanager;

import android.content.Context;
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
            createCompanyOperation(CREATE_COMPANY_SERVICE, jsonParser, params);
        }
        else if(operationType.equals(ADD_INCOME)){
            addRevenueAndExpensesOperation(ADD_INCOME_SERVICE, jsonParser, params);
        }
        else if(operationType.equals(ADD_OUTGOING)){
            addRevenueAndExpensesOperation(ADD_OUTGOING_SERVICE, jsonParser, params);
        }

        return null;
    }



    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {

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
                        // successfully received product details
                        JSONArray idObj = jsonID.getJSONArray(ID_COMPANY_TAG); // JSON Array

                        // get first product object from JSON Array
                        JSONObject id = idObj.getJSONObject(0);


                        id_company =  id.getString(ID_COMPANY_TAG);

                    }else{
                        // product with pid not found
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(context, MainWindowActivity.class);
                edit.putString("id_company", id_company);
                edit.commit();
                System.out.println("Zapisane id: " + sp.getString("id_company", ""));
                System.out.println("ID " + id_company);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);

            } else {
                //blad
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void createCompanyOperation(String type, JSONParser jsonParser, String... params){
        String id_company = params[1];
        String companyName = params[2];
        String companyEmail = params[3];
        String companyPhoneNumber = params[4];
        String companyNIP = params[5];
        String companyCity = params[6];
        String companyPostalCode = params[7];
        String companyStreet = params[8];
        String companyBuildingNumber = params[9];
        String comapnyDoorNumber = params[10];

        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("id_company", id_company));
        list.add(new BasicNameValuePair("companyName", companyName));
        list.add(new BasicNameValuePair("companyEmail", companyEmail));
        list.add(new BasicNameValuePair("companyPhoneNumber", companyPhoneNumber));
        list.add(new BasicNameValuePair("companyNIP", companyNIP));
        list.add(new BasicNameValuePair("companyCity", companyCity));
        list.add(new BasicNameValuePair("companyPostalCode", companyPostalCode));
        list.add(new BasicNameValuePair("street", companyStreet));
        list.add(new BasicNameValuePair("building_number", companyBuildingNumber));
        list.add(new BasicNameValuePair("door_number", comapnyDoorNumber));

        JSONObject json = jsonParser.makeHttpRequest(type, "POST", list);
        System.out.println(CREATE_COMPANY_SERVICE);

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
}
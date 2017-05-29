package appzaliczenie.financemanager;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.EditText;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MyProfileActivity extends AppCompatActivity implements DatabaseOperations {

    private EditText nameET, emailET, phoneNumberET, nipET, cityET, streetET, buildingNumberET, doorNumberET, postalCodeET;
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;
    private String id_company;
    private CheckData check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Moj Profil");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        nameET = (EditText) findViewById(R.id.companyNameET);
        emailET = (EditText) findViewById(R.id.emailET);
        phoneNumberET = (EditText) findViewById(R.id.phoneET);
        nipET = (EditText) findViewById(R.id.nipET);
        cityET = (EditText) findViewById(R.id.cityET);
        streetET = (EditText) findViewById(R.id.streetET);
        buildingNumberET = (EditText) findViewById(R.id.buildingNumET);
        doorNumberET = (EditText) findViewById(R.id.doorNumET);
        postalCodeET = (EditText) findViewById(R.id.postalCodeET);

        sp = getSharedPreferences("appzaliczenie.financemanager", Context.MODE_PRIVATE);
        edit = sp.edit();
        id_company = sp.getString("id_company", "");
        check = new CheckData();
    }

    public void onCreateCompany(View view) {
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
            if(check.checkEmail(companyEmail) && check.checkPhoneNumber(companyPhoneNumber)
                    && check.checkPostalCode(companyPostalCode) && check.checkNip(companyNIP)) {
                edit.putString(ACCOUNT_CREATED, ACCOUNT_CREATED);
                edit.apply();
                BackgroundWorker loginWorker = new BackgroundWorker(this);
                loginWorker.execute(CREATE_COMPANY, id_company, companyName, companyEmail, companyPhoneNumber, companyNIP,
                        companyCity, companyPostalCode, companyStreet, companyBuildingNumber, companyDoorNumber);
            }else
                new Toast("Bledne dane", this);
        }
    }

}


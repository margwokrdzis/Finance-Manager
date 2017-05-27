package appzaliczenie.financemanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.EditText;

public class MyProfileActivity extends AppCompatActivity implements DatabaseOperations{

    private EditText nameET, emailET, phoneNumberET, nipET, cityET, streetET, buildingNumberET, doorNumberET, postalCodeET;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
    }

    public void updateInfo(View view){
        String id_company = sp.getString("id_company", "");

        String companyName = nameET.getText().toString();
        String companyEmail = emailET.getText().toString();
        String companyPhoneNumber = phoneNumberET.getText().toString();
        String companyNIP = nipET.getText().toString();
        String companyCity = cityET.getText().toString();
        String companyPostalCode = postalCodeET.getText().toString();
        String companyBuildingNumber = buildingNumberET.getText().toString();
        String companyDoorNumber = doorNumberET.getText().toString();
        String companyStreet = streetET.getText().toString();
        String companyAdress = "";

        if(doorNumberET.equals(null)){
            companyAdress = "ul. " + companyStreet + " " + companyBuildingNumber;
        }
        else{
            companyAdress = "ul. " + companyStreet + " " + companyBuildingNumber + "/" + companyDoorNumber;
        }

        BackgroundWorker loginWorker = new BackgroundWorker(this);
        loginWorker.execute(CREATE_COMPANY, id_company, companyName, companyEmail, companyPhoneNumber, companyNIP, companyAdress, companyCity, companyPostalCode);
    }
}

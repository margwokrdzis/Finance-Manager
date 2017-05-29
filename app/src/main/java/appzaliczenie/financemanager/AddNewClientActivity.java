package appzaliczenie.financemanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddNewClientActivity extends AppCompatActivity implements DatabaseOperations{

    private EditText nameET, emailET, phoneNUmberET, nipET, cityET, postalCodeET, streetET, buildingNumberET, doorNumberET;
    private SharedPreferences sp;
    private String id_company;
    private CheckData check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_client);
        sp = getSharedPreferences("appzaliczenie.financemanager", Context.MODE_PRIVATE);
        id_company = sp.getString("id_company", "");
        check = new CheckData();

        nameET = (EditText) findViewById(R.id.newNameET);
        emailET = (EditText) findViewById(R.id.newEmailET);
        phoneNUmberET = (EditText) findViewById(R.id.newPhoneET);
        nipET = (EditText) findViewById(R.id.newNipET);
        cityET = (EditText) findViewById(R.id.newCityET);
        postalCodeET = (EditText) findViewById(R.id.newPostalCodeET);
        streetET = (EditText) findViewById(R.id.newStreetET);
        buildingNumberET = (EditText) findViewById(R.id.newBuildingNumET);
        doorNumberET = (EditText) findViewById(R.id.newPremisesET);
    }

    public void addNewClient(View view){
        String clientName = nameET.getText().toString();
        String clientEmail = emailET.getText().toString();
        String clientPhoneNumber = phoneNUmberET.getText().toString();
        String clientNip = nipET.getText().toString();
        String clientCity = cityET.getText().toString();
        String clientPostalCode = postalCodeET.getText().toString();
        String clientStreet = streetET.getText().toString();
        String clientBuildingNumber = buildingNumberET.getText().toString();
        String clientDoorNumber = doorNumberET.getText().toString();

        if(clientName.equals("") || clientEmail.equals("") || clientPhoneNumber.equals("") ||
                clientNip.equals("") || clientCity.equals("") || clientPostalCode.equals("") ||
                clientStreet.equals("") || clientBuildingNumber.equals("")){
            new Toast("Wprowadz wszystkie dane", this);
        }else {
            if(check.checkEmail(clientEmail) && check.checkPhoneNumber(clientPhoneNumber)
                    && check.checkPostalCode(clientPostalCode) && check.checkNip(clientNip)) {
                BackgroundWorker loginWorker = new BackgroundWorker(this);
                loginWorker.execute(CREATE_CLIENT, id_company, clientName, clientEmail, clientPhoneNumber, clientNip,
                        clientCity, clientPostalCode, clientStreet, clientBuildingNumber, clientDoorNumber);
            }else
                new Toast("Bledne dane", this);
        }
    }
}

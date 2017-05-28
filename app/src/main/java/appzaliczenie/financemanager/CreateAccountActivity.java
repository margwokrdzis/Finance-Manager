package appzaliczenie.financemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreateAccountActivity extends AppCompatActivity implements DatabaseOperations{

    private EditText userNameET, passwordET, repeatPasswordET;
    private Toast msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Tworzenie profilu");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        userNameET = (EditText) findViewById(R.id.setLoginET);
        passwordET = (EditText) findViewById(R.id.setPasswordET);
        repeatPasswordET = (EditText) findViewById(R.id.setPasswordRepeatET);
    }

    public void onAddNewAcc(View view){
        String userName = userNameET.getText().toString();
        String password = passwordET.getText().toString();
        String repeatPassword = repeatPasswordET.getText().toString();

        if(password.equals(repeatPassword)) {
            BackgroundWorker loginWorker = new BackgroundWorker(this);
            loginWorker.execute(CREATE_ACCOUNT, userName, password);
        }
        else{
            msg = new Toast("Bledne dane", this);
        }
    }
}

package appzaliczenie.financemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements DatabaseOperations {

    EditText userNameET, passwordET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userNameET = (EditText) findViewById(R.id.loginET);
        passwordET = (EditText) findViewById(R.id.passwordET);
    }

    public void CreateAccount(View view) {

        Intent intent = new Intent(this, ClientListActivity.class);
        startActivity(intent);
    }

    public void onLogin(View view){
        String userName = userNameET.getText().toString();
        String password = passwordET.getText().toString();

        BackgroundWorker loginWorker = new BackgroundWorker(this);
        loginWorker.execute(userName, password, LOGIN);
    }

}

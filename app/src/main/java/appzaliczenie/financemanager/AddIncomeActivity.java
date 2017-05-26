package appzaliczenie.financemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.EditText;

public class AddIncomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Okno dodawania wpłat");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);

        final EditText et = (EditText) findViewById(R.id.incomeAmountET);

        et.setFilters(new InputFilter[] {
                new DigitsKeyListener(Boolean.FALSE, Boolean.TRUE) {
                    int beforeDecimal = 6, afterDecimal = 2;

                    @Override
                    public CharSequence filter(CharSequence source, int start, int end,
                                               Spanned dest, int dstart, int dend) {
                        String temp = et.getText() + source.toString();

                        if (temp.equals(".")) {
                            return "0.";
                        }
                        else if (temp.toString().indexOf(".") == -1) {
                            // no decimal point placed yet
                            if (temp.length() > beforeDecimal) {
                                return "";
                            }
                        } else {
                            temp = temp.substring(temp.indexOf(".") + 1);
                            if (temp.length() > afterDecimal) {
                                return "";
                            }
                        }

                        return super.filter(source, start, end, dest, dstart, dend);
                    }
                }
        });


    }

    public void addIncomeItem(View view){

    }
}

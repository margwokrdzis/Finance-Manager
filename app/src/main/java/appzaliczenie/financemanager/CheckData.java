package appzaliczenie.financemanager;

import android.text.TextUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CheckData {


    public CheckData(){
    }

    public boolean checkEmail(String string){
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher m = p.matcher(string);
        boolean matchFound = m.matches();
        System.out.println("mail " + matchFound);
        if (matchFound) {
            return true;
        }else
            return false;
    }

    public boolean checkPhoneNumber(String string) {
        String regexStr = "^[0-9]{9}$";
        Pattern pattern = Pattern.compile(regexStr);
        Matcher m = pattern.matcher(string);
        boolean matchFound = m.matches();
        System.out.println("phone " + matchFound);
        if (matchFound) {
            return true;
        }else
            return false;

    }

    public boolean checkPostalCode(String string){
        String regex = "^[0-9]{2}(-[0-9]{3})?$";

        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(string);
        boolean matchFound = m.matches();
        System.out.println("postal code  " + matchFound);
        if (matchFound) {
            return true;
        }else
            return false;
    }

    public boolean checkNip(String string){
        String regexStr = "^[0-9]{10}$";

        Pattern pattern = Pattern.compile(regexStr);
        Matcher m = pattern.matcher(string);
        boolean matchFound = m.matches();
        System.out.println("postal code  " + matchFound);
        if (matchFound) {
            return true;
        }else
            return false;
    }
}

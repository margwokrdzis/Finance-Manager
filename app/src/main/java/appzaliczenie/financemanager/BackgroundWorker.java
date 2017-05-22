package appzaliczenie.financemanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class BackgroundWorker extends AsyncTask<String, String, String> implements DatabaseOperations{

    private Context context;
    private String operationType;
    private URL url;
    private AlertDialog alertDialog;

    public BackgroundWorker(Context context){
        this.context = context;
    }


    @Override
    protected String doInBackground(String... params) {
        operationType = params[2];

        if(operationType.equals(LOGIN)){
            try {
                String userName = params[0];
                String password = params[1];
                url = new URL(LOGIN_SERVICE);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream output= httpURLConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output, "UTF-8"));
                String postData = URLEncoder.encode("userName", "UTF-8")+"="+URLEncoder.encode(userName, "UTF-8")+"&"+
                        URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(password, "UTF-8");
                System.out.println(userName + " " + password);
                writer.write(postData);
                writer.flush();
                writer.close();
                output.close();

                InputStream input = httpURLConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input,"iso-8859-1"));
                String result = "";
                String line = "";
                while((line = reader.readLine())!=null){
                    result += line;
                }
                reader.close();
                input.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        System.out.println(operationType);
        System.out.println(result);
        if(operationType.equals(LOGIN)) {
            if(result.equals(SUCCESS)){
                Intent intent = new Intent(context, MainWindowActivity.class);
                context.startActivity(intent);
                Activity activity = (Activity) context;
                activity.finish();
            }
            else if(result.equals(FAILED)) {

            }
        }

    }
}

package mx.adrianbrito.shoex.ui.user.signinup;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import intelifin.frwk.core.services.core.ServicesManager;
import mx.adrianbrito.shoex.R;

public class SignIn extends AppCompatActivity {

    private EditText txtUser;
    private EditText txtPassword;
    private Toolbar toolbar;
    private Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        txtUser = (EditText) findViewById(R.id.txtUser);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        /*toolbar = (Toolbar) findViewById(R.id.navbar);
        setSupportActionBar(toolbar);*/
        btnSend = (Button) findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    /*java.util.TreeMap<String, String> _headers = new java.util.TreeMap<String, String>();
                    _headers.put("Authorization", "key=AIzaSyC4AcCjPMiFqCymqAMNT1QLlMuJZx2pWFM");

                    org.json.JSONObject _payload = new org.json.JSONObject();
                    _payload.put("content_available", true);
                    _payload.put("to", "fyZ5_y6VTI0:APA91bHGI52ORgkoI9hA5qzIrDV8aNmkloymFW1DNg9HVXF7vA1DUJC5xcFNYSahzQIS6Qy0exfXhBjzlwypIOq374icOUSq6PZJQpf_KAp1JjhEA7vaDS-QnrXZPRi7eunPtk_iAkwy");
                    _payload.put("priority", "high");
                    _payload.put("notification", new org.json.JSONObject().put("body", "hola").put("title", "titulo").put("badge", 1).put("sound", "default"));

                    org.json.JSONObject _json = ServicesManager.getInstance().getHttpService().post("https://gcm-http.googleapis.com/gcm/send", _headers, _payload);

                    System.out.println(_json.toString());*/

                   /* java.util.TreeMap<String, String> _headers = new java.util.TreeMap<String, String>();
                    _headers.put("albo-tx", "deeplink@1:send");
                    _headers.put("albo-identity", "back-off.9873892ABB23DFFBA9802717882CADD19901BC91A12C23D332BB99FF");
                    _headers.put("albo-role", "albo::role::common-user");

                    org.json.JSONObject _payload = new org.json.JSONObject();
                    _payload.put("email", "a_brito@outlook.com");
                    _payload.put("phone", "9818187588");
                    _payload.put("cmd", "reinstall");

                    org.json.JSONObject _json = ServicesManager.getInstance().getHttpService().post("https://mi.albo.mx/v1/iop/selfservice", _headers, _payload);*/

                    /*intelifin.frwk.core.services.impl.HttpService obj = new intelifin.frwk.core.services.impl.HttpService();

                    obj.post(new String[]{"a_brito@outlook.com", "9818187588"});*/

                    android.os.AsyncTask _async = new FetchService().execute(new String[]{"a_brito@outlook.com", "9818187588"});


                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
        });
    }


}

 class FetchService extends android.os.AsyncTask<String, Void, String> {
    /*private ProgressDialog dialog;*/

    @Override
    protected void onPreExecute(){
        /*dialog = ProgressDialog.show(MainActivity.this, "",
                "Recuperando sesión...", true);*/
    }

    @Override
    protected String doInBackground(String... params) {
        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpsURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String forecastJsonStr = null;

        try {
            // Construct the URL for the OpenWeatherMap query
            // Possible parameters are avaiable at OWM's forecast API page, at
            // http://openweathermap.org/API#forecast
            URL url = new URL("https://mi.albo.mx/v1/iop/selfservice");

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpsURLConnection) url.openConnection();

            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("albo-tx", "deeplink@1:send");
            urlConnection.setRequestProperty("albo-identity", "back-off.9873892ABB23DFFBA9802717882CADD19901BC91A12C23D332BB99FF");
            urlConnection.setRequestProperty("albo-role", "albo::role::common-user");

            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);

            JSONObject bodyJson = new JSONObject();
            JSONObject dataJson = new JSONObject();
            //     "data" : {email:email, phone:phone, cmd:"reinstall"}
            dataJson.put("email",params[0]);
            dataJson.put("phone",params[1]);
            dataJson.put("cmd","reinstall");
            bodyJson.put("data",dataJson);
            String body = bodyJson.toString();

            OutputStream os = urlConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(body);
            writer.flush();
            writer.close();
            os.close();

            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                /*Log.i("info","RETURNING NULL 1");*/
                // Stream was empty.  No point in parsing.
                return null;
            }
            forecastJsonStr = buffer.toString();

            System.out.println(forecastJsonStr);
            return forecastJsonStr;

        } catch (Exception e) {
            System.out.println("@Exception1: "+e.getMessage());
            e.printStackTrace();
            return null;
        } finally{
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    System.out.println("@Exception2: "+e.getMessage());
                    e.printStackTrace();
                    /*Log.e("PlaceholderFragment", "Error closing stream", e);*/
                }
            }
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        /*dialog.dismiss();
        Log.i("json", s+"");*/
        try{
            JSONObject JSONResponse = new JSONObject(s);

            /*if (JSONResponse.getJSONObject("data").getString("status").equals("OK")) {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Información");
                alertDialog.setMessage("Su sesión ha sido reestablecida con éxito");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                return;
            }*/

        }catch(Exception e){
           /* Log.e("PlaceholderFragment", "Error ", e);*/
        }
        /*AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Información");
        alertDialog.setMessage("Error al reestablecer la sesión");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();*/
    }


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}


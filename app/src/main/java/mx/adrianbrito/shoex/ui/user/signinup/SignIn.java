package mx.adrianbrito.shoex.ui.user.signinup;


import android.app.ProgressDialog;
import android.content.Intent;
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

    private View scrollView;
    private EditText txtUser;
    private EditText txtPassword;
    private Button btnSend;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        txtUser = (EditText) findViewById(R.id.txtUser);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnSend = (Button) findViewById(R.id.btnSend);
        scrollView = findViewById(R.id.scrollview_signin);
        /*this.initToolbar();*/

        btnSend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    //new FetchService().execute(new String[]{"a_brito@outlook.com", "9818187588"});

                    /*if (formIsValid())
                        new FetchService().execute(new String[]{txtUser.getText().toString(), txtPassword.getText().toString()});*/

                   Intent intent =
                            new Intent(mx.adrianbrito.shoex.ui.user.signinup.SignIn.this,
                                    mx.adrianbrito.shoex.ui.user.signinup.PhoneValidation.class);
                                    //mx.adrianbrito.shoex.ui.catalog.BankSelector.class);

                    startActivity(intent);

                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
        });
    }

    public boolean formIsValid() {
        if (txtUser.getText().toString().equals("")){
            //throw alert
            showAlert("Introduce tu usuario");
            return false;
        }

        if (txtPassword.getText().toString().equals("")){
            //throw alert
            showAlert("Introduce tu contraseña");
            return false;
        }

        return true;
    }

    public void showAlert(String _text){
        android.app.AlertDialog.Builder alertDialogBuilder =
                new android.app.AlertDialog.Builder(this);//new android.app.AlertDialog.Builder(context);

        // set title
        alertDialogBuilder.setTitle(_text);

        // set dialog message
        alertDialogBuilder
                //.setMessage(_text)
                .setCancelable(false)
                .setNeutralButton("Ok",new android.content.DialogInterface.OnClickListener() {
                    public void onClick(android.content.DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                /*.setPositiveButton("Yes",new android.content.DialogInterface.OnClickListener() {
                    public void onClick(android.content.DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        SignIn.this.finish();
                    }
                })
                .setNegativeButton("No",new android.content.DialogInterface.OnClickListener() {
                    public void onClick(android.content.DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                })*/;

        // create alert dialog
        android.app.AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    /*public void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Sign In");
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_toolbar_backarrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignIn.super.finish();
            }
        });
    }*/

    private class FetchService extends android.os.AsyncTask<String, Void, String> {
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute(){
            dialog = ProgressDialog.show(SignIn.this, "",
                    "Cargando...", true);
            System.out.println("@onPreExecute..");
        }

        @Override
        protected String doInBackground(String... params) {
            System.out.println("@doInBackground..");

            HttpsURLConnection conn = null;
            BufferedReader reader = null;

            String str = null;
            int _responseCode = -1;

            try {

                URL url = new URL("https://mi.albo.mx/v1/iop/selfservice");

                System.out.println("@doInBackground.. 1");

                conn = (HttpsURLConnection) url.openConnection();

                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("albo-tx", "deeplink@1:send");
                conn.setRequestProperty("albo-identity", "back-off.9873892ABB23DFFBA9802717882CADD19901BC91A12C23D332BB99FF");
                conn.setRequestProperty("albo-role", "albo::role::common-user");

                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                JSONObject _data = new JSONObject();
                JSONObject _payload = new JSONObject();

                _payload.put("email",params[0]);
                _payload.put("phone",params[1]);
                _payload.put("cmd","reinstall");
                _data.put("data", _payload);

                System.out.println("@doInBackground.. 2");

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(_data.toString());
                writer.flush();
                writer.close();
                os.close();

                System.out.println("@doInBackground.. 3");

                conn.connect();

                _responseCode = conn.getResponseCode();

                System.out.println("@doInBackground.. 4: responseCode["+_responseCode+"]");

                if (_responseCode >= 200 && _responseCode <= 399){
                    // Read the input stream into a String
                    InputStream inputStream = conn.getInputStream();
                    StringBuffer buffer = new StringBuffer();
                    if (inputStream == null) {
                        return null;
                    }
                    reader = new BufferedReader(new InputStreamReader(inputStream));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        buffer.append(line + "\n");
                    }

                    if (buffer.length() == 0) {
                        return null;
                    }
                    str = buffer.toString();

                    System.out.println(str);

                    return str;
                }

                throw new Exception("The webservice threw exception with HttpStatus["+_responseCode+"]");
            }
            catch (Exception e) {
                System.out.println("@Exception1: "+e.getMessage());
                e.printStackTrace();
                return null;
            }
            finally {
                if (conn != null) {
                    conn.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        System.out.println("@Exception2: "+e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        }

        @Override
        protected void onPostExecute(String s) {
            System.out.println("@onPostExecute..");
            super.onPostExecute(s);
        dialog.dismiss();
        /*Log.i("json", s+"");*/
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


}




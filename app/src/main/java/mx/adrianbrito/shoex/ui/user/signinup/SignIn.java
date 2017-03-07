package mx.adrianbrito.shoex.ui.user.signinup;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

                    intelifin.frwk.core.services.impl.HttpService obj = new intelifin.frwk.core.services.impl.HttpService();

                    obj.post();
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
        });
    }


}

package mx.adrianbrito.shoex.ui.user.signinup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import mx.adrianbrito.shoex.R;

public class SignUp extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText txtGname;
    private EditText txtFname;
    private EditText txtSFname;
    private View scrollView;
    private Button btnAccept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        this.initToolbar();
        txtGname = (EditText) findViewById(R.id.txtGname);
        txtFname = (EditText) findViewById(R.id.txtFname);
        txtSFname = (EditText) findViewById(R.id.txtSFname);

        scrollView = findViewById(R.id.scrollview_signup);

        btnAccept = (Button) findViewById(R.id.btnAccept);
    }

    public void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Sign Up");
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_toolbar_backarrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp.super.finish();
            }
        });
    }
}

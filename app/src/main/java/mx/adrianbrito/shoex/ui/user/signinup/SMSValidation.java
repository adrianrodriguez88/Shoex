package mx.adrianbrito.shoex.ui.user.signinup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import mx.adrianbrito.shoex.R;

public class SMSValidation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_smsvalidation);
        this.initToolbar();
    }

    private void initToolbar(){
        getSupportActionBar().setTitle("Registro 4 de 6");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}

package mx.adrianbrito.shoex.ui.user.signinup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import mx.adrianbrito.shoex.R;

public class PhoneValidation extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_phonevalidation);
        this.initToolbar();



    }

    private void initToolbar(){
        getSupportActionBar().setTitle("Registro 3 de 6");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        /*toolbar.setNavigationIcon(R.drawable.ic_toolbar_backarrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignIn.super.finish();
            }
        });*/
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

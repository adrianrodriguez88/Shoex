package mx.adrianbrito.shoex.ui.user.signinup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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

    //rightButton

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_next, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu_next:
                Intent intent =
                        new Intent(mx.adrianbrito.shoex.ui.user.signinup.PhoneValidation.this,
                                mx.adrianbrito.shoex.ui.user.signinup.SMSValidation.class);

                startActivity(intent);
                break;
        }
        return true;
    }

}

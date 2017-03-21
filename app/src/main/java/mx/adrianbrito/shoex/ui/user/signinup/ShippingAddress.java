package mx.adrianbrito.shoex.ui.user.signinup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import mx.adrianbrito.shoex.R;

public class ShippingAddress extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_shippingaddress);
        this.initToolbar();
    }

    private void initToolbar(){
        getSupportActionBar().setTitle("Registro 5 de 6");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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
                        new Intent(mx.adrianbrito.shoex.ui.user.signinup.ShippingAddress.this,
                                mx.adrianbrito.shoex.ui.user.signinup.PinCreate.class);

                startActivity(intent);
                break;
            default:
                super.finish();
                break;
        }
        return true;
    }

}

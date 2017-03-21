package mx.adrianbrito.shoex.ui.user.signinup;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import mx.adrianbrito.shoex.R;

public class PinCreate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_pincreate);
        getSupportActionBar().show();
        this.initToolbar();
    }

    private void initToolbar(){
        getSupportActionBar().setTitle("Registro 6 de 6");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final MenuItem menuItem = menu.add(Menu.NONE, 1000, Menu.NONE, "Okay");
        MenuItemCompat.setShowAsAction(menuItem, MenuItem.SHOW_AS_ACTION_IF_ROOM);

        return super.onCreateOptionsMenu(menu);
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_next, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu_next:
                System.out.println("Next clicked..");
                break;
            default:
                super.finish();
                break;
        }
        return true;
    }

}

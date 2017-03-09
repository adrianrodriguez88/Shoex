package mx.adrianbrito.shoex.ui.catalog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import mx.adrianbrito.shoex.R;

public class BankSelector extends AppCompatActivity {

    private TextView txtTitle;
    private View viewSeparator;
    private TextView txtRoundImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bankselector);

        txtTitle = (TextView) findViewById(R.id.txtTitle);
        viewSeparator = findViewById(R.id.viewSeparator);
        txtRoundImage = (TextView) findViewById(R.id.txtRoundImage);
    }
}

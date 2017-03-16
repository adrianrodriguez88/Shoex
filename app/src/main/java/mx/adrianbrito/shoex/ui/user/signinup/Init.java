package mx.adrianbrito.shoex.ui.user.signinup;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import mx.adrianbrito.shoex.R;

public class Init extends AppCompatActivity {

    private ImageView imgMain;
    private Button btnSignIn;
    private Button btnSignUp;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

        imgMain = (ImageView) findViewById(R.id.imgMain);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        /*toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        btnSignIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent =
                        new Intent(mx.adrianbrito.shoex.ui.user.signinup.Init.this,
                                //mx.adrianbrito.shoex.ui.user.signinup.SignIn.class);
                                mx.adrianbrito.shoex.ui.user.signinup.PhoneValidation.class);

                startActivity(intent);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent =
                        new Intent(mx.adrianbrito.shoex.ui.user.signinup.Init.this,
                                mx.adrianbrito.shoex.ui.user.signinup.SignUp.class);

                startActivity(intent);
            }
        });
    }
}

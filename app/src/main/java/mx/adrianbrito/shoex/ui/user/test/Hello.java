package mx.adrianbrito.shoex.ui.user.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import mx.adrianbrito.shoex.R;

public class Hello extends AppCompatActivity {

    private EditText txtUser;
    private Button btnAccept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        txtUser = (EditText) findViewById(R.id.txtUser);
        btnAccept = (Button) findViewById(R.id.btnAccept);

        btnAccept.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Creamos el Intent
                Intent intent =
                        new Intent(Hello.this, Hello2.class);

                //Creamos la información a pasar entre actividades
                Bundle b = new Bundle();
                b.putString("user", txtUser.getText().toString());

                //Añadimos la información al intent
                intent.putExtras(b);

                //Iniciamos la nueva actividad
                startActivity(intent);
            }
        });
    }


}

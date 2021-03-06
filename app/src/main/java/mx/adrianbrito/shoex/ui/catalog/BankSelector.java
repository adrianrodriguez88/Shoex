package mx.adrianbrito.shoex.ui.catalog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import mx.adrianbrito.shoex.R;
import mx.adrianbrito.shoex.adapters.BankAdapter;
import mx.adrianbrito.shoex.domain.Bank;

public class BankSelector extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Bank> listBanks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);

        listBanks = new ArrayList<>();

        for (int i=0; i<50; i++){
            listBanks.add(new Bank(i+"", "Descripcion "+i, "http://tusimagenesde.com/wp-content/uploads/2016/05/batman-logo-2.png"));
        }

        BankAdapter adapter = new BankAdapter(listBanks);
        recyclerView.setAdapter(adapter);
    }
}

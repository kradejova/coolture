package cz.radejova.kaja.coolture;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    private AppDatabase appDatabase;
    private EditText detail_nazev;
    private EditText detail_datum;
    private EditText detail_akce;
    private EditText detail_popis;
    private EditText detail_likeDislike;
    private ArrayList arrayList;
    private Adapter adapter;
    private ListView main_list;
    private FloatingActionButton detail_floating_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detail_nazev = findViewById(R.id.detail_nazev);
        detail_datum = findViewById(R.id.detail_datum);
        detail_akce = findViewById(R.id.detail_akce);
        detail_popis = findViewById(R.id.detail_popis);
        detail_likeDislike = findViewById(R.id.detail_likeDislike);
        //main_list = findViewById(R.id.main_list);


        appDatabase = Room.databaseBuilder(this, AppDatabase.class, "database").allowMainThreadQueries().build();

        adapter = new Adapter(this, appDatabase);

        //main_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        findViewById(R.id.detail_floating_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nazev = detail_nazev.getText().toString();
                String datum = detail_datum.getText().toString();
                String akce = detail_akce.getText().toString();
                String popis = detail_popis.getText().toString();
                String likeDislike = detail_likeDislike.getText().toString();

                Polozka polozka = new Polozka(nazev, datum, akce, popis, likeDislike);
                appDatabase.polozkaDao().insertAll(polozka);
                Log.d("Kaja", polozka.nazev + " " + polozka.datum + " " +polozka.akce + " " + polozka.popis + " " + polozka.likeDislike);
                adapter.notifyDataSetChanged();

                //Toast.makeText(this, "Úspěšně uloženo.", Toast.LENGTH_LONG).show();
                finish();
            }
        });


    }




}


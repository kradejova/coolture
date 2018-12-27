package cz.radejova.kaja.coolture;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditPolozkaActivity extends AppCompatActivity {
    private String nazev;
    private String popis;
    private String datum;
    private String likeDislike;
    private String akce;
    private EditText edit_detailPolozka_nazev;
    private EditText edit_detailPolozka_datum;
    private EditText edit_detailPolozka_akce;
    private EditText edit_detailPolozka_popis;
    private EditText edit_detailPolozka_likeDislike;
    private Polozka polozka;
    private Adapter adapter;
    private AppDatabase appDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_polozka);

        edit_detailPolozka_akce = findViewById(R.id.edit_detailPolozka_akce);
        edit_detailPolozka_nazev = findViewById(R.id.edit_detailPolozka_nazev);
        edit_detailPolozka_datum = findViewById(R.id.edit_detailPolozka_datum);
        edit_detailPolozka_likeDislike = findViewById(R.id.edit_detailPolozka_likeDislike);
        edit_detailPolozka_popis = findViewById(R.id.edit_detailPolozka_popis);

        int uid = getIntent().getIntExtra("uid", 0);

        AppDatabase appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database").allowMainThreadQueries().build();

        Polozka polozka = appDatabase.polozkaDao().getByUid(uid);

        //zobrazeni textu polozky na strance
        edit_detailPolozka_nazev.setText(polozka.nazev);
        edit_detailPolozka_datum.setText(polozka.datum);
        edit_detailPolozka_akce.setText(polozka.akce);
        edit_detailPolozka_popis.setText(polozka.popis);
        edit_detailPolozka_likeDislike.setText(polozka.likeDislike);

        findViewById(R.id.floating_button_detail_polozka).setOnClickListener(new View.OnClickListener() { //floating button, po kliknuti se otevre formular na pridani nove akce do DB
            @Override
            public void onClick(View v) {
                AppDatabase appDatabase = Room.databaseBuilder(EditPolozkaActivity.this, AppDatabase.class, "database").allowMainThreadQueries().build();

                int uidEdit = getIntent().getIntExtra("uid", 0);

                Polozka polozka = appDatabase.polozkaDao().getByUid(uidEdit);

                appDatabase.polozkaDao().insertAll(polozka);
                adapter = new Adapter(getApplicationContext(), appDatabase);
                adapter.notifyDataSetChanged();

                Intent intent = new Intent(getApplicationContext(), EditPolozkaActivity.class);
                intent = intent.putExtra("uidEdit", uidEdit);
                startActivity(intent);
            }
        });


    }

}

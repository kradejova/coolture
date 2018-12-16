package cz.radejova.kaja.coolture;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailPolozka extends AppCompatActivity {
    private TextView detailPolozka_nazev;
    private TextView detailPolozka_datum;
    private TextView detailPolozka_akce;
    private TextView detailPolozka_popis;
    private TextView detailPolozka_likeDislike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_polozka);

        detailPolozka_nazev = findViewById(R.id.detailPolozka_nazev);
        detailPolozka_datum = findViewById(R.id.detailPolozka_datum);
        detailPolozka_akce = findViewById(R.id.detailPolozka_akce);
        detailPolozka_popis = findViewById(R.id.detailPolozka_popis);
        detailPolozka_likeDislike = findViewById(R.id.detailPolozka_likeDislike);

        AppDatabase appDatabase = Room.databaseBuilder(this, AppDatabase.class, "database").allowMainThreadQueries().build();

        final int uid = getIntent().getIntExtra("uid", 0);

        Polozka polozka = appDatabase.polozkaDao().getByUid(uid);

        detailPolozka_nazev.setText(polozka.nazev);
        detailPolozka_datum.setText(polozka.datum);
        detailPolozka_akce.setText(polozka.akce);
        detailPolozka_popis.setText(polozka.popis);
        detailPolozka_likeDislike.setText(polozka.likeDislike);

//        findViewById(R.id.editDetail_floating_button).setOnClickListener(new View.OnClickListener() { //po kliknuti muze uzivatel editovat text v polozkach
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), EditDetailActivity.class);
//                intent = intent.putExtra("uid", (int) uid);
//                intent.putExtra("detailPolozka_nazev", detailPolozka_nazev.toString());
//                intent.putExtra("detailPolozka_datum", detailPolozka_datum.toString());
//                intent.putExtra("detailPolozka_akce", detailPolozka_akce.toString());
//                intent.putExtra("detailPolozka_popis", detailPolozka_popis.toString());
//                intent.putExtra("detailPolozka_likeDislike", detailPolozka_likeDislike.toString());
//                startActivity(intent);
//
//            }
//        });

    }
}

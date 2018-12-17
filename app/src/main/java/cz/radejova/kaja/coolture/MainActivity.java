package cz.radejova.kaja.coolture;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.afollestad.materialdialogs.MaterialDialog;

public class MainActivity extends AppCompatActivity {
    private Polozka polozka;
    private AppDatabase appDatabase;
    private ListView main_list;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_list = findViewById(R.id.main_list);

        findViewById(R.id.floating_button).setOnClickListener(new View.OnClickListener() { //floating button, po kliknuti se otevre formular na pridani nove akce do DB
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });


        appDatabase = Room.databaseBuilder(this, AppDatabase.class, "database").allowMainThreadQueries().build();

        adapter = new Adapter(this, appDatabase);

        main_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        main_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) { //zobrazeni detailu akce
                Intent intent = new Intent(MainActivity.this, DetailPolozka.class);
                intent.putExtra("uid", (int) id);
                startActivity(intent);
            }
        });

//main_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//    @Override
//    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//
//        MaterialDialog dialog = new MaterialDialog.Builder(getApplicationContext())
//                .title(getString(R.string.main_dialog_title))
//                .content(getString(R.string.main_dialog_content))
//                .positiveText("OK")
//                .negativeText("Cancel")
//                .show();
//        return false;
//    }
//});
    }

    @Override
    public void onResume() {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here
        obnoveniHlavniObrazovky();
    }


    public void vlozitNovyZaznam(View view) {
        Intent intent = new Intent(this, DetailActivity.class);
        startActivity(intent);

    }

    public void obnoveniHlavniObrazovky() {

        appDatabase = Room.databaseBuilder(this, AppDatabase.class, "database").allowMainThreadQueries().build();
        adapter = new Adapter(this, appDatabase);
        main_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        startActivity(getIntent());
    }

}

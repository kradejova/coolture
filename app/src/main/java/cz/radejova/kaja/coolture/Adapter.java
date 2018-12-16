package cz.radejova.kaja.coolture;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapter extends BaseAdapter {
    private Context context;
    private List<Polozka> polozkas;
    private AppDatabase app;

    public Adapter(Context context, AppDatabase app) {
        this.context = context;
        this.app = app;

        polozkas = app.polozkaDao().getAll();
    }

    @Override
    public void notifyDataSetChanged() {
        polozkas = app.polozkaDao().getAll();
        super.notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return polozkas.size();
    }

    @Override
    public Polozka getItem(int position) {
        return polozkas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).uid;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_polozka_main, parent, false); //natahni mi tento design, inflate vrací view

        TextView nazev = view.findViewById(R.id.main_nazev);
        TextView datum = view.findViewById(R.id.main_datum);
        //TextView akce = view.findViewById(R.id.main_akce);
        //TextView popis = view.findViewById(R.id.main_popis);
        //extView likeDislike = view.findViewById(R.id.main_likeDislike);

        Polozka polozka = getItem(position); //lze pouzit metodu getItem(position) misto metody data.get(position)

        nazev.setText(polozka.nazev);
        datum.setText(polozka.datum);
        //akce.setText(polozka.akce);
        //popis.setText(polozka.popis);
        //likeDislike.setText(polozka.likeDislike);

        return view;
    }
}

package cz.radejova.kaja.coolture;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "polozka")

public class Polozka {

    public Polozka() {}

    public
    Polozka(String nazev, String datum, String akce, String popis, String likeDislike){
        this.nazev = nazev;
        this.datum = datum;
        this.akce = akce;
        this.popis = popis;
        this.likeDislike = likeDislike;
    }

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public Integer uid;

    public String nazev;

    public String datum;

    public String akce;

    public String popis;

    public String likeDislike;
}

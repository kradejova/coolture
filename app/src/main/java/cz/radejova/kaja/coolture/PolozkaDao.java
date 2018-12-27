package cz.radejova.kaja.coolture;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface PolozkaDao {
    @Insert
    void insertAll(Polozka ... polozkas); //metoda pro vkladani do DB

    @Delete
    void delete(Polozka polozka);

    @Query("SELECT * FROM polozka ORDER BY uid DESC")
    List<Polozka> getAll();

    @Query("SELECT * FROM polozka WHERE uid = :uid")
    Polozka getByUid(int uid);

    @Query("SELECT * FROM polozka WHERE nazev = :nazev AND akce = :akce AND popis = :popis AND likeDislike = :likeDislike")
    Polozka getByNames(String nazev, String akce, String popis, String likeDislike);

    @Query("UPDATE polozka SET nazev = :nazev, akce = :akce, popis = :popis, datum = :datum, likeDislike = :likeDislike WHERE uid = :uid    ")
    Polozka update(String nazev, String datum, String akce, String popis, String likeDislike);
}

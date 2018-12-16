package cz.radejova.kaja.coolture;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(version = 1, entities = {Polozka.class})
public abstract class AppDatabase extends RoomDatabase {
public abstract  PolozkaDao polozkaDao();
}

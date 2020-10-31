package com.example.dentisttest.appDatabase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.dentisttest.person.Assistant;
import com.example.dentisttest.person.Dentist;
import com.example.dentisttest.person.Patient;
import com.example.dentisttest.person.personDao.AssistantDao;
import com.example.dentisttest.person.personDao.DentistDao;
import com.example.dentisttest.person.personDao.PatientDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(version = 1, exportSchema = false, entities = {Dentist.class, Patient.class, Assistant.class})
public abstract class AppDatabase extends RoomDatabase {

    public static volatile AppDatabase INSTANCE;

    public abstract PatientDao patientDao();

    public abstract DentistDao dentistDao();

    public abstract AssistantDao assistantDao();

    public static final ExecutorService dbExecutor = Executors.newFixedThreadPool(2);

    public static synchronized AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "app_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(populateCallback)
                    .build();
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback populateCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            dbExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    DentistDao dentistDao = INSTANCE.dentistDao();

                    dentistDao.insert(new Dentist("Aaaaaa", "Miskovic", "Belgrade"));
                    dentistDao.insert(new Dentist("Djoka", "Djokic", "Novi Sad"));
                    dentistDao.insert(new Dentist("Mika", "Djokic", "New York"));

                    PatientDao patientDao = INSTANCE.patientDao();
                    patientDao.insert(new Patient("Milois", "Jovic", "Nis", 2));
                    patientDao.insert(new Patient("Milois", "Jovic", "Nis", 1));
                    patientDao.insert(new Patient("Milois", "Jovic", "Nis", 2));
                    patientDao.insert(new Patient("Milois", "Jovic", "Nis", 1));

                    AssistantDao assistantDao = INSTANCE.assistantDao();
                    assistantDao.insert(new Assistant("Blah", "Blah", "Nis", 2));
                    assistantDao.insert(new Assistant("Blah", "Blah", "Nis", 2));
                }
            });

        }
    };


}

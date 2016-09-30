package android.mi.ur.de.urfit.Hilfsklassen;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Database {

    private static final String LOG_TAGS = dbOpenHelper.class.getSimpleName();
    private static final String LOG_TAG = Database.class.getSimpleName();


    private static final String DATABASE_NAME = "urfitDatenbank.db";
    private static final int DATABASE_VERSION = 1;

    // URFitItem

    private static final String DATABASE_TABLE = "activity_list";

    private static final String KEY_ID = "_id";
    private static final String KEY_DATE = "date";
    private static final String KEY_STEPS = "steps";
    private static final String KEY_CALORIES = "calories";

    private static final int COLUMN_DATE_INDEX = 1;
    private static final int COLUMN_STEPS_INDEX = 2;
    private static final int COLUMN_CALORIES_INDEX = 3;

    // User

    private static final String USER_TABLE = "users_list";

    private static final String USER_KEY_ID = "id";
    private static final String USER_KEY_NAME = "name";
    private static final String USER_KEY_GENDER = "gender";
    private static final String USER_KEY_STEP_LENGTH = "step_length";
    private static final String USER_KEY_LEVEL = "level";

    private static final int USER_COLUMN_NAME_INDEX = 1;
    private static final int USER_COLUMN_GENDER_INDEX = 2;
    private static final int USER_COLUMN_STEP_LENGTH_INDEX = 3;
    private static final int USER_COLUMN_LEVEL_INDEX = 4;

    // User Aim

    private static final String USER_AIM_TABLE = "user_aim_list";

    private static final String USER_AIM_KEY_ID = "id";
    private static final String USER_AIM_KEY_TITEL = "titel";
    private static final String USER_AIM_KEY_STEPS = "steps";
    private static final String USER_AIM_KEY_REACHED = "reached";

    private static final int USER_AIM_COLUMN_TITEL_INDEX = 1;
    private static final int USER_AIM_COLUMN_STEPS_INDEX = 2;
    private static final int USER_AIM_COLUMN_REACHED_INDEX = 3;


    private dbOpenHelper dbHelper;

    private SQLiteDatabase db;

    public Database(Context context) {
        dbHelper = new dbOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open() throws SQLException {
        try {
            Log.d(LOG_TAG, "Eine Referenz auf die Datenbank wird jetzt angefragt.");
            db = dbHelper.getWritableDatabase();
        } catch (SQLException e) {
            Log.d(LOG_TAG, "Datenbank-Referenz erhalten. Pfad zur Datenbank: " + db.getPath());
            db = dbHelper.getReadableDatabase();
        }
    }

    public void close() {

        db.close();
        Log.d(LOG_TAG, "Datenbank mit Hilfe des DbHelpers geschlossen.");
    }

    public long insertUserAim(UserAim newUserAim) {
        ContentValues newUserAimValues = new ContentValues();

        newUserAimValues.put(USER_AIM_KEY_TITEL, newUserAim.getTitel());
        newUserAimValues.put(USER_AIM_KEY_STEPS, newUserAim.getSteps());
        newUserAimValues.put(USER_AIM_KEY_REACHED, newUserAim.getReached());

        return db.insert(USER_AIM_TABLE, null, newUserAimValues);
    }

    public long insertUser(User newUser) {
        ContentValues newUserValues = new ContentValues();

        newUserValues.put(USER_KEY_NAME, newUser.getName());
        newUserValues.put(USER_KEY_GENDER, newUser.getGender());
        newUserValues.put(USER_KEY_STEP_LENGTH, newUser.getStepLength());
        newUserValues.put(USER_KEY_LEVEL, newUser.getLevel());

        return db.insert(USER_TABLE, null, newUserValues);
    }

    public long insertURFitItem(URFitItem item) {
        ContentValues newURFitValues = new ContentValues();

        newURFitValues.put(KEY_DATE, item.getFormattedDate());
        newURFitValues.put(KEY_STEPS, item.getSteps());
        newURFitValues.put(KEY_CALORIES, item.getCalories());

        return db.insert(DATABASE_TABLE, null, newURFitValues);
    }

    public void removeUser(User user) {
        String whereClause = USER_KEY_NAME + " = '" + user.getName() + "' AND "
                + USER_KEY_GENDER + " = '" + user.getGender() + "' AND "
                + USER_KEY_STEP_LENGTH + " = '" + user.getStepLength() + "' AND "
                + USER_KEY_LEVEL + " = '" + user.getLevel() + "'";

        db.delete(USER_TABLE, whereClause, null);
    }

    public void removeUserAim(UserAim aim) {
        String whereClause = USER_AIM_KEY_TITEL + " = '" + aim.getTitel() + "' AND "
                + USER_AIM_KEY_STEPS + " = '" + aim.getSteps() + "' AND "
                + "' AND " + USER_AIM_KEY_REACHED + " = '" + aim.getReached() + "'";

        db.delete(USER_AIM_TABLE, whereClause, null);
    }

    public void removeURFitItem(URFitItem item) {
        String whereClause = KEY_DATE + " = '" + item.getFormattedDate() + "' AND "
                + KEY_STEPS + " = '" + item.getSteps() + "' AND "
                + KEY_CALORIES + " = '" + item.getCalories() + "'";

        db.delete(DATABASE_TABLE, whereClause, null);
    }

    public ArrayList<User> getAllUser() {
        ArrayList<User> users = new ArrayList<>();
        Cursor cursor = db.query(USER_TABLE, new String[]{USER_KEY_ID, USER_KEY_NAME, USER_KEY_GENDER, USER_KEY_STEP_LENGTH, USER_KEY_LEVEL}, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(USER_COLUMN_NAME_INDEX);
                String gender = cursor.getString(USER_COLUMN_GENDER_INDEX);
                String stepLength = cursor.getString(USER_COLUMN_STEP_LENGTH_INDEX);
                String level = cursor.getString(USER_COLUMN_LEVEL_INDEX);
                Boolean _gender;

                if (gender.equals("male")) {
                    _gender = true;
                } else {
                    _gender = false;
                }

                users.add(new User(name, _gender, stepLength, level));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return users;
    }


    public ArrayList<UserAim> getAllUserAims() {
        ArrayList<UserAim> aims = new ArrayList<UserAim>();
        Cursor cursor = db.query(USER_AIM_TABLE, new String[]{USER_AIM_KEY_ID, USER_AIM_KEY_TITEL, USER_AIM_KEY_STEPS, USER_AIM_KEY_REACHED}, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String titel = cursor.getString(USER_AIM_COLUMN_TITEL_INDEX);
                String steps = cursor.getString(USER_AIM_COLUMN_STEPS_INDEX);
                boolean reached = false;
                if (cursor.getInt(USER_AIM_COLUMN_REACHED_INDEX) == 1) {
                    reached = true;
                }


                aims.add(new UserAim(titel, steps, reached));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return aims;
    }

    public ArrayList<URFitItem> getAllURFitItems() {
        ArrayList<URFitItem> items = new ArrayList<URFitItem>();
        Cursor cursor = db.query(DATABASE_TABLE, new String[]{KEY_ID, KEY_DATE, KEY_STEPS, KEY_CALORIES}, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String date = cursor.getString(COLUMN_DATE_INDEX);
                String steps = cursor.getString(COLUMN_STEPS_INDEX);
                String calories = cursor.getString(COLUMN_CALORIES_INDEX);

                Date formatedDate = null;
                try {
                    formatedDate = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN).parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Calendar cal = Calendar.getInstance(Locale.GERMAN);
                cal.setTime(formatedDate);

                items.add(new URFitItem(steps, calories, cal.get(Calendar.DAY_OF_MONTH),
                        cal.get(Calendar.MONTH), cal.get(Calendar.YEAR)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return items;
    }


    private class dbOpenHelper extends SQLiteOpenHelper {
        private static final String DATABASE_CREATE = "CREATE TABLE "
                + DATABASE_TABLE + " (" + KEY_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_DATE
                + " TEXT, " + KEY_STEPS + " TEXT, " + KEY_CALORIES + " TEXT);";

        private static final String USER_DATABASE_CREATE = "CREATE TABLE "
                + USER_TABLE + " (" + USER_KEY_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USER_KEY_NAME
                + " TEXT, " + USER_KEY_GENDER + " TEXT, " + USER_KEY_STEP_LENGTH + " TEXT, "
                + USER_KEY_LEVEL + " TEXT);";

        private static final String USER_AIM_DATABASE_CREATE = "CREATE TABLE "
                + USER_AIM_TABLE + " (" + USER_AIM_KEY_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USER_AIM_KEY_TITEL
                + " TEXT, " + USER_AIM_KEY_STEPS + " TEXT, "
                + USER_AIM_KEY_REACHED + " INTEGER);";

        public dbOpenHelper(Context c, String dbname, SQLiteDatabase.CursorFactory factory, int version) {
            super(c, dbname, factory, version);
            Log.d(LOG_TAGS, "DbHelper hat die Datenbank: " + getDatabaseName() + " erzeugt.");
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                Log.d(LOG_TAGS, "Die Tabelle wird mit SQL-Befehl: " + DATABASE_CREATE + " angelegt.");
                db.execSQL(DATABASE_CREATE);
                Log.d(LOG_TAGS, "Die Tabelle wird mit SQL-Befehl: " + USER_DATABASE_CREATE + " angelegt.");
                db.execSQL(USER_DATABASE_CREATE);
                Log.d(LOG_TAGS, "Die Tabelle wird mit SQL-Befehl: " + USER_AIM_DATABASE_CREATE + " angelegt.");
                db.execSQL(USER_AIM_DATABASE_CREATE);

            } catch (Exception ex) {
                Log.e(LOG_TAGS, "Fehler beim Anlegen der Tabelle: " + ex.getMessage());
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

}


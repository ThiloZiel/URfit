package android.mi.ur.de.urfit;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Database {
    private static final String DATABASE_NAME = "urfit.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_TABLE = "Items";

    public static final String KEY_ID = "_id";
    public static final String KEY_DATE ="date";
    public static final String KEY_STEPS = "Steps";
    public static final String KEY_CALORIES = "Calories";

    public static final int COLUMN_DATE_INDEX = 1;
    public static final int COLUMN_STEPS_INDEX = 2;
    public static final int COLUMN_CALORIES_INDEX = 3;

    private dbOpenHelper dbHelper;

    private SQLiteDatabase db;

    public Database(Context context){
        dbHelper = new dbOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public void open() throws SQLException{
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLException e){
            db = dbHelper.getReadableDatabase();
        }
    }

    public void close() {
        db.close();
    }

    public long insertItem(URFitItem item){
        ContentValues newToDoValues = new ContentValues();

        newToDoValues.put(KEY_DATE, item.getFormattedDate());
        newToDoValues.put(KEY_STEPS, item.getSteps());
        newToDoValues.put(KEY_CALORIES, item.getCalories());

        return db.insert(DATABASE_TABLE, null, newToDoValues);
    }

    public void removeItem(URFitItem item) {
        String whereClause = KEY_DATE + " = '" + item.getFormattedDate() + "' AND "
                + KEY_STEPS + " = '" + item.getSteps() + "' AND "
                + KEY_CALORIES + " = '" + item.getCalories() + "'";

        db.delete(DATABASE_TABLE, whereClause, null);
    }

    public ArrayList<URFitItem> getAllURFitItems() {
        ArrayList<URFitItem> items = new ArrayList<URFitItem>();
        Cursor cursor = db.query(DATABASE_TABLE, new String[] {KEY_ID, KEY_DATE, KEY_STEPS, KEY_CALORIES}, null, null, null, null, null);
        if(cursor.moveToFirst()){
            do{
                String date = cursor.getString(COLUMN_DATE_INDEX);
                String steps = cursor.getString(COLUMN_STEPS_INDEX);
                String calories = cursor.getString(COLUMN_CALORIES_INDEX);

                Date formatedDate = null;
                try {
                    formatedDate = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN).parse(date);
                } catch (ParseException e){
                    e.printStackTrace();
                }

                Calendar cal = Calendar.getInstance(Locale.GERMAN);
                cal.setTime(formatedDate);

                items.add(new URFitItem(steps, calories, cal.get(Calendar.DAY_OF_MONTH),
                        cal.get(Calendar.MONTH), cal.get(Calendar.YEAR)));
            } while (cursor.moveToNext());
        }
        return items;
    }



    private class dbOpenHelper extends SQLiteOpenHelper {
        private static final String DATABASE_CREATE = "create table"
                + DATABASE_TABLE + " (" + KEY_ID
                + " integer primary key autoincrement, " + KEY_DATE
                + "text, " + KEY_STEPS + "text, " + KEY_CALORIES + "text);";

        public dbOpenHelper(Context c, String dbname, SQLiteDatabase.CursorFactory factory, int version){
            super(c, dbname, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

}


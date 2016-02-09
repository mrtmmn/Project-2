package com.example.maratmamin.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by maratmamin on 2/4/16.
 */
public class LocationSQLiteOpenHelper extends SQLiteOpenHelper {
//    private static final String TAG = LocationSQLiteOpenHelper.class.getCanonicalName();

    private static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "LOCATION_DB";
    public static final String LOCATION_LIST_TABLE_NAME = "LOCATION_LIST";

    public static final String COL_ID="_id";
    public static final String COL_LOCATION_NAME="LOCATION_NAME";
    public static final String COL_LOCATION_ADDRESS="LOCATION_ADDRESS";
    public static final String COL_LOCATION_NEIGHBORHOOD="LOCATION_NEIGHBORHOOD";
    public static final String COL_FAVORITES="LOCATION_FAVORITES";
    public static final String COL_IMAGES="LOCATION_IMAGES";


    public static final String [] LOCATION_COLUMNS = {COL_ID, COL_LOCATION_NAME, COL_LOCATION_ADDRESS, COL_LOCATION_NEIGHBORHOOD, COL_FAVORITES, COL_IMAGES };

    private static final String CREATE_LOCATION_LIST_TABLE = "CREATE TABLE " + LOCATION_LIST_TABLE_NAME +
            " ( " +
            COL_ID + " INTEGER PRIMARY KEY, " + COL_LOCATION_NAME + " TEXT, " +
            COL_LOCATION_ADDRESS + " TEXT, "
            + COL_LOCATION_NEIGHBORHOOD + " TEXT, "
    + COL_FAVORITES + " INTEGER " +
    COL_IMAGES + " BLOB )";

    private static LocationSQLiteOpenHelper instance;

    public static LocationSQLiteOpenHelper getInstance(Context context) {
        if(instance == null) {
            instance = new LocationSQLiteOpenHelper(context);
        }
        return instance;
    }

    private LocationSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
     db.execSQL(CREATE_LOCATION_LIST_TABLE);
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + LOCATION_LIST_TABLE_NAME);
        this.onCreate(db);
    }

    public long addItem(String name, String address, String environment){
        ContentValues values = new ContentValues();
        values.put(COL_LOCATION_NAME, name);
        values.put(COL_LOCATION_ADDRESS, address);
        values.put(COL_LOCATION_NEIGHBORHOOD, environment);

        //pass the database as a parameter instead of "getting" a new one

        SQLiteDatabase db = this.getWritableDatabase();
        long returnId = db.insert(LOCATION_LIST_TABLE_NAME, null, values);
        db.close();
        return returnId;
    }

    public boolean checkFavorites()

    public Cursor getLocationList () {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(LOCATION_LIST_TABLE_NAME,
                LOCATION_COLUMNS,
                null,
                null,
                null,
                null,
                null,
                null);

        return cursor;
    }

    public int deleteItem(int id){
        SQLiteDatabase db = getWritableDatabase();
        int deleteNum = db.delete(LOCATION_LIST_TABLE_NAME,
                COL_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
        return deleteNum;
    }

    public Cursor searchLocationList (String query) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(LOCATION_LIST_TABLE_NAME,
                LOCATION_COLUMNS,
                COL_LOCATION_NAME + "LIKE ? OR" + COL_LOCATION_ADDRESS + "LIKE ? OR" + COL_LOCATION_NEIGHBORHOOD + "LIKE ? OR",
                new String []{"%" + query + "%"},
                null,
                null,
                null,
                null);

        return cursor;
    }

    public String[] getDescriptionById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(LOCATION_LIST_TABLE_NAME,
                new String[] {COL_LOCATION_NAME, COL_LOCATION_ADDRESS, COL_LOCATION_NEIGHBORHOOD},
                COL_ID+" = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);

        cursor.moveToFirst();

        String [] detailsArray = new String[]{
                cursor.getString(cursor.getColumnIndex(COL_LOCATION_NAME)),
                cursor.getString(cursor.getColumnIndex(COL_LOCATION_ADDRESS)),
                cursor.getString(cursor.getColumnIndex(COL_LOCATION_NEIGHBORHOOD)),
        };

                return detailsArray;

    }
}

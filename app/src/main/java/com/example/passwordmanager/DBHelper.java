package com.example.passwordmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    static final String TABLE_NAME = "PasswordTable";
    static final String KEY_ROWID = "_id";
    static final String KEY_USER = "Username";
    static final String KEY_WEB = "Website";
    static final String KEY_PASS = "Password";

    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "PasswordDatabase";

    private static SQLiteDatabase DB;

    private String TAG = "DBHelper";

    private static String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ("+KEY_ROWID+" integer PRIMARY KEY,"+KEY_WEB+", "+KEY_USER+", "+KEY_PASS+" );";

    DBHelper (Context context)
    {
        super (context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Log.d(TAG, "Table Created Successfully");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    void insertValues(String website, String username, String password)
    {
        ContentValues values = new ContentValues();

        values.put(KEY_WEB, website);
        values.put(KEY_USER, username);
        values.put(KEY_PASS, password);

        DB = this.getWritableDatabase();

        DB.insert(TABLE_NAME, null, values);

        Log.d(TAG, "Values Inserted");
    }

    public void deleteWorkout(int deleteID)
    {
        String rowID = Integer.toString(deleteID);

        DB = this.getWritableDatabase();

        DB.delete(TABLE_NAME, KEY_ROWID+" = "+rowID, null);

        DB.execSQL("UPDATE " + TABLE_NAME + " set " + KEY_ROWID + "= (" + KEY_ROWID + "-1) where "+ KEY_ROWID+" > "+deleteID);

        Log.d(TAG, "Delete Successful.");
    }

    public ArrayList<PasswordModel> readPasswords()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        ArrayList<PasswordModel> passwordModels = new ArrayList<>();

        if (cursor.moveToFirst()){
            do {
                passwordModels.add (new PasswordModel(cursor.getString(1), cursor.getString(2), cursor.getString(3)));
            }while(cursor.moveToNext());
        }

        cursor.close();
        return passwordModels;
    }


}

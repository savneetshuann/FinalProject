package com.example.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import androidx.annotation.Nullable;

import java.io.FileInputStream;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "User";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PHONE = "phone";
    private static final String IMAGE = "image";


    public DatabaseHelper(@Nullable Context context) {
        // cursor factory is when you are using your own custom cursor
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER NOT NULL CONSTRAINT user_pk PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " varchar(50) NOT NULL, " +
                COLUMN_EMAIL + " varchar(200) NOT NULL, " +
                COLUMN_PHONE + " varchar(12) NOT NULL, "+
                IMAGE + " blob );";
        db.execSQL(sql);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*
         * we are just dropping the table and recreate it
         * */

        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        db.execSQL(sql);
        onCreate(db);
    }

    boolean addEmployee(String name,String phone,String email,Bitmap image) {

        // in order to insert items into database, we need a writable database
        // this method returns a SQLite database instance
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();



        // we need to define a contentValues instance
        ContentValues cv = new ContentValues();

        // the first argument of the put method is the column name and the second the value
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_EMAIL, phone);
        cv.put(COLUMN_PHONE, email);
        cv.put(IMAGE,  Utility.getBytes(image));


        // the insert method returns row number if the insertion is successful and -1 if unsuccessful
        return sqLiteDatabase.insert(TABLE_NAME, null, cv) != -1;
//        return true;
    }
    Cursor getAllUsers() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

//    boolean updateEmployee(int id, String name, String dept, double salary) {
//        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
//
//        ContentValues cv = new ContentValues();
//        cv.put(COLUMN_NAME, name);
//        cv.put(COLUMN_DEPT, dept);
//        cv.put(COLUMN_SALARY, String.valueOf(salary));
//
//        // this method returns the number of rows affected
//        return sqLiteDatabase.update(TABLE_NAME, cv, COLUMN_ID + "=?", new String[]{String.valueOf(id)}) > 0;
//    }
//    boolean deleteEmployee(int id) {
//        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
//        // the delete method returns the number of rows affected
//        return sqLiteDatabase.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(id)}) > 0;
//    }


}

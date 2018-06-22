package com.example.myself.fragmentstask;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Rooman on 6/21/2018.
 */

public class DbAdapter {

    MyHelper myHelper;

    public DbAdapter(Context context) {
        myHelper = new MyHelper(context);
        Log.d("DbAdapter", "DbAdapter:constructor success");
    }

    public long insertData(String name, String designation) {
        Log.d("sss===", "" + name + "   " + designation);
        SQLiteDatabase db = myHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyHelper.EMPLOYEE_NAME, name);
        contentValues.put(MyHelper.EMPLOYEE_DESIGNATION, designation);
        long id = db.insert(MyHelper.TABLE_NAME, null, contentValues);
        return id;
    }


    public String getData() {
        String[] columns = {MyHelper.UID, MyHelper.EMPLOYEE_NAME, MyHelper.EMPLOYEE_DESIGNATION};
        SQLiteDatabase db = myHelper.getWritableDatabase();
        Cursor cursor = db.query(MyHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            buffer.append(cursor.getInt(cursor.getColumnIndex(MyHelper.UID)) + "\t\t" + cursor.getString(cursor.getColumnIndex(MyHelper.EMPLOYEE_NAME)) + "\t\t" + cursor.getString(cursor.getColumnIndex(MyHelper.EMPLOYEE_DESIGNATION)) + "\n");
        }
        Log.d("ddd===", "" + buffer.toString());
        return buffer.toString();
    }


    public int delete(String name) {
        SQLiteDatabase db = myHelper.getWritableDatabase();
        String[] whereArgs = {name};

        int id = db.delete(myHelper.TABLE_NAME, myHelper.EMPLOYEE_NAME + " = ?", whereArgs);
        return id;
    }


    public int updateName(String oldName, String newName) {
        SQLiteDatabase db = myHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyHelper.EMPLOYEE_NAME, newName);
        String[] whereArgs = {oldName};
        int count = db.update(MyHelper.TABLE_NAME, contentValues, MyHelper.EMPLOYEE_NAME + " = ?", whereArgs);
        return count;
    }


    static class MyHelper extends SQLiteOpenHelper {

        Context context;

        private static final String DATABASE_NAME = "employeeDb";
        private static final String TABLE_NAME = "employeeTable";
        private static final int DATABASE_VERSION = 1;
        private static final String UID = "_id";
        private static final String EMPLOYEE_NAME = "Name";
        private static final String EMPLOYEE_DESIGNATION = "Designation";


        public MyHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
            Log.d("DbAdapter", "MyHelper:constructor success");
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + EMPLOYEE_NAME + " VARCHAR, " + EMPLOYEE_DESIGNATION + " VARCHAR);");
                Log.d("DbAdapter", "MyHelper:oncreate success");
            } catch (SQLiteException e) {
                e.printStackTrace();
                Log.d("DbAdapter", "MyHelper:oncreate Error: onCreate: " + e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            try {
                db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
                onCreate(db);
                Log.d("DbAdapter", "MyHelper:onupgrade success");
            } catch (SQLiteException e) {
                e.printStackTrace();
                Log.d("DbAdapter", "MyHelper:onupgrade Error: onCreate: " + e);
            }

        }
    }
}

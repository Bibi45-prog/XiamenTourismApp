package com.xiamenTourism.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;


import com.xiamenTourism.model.TourismModel;

import java.util.ArrayList;

public class TourismDatabase {
    private static final String DATABASE_NAME = "tourism.db";
    private static final String DATABASE_TABLE = "Tourism";
    private static final String ID = "`id`";

    private static final String USER_NAME = "`user_name`";
    private static final String EMAIL_ADDRESS = "`email_address`";
    private static final String PHONE_NUMBER = "`phone_number`";
    private static final String PASSWORD = "`password`";
    private static final int VERSION = 1;
    private static SQLiteDatabase database;
    private SqliteDateBaseHandler handler;

    public TourismDatabase(Context context) {
        this.handler = new SqliteDateBaseHandler(context, DATABASE_NAME, null, VERSION);
    }

    public long insertUserData(String userName, String emailAddress, String phoneNumber, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME, userName);
        contentValues.put(EMAIL_ADDRESS, emailAddress);
        contentValues.put(PHONE_NUMBER, phoneNumber);
        contentValues.put(PASSWORD, password);

        return database.insert(DATABASE_TABLE, null, contentValues);
    }
    public void updateDatabase(int id , String userName, String userPassword, String userPhoneNumber){
        ContentValues newValues = new ContentValues();
        newValues.put("user_name", userName);
        newValues.put("password", userPassword);
        newValues.put("phone_number", userPhoneNumber);

        database.update("Tourism", newValues, "id=" + id, null);
    }

    public void DeletePlaceById(int id) {
        database.execSQL("delete from Tourism where id=" + id);
    }



    @SuppressLint("Range")
    public ArrayList<TourismModel> getUserData() {//String customId, PurchasePreference appPurchasePref
        ArrayList<TourismModel> saved_route_list = null;
        Cursor cursor = null;
        try {
            cursor = database.query(true, DATABASE_TABLE, new String[]{ID, USER_NAME, EMAIL_ADDRESS,PHONE_NUMBER, PASSWORD},
                    null, null, null, null, null, null);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToLast();

                saved_route_list = new ArrayList<>();
                while (!cursor.isBeforeFirst()) {
                    saved_route_list.add(new TourismModel(
                            cursor.getInt(cursor.getColumnIndex("id")),
                            cursor.getString(cursor.getColumnIndex("user_name")),
                            cursor.getString(cursor.getColumnIndex("email_address")),
                            cursor.getString(cursor.getColumnIndex("phone_number")),
                            cursor.getString(cursor.getColumnIndex("password"))));

                    cursor.moveToPrevious();
                }
            }
        } catch (Exception ignored) {
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return saved_route_list;
    }

    private static class SqliteDateBaseHandler extends SQLiteOpenHelper {
        private static final String DATABASE_CREATE = "create table Tourism (`id` INTEGER PRIMARY KEY AUTOINCREMENT,`user_name` TEXT, `email_address` TEXT, `phone_number` TEXT, `password` TEXT);";

        SqliteDateBaseHandler(Context context, String name, CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        public void onUpgrade(SQLiteDatabase db, int _oldVersion, int _newVersion) {
            db.execSQL("DROP TABLE IF EXISTS Tourism");
            onCreate(db);
        }
    }
    public void clear() {
        database.execSQL("delete from Tourism;");
    }
    public void CloseDatabase() {
        if (database.isOpen()) {
            database.close();
        }
    }
    public void OpenDatabase() throws SQLiteException {
        try {
            database = this.handler.getWritableDatabase();
        } catch (SQLiteException e) {
            database = this.handler.getReadableDatabase();
        }
    }
    public void DeleteAllRows() {
        database.delete(DATABASE_TABLE, null, null);
    }
    public boolean IsDatabaseOpen() {
        if (database != null) {
            return database.isOpen();
        }else {
            return false;
        }

    }
    @SuppressLint("Range")
    public ArrayList<String> getUserName() {
        ArrayList<String> ayahs_list = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = database.rawQuery("SELECT * FROM Tourism GROUP BY email_address", null, null);

            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();


                while (!cursor.isAfterLast()) {
                    ayahs_list.add(cursor.getString(cursor.getColumnIndex("email_address")));

                    cursor.moveToNext();
                }
            }


        } catch (Exception ignored) {
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return ayahs_list;
    }


    @SuppressLint("Range")
    public ArrayList<String> getUserPassword() {
        ArrayList<String> ayahs_list = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = database.rawQuery("SELECT * FROM Tourism GROUP BY password", null, null);

            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();


                while (!cursor.isAfterLast()) {
                    ayahs_list.add(cursor.getString(cursor.getColumnIndex("password")));

                    cursor.moveToNext();
                }
            }


        } catch (Exception ignored) {
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return ayahs_list;
    }
}

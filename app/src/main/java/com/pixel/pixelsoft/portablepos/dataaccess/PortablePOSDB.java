package com.pixel.pixelsoft.portablepos.dataaccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.pixel.pixelsoft.portablepos.datamodels.DBContents;

import java.util.ArrayList;
import java.util.List;

public class PortablePOSDB extends SQLiteOpenHelper implements IDataRepository {

    private static final int DB_VER = 1;

    public PortablePOSDB(Context context){
        super(context, DBContents.DATABASE.toString(),null,DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //create profile
        sqLiteDatabase.execSQL("CREATE TABLE " + DBContents.TBL_PROFILE + " ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NUlL,"
                + "name TEXT(100),"
                + "address TEXT(500),"
                + "phone TEXT(100),"
                + "email TEXT(100),"
                + "isActivate INTEGER"
                + ");");
        Log.d("CREATE DATABASE","Create " + DBContents.TBL_PROFILE );

        //create item
        sqLiteDatabase.execSQL("CREATE TABLE " + DBContents.TBL_ITEM + " ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NUlL,"
                + "name TEXT(100),"
                + "category_id INTEGER,"
                + "unit_price DOUBLE,"
                + "barcode TEXT(200),"
                + "status INTEGER,"
                + "photo TEXT(500),"
                + "isActive INTEGER"
                + ");");
        Log.d("CREATE TABLE ","Create " + DBContents.TBL_ITEM);

        //create item_category
        sqLiteDatabase.execSQL("CREATE TABLE " + DBContents.TBL_ITEM_CATEGORY + " ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NUlL,"
                + "name TEXT(100),"
                + "isActive INTEGER"
                + ");");
        Log.d("CREATE TABLE ","Create " + DBContents.TBL_ITEM_CATEGORY);

        //create invoice header
        sqLiteDatabase.execSQL("CREATE TABLE " + DBContents.TBL_RECEIPT + " ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NUlL,"
                + "invoice_number TEXT(100),"
                + "total DOUBLE,"
                + "created_date DATETIME,"
                + "isAmend TEXT(1),"
                + "createdby TEXT(500),"
                + "isActive INTEGER"
                + ");");
        Log.d("CREATE TABLE ","Create " + DBContents.TBL_RECEIPT);
        //create invoice detail
        sqLiteDatabase.execSQL("CREATE TABLE " + DBContents.TBL_RECEIPT_DETAIL + " ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NUlL,"
                + "invoice_number TEXT(100),"
                + "unit_price DOUBLE,"
                + "quantity INTEGER,"
                + "item_id INTEGER,"
                + "created_date DATETIME,"
                + "isAmend TEXT(1),"
                + "isActive INTEGER"
                + ");");
        Log.d("CREATE TABLE ","Create " + DBContents.TBL_RECEIPT_DETAIL);
        //create temp invoice detail
        sqLiteDatabase.execSQL("CREATE TABLE " + DBContents.TBL_RECEIPT_DETAIL_TEMP + " ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NUlL,"
                + "invoice_number TEXT(100),"
                + "unit_price DOUBLE,"
                + "quantity INTEGER,"
                + "item_id INTEGER,"
                + "created_date DATETIME,"
                + "isAmend TEXT(1),"
                + "isActive INTEGER"
                + ");");
        Log.d("CREATE TABLE ","Create " + DBContents.TBL_RECEIPT_DETAIL_TEMP);
        //create stock
        sqLiteDatabase.execSQL("CREATE TABLE " + DBContents.TBL_STOCK + " ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NUlL,"
                + "item_id INTEGER,"
                + "quantity INTEGER,"
                + "isActive INTEGER"
                + ");");
        Log.d("CREATE TABLE ","Create " + DBContents.TBL_STOCK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    @Override
    public int insert(String tableName, Object content) {
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            int result = (int) database.insert(tableName, null, (ContentValues) content);
            database.close();
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public boolean update(String tableName, Object content) {
        try{
            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues contentValues = (ContentValues) content;
            String[] array = new String[]{contentValues.get("_id")+""};
            database.update(tableName,contentValues,"_id = ?",array);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String tableName, int id) {
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            database.delete(tableName, " _id = ?", new String[]{id+""});
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Object> select(String queryString) {
        try {

            SQLiteDatabase database = this.getWritableDatabase();
            List<Object> list = new ArrayList<Object>();
            Cursor cursor = database.rawQuery(queryString, null);
            if (cursor != null) {

                if (cursor.moveToFirst()) {
                    do {
                        ContentValues contentValues = new ContentValues();
                        String[] columnNames = cursor.getColumnNames();
                        for (String columnName : columnNames) {
                            contentValues.put(columnName, cursor.getString(cursor.getColumnIndex(columnName)));
                        }
                        list.add(contentValues);
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            database.close();
            return list;
        }
        catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }

    @Override
    public boolean execute(String queryString) {
        try{
            SQLiteDatabase database = this.getWritableDatabase();
            database.execSQL(queryString);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}

package com.example.hp.studentsqlitecruddemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DB_Controller extends SQLiteOpenHelper {


    public DB_Controller(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "Test.db", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE STUDENT( ID INTEGER PRIMARY KEY AUTOINCREMENT, FIRSTNAME TEXT UNIQUE, LASTNAME TEXT );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS STUDENT;");
        onCreate(sqLiteDatabase);
    }

    public void insert_data(String fn, String ln)
    {
        ContentValues contentValues= new ContentValues();
        contentValues.put("FIRSTNAME",fn);
        contentValues.put("LASTNAME",ln);
        this.getWritableDatabase().insertOrThrow("STUDENT", " ", contentValues);

    }
    public void delete_data(String fn)
    {
        this.getWritableDatabase().delete("STUDENT","FIRSTNAME='"+fn+"'",null);
    }
    public void update_data(String old_fn,String new_fn)
    {
        this.getWritableDatabase().execSQL("UPDATE STUDENT SET FIRSTNAME='"+new_fn+"' where FIRSTNAME='"+old_fn+"'");
    }

    public void list_data(TextView textview)
    {
        Cursor cursor=this.getReadableDatabase().rawQuery("SELECT * FROM STUDENT",null);
        textview.setText(" ");
        while(cursor.moveToNext())
        {
            textview.append(cursor.getString(1) + " " +cursor.getString(2) + "\n");
        }

    }
}

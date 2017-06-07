package com.example.carlosandres.trueque_iue;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by CarlosAndrés on 25/05/2017.
 */

public class DBHelper extends SQLiteOpenHelper {


    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // ----- LOGIN
        db.execSQL("CREATE TABLE IF NOT EXISTS tblUser(code integer primary key autoincrement, id_number text, user_name text, password text, email text, carrer text, user_avatar blob)");
        db.execSQL("insert into tblUser (id_number, user_name, password) values ('1','Carlos Andrés Montoya Cardona','a','capiano@gmail.com','Ingenieria de sistemas','')");  //Registro 1

        // ---- Enroll

 /*       db.execSQL("CREATE TABLE IF NOT EXISTS tblEnroll(code integer primary key autoincrement, owner text, cell text, ime text)");
        db.execSQL("insert into tblEnroll (cell,owner,ime) values ('Carlos','Huawei','12345')");

        // ---- Cordenadas

        db.execSQL("create tblCordenada (code integer primary key autoincrement, latitud integer, longitud integer)");
        //db.execSQL("insert into tblCordenada values ('0', '0')");*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}

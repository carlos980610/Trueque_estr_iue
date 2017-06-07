package com.example.carlosandres.trueque_iue;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Toast;

/**
 * Created by CarlosAndrés on 25/05/2017.
 */

public class DB_Connection {


    public DB_Connection() {

    }

    //Login
    public static boolean Access_Login (View v, String id_number, String password, Context context)
    {
        Cursor cursor; //Cursor que va a la base de datos
        boolean conection = false; //Estado de la conexión

        try {
            DBHelper admin = new DBHelper(context, "database.sqlite", null, 1);
            SQLiteDatabase db = admin.getWritableDatabase();

            cursor = db.rawQuery("select id_number, password from tblUser where id_number = '"+id_number+"' and password='"+password+"'", null);
            Toast.makeText(context, "Llego a la base", Toast.LENGTH_SHORT).show();
            if (cursor.moveToFirst())
            {
                do
                {
                    String number = cursor.getString(0);
                    String pass = cursor.getString(1);

                    if (id_number.equals(number) && password.equals(pass))
                    {
                        conection = true;
                    }
                }while (cursor.moveToNext());
            }

        }
        catch (Exception exception){
            conection = false;
            new AlertDialog.Builder(context)
                    .setTitle("Error de conexión")
                    .setMessage("No se logró establecer una conexión con la base de datos, intentelo de nuevo")
                    .setCancelable(true)
                    .setPositiveButton("Intentar de nuevo", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .show();
        }
        return conection;
    }






    //Registro
    public static boolean Access_SingUp (View v, String id_number, String password, Context context)
    {
        Cursor cursor; //Cursor que va a la base de datos
        boolean conection = false; //Estado de la conexión

        try {
            DBHelper admin = new DBHelper(context, "database.sqlite", null, 1);
            SQLiteDatabase db = admin.getWritableDatabase();

            cursor = db.rawQuery("select id_number, password from tblUser where id_number = '"+id_number+"' and password='"+password+"'", null);
            Toast.makeText(context, "Llego a la base", Toast.LENGTH_SHORT).show();
            if (cursor.moveToFirst())
            {
                do
                {
                    String number = cursor.getString(0);
                    String pass = cursor.getString(1);

                    if (id_number.equals(number) && password.equals(pass))
                    {
                        conection = true;
                    }
                }while (cursor.moveToNext());
            }

        }
        catch (Exception exception){
            conection = false;
            new AlertDialog.Builder(context)
                    .setTitle("Error de conexión")
                    .setMessage("No se logró establecer una conexión con la base de datos, intentelo de nuevo")
                    .setCancelable(true)
                    .setPositiveButton("Intentar de nuevo", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .show();
        }
        return conection;
    }
























    //Obtener datos del usuario
    public static String[] getUserData (View v, String id_number, Context context){
        String [] user_data = new String[2]; //Array para sacar los datos
        Cursor cursor; //Cursor que va a la base de datos

        try {

            DBHelper admin = new DBHelper(context, "database.sqlite", null, 1);
            SQLiteDatabase db = admin.getWritableDatabase();

            cursor = db.rawQuery("select id_number, user_name from tblUser where id_number = '"+id_number+"'", null);

            if (cursor.moveToFirst())
            {
                do
                {
                    if (cursor.getString(0).equals(id_number))
                    {
                        user_data [0] = cursor.getString(0);
                        user_data [1] = cursor.getString(1);
                    }
                }while (cursor.moveToNext());
            }
        }
        catch (Exception exception){
            Toast.makeText(context, "No se cargo la informacion del usuario", Toast.LENGTH_SHORT).show();
        }
        return user_data;
    }
}



















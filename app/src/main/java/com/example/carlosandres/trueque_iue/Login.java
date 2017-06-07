package com.example.carlosandres.trueque_iue;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;


public class Login extends AppCompatActivity {

    EditText txtId_number, txtPassword; //Declaración de los txt del login
    public static String id, password;
    View v;
    boolean connection_status = false;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final Button Login = (Button) findViewById(R.id.btnLogin);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtId_number = (EditText)findViewById(R.id.txtId_number); //Asignación al txtUser
                txtPassword = (EditText)findViewById(R.id.txtPassword); //Asignación al txt password

                id = txtId_number.getText().toString();
                password = txtPassword.getText().toString();

                connection_status = DB_Connection.Access_Login(v, id, password, context);

                if (connection_status == true){
                    Toast.makeText(getApplicationContext(), "Sesión iniciada correctamente", Toast.LENGTH_SHORT).show();
                    Intent new_from = new Intent(Login.this, MainActivity.class);
                    startActivity(new_from);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Sesión no iniciada, intentelo de nuevo", Toast.LENGTH_SHORT).show();
                    txtId_number.setText("");
                    txtPassword.setText("");
                }
            }
        });



        final Button SingUp = (Button) findViewById(R.id.btnCreateAccount);
        SingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent new_from = new Intent(Login.this, SingUp.class);
                startActivity(new_from);
            }
        });
    }
    public static String getUser_Id (){
        return id;
    }

}

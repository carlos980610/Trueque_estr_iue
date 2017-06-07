package com.example.carlosandres.trueque_iue;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.content.Context;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Profile.OnFragmentInteractionListener, Publish_Book.OnFragmentInteractionListener,
        Books.OnFragmentInteractionListener, Configuration.OnFragmentInteractionListener{

    View v;
    Context context = this;
    String id;
    String user_data[] = new String[2];
    TextView Name, Id;
    LayoutInflater inflater;
    ViewGroup container;

    NavigationView navegation_drawer; //inicialización del navegation drawer

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Trae los datos de usuario
        id = Login.getUser_Id();
        user_data = DB_Connection.getUserData(v, id, context);

        //Sentencia de las variables del drawer
        navegation_drawer = (NavigationView) findViewById(R.id.nav_view);
        navegation_drawer.setNavigationItemSelectedListener(this);
        View headerView = navegation_drawer.getHeaderView(0);

        //Sentencia de los objetos en variables
        Name = (TextView) headerView.findViewById(R.id.lblUserName);
        Id = (TextView) headerView.findViewById(R.id.lblUserId);

        //Set de datos en el drawer
        Name.setText(user_data[1]);
        Id.setText(user_data[0]);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean select_fragment;
                Fragment fragment;

                fragment = new Books();
                select_fragment = true;

                if (select_fragment){
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_fragments, fragment).commit();
                }
                Snackbar.make(view, "Buscar un libro", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //Toast.makeText(getApplicationContext(), "Log successfully", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.action_request){

        }
        else if (id == R.id.action_exit){
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Salir de la aplicación")
                    .setMessage("¿Esta seguro que desea salir de la aplicación?")
                    .setCancelable(true)
                    .setPositiveButton("Salir", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .show();
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
            boolean select_fragment = false;

            Fragment fragment = null;

        if (id == R.id.nav_camera) {
            // Handle the camera action
            fragment = new Profile();
            select_fragment = true;

        } else if (id == R.id.nav_gallery) {
            fragment = new Publish_Book();
            select_fragment = true;

        } else if (id == R.id.nav_slideshow) {
            fragment = new Books();
            select_fragment = true;

        } else if (id == R.id.nav_manage) {
            fragment = new Configuration();
            select_fragment = true;

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        if (select_fragment){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_fragments, fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

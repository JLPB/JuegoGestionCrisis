package com.iesperemaria.practicaglobalandroidstudio2ev;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button jugar;
    Button puntuaciones;
    Button instrucciones;
    ScrollView currentScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Linkeado de objetos
        jugar = (Button)findViewById(R.id.btnJugar);
        puntuaciones =(Button)findViewById(R.id.btnPuntuaciones);
        instrucciones =(Button)findViewById(R.id.btnInstrucciones);
        currentScrollView = (ScrollView)findViewById(R.id.scrollView);

        // set listeners
        jugar.setOnClickListener(this);
        puntuaciones.setOnClickListener(this);
        instrucciones.setOnClickListener(this);

        //Color del background

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean background = pref.getBoolean("fondo", true);

        if(background == false){
            currentScrollView.setBackgroundResource(R.drawable.background1);
        }
        else{
            currentScrollView.setBackgroundResource(R.drawable.background2);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        currentScrollView = (ScrollView)findViewById(R.id.scrollView);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean background = pref.getBoolean("fondo", true);

        if(background == false){
            currentScrollView.setBackgroundResource(R.drawable.background1);
        }
        else{
            currentScrollView.setBackgroundResource(R.drawable.background2);
        }
    }

    // Action bar
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.bar_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.opciones:
                Intent opciones = new Intent(this, preferencias.class);
                startActivity(opciones);
                break;
            case R.id.autor:
                Toast toast = Toast.makeText(this, getResources().getString(R.string.autor), Toast.LENGTH_LONG);
                toast.show();
                break;
            case R.id.disclaimer:
                Toast toastDisclaimer = Toast.makeText(this, getResources().getString(R.string.disclaimerAction), Toast.LENGTH_LONG);
                toastDisclaimer.show();
                break;
        }
        return true;
    }


    //Gestión de los botones
    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btnJugar){
            Intent jugar = new Intent(this, DatosJugador.class);
            startActivity(jugar);
        }
        else if(v.getId() == R.id.btnPuntuaciones){
            Toast toastPuntuaciones = Toast.makeText(this, getResources().getString(R.string.noDisponible), Toast.LENGTH_LONG);
            toastPuntuaciones.show();
            // TODO arreglar la lista de puntuaciones
            /*Intent puntuaciones = new Intent(this, ListaPuntuaciones.class);
            startActivity(puntuaciones);*/
        }
        else{
            Intent instrucciones = new Intent(this, Instrucciones.class);
            startActivity(instrucciones);
        }
    }
}

package com.iesperemaria.practicaglobalandroidstudio2ev;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListaPuntuaciones extends AppCompatActivity {
    private ListView listaPuntuaciones;
    private ArrayList<Puntuaciones> lista = new ArrayList<>();
    private AdaptadorPuntuaciones adaptador;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista);
        populateListView();
    }
    public ListaPuntuaciones(){

    }
    public ListaPuntuaciones(String nombre, String porcentaje){
        lista.add(new Puntuaciones(nombre, porcentaje));
    }

    public void populateListView(){
        listaPuntuaciones = (ListView)findViewById(R.id.listView);
        lista.add(new Puntuaciones("José Luis", "90"));
        lista.add(new Puntuaciones("Paco", "63"));
        lista.add(new Puntuaciones("Pedro", "41"));
        adaptador = new AdaptadorPuntuaciones(this, lista);
        listaPuntuaciones.setAdapter(adaptador);
    }
}

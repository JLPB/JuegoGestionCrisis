package com.iesperemaria.practicaglobalandroidstudio2ev;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.appcompat.view.menu.MenuView;

import java.util.ArrayList;

public class AdaptadorPuntuaciones extends BaseAdapter {

    Activity context;
    ArrayList<Puntuaciones> puntos;
    private static LayoutInflater inflater = null;

    public AdaptadorPuntuaciones(Activity context, ArrayList<Puntuaciones> puntos) {
        this.context = context;
        this.puntos = puntos;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return puntos.size();
    }

    @Override
    public Puntuaciones getItem(int position) {
        return puntos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // Aquí utilizo list_item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        //Inflamos el layout y posicionamos las variables
        itemView =(itemView == null)? inflater.inflate(R.layout.list_item, null): itemView;
        TextView textViewNombre = (TextView)itemView.findViewById(R.id.nombrePuntuaciones);
        TextView textViewPorcentaje = (TextView)itemView.findViewById(R.id.porcentajePuntuaciones);
        Puntuaciones seleccionPuntos = puntos.get(position);
        textViewNombre.setText(seleccionPuntos.nombre);
        textViewPorcentaje.setText(seleccionPuntos.porcentaje);
        return itemView;
    }
}

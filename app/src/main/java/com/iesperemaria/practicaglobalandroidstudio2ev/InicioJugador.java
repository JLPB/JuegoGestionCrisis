package com.iesperemaria.practicaglobalandroidstudio2ev;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;



public class InicioJugador extends Activity implements View.OnClickListener {

    String nombre;
    String genero;
    String texto;


    Button gestionar;
    Button dimitir;
    TextView intro;
    TextView crisis;
    TextView exito;
    ImageView imagenResult;
    TextView fallosConsecutivos;

    //Variables de las puntuaciones
    int marcadorCrisis;
    int marcadorExito;
    int fracasos; //Acumulamos los fracasos, si llega a 3 el juego termina.

    @SuppressLint("StringFormatInvalid")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_jugador);

        // Linkeo de objetos
        gestionar = (Button)findViewById(R.id.btnGestionar);
        dimitir = (Button)findViewById(R.id.btnDimitir);
        intro =(TextView)findViewById(R.id.introduccion);
        crisis = (TextView)findViewById(R.id.marcadorCrisis);
        exito = (TextView)findViewById(R.id.marcadorExito);
        imagenResult =(ImageView) findViewById(R.id.imagenResultado);
        fallosConsecutivos =(TextView)findViewById(R.id.txtFallosConsecutivos);

        // Linkeo de listeners
        gestionar.setOnClickListener(this);
        dimitir.setOnClickListener(this);

        //Recuperamos los datos que nos ha pasado la pantalla anterior
        Bundle extras = getIntent().getExtras();
        nombre = extras.getString("nombre");
        genero = extras.getString("genero");

        // Ajustamos la introducción con el nombre recibido
        texto = getString(R.string.introduccion, nombre);
        intro.setText(texto);

        //Inicializamos los marcadores
        crisis.setText(Integer.toString(marcadorCrisis));
        exito.setText(Integer.toString(marcadorExito));
    }
    // Gestión botones
    public void onClick(View v){
        if(v.getId() == R.id.btnGestionar){
            Intent gestionar = new Intent(this, PantallaCrisis.class);
            startActivityForResult(gestionar, 1);
        }
        if(v.getId() == R.id.btnDimitir){

            Intent dimitir = new Intent(this, Resultado.class);
            dimitir.putExtra("marcador",exito.getText().toString());
            startActivity(dimitir);
            finish();
        }
    }
    // Método para gestionar el resultado de la actividad
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data ){
        marcadorCrisis++;
        if(requestCode == 1 && resultCode == RESULT_OK){
            String valor = data.getExtras().getString("resultado");
            marcadorExito = marcadorExito + Integer.parseInt(valor);
            crisis.setText(Integer.toString(marcadorCrisis));
            exito.setText(Integer.toString(marcadorExito));
            imagenResult.setImageDrawable(getDrawable(R.drawable.exito));
            fallosConsecutivos.setText("");
            fracasos = 0;
        }
        else{
            crisis.setText(Integer.toString(marcadorCrisis));
            fallosConsecutivos.setText(getResources().getString(R.string.txtFallosConsecutivos) + fracasos + "/3");
            imagenResult.setImageDrawable(getDrawable(R.drawable.fracaso));
            fracasos++;
            fallosConsecutivos.setText(getResources().getString(R.string.txtFallosConsecutivos) + fracasos + "/3");
            if(fracasos >= 3){
                gestionar.setEnabled(false);
                dimitir.setEnabled(false);
                imagenResult.setImageDrawable(getDrawable(R.drawable.gameover));
                final Intent resultado = new Intent(this, Resultado.class);
                //Ajustar resultado
                int recuperarResultado = Integer.parseInt(exito.getText().toString());
                int resultadoAjustado = recuperarResultado/2;
                resultado.putExtra("marcador",String.valueOf(resultadoAjustado));
            }
        }
    }
}

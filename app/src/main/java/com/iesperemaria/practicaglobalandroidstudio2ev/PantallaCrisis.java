package com.iesperemaria.practicaglobalandroidstudio2ev;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PantallaCrisis extends Activity implements View.OnClickListener {

    int derechos;
    int economia;
    int aprobacion;
    int exito;
    boolean ayuda;
    String textoCrisis;

    TextView txtCrisis;
    TextView txtDerechos;
    TextView txtEconomia;
    TextView txtAprobacion;


    Button ortodoxa;
    Button panico;
    Button ayudaInternacional;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_crisis);

        //Linkeo de objetos
        txtCrisis = (TextView)findViewById(R.id.txtProblema);
        txtDerechos = (TextView)findViewById(R.id.txtDerechos);
        txtEconomia = (TextView)findViewById(R.id.txtEconomia);
        txtAprobacion = (TextView)findViewById(R.id.txtAprobacion);
        ortodoxa=(Button)findViewById(R.id.btnOrtodoxa);
        panico =(Button)findViewById(R.id.btnPanico);
        ayudaInternacional =(Button)findViewById(R.id.btnAyudaInternacional);


        //Inicializar los valores
        derechos =(int)(Math.random()*3)+1;
        economia =(int)(Math.random()*3)+1;
        aprobacion =(int)(Math.random()*3)+1;
        ayuda = true;

        //Mostrar valores
        String textoDerechosCompleto = getString(R.string.txtDerechos,derechos);
        String textoEconomiaCompleto = getString(R.string.txtEconomia,economia);
        String textoAprobacionCompleto = getString(R.string.txtAprobacion,aprobacion);

        // Dar valor a los textos
        txtDerechos.setText(textoDerechosCompleto);
        txtEconomia.setText(textoEconomiaCompleto);
        txtAprobacion.setText(textoAprobacionCompleto);
        txtCrisis.setText(gestionTextos());

        // Linkear listeners
        ortodoxa.setOnClickListener(this);
        panico.setOnClickListener(this);
        ayudaInternacional.setOnClickListener(this);

        //recuperar preferencias
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String nivelDificultad = pref.getString("dificultad", "1");
        // Asignamos el valor de éxito en base al nivel de dificultad
        exito = this.ajustarResultado(nivelDificultad);


    }
    // Gestión botones
    public void onClick(View v){
        if(v.getId() == R.id.btnOrtodoxa){

            int resultado = this.calcularResultado();
            resultado = resultado + 2;
            Intent valor = new Intent();

            if(resultado > exito){
                valor.putExtra("resultado", "1");
                setResult(RESULT_OK, valor);
                finish();
            }
            else{
                valor.putExtra("resultado", "0");
                setResult(RESULT_CANCELED, valor);
                finish();
            }
        }
        if(v.getId() == R.id.btnPanico){

            int resultado = this.calcularResultado();
            resultado = resultado *(int)(Math.random()*3);
            Intent valor = new Intent();

            if(resultado >= exito){
                valor.putExtra("resultado", "1");
                setResult(RESULT_OK, valor);
                finish();
            }
            else{
                valor.putExtra("resultado", "0");
                setResult(RESULT_CANCELED, valor);
                finish();
            }
        }
        if(v.getId() == R.id.btnAyudaInternacional){
            if (ayuda == true){
                ayuda = false;
                Intent ayudaInt= new Intent(this, AyudaInternacional.class);
                startActivityForResult(ayudaInt,1);
            }
        }
    }
    // Gestión de textos
    public String gestionTextos(){
        int texto =(int)(Math.random()*3)+1;

        if(texto == 1){
            return textoCrisis = getString(R.string.crisis1);
        }
        else if(texto == 2){
            return textoCrisis = getString(R.string.crisis2);
        }
        else{
            return textoCrisis = getString(R.string.crisis3);
        }
    }

    // Gestión de resultados
    protected void onActivityResult(int requestCode, int resultCode, Intent data ){
        if(requestCode == 1 && resultCode == RESULT_OK){
            String valor = data.getExtras().getString("resultado");
            derechos = derechos*Integer.parseInt(valor);
            economia = economia*Integer.parseInt(valor);
            aprobacion = aprobacion*Integer.parseInt(valor);
            //regenerar los textos
            String textoDerechosCompleto = getString(R.string.txtDerechos,derechos);
            String textoEconomiaCompleto = getString(R.string.txtEconomia,economia);
            String textoAprobacionCompleto = getString(R.string.txtAprobacion,aprobacion);
            // Poner en pantalla los textos
            txtDerechos.setText(textoDerechosCompleto);
            txtEconomia.setText(textoEconomiaCompleto);
            txtAprobacion.setText(textoAprobacionCompleto);
        }
    }
    //Métodos para el ajuste y cálculo de resultados
    public int ajustarResultado(String dificultad){
        int valor = Integer.parseInt(dificultad);
        if(valor == 0){
            return 7;
        }
        else if(valor == 1){
            return 8;
        }
        else{
            return 9;
        }
    }
    public int calcularResultado(){
        int sumaTotal = derechos + economia + aprobacion;
        return sumaTotal;
    }
}


package com.iesperemaria.practicaglobalandroidstudio2ev;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AyudaInternacional extends Activity implements View.OnClickListener  {

    Button aceptar;
    Button rechazar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ayuda_internacional);

        //Linkeo de objetos
        aceptar = (Button)findViewById(R.id.btnAceptarInternacional);
        rechazar = (Button)findViewById(R.id.btnCancelarInternacional);

        //Listeners
        aceptar.setOnClickListener(this);
        rechazar.setOnClickListener(this);
    }
    public void onClick(View v){
        int resultado = (int)(Math.random()*3); // devuelve los valores 0,1 o 2
        String respuesta = String.valueOf(resultado);
        Intent decision = new Intent();

        if(v.getId() == R.id.btnAceptarInternacional){
            decision.putExtra("resultado", respuesta);
            setResult(RESULT_OK, decision);
            finish();
        }
        else{
            setResult(RESULT_CANCELED, decision);
            finish();
        }
    }
}

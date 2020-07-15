package com.iesperemaria.practicaglobalandroidstudio2ev;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class DatosJugador extends Activity implements View.OnClickListener  {

    EditText nombre;
    RadioGroup grupoButtons;
    RadioButton hombre;
    RadioButton mujer;
    Button crear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datos_jugador);
        //linkear objetos
        nombre =(EditText)findViewById(R.id.campoNombre);
        grupoButtons =(RadioGroup)findViewById(R.id.radioGroupGenero);
        hombre = (RadioButton)findViewById(R.id.radioHombre);
        mujer = (RadioButton)findViewById(R.id.radioMujer);
        crear = (Button)findViewById(R.id.btnCrear);

        //Listeners
        crear.setOnClickListener(this);
        grupoButtons.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioHombre){
                    crear.setTextColor(Color.parseColor("#F3FD10"));
                }
                else{
                    crear.setTextColor(Color.parseColor("#FD10EE"));
                }
            }
        });
    }

    // Gestion

    // Gestión del botón
    public void onClick(View v) {

        //Comprobamos que uno de los radiobuttons esté marcado
        if(hombre.isChecked() == false && mujer.isChecked() ==false){
            Toast toast = Toast.makeText(this, getResources().getString(R.string.radioVacio), Toast.LENGTH_LONG);
            toast.show();
        }
        //Comprobamos que el nombre no esté vacio, si lo está muestra un toast y no pasa a la pantalla siguiente
        else if(nombre.getText().toString().equals("")){
                Toast toast = Toast.makeText(this, getResources().getString(R.string.usuarioVacio), Toast.LENGTH_LONG);
                toast.show();
            }
        else{
            // Recogemos el id del radiobutton
            int eleccion = grupoButtons.getCheckedRadioButtonId();
            RadioButton checkedRadioButton = (RadioButton) findViewById(eleccion);
            // Recogemos el texto del radiobutton seleccionado
            String texto = checkedRadioButton.getText().toString();
                if(texto.equals("Masculino") || texto.equals("Male")){
                    Intent jugar = new Intent(this, InicioJugador.class);
                    jugar.putExtra("nombre",nombre.getText().toString());
                    startActivity(jugar);
                }
                if(texto.equals("Femenino") || texto.equals("Female")){
                    Intent jugar = new Intent(this, InicioJugador.class);
                    jugar.putExtra("nombre",nombre.getText().toString());
                    startActivity(jugar);
                }
                finish();
            }
        }
    }

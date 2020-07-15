package com.iesperemaria.practicaglobalandroidstudio2ev;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Resultado extends Activity implements View.OnClickListener {

    String resultado;
    String textoCompleto;
    int x = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new EjemploView(this));


        //Recuperamos los datos que nos ha pasado la pantalla anterior
        Bundle extras = getIntent().getExtras();
        resultado = extras.getString("marcador");
        textoCompleto = getString(R.string.endGame,resultado);


    }
    public void onClick(View v) {

    }
    // Canvas
    public class EjemploView extends View {
        public EjemploView(Context context) {
            super(context);
        }
        //TODO Investigar porque sale el canvas más grande de lo normal
        @Override
        protected void onDraw(Canvas canvas) {

            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawPaint(paint);

            // Línea 1
            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(30);
            canvas.drawLine(100, 100, 800, 100, paint);

            //Añadir el texto
            paint.setColor(Color.BLACK);
            paint.setTextSize(50);
            canvas.drawText(textoCompleto, 100, 200, paint);

            //Añadir los puntos
            for(int i = 0; i < Integer.parseInt(resultado); i++){
                paint.setColor(Color.YELLOW);
                paint.setStrokeWidth(40);
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                canvas.drawPoint(x, 300, paint);
                x = x + 60;
            }
            //Línea 2
            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(30);
            canvas.drawLine(100, 400, 800, 400, paint);




        }


    }
}

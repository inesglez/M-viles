package com.example.inesprueba2eval2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class VistaPersonalizada extends View {
    private Paint pinturaLineas;
    private Paint pinturaTexto;
    private float escalaPantalla;
    private int anchoPantalla, altoPantalla;

    public VistaPersonalizada(Context contexto, AttributeSet attrs) {
        super(contexto, attrs);

        // Pintura para las líneas verdes
        pinturaLineas = new Paint();
        pinturaLineas.setColor(Color.GREEN);
        pinturaLineas.setStrokeWidth(3);
        pinturaLineas.setStyle(Paint.Style.STROKE);
        pinturaLineas.setPathEffect(new android.graphics.DashPathEffect(new float[]{10, 10}, 0));

        // Pintura para el texto
        pinturaTexto = new Paint();
        pinturaTexto.setColor(Color.BLACK);
        pinturaTexto.setTextSize(30);
    }

    @Override
    protected void onSizeChanged(int ancho, int alto, int anchoViejo, int altoViejo) {
        super.onSizeChanged(ancho, alto, anchoViejo, altoViejo);
        this.anchoPantalla = ancho;
        this.altoPantalla = alto;
        this.escalaPantalla = (float) ancho / 350;  // Escala del ancho de la pantalla
    }

    @Override
    protected void onDraw(Canvas lienzo) {
        super.onDraw(lienzo);

        // espacio entre líneas
        float espaciadoLineas = 70;

        //  pintura de las líneas
        Paint pinturaLineas = new Paint();
        pinturaLineas.setColor(Color.GREEN);
        pinturaLineas.setStyle(Paint.Style.STROKE);
        pinturaLineas.setStrokeWidth(3);

        // pintura del texto
        Paint pinturaTexto = new Paint();
        pinturaTexto.setColor(Color.BLACK);
        pinturaTexto.setTextSize(30);

        // pintura para los 4 textos mas grándes
        Paint pinturaTextoGrande = new Paint();
        pinturaTextoGrande.setColor(Color.BLACK);
        pinturaTextoGrande.setTextSize(50);

        // Dibujar líneas horizontales y sus valores
        for (int i = 1; i <= 30; i++) {
            float posicionY = i * espaciadoLineas;
            lienzo.drawLine(0, posicionY, anchoPantalla, posicionY, pinturaLineas);
            lienzo.drawText(String.format("%.4f", posicionY), 20, posicionY - 10, pinturaTexto);
        }

        float desplazamientoTexto = 40;
        float desplazamientoX = 250;    //espacio lateral para que no se monten uno encima de otros

        // Dibujar textos descriptivos con tamaño más grande
        lienzo.drawText("Fila: 544  scale = " + String.format("%.5f", escalaPantalla),
                desplazamientoX, 544 + desplazamientoTexto, pinturaTextoGrande);

        lienzo.drawText("Fila: 1088  width = " + anchoPantalla,
                desplazamientoX, 1088 + desplazamientoTexto, pinturaTextoGrande);

        lienzo.drawText("Fila: 1632  height = " + altoPantalla,
                desplazamientoX, 1632 + desplazamientoTexto, pinturaTextoGrande);

        lienzo.drawText("Ratio = " + String.format("%.6f", (float) anchoPantalla / altoPantalla),
                desplazamientoX, altoPantalla - 100, pinturaTextoGrande);
    }

}

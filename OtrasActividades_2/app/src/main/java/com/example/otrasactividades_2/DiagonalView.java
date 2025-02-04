package com.example.otrasactividades_2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class DiagonalView extends View {
    private Paint paintLines;
    private float escala;
    private int width, height;

    public DiagonalView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paintLines = new Paint();
        paintLines.setColor(Color.BLUE);
        paintLines.setStrokeWidth(5);
        paintLines.setStyle(Paint.Style.STROKE);
        escala = getResources().getDisplayMetrics().density; // Escala según densidad de pantalla
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Establecer el color de fondo en amarillo
        canvas.drawColor(Color.YELLOW);

        // Ajustar el grosor de las líneas según la escala
        paintLines.setStrokeWidth(2 * escala);

        // Dibujar las diagonales
        canvas.drawLine(0, 0, width, height, paintLines); // Diagonal de izquierda a derecha
        canvas.drawLine(width, 0, 0, height, paintLines); // Diagonal de derecha a izquierda
    }
}


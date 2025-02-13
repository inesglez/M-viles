package com.example.action_up;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MiVista extends View {
    private Paint paint;
    private Path path;

    public MiVista(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        // Configurar el pincel (Paint)
        paint = new Paint();
        paint.setColor(Color.BLUE); // Color azul
        paint.setStyle(Paint.Style.STROKE); // Solo contorno
        paint.setStrokeWidth(8); // Grosor del trazo
        paint.setAntiAlias(true); // Suavizar líneas

        // Inicializar el Path
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.rgb(255, 228, 196)); // Fondo color crema
        canvas.drawPath(path, paint); // Dibujar las líneas trazadas
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x, y); // Inicia un nuevo trazo
                invalidate();
                return true;

            case MotionEvent.ACTION_MOVE:
                path.lineTo(x, y); // Dibuja mientras se mueve el dedo
                invalidate();
                return true;

            case MotionEvent.ACTION_UP:
                // No hacemos nada especial al levantar el dedo
                return true;
        }
        return false;
    }
}

package com.example.action_down;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

public class MiVista extends View {
    private float x = 50, y = 50; // Coordenadas iniciales
    private float radio; // Radio del círculo
    private Paint paint; // Objeto Paint para dibujar
    private String mensaje = "Esperando interacción..."; // Mensaje del evento actual

    public MiVista(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        // Configurar el Paint
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);

        // Obtener la densidad de la pantalla y ajustar el radio
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float escala = metrics.density;
        radio = 50 * escala;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Dibujar el círculo en la posición actual
        canvas.drawCircle(x, y, radio, paint);

        // Dibujar el mensaje y coordenadas en pantalla
        paint.setTextSize(40);
        paint.setColor(Color.BLACK);
        canvas.drawText(mensaje, 100, 100, paint);
        canvas.drawText("x= " + x, 100, 150, paint);
        canvas.drawText("y= " + y, 100, 200, paint);

        // Restaurar color de pintura a rojo
        paint.setColor(Color.RED);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mensaje = "Evento: ACTION_DOWN";
                x = event.getX();
                y = event.getY();
                invalidate(); // Redibujar la vista
                return true;

            case MotionEvent.ACTION_MOVE:
                mensaje = "Evento: ACTION_MOVE";
                x = event.getX();
                y = event.getY();
                invalidate(); // Redibujar la vista dinámicamente
                return true;

            case MotionEvent.ACTION_UP:
                mensaje = "Evento: ACTION_UP";
                x = event.getX();
                y = event.getY();
                invalidate(); // Redibujar con la nueva posición final
                return true;
        }
        return false;
    }
}

package com.example.otrasactividades_1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MiPrimerGraficoView extends View {
    private Paint paintText;
    private Paint paintRedLine;
    private Paint paintGreenLine;
    private float escala;
    private int width, height;

    public MiPrimerGraficoView(Context context) {
        super(context);
        init();
    }

    public MiPrimerGraficoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paintText = new Paint();
        paintText.setColor(Color.BLACK);
        paintText.setTextSize(50);

        paintRedLine = new Paint();
        paintRedLine.setColor(Color.RED);
        paintRedLine.setStrokeWidth(5);

        paintGreenLine = new Paint();
        paintGreenLine.setColor(Color.GREEN);
        paintGreenLine.setStrokeWidth(5);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        escala = getResources().getDisplayMetrics().density; // Escala según densidad de pantalla
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Dibujar dimensiones y escala
        canvas.drawText("width: " + width + " height: " + height, 20 * escala, 30 * escala, paintText);
        canvas.drawText("escala: " + escala, 20 * escala, 80 * escala, paintText);

        // Dibujar línea verde (horizontal)
        canvas.drawLine(0, 40 * escala, width, 40 * escala, paintGreenLine);

        // Dibujar línea roja (vertical)
        canvas.drawLine(20 * escala, 0, 20 * escala, height, paintRedLine);
    }
}

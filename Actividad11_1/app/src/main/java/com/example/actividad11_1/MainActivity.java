package com.example.actividad11_1;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener referencia al TextView
        TextView tvResolution = findViewById(R.id.tvResolution);

        // Obtener las métricas de la pantalla
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        // Extraer ancho y alto en píxeles
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        // Mostrar resolución en el TextView
        String resolution = "Resolución: " + width + " x " + height + " píxeles";
        tvResolution.setText(resolution);
    }
}

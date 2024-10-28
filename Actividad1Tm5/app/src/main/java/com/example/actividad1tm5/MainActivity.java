package com.example.actividad1tm5;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progreso);
        button = findViewById(R.id.boton);

        button.setOnClickListener(v -> {
            // Muestra el spinner y el mensaje tipo toast
            progressBar.setVisibility(View.VISIBLE);
            Toast.makeText(MainActivity.this, "Elemento seleccionado", Toast.LENGTH_SHORT).show();

            // Oculta el spinner después de un tiempo (ej., 2 segundos)
            new Handler().postDelayed(() -> progressBar.setVisibility(View.GONE), 2000);
        });
    }
}
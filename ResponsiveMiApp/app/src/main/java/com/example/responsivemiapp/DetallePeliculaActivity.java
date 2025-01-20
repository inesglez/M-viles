package com.example.responsivemiapp;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetallePeliculaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pelicula);

        // Obtener datos del Intent
        String titulo = getIntent().getStringExtra("titulo");
        String sinopsis = getIntent().getStringExtra("sinopsis");
        String genero = getIntent().getStringExtra("genero");
        int duracion = getIntent().getIntExtra("duracion", 0);
        float puntuacion = getIntent().getFloatExtra("puntuacion", 0);
        int imagenResId = getIntent().getIntExtra("imagenResId", 0); // ID de recurso de imagen

        // Asignar datos a los elementos de la vista
        ((TextView) findViewById(R.id.detallePeliculaTitulo)).setText(titulo);
        ((TextView) findViewById(R.id.detallePeliculaSinopsis)).setText(sinopsis);
        ((TextView) findViewById(R.id.detallePeliculaGenero)).setText(genero);
        ((TextView) findViewById(R.id.detallePeliculaDuracion)).setText(duracion + " min");
        ((RatingBar) findViewById(R.id.detallePeliculaPuntuacion)).setRating(puntuacion);

        // Cargar la imagen desde el recurso local utilizando setImageResource
        ImageView imagen = findViewById(R.id.detallePeliculaImagen);
        imagen.setImageResource(imagenResId);
    }
}

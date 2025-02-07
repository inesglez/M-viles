package com.example.cine;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
        //  Obtener imagenBytes (array de bytes)
        byte[] imagenBytes = getIntent().getByteArrayExtra("imagenBytes");


        // Asignar datos a los elementos de la vista
        ((TextView) findViewById(R.id.detallePeliculaTitulo)).setText(titulo);
        ((TextView) findViewById(R.id.detallePeliculaSinopsis)).setText(sinopsis);
        ((TextView) findViewById(R.id.detallePeliculaGenero)).setText(genero);
        ((TextView) findViewById(R.id.detallePeliculaDuracion)).setText(duracion + " min");
        ((RatingBar) findViewById(R.id.detallePeliculaPuntuacion)).setRating(puntuacion);

        ImageView imagen = findViewById(R.id.detallePeliculaImagen);

        // Comprobar si se recibieron los datos de la imagen
        if (imagenBytes != null && imagenBytes.length > 0) {
            // Decodificar el array de bytes a Bitmap
            Bitmap bitmap = BitmapFactory.decodeByteArray(imagenBytes, 0, imagenBytes.length);
            imagen.setImageBitmap(bitmap);
        } else {
            // Si no hay datos de imagen, puedes mostrar una imagen por defecto
            imagen.setImageResource(R.drawable.cinemania); // Reemplaza con tu imagen por defecto
        }
    }
}
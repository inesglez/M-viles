package com.example.cine;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;

public class AñadirPelicula extends AppCompatActivity {

    private EditText etTitulo, etSinopsis, etDuracion, etPuntuacion;
    private Spinner spinnerGenero;
    private Button btnGuardar, btnCancelar, btnTomarFoto;
    private ImageView imgPelicula;

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private Bitmap imagenCapturada = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_pelicula);

        // Inicializar vistas
        etTitulo = findViewById(R.id.etTitulo);
        etSinopsis = findViewById(R.id.etSinopsis);
        etDuracion = findViewById(R.id.etDuracion);
        etPuntuacion = findViewById(R.id.etPuntuacion);
        spinnerGenero = findViewById(R.id.spinnerGenero);
        imgPelicula = findViewById(R.id.imgPelicula);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnTomarFoto = findViewById(R.id.btnTomarFoto);

        // Configurar Spinner
        String[] generos = {"Acción", "Comedia", "Drama", "Ciencia Ficción", "Terror"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, generos);
        spinnerGenero.setAdapter(adapter);

        // Acción del Spinner
        spinnerGenero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                imgPelicula.setImageResource(obtenerImagenSeleccionada());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                imgPelicula.setImageResource(R.drawable.cinemania);
            }
        });

        // Acción del botón para guardar
        btnGuardar.setOnClickListener(view -> {
            String titulo = etTitulo.getText().toString().trim();
            String sinopsis = etSinopsis.getText().toString().trim();
            String duracionStr = etDuracion.getText().toString().trim();
            String puntuacionStr = etPuntuacion.getText().toString().trim();

            if (titulo.isEmpty() || sinopsis.isEmpty() || duracionStr.isEmpty() || puntuacionStr.isEmpty()) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int duracion = Integer.parseInt(duracionStr);
                float puntuacion = Float.parseFloat(puntuacionStr);
                String genero = spinnerGenero.getSelectedItem().toString();
                int imagen = obtenerImagenSeleccionada();

                // Convertir Bitmap a byte[]
                byte[] imagenBytes = null;
                if (imagenCapturada != null) {
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    imagenCapturada.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    imagenBytes = stream.toByteArray();
                }

                // Crear nueva película
                Pelicula nuevaPelicula = new Pelicula(titulo, sinopsis, genero, duracion, puntuacion, imagen, imagenBytes);

                // Enviar la película a la otra actividad
                Intent intent = new Intent();
                intent.putExtra("pelicula", nuevaPelicula);
                setResult(RESULT_OK, intent);
                finish();
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Duración y puntuación deben ser números válidos", Toast.LENGTH_SHORT).show();
            }
        });

        // Acción del botón de cancelar
        btnCancelar.setOnClickListener(v -> finish());

        // Acción del botón para tomar una foto
        btnTomarFoto.setOnClickListener(v -> {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        });
    }

    // Método para manejar el resultado de la foto
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imagenCapturada = (Bitmap) extras.get("data");
            imgPelicula.setImageBitmap(imagenCapturada); // Mostrar la imagen capturada en el ImageView
        }
    }

    private int obtenerImagenSeleccionada() {
        String generoSeleccionado = spinnerGenero.getSelectedItem().toString();
        switch (generoSeleccionado) {
            case "Acción":
            case "Comedia":
            case "Drama":
            case "Ciencia Ficción":
            case "Terror":
                return R.drawable.cinemania;
            default:
                return R.drawable.cinemania;
        }
    }
}

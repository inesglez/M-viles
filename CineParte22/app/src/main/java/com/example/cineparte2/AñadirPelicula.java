package com.example.cineparte2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AñadirPelicula extends AppCompatActivity {

    private EditText etTitulo, etSinopsis, etDuracion, etPuntuacion;
    private Spinner spinnerGenero;
    private Button btnGuardar, btnCancelar;
    private ImageView imgPelicula;

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

        // Configurar Spinner
        String[] generos = {"Acción", "Comedia", "Drama", "Ciencia Ficción", "Terror"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, generos);
        spinnerGenero.setAdapter(adapter);

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

                Pelicula nuevaPelicula = new Pelicula(titulo, sinopsis, genero, duracion, puntuacion, imagen);

                Intent intent = new Intent();
                intent.putExtra("pelicula", nuevaPelicula);
                setResult(RESULT_OK, intent);
                finish();
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Duración y puntuación deben ser números válidos", Toast.LENGTH_SHORT).show();
            }
        });

        btnCancelar.setOnClickListener(v -> finish());
    }

    private int obtenerImagenSeleccionada() {
        String generoSeleccionado = spinnerGenero.getSelectedItem().toString();

        // Validar si se seleccionó un género válido
        if (generoSeleccionado.equals("Género")) {
            return -1; // Valor inválido, puedes manejar esto como prefieras
        }

        // Retornar imagen según el género seleccionado
        switch (generoSeleccionado) {
            case "Acción":
                return R.drawable.cinemania;
            case "Comedia":
                return R.drawable.cinemania;
            case "Drama":
                return R.drawable.cinemania;
            case "Ciencia Ficción":
                return R.drawable.cinemania;
            case "Terror":
                return R.drawable.cinemania;
            default:
                return R.drawable.cinemania; // Imagen predeterminada
        }
    }
}

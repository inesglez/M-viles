package com.example.responsivemiapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EditarPeliculaActivity extends AppCompatActivity {

    private EditText editTitulo, editSinopsis, editDuracion;
    private Spinner editGenero;
    private RatingBar editPuntuacion;
    private Button btnGuardar, btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_pelicula);

        // Inicializar vistas
        editTitulo = findViewById(R.id.editPeliculaTitulo);
        editSinopsis = findViewById(R.id.editPeliculaSinopsis);
        editDuracion = findViewById(R.id.editPeliculaDuracion);
        editGenero = findViewById(R.id.editPeliculaGenero);
        editPuntuacion = findViewById(R.id.editPeliculaPuntuacion);
        btnGuardar = findViewById(R.id.guardarEdicion);
        btnCancelar = findViewById(R.id.cancelarEdicion);

        // Obtener datos de la película seleccionada
        Intent intent = getIntent();
        String titulo = intent.getStringExtra("titulo");
        String sinopsis = intent.getStringExtra("sinopsis");
        String genero = intent.getStringExtra("genero");
        int duracion = intent.getIntExtra("duracion", 0);
        float puntuacion = intent.getFloatExtra("puntuacion", 0);

        // Llenar los campos con los datos actuales
        editTitulo.setText(titulo);
        editSinopsis.setText(sinopsis);
        editDuracion.setText(String.valueOf(duracion));
        editPuntuacion.setRating(puntuacion);

        // Listado de géneros fijo
        String[] generos = {"Acción", "Comedia", "Drama", "Ciencia Ficción", "Terror"};

        // Asignar el spinner con los géneros
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, generos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editGenero.setAdapter(adapter);

        // Seleccionar el género previamente asignado
        int generoPos = adapter.getPosition(genero);
        editGenero.setSelection(generoPos);

        // Configurar el botón de guardar
        btnGuardar.setOnClickListener(v -> {
            // Obtener los nuevos datos
            String nuevoTitulo = editTitulo.getText().toString();
            String nuevaSinopsis = editSinopsis.getText().toString();
            String nuevoGenero = editGenero.getSelectedItem().toString();
            int nuevaDuracion = Integer.parseInt(editDuracion.getText().toString());
            float nuevaPuntuacion = editPuntuacion.getRating();

            // Aquí puedes actualizar la película en la base de datos o en el modelo
            // Por ejemplo, si estás usando una base de datos:
            // db.updatePelicula(id, nuevoTitulo, nuevaSinopsis, nuevoGenero, nuevaDuracion, nuevaPuntuacion);

            // Mostrar un mensaje de éxito
            Toast.makeText(this, "Película editada con éxito", Toast.LENGTH_SHORT).show();
            finish();
        });

        // Configurar el botón de cancelar
        btnCancelar.setOnClickListener(v -> finish());
    }
}

package com.example.cineparte2;

import  android.annotation.SuppressLint;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
public class EntradasActivity extends AppCompatActivity {
    private Spinner spinnerPeliculas, spinnerHoras;
    private TextView textFecha;
    private Button btnComprar;
    private ImageView imagenPelicula;  // Imagen de la película
    private List<String> peliculas;
    private List<String> horariosDisponibles;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entradas);

        // Inicializar vistas
        spinnerPeliculas = findViewById(R.id.spinnerPeliculas);
        spinnerHoras = findViewById(R.id.spinnerHoras);
        textFecha = findViewById(R.id.textFecha);
        btnComprar = findViewById(R.id.btnComprar);
        imagenPelicula = findViewById(R.id.imagenPelicula);  // Obtener el ImageView

        // Inicializar datos de películas
        peliculas = new ArrayList<>();
        peliculas.add("La Infiltrada");
        peliculas.add("Gru, Mi Villano Favorito");
        peliculas.add("Smile");
        peliculas.add("Venom");
        peliculas.add("Joker");

        horariosDisponibles = new ArrayList<>();

        // Configurar adaptadores de los Spinners
        ArrayAdapter<String> adapterPeliculas = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, peliculas);
        adapterPeliculas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPeliculas.setAdapter(adapterPeliculas);

        ArrayAdapter<String> adapterHoras = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, horariosDisponibles);
        adapterHoras.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHoras.setAdapter(adapterHoras);

        // Listener para actualizar los horarios y la imagen al seleccionar una película
        spinnerPeliculas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String peliculaSeleccionada = peliculas.get(position);
                actualizarHorariosDisponibles(peliculaSeleccionada);
                mostrarImagenPelicula(peliculaSeleccionada);  // Cargar la imagen
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Configurar selector de fecha
        textFecha.setOnClickListener(v -> mostrarSelectorDeFecha());

        // Configurar botón de comprar
        btnComprar.setOnClickListener(v -> {
            String peliculaSeleccionada = spinnerPeliculas.getSelectedItem().toString();
            String horaSeleccionada = spinnerHoras.getSelectedItem() != null ? spinnerHoras.getSelectedItem().toString() : "";
            String fechaSeleccionada = textFecha.getText().toString();

            if (peliculaSeleccionada.isEmpty() || horaSeleccionada.isEmpty() || fechaSeleccionada.isEmpty()) {
                Toast.makeText(EntradasActivity.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Mostrar información de la compra
            Toast.makeText(EntradasActivity.this, "Entrada comprada:\n" +
                    "Película: " + peliculaSeleccionada + "\n" +
                    "Fecha: " + fechaSeleccionada + "\n" +
                    "Hora: " + horaSeleccionada, Toast.LENGTH_LONG).show();
        });
    }

    // Actualizar horarios disponibles según la película seleccionada
    private void actualizarHorariosDisponibles(String peliculaSeleccionada) {
        horariosDisponibles.clear();

        switch (peliculaSeleccionada) {
            case "La Infiltrada":
                horariosDisponibles.add("10:00 AM");
                horariosDisponibles.add("2:00 PM");
                horariosDisponibles.add("6:00 PM");
                break;
            case "Gru, Mi Villano Favorito":
                horariosDisponibles.add("9:00 AM");
                horariosDisponibles.add("1:00 PM");
                horariosDisponibles.add("5:00 PM");
                break;
            case "Smile":
                horariosDisponibles.add("11:00 AM");
                horariosDisponibles.add("3:00 PM");
                horariosDisponibles.add("7:00 PM");
                break;
            case "Venom":
                horariosDisponibles.add("12:00 PM");
                horariosDisponibles.add("4:00 PM");
                horariosDisponibles.add("8:00 PM");
                break;
            case "Joker":
                horariosDisponibles.add("1:00 PM");
                horariosDisponibles.add("5:00 PM");
                horariosDisponibles.add("9:00 PM");
                break;
        }

        // Notificar cambios al adaptador
        ArrayAdapter<String> adapterHoras = (ArrayAdapter<String>) spinnerHoras.getAdapter();
        if (adapterHoras != null) {
            adapterHoras.notifyDataSetChanged();
        }
    }

    // Mostrar imagen de la película seleccionada
    private void mostrarImagenPelicula(String peliculaSeleccionada) {
        int imagenResId = 0;
        switch (peliculaSeleccionada) {
            case "La Infiltrada":
                imagenResId = R.drawable.la_infiltrada;
                break;
            case "Gru, Mi Villano Favorito":
                imagenResId = R.drawable.gru;
                break;
            case "Smile":
                imagenResId = R.drawable.smile;
                break;
            case "Venom":
                imagenResId = R.drawable.venom;
                break;
            case "Joker":
                imagenResId = R.drawable.joker;
                break;
        }
        imagenPelicula.setImageResource(imagenResId);  // Establecer la imagen
    }

    // Mostrar selector de fecha
    private void mostrarSelectorDeFecha() {
        final Calendar calendario = Calendar.getInstance();
        int anio = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year, month, dayOfMonth) -> {
                    String fechaSeleccionada = dayOfMonth + "/" + (month + 1) + "/" + year;
                    textFecha.setText(fechaSeleccionada);
                }, anio, mes, dia);
        datePickerDialog.show();
    }
}

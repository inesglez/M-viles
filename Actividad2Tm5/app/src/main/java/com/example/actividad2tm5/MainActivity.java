package com.example.actividad2tm5;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private TextView textoSeleccionado;
    private Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        textoSeleccionado = findViewById(R.id.textoSeleccionado);
        boton = findViewById(R.id.boton);

        // Configurar el Spinner con una lista de opciones
        String[] opciones = {"Opción 1", "Opción 2", "Opción 3", "Opción 4"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Configurar el botón para mostrar el elemento seleccionado en el TextView
        boton.setOnClickListener(v -> {
            // Obtener el elemento seleccionado del Spinner
            String elementoSeleccionado = spinner.getSelectedItem().toString();
            // Mostrar el elemento seleccionado en el TextView
            textoSeleccionado.setText("Seleccionaste: " + elementoSeleccionado);
        });
    }
}

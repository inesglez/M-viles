package com.example.actividad6_3;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerCountries;
    private TextView textViewSelectedCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerCountries = findViewById(R.id.spinnerCountries);
        textViewSelectedCountry = findViewById(R.id.textViewSelectedCountry);

        // Array de países europeos
        String[] countries = {
                "España", "Francia", "Italia", "Alemania", "Portugal",
                "Países Bajos", "Suiza", "Bélgica", "Suecia", "Noruega"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                countries
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerCountries.setAdapter(adapter);

        spinnerCountries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Obtener el país seleccionado
                String selectedCountry = countries[position];

                // Actualizar el TextView con el país seleccionado
                textViewSelectedCountry.setText("País seleccionado: " + selectedCountry);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Si no hay selección, podemos dejar el TextView en blanco o con un mensaje por defecto
                textViewSelectedCountry.setText("Seleccione un país");
            }
        });
    }
}
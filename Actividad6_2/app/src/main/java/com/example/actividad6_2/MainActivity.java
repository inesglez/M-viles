package com.example.actividad6_2;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private GridView gridViewCountries;
    private TextView textViewSelectedCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridViewCountries = findViewById(R.id.gridViewCountries);
        textViewSelectedCountry = findViewById(R.id.textViewSelectedCountry);

        // Array de países europeos
        String[] countries = {
                "España", "Francia", "Italia", "Alemania", "Portugal",
                "Países Bajos", "Suiza", "Bélgica", "Suecia", "Noruega"
        };

        // Adaptador para el GridView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                countries
        );

        gridViewCountries.setAdapter(adapter);

        // Configuramos el evento de clic para los elementos del GridView
        gridViewCountries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtener el país seleccionado
                String selectedCountry = countries[position];

                // Actualizar el TextView con el país seleccionado
                textViewSelectedCountry.setText("País seleccionado: " + selectedCountry);
            }
        });
    }
}
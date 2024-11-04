package com.example.actividad6_1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ListView listViewCountries;
    private TextView textViewSelectedCountry;
    private EditText editTextCountry;
    private Button buttonAddCountry, buttonRemoveCountry;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> countriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializamos los elementos del layout
        listViewCountries = findViewById(R.id.listViewCountries);
        textViewSelectedCountry = findViewById(R.id.textViewSelectedCountry);
        editTextCountry = findViewById(R.id.editTextCountry);
        buttonAddCountry = findViewById(R.id.buttonAddCountry);
        buttonRemoveCountry = findViewById(R.id.buttonRemoveCountry);

        // Array de países europeos
        String[] countries = {
                "España", "Francia", "Italia", "Alemania", "Portugal",
                "Países Bajos", "Suiza", "Bélgica", "Suecia", "Noruega"
        };

        countriesList = new ArrayList<>(Arrays.asList(countries));
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, countriesList);

        listViewCountries.setAdapter(adapter);

        listViewCountries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtener el país seleccionado
                String selectedCountry = countriesList.get(position);
                // Actualizar el TextView con el país seleccionado
                textViewSelectedCountry.setText("País seleccionado: " + selectedCountry);
            }
        });

        // Configuramos el botón de agregar país
        buttonAddCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newCountry = editTextCountry.getText().toString().trim();
                if (!newCountry.isEmpty() && !countriesList.contains(newCountry)) {
                    countriesList.add(newCountry);
                    adapter.notifyDataSetChanged();
                    editTextCountry.setText(""); 
                    Toast.makeText(MainActivity.this, newCountry + " agregado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "El país ya existe o el nombre está vacío", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Configuramos el botón de eliminar país
        buttonRemoveCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String countryToRemove = editTextCountry.getText().toString().trim();
                if (countriesList.contains(countryToRemove)) {
                    countriesList.remove(countryToRemove);
                    adapter.notifyDataSetChanged();
                    editTextCountry.setText(""); // Limpiar el campo de texto
                    Toast.makeText(MainActivity.this, countryToRemove + " eliminado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "El país no se encuentra en la lista", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

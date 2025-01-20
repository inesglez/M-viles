package com.example.responsivemiapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements ListaFragment.Callbacks {

    private boolean enModoDual = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Comprobar si está en modo dual
        if (findViewById(R.id.contenedor_detalle) != null) {
            enModoDual = true;
        }

        // Cargar el fragmento de lista en el contenedor principal si no se ha cargado aún
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedor_principal, new ListaFragment())
                    .commit();
        }
    }

    @Override
    public void onItemSelected(Pelicula pelicula) {
        if (enModoDual) {
            // Modo dual: cargar DetalleFragment en el contenedor_detalle
            DetalleFragment detalleFragment = DetalleFragment.newInstance(pelicula);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenedor_detalle, detalleFragment)
                    .commit();
        } else {
            // Modo de un solo panel: cargar DetalleFragment en el contenedor_principal
            DetalleFragment detalleFragment = DetalleFragment.newInstance(pelicula);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenedor_principal, detalleFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}

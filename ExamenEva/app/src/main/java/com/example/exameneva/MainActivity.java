package com.example.exameneva;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements FragmentoEntradaTexto.EnviarTextoListener {

    private FragmentoVisualizacion fragmentoVisualizacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Agregar el Fragmento de Entrada
        FragmentoEntradaTexto fragmentoEntradaTexto = new FragmentoEntradaTexto();
        fragmentoEntradaTexto.setEnviarTextoListener(this);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contenedorEntrada, fragmentoEntradaTexto)
                .commit();

        // Agregar el Fragmento de Visualización
        fragmentoVisualizacion = new FragmentoVisualizacion();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contenedorVisualizacion, fragmentoVisualizacion)
                .commit();
    }

    @Override
    public void onTextoEnviado(String texto) {
        fragmentoVisualizacion.actualizarTexto(texto);
    }
}

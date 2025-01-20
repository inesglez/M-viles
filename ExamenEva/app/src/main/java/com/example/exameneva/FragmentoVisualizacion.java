package com.example.exameneva;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentoVisualizacion extends Fragment {

    private TextView textoVista;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragmento_visualizacion, container, false);
        textoVista = vista.findViewById(R.id.textoVista);
        return vista;
    }

    public void actualizarTexto(String texto) {
        if (textoVista != null) {
            textoVista.setText(texto);
        }
    }
}

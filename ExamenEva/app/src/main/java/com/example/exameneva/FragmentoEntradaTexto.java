package com.example.exameneva;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentoEntradaTexto extends Fragment {

    private EnviarTextoListener listener;

    public interface EnviarTextoListener {
        void onTextoEnviado(String texto);
    }

    public void setEnviarTextoListener(EnviarTextoListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragmento_entrada, container, false);

        EditText campoTexto = vista.findViewById(R.id.campoTexto);
        Button botonEnviar = vista.findViewById(R.id.botonEnviar);

        botonEnviar.setOnClickListener(v -> {
            if (listener != null) {
                String texto = campoTexto.getText().toString();
                listener.onTextoEnviado(texto);
            }
        });

        return vista;
    }
}

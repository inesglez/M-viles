package com.example.actividad9_4;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;

public class Fragmento1 extends ListFragment {

    private Callbacks mCallbacks = Callbacks.CallbacksVacios;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new Adaptador(getActivity(), R.layout.layout_listado, Contenido.ENT_LISTA) {
            @Override
            public void onEntrada(Object entrada, View view) {
                // Obtén el TextView y el ImageView del diseño
                TextView texto_superior_entrada = view.findViewById(R.id.textoTitulo);
                if (texto_superior_entrada != null) {
                    texto_superior_entrada.setText(((Contenido.Lista_entrada) entrada).textoEncima);
                } else {
                    Log.e("Fragmento1", "textoTitulo no encontrado");
                }

                ImageView imagen_entrada = view.findViewById(R.id.imagenlista);
                if (imagen_entrada != null) {
                    imagen_entrada.setImageResource(((Contenido.Lista_entrada) entrada).idImagen);
                } else {
                    Log.e("Fragmento1", "imagenlista no encontrada");
                }
            }
        });
    }

    public interface Callbacks {
        Callbacks CallbacksVacios = new Callbacks() {
            @Override
            public void onEntradaSeleccionada(String id) {
                // Implementación vacía
            }
        };

        void onEntradaSeleccionada(String id);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Callbacks) {
            mCallbacks = (Callbacks) context;
        } else {
            throw new ClassCastException(context.toString() + " debe implementar Callbacks");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = Callbacks.CallbacksVacios;
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        mCallbacks.onEntradaSeleccionada(Contenido.ENT_LISTA.get(position).id);
    }
}

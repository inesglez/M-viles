package com.example.responsivemiapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListaFragment extends Fragment {

    private RecyclerView recyclerView;
    private PeliculaAdapter peliculaAdapter;
    private List<Pelicula> peliculaList;
    private Callbacks mCallbacks;

    public interface Callbacks {
        void onItemSelected(Pelicula pelicula);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Callbacks) {
            mCallbacks = (Callbacks) context;
        } else {
            throw new RuntimeException(context.toString() + " debe implementar Callbacks.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewCartelera);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Crear la lista de películas
        peliculaList = new ArrayList<>();
        peliculaList.add(new Pelicula("La Infiltrada", "Una espía encubierta se infiltra en una peligrosa red criminal.", "Acción", 120, 4.5f, R.drawable.la_infiltrada));
        peliculaList.add(new Pelicula("Gru, Mi Villano Favorito", "Gru planea el mayor atraco de la historia, mientras cría a tres niñas huérfanas.", "Animación", 95, 4.7f, R.drawable.gru));
        peliculaList.add(new Pelicula("Smile", "Una joven psiquiatra enfrenta aterradoras visiones tras un evento traumático.", "Terror", 110, 4.0f, R.drawable.smile));
        peliculaList.add(new Pelicula("Venom", "Un periodista adquiere poderes tras fusionarse con un simbionte alienígena.", "Acción/Sci-Fi", 112, 3.8f, R.drawable.venom));
        peliculaList.add(new Pelicula("Joker", "Historia oscura sobre la evolución de Arthur Fleck en el icónico Joker.", "Drama", 122, 1.5f, R.drawable.joker));

        // Configurar el adaptador con el contexto y la lista de películas
        peliculaAdapter = new PeliculaAdapter(peliculaList, pelicula -> {
            if (mCallbacks != null) {
                mCallbacks.onItemSelected(pelicula);
            }
        });

        recyclerView.setAdapter(peliculaAdapter);

        return view;
    }
}

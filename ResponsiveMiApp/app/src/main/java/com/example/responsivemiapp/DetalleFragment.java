package com.example.responsivemiapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DetalleFragment extends Fragment {

    public static final String ARG_PELICULA = "pelicula";

    private Pelicula pelicula;

    public static DetalleFragment newInstance(Pelicula pelicula) {
        DetalleFragment fragment = new DetalleFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PELICULA, pelicula);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle, container, false);

        if (getArguments() != null) {
            pelicula = (Pelicula) getArguments().getSerializable(ARG_PELICULA);
        }

        if (pelicula != null) {
            ImageView imagen = view.findViewById(R.id.detallePeliculaImagen);
            TextView titulo = view.findViewById(R.id.detallePeliculaTitulo);
            TextView sinopsis = view.findViewById(R.id.detallePeliculaSinopsis);
            TextView genero = view.findViewById(R.id.detallePeliculaGenero);
            TextView duracion = view.findViewById(R.id.detallePeliculaDuracion);
            RatingBar puntuacion = view.findViewById(R.id.detallePeliculaPuntuacion);

            imagen.setImageResource(pelicula.getImagenResId());
            titulo.setText(pelicula.getTitulo());
            sinopsis.setText(pelicula.getSinopsis());
            genero.setText(pelicula.getGenero());
            duracion.setText(pelicula.getDuracion() + " min");
            puntuacion.setRating(pelicula.getPuntuacion());
        }

        return view;
    }
}

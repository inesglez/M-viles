package com.example.responsivemiapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PeliculaAdapter extends RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder> {

    private List<Pelicula> peliculas;
    private OnPeliculaClickListener listener;

    // Constructor
    public PeliculaAdapter(List<Pelicula> peliculas, OnPeliculaClickListener listener) {
        this.peliculas = peliculas;
        this.listener = listener;
    }

    @Override
    public PeliculaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Infla el layout para cada ítem de la lista
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pelicula, parent, false);
        return new PeliculaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PeliculaViewHolder holder, int position) {
        // Establecer los datos de la película en las vistas
        Pelicula pelicula = peliculas.get(position);
        holder.titulo.setText(pelicula.getTitulo());
        holder.genero.setText(pelicula.getGenero());
        holder.imagen.setImageResource(pelicula.getImagenResId());
        holder.ratingBar.setRating(pelicula.getPuntuacion());

        // Manejar el clic en el item
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemSelected(pelicula);
            }
        });
    }

    @Override
    public int getItemCount() {
        return peliculas.size();
    }

    // Interface para manejar el clic en los elementos de la lista
    public interface OnPeliculaClickListener {
        void onItemSelected(Pelicula pelicula);
    }

    // ViewHolder para manejar las vistas
    public static class PeliculaViewHolder extends RecyclerView.ViewHolder {
        TextView titulo;
        TextView genero;
        ImageView imagen;
        RatingBar ratingBar;

        public PeliculaViewHolder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.peliculaTitulo);
            genero = itemView.findViewById(R.id.peliculaGenero);
            imagen = itemView.findViewById(R.id.peliculaImagen);
            ratingBar = itemView.findViewById(R.id.peliculaPuntuacion);
        }
    }
}

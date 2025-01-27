package com.example.cineparte2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PeliculaAdapter extends RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder> {

    private Context context;
    private List<Pelicula> peliculas;

    // Constructor
    public PeliculaAdapter(Context context, List<Pelicula> peliculas) {
        this.context = context;
        this.peliculas = peliculas;
    }

    @NonNull
    @Override
    public PeliculaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pelicula, parent, false);
        return new PeliculaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeliculaViewHolder holder, int position) {
        Pelicula pelicula = peliculas.get(position);

        holder.titulo.setText(pelicula.getTitulo());
        holder.genero.setText(pelicula.getGenero());
        holder.puntuacion.setRating(pelicula.getPuntuacion());

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), pelicula.getImagenResId());
        holder.imagen.setImageBitmap(bitmap);

        // Clic para ver detalles de la película
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetallePeliculaActivity.class);
            intent.putExtra("titulo", pelicula.getTitulo());
            intent.putExtra("sinopsis", pelicula.getSinopsis());
            intent.putExtra("genero", pelicula.getGenero());
            intent.putExtra("duracion", pelicula.getDuracion());
            intent.putExtra("puntuacion", pelicula.getPuntuacion());
            intent.putExtra("imagenResId", pelicula.getImagenResId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return peliculas.size();
    }

    public static class PeliculaViewHolder extends RecyclerView.ViewHolder {

        ImageView imagen;
        TextView titulo;
        TextView genero;
        RatingBar puntuacion;

        public PeliculaViewHolder(View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.peliculaImagen);
            titulo = itemView.findViewById(R.id.peliculaTitulo);
            genero = itemView.findViewById(R.id.peliculaGenero);
            puntuacion = itemView.findViewById(R.id.peliculaPuntuacion);
        }
    }
}

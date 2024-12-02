package com.example.cine;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
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
import android.app.AlertDialog;
import android.widget.Toast;

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

        Bitmap bitmap = decodeSampledBitmapFromResource(context.getResources(), pelicula.getImagenResId(), 100, 150);
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

        // Clic largo para mostrar menú de edición y eliminación
        holder.itemView.setOnLongClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Acciones sobre la Película")
                    .setItems(new CharSequence[]{"Editar", "Eliminar"}, (dialog, which) -> {
                        if (which == 0) {
                            // Acción de Editar
                            Intent intent = new Intent(context, EditarPeliculaActivity.class);
                            intent.putExtra("titulo", pelicula.getTitulo());
                            intent.putExtra("sinopsis", pelicula.getSinopsis());
                            intent.putExtra("genero", pelicula.getGenero());
                            intent.putExtra("duracion", pelicula.getDuracion());
                            intent.putExtra("puntuacion", pelicula.getPuntuacion());
                            intent.putExtra("imagenResId", pelicula.getImagenResId());
                            context.startActivity(intent);
                        } else if (which == 1) {
                            // Acción de Eliminar con confirmación
                            mostrarDialogoConfirmacion(position);
                        }
                    })
                    .show();
            return true; // Indicar que el evento ha sido consumido
        });
    }

    @Override
    public int getItemCount() {
        return peliculas.size();
    }

    // Método para mostrar un diálogo de confirmación antes de eliminar
    private void mostrarDialogoConfirmacion(int position) {
        new AlertDialog.Builder(context)
                .setTitle("Confirmar eliminación")
                .setMessage("¿Estás seguro de que deseas eliminar esta película?")
                .setPositiveButton("Sí", (dialog, which) -> {
                    // Si el usuario confirma, eliminar la película
                    eliminarPelicula(position);
                })
                .setNegativeButton("No", (dialog, which) -> {
                    // Si el usuario cancela, cerrar el cuadro de diálogo
                    dialog.dismiss();
                })
                .show();
    }

    // Método para eliminar una película
    private void eliminarPelicula(int position) {
        peliculas.remove(position); // Eliminar de la lista
        notifyItemRemoved(position); // Notificar al RecyclerView
        notifyItemRangeChanged(position, peliculas.size()); // Actualizar el rango de los elementos
        Toast.makeText(context, "Película eliminada", Toast.LENGTH_SHORT).show();
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

    public Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        final int inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        options.inJustDecodeBounds = false;
        options.inSampleSize = inSampleSize;

        return BitmapFactory.decodeResource(res, resId, options);
    }

    public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
}

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
        // Inflar el layout de cada item
        View view = LayoutInflater.from(context).inflate(R.layout.item_pelicula, parent, false);
        return new PeliculaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeliculaViewHolder holder, int position) {
        // Obtener el objeto pelicula en la posición actual
        Pelicula pelicula = peliculas.get(position);

        // Establecer los datos en los elementos de la vista
        holder.titulo.setText(pelicula.getTitulo());
        holder.genero.setText(pelicula.getGenero());
        holder.puntuacion.setRating(pelicula.getPuntuacion());

        // Cargar la imagen desde el recurso con redimensionamiento
        Bitmap bitmap = decodeSampledBitmapFromResource(context.getResources(), pelicula.getImagenResId(), 100, 150); // Redimensionamos a 100x150
        holder.imagen.setImageBitmap(bitmap);

        // Configurar el clic para abrir la pantalla de detalles
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
        // Devuelve el número total de elementos en la lista de películas
        return peliculas.size();
    }

    // ViewHolder para los elementos de la lista
    public static class PeliculaViewHolder extends RecyclerView.ViewHolder {

        // Elementos del layout item_pelicula.xml con los nuevos IDs
        ImageView imagen;
        TextView titulo;
        TextView genero;
        RatingBar puntuacion;

        // Constructor
        public PeliculaViewHolder(View itemView) {
            super(itemView);

            imagen = itemView.findViewById(R.id.peliculaImagen);
            titulo = itemView.findViewById(R.id.peliculaTitulo);
            genero = itemView.findViewById(R.id.peliculaGenero);
            puntuacion = itemView.findViewById(R.id.peliculaPuntuacion);
        }
    }

    // Método para redimensionar la imagen antes de cargarla
    public Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        // Primero, decodificar solo la información del tamaño (sin cargar la imagen en memoria)
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calcular el factor de escala adecuado
        final int inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decodificar la imagen real con el tamaño ajustado
        options.inJustDecodeBounds = false;
        options.inSampleSize = inSampleSize;

        return BitmapFactory.decodeResource(res, resId, options);
    }

    // Método para calcular el tamaño de la muestra
    public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Calcular el tamaño de muestra más cercano para ajustarse a los límites
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Asegurarse de que la imagen se ajusta al tamaño necesario
            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
}

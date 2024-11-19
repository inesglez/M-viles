package com.example.ines_prueba3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder> {

    private final ArrayList<HashMap<String, Object>> animales;
    private final Context context;

    public AnimalAdapter(Context context, ArrayList<HashMap<String, Object>> animales) {
        this.context = context;
        this.animales = animales;
    }

    @NonNull
    @Override
    public AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_animal, parent, false);
        return new AnimalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalViewHolder holder, int position) {
        HashMap<String, Object> animal = animales.get(position);
        holder.imageView.setImageResource((int) animal.get("imagen"));
        holder.textViewNombre.setText((String) animal.get("nombre"));
        holder.textViewDescripcion.setText((String) animal.get("descripcionBreve"));

        // Evento de clic: Mostrar descripción completa
        holder.itemView.setOnClickListener(v -> {
            String descripcionCompleta = (String) animal.get("descripcionCompleta");
            Toast.makeText(context, descripcionCompleta, Toast.LENGTH_LONG).show();
        });

        // Evento de clic largo: Eliminar elemento
        holder.itemView.setOnLongClickListener(v -> {
            animales.remove(position);
            notifyItemRemoved(position);
            Toast.makeText(context, "Animal eliminado", Toast.LENGTH_SHORT).show();
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return animales.size();
    }

    public static class AnimalViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewNombre, textViewDescripcion;

        public AnimalViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textViewNombre = itemView.findViewById(R.id.textViewNombre);
            textViewDescripcion = itemView.findViewById(R.id.textViewDescripcion);
        }
    }
}

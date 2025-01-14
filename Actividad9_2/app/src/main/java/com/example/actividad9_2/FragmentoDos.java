package com.example.actividad9_2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentoDos extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // Crear una vista programáticamente para este fragmento
        TextView textView = new TextView(getContext());
        textView.setText("Fragmento Dos");
        textView.setTextSize(20f);
        textView.setTextColor(getResources().getColor(android.R.color.white));
        textView.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
        textView.setGravity(View.TEXT_ALIGNMENT_CENTER);

        return textView;
    }
}

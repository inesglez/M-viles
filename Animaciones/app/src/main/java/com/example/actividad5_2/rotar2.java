package com.example.actividad5_2;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class rotar2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotar2);

        // Cargar la vista de la imagen
        ImageView imageView = findViewById(R.id.imageView2);

        // Cargar la animación de rotación
        Animation rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotar2);

        // Iniciar la animación
        imageView.startAnimation(rotateAnimation);
    }
}
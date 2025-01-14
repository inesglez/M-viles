package com.example.actividad9_2;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Crear el contenedor principal con un LinearLayout
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ));

        // Crear los contenedores para los fragmentos con IDs
        LinearLayout.LayoutParams fragmentoParametrosUno = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 0, 1);
        LinearLayout.LayoutParams fragmentoParametrosDos = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 0, 2);

        LinearLayout contenedorFragmentoUno = new LinearLayout(this);
        contenedorFragmentoUno.setId(View.generateViewId());
        contenedorFragmentoUno.setLayoutParams(fragmentoParametrosUno);

        LinearLayout contenedorFragmentoDos = new LinearLayout(this);
        contenedorFragmentoDos.setId(View.generateViewId());
        contenedorFragmentoDos.setLayoutParams(fragmentoParametrosDos);

        // Agregar los contenedores al LinearLayout principal
        mainLayout.addView(contenedorFragmentoUno);
        mainLayout.addView(contenedorFragmentoDos);

        // Establecer el LinearLayout principal como vista de la actividad
        setContentView(mainLayout);

        // Añadir los fragmentos a sus respectivos contenedores
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(contenedorFragmentoUno.getId(), new FragmentoUno());
        transaction.add(contenedorFragmentoDos.getId(), new FragmentoDos());
        transaction.commit();
    }
}

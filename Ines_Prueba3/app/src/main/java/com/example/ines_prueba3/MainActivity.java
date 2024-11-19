package com.example.ines_prueba3;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<HashMap<String, Object>> listaDatosAnimales;
    AnimalAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Datos de los animales
        String[] nombresAnimales = {"BUHO", "COLIBRÍ", "CUERVO", "FLAMENCO", "KIWI", "LORO", "PAVO", "PINGÜINO"};
        int[] imagenesAnimales = {R.drawable.im_buho, R.drawable.im_colibri, R.drawable.im_cuervo,
                R.drawable.im_flamenco, R.drawable.im_kiwi, R.drawable.im_loro,
                R.drawable.im_pavo, R.drawable.im_pinguino};
        String[] descripcionesBreves = {
                "Ave nocturna con plumas como orejas.",
                "Pequeña ave de colores brillantes.",
                "Ave negra e inteligente, común en todo el mundo.",
                "Aves con patas largas y plumaje rosa.",
                "Ave sin alas originaria de Nueva Zelanda.",
                "Ave exótica y colorida, incluye loros y guacamayos.",
                "Ave galliforme con plumaje brillante.",
                "Ave marina no voladora del hemisferio sur."
        };
        String[] descripcionesCompletas = {
                "Descripción completa del BÚHO: Búho es el nombre común de aves de la familia Strigidae, del orden de las estrigiformes o aves rapaces nocturnas. Habitualmente designa especies que, a diferencia de las lechuzas, tienen plumas alzadas que parecen orejas",
                "Descripción completa del COLIBRÍ:Los troquilinos (Trochilinae) son una subfamilia de aves apodiformes de la familia Trochilidae, conocidas vulgarmente como colibríes, quindes, tucusitos, picaflores, chupamirtos, chuparrosas, huichichiquis (idioma nahuatl), mainumby (idioma guaraní) o guanumby. Conjuntamente con las ermitas, que pertenecen a la subfamilia Phaethornithinae, conforman la familia Trochilidae que, en la sistemática de Charles Sibley, se clasifica en un orden propio: Trochiliformes, independiente de los vencejos del orden Apodiformes. La subfamilia Trochilinae incluye más de 100 géneros que comprenden un total de 330 a 340 especies",
                "Descripción completa del CUERVO: El cuervo común (Corvus corax) es una especie de ave paseriforme de la familia de los córvidos (Corvidae). Presente en todo el hemisferio septentrional, es la especie de córvido con la mayor superficie de distribución. Con el cuervo de pico grueso, es el mayor de los córvidos y probablemente la paseriforme más pesada; en su madurez, el cuervo común mide entre 52 y 69 centímetros de longitud y su peso varía de 0,69 a 1,7 kilogramos. Los cuervos comunes viven generalmente de 10 a 15 años pero algunos individuos han vivido 40 años. Los juveniles pueden desplazarse en grupos pero las parejas ya formadas permanecen juntas toda su vida, cada pareja defendiendo un territorio. Existen 8 subespecies conocidas que se diferencian muy poco aparentemente, aunque estudios recientes hayan demostrado diferencias genéticas significativas entre las poblaciones de distintas regiones.",
                "Descripción completa del FLAMENCO: Los fenicopteriformes (Phoenicopteriformes), los cuales reciben el nombre vulgar de flamencos, son un orden de aves neognatas, con un único género viviente: Phoenicopterus. Son aves que se distribuyen tanto por el hemisferio occidental como por el hemisferio oriental: existen cuatro especies en América y dos en el Viejo Mundo. Tienen cráneo desmognato holorrino, con 16 a 20 vértebras cervicales y pies anisodáctilos..",
                "Descripción completa del KIWI: Los kiwis (Apterix, gr. 'sin alas') son un género de aves paleognatas compuesto por cinco especies endémicas de Nueva Zelanda.1 2 Son aves no voladoras pequeñas, aproximadamente del tamaño de una gallina. Antes de la llegada de los humanos alrededor del año 1300, en Nueva Zelanda los únicos mamíferos que había eran murciélagos, y los nichos ecológicos que en otras partes del mundo eran ocupados por animales tan diversos como caballos, lobos y ratones fueron utilizados en Nueva Zelanda por pájaros (y en menor proporción por ciertas especies de reptiles). La denominación kiwi es maorí, idioma del pueblo homónimo de linaje malayopolinesio que colonizó Nueva Zelanda antes de la llegada de los europeos.",
                "Descripción completa del LORO: Las Psitácidas (Psittacidae) son una familia de aves psitaciformes llamadas comúnmente loros o papagayos, e incluye a los guacamayos, las cotorras, los periquitos, los agapornis y formas afines..",
                "Descripción completa del PAVO: Pavo es un género de aves galliformes de la familia Phasianidae, que incluye dos especies, el pavo real común (Pavo cristatus) y el pavo real cuelliverde (Pavo muticus).1\"",
                "Descripción completa del PINGÜINO: Los pingüinos (familia Spheniscidae, orden Sphenisciformes) son un grupo de aves marinas, no voladoras, que se distribuyen únicamente en el Hemisferio Sur, sobre todo en sus altas latitudes"
        };

        // Validar que los arreglos tengan la misma longitud
        if (nombresAnimales.length != imagenesAnimales.length ||
                nombresAnimales.length != descripcionesBreves.length ||
                nombresAnimales.length != descripcionesCompletas.length) {
            Toast.makeText(this, "Error: Los datos no están completos o no son consistentes.", Toast.LENGTH_LONG).show();
            return;
        }

        listaDatosAnimales = new ArrayList<>();
        for (int i = 0; i < nombresAnimales.length; i++) {
            HashMap<String, Object> mapa = new HashMap<>();
            mapa.put("nombre", nombresAnimales[i]);
            mapa.put("imagen", imagenesAnimales[i]);
            mapa.put("descripcionBreve", descripcionesBreves[i]);
            mapa.put("descripcionCompleta", descripcionesCompletas[i]);
            listaDatosAnimales.add(mapa);
        }

        adaptador = new AnimalAdapter(this, listaDatosAnimales);
        recyclerView.setAdapter(adaptador);
    }
}

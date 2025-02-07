package com.example.propuesta8_2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class Adapter extends FragmentStatePagerAdapter {
    int numeroTab;

    public Adapter(@NonNull FragmentManager fm, int numeroTab) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.numeroTab = numeroTab;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new PrimeraPantalla();
        } else if (position == 1){
            return new SegundaPantalla();
        } else if (position == 2){
            return new TerceraPantalla();
        } else {
            throw new IllegalArgumentException("Posición no válida" + position);
        }

    }

    @Override
    public int getCount() {
        return numeroTab;
    }
}

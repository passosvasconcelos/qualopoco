package com.gse.pdep.qualopoco3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TelaInicialFragment extends Fragment {
    //private FloatingActionButton btnSincronizar;
    //MainActivity mainActivity = new MainActivity();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tela_inicial, container, false);

        /*btnSincronizar = (FloatingActionButton) view.findViewById(R.id.fab_sincronizar);
        btnSincronizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sincronizar();
            }
        });*/
        return  view;

    }


    public static TelaInicialFragment newInstance() {
        return new TelaInicialFragment();
    }
}

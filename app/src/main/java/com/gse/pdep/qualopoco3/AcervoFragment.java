package com.gse.pdep.qualopoco3;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class AcervoFragment extends Fragment {
    private Button btnAlaI33;
    private Button btnAlaI27;
    private Button btnAlaE01;
    private Button btnPredio20;
    private Button btnTorgua;
    private Button btnParque;
    public String laboratorio;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_acervo, container, false);
        btnAlaI33 = (Button) view.findViewById(R.id.btnAlaI33);
        btnAlaI27 = (Button) view.findViewById(R.id.btnAlaI27);
        btnAlaE01 = (Button) view.findViewById(R.id.btnAlaE01);
        btnPredio20 = (Button) view.findViewById(R.id.btnPredio20);
        btnTorgua = (Button) view.findViewById(R.id.btnTorgua);
        btnParque = (Button) view.findViewById(R.id.btnParque);

        btnAlaI33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentEnviadora= new Intent(getActivity(), ListaCaixasLab.class);
                laboratorio = "I33";
                intentEnviadora.putExtra("laboratorio", laboratorio);
                startActivity(intentEnviadora);
            }
        });
        btnAlaI27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentEnviadora= new Intent(getActivity(), ListaCaixasLab.class);
                laboratorio = "I27";
                intentEnviadora.putExtra("laboratorio", laboratorio);
                startActivity(intentEnviadora);
            }
        });
        btnAlaE01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentEnviadora= new Intent(getActivity(), ListaCaixasLab.class);
                laboratorio = "AlaE";
                intentEnviadora.putExtra("laboratorio", laboratorio);
                startActivity(intentEnviadora);
            }
        });
        btnPredio20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentEnviadora= new Intent(getActivity(), ListaCaixasLab.class);
                laboratorio = "P20";
                intentEnviadora.putExtra("laboratorio", laboratorio);
                startActivity(intentEnviadora);
            }
        });
        btnParque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentEnviadora= new Intent(getActivity(), ListaCaixasLab.class);
                laboratorio = "Parque";
                intentEnviadora.putExtra("laboratorio", laboratorio);
                startActivity(intentEnviadora);
            }
        });
        btnTorgua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentEnviadora= new Intent(getActivity(), ListaCaixasLab.class);
                laboratorio = "Litoteca";
                intentEnviadora.putExtra("laboratorio", laboratorio);
                startActivity(intentEnviadora);
            }
        });
        return  view;
    }


    public static AcervoFragment newInstance() {
        return new AcervoFragment();
    }
}


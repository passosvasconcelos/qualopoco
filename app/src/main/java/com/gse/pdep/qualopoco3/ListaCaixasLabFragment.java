package com.gse.pdep.qualopoco3;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class ListaCaixasLabFragment extends Fragment {
    private ArrayList<String[]> listaCaixasLab;
    private String[] itemListaCaixasLab;
    private RecyclerView mRecyclerView;

    private ListaCaixasLabAdapter mListaCaixasLabAdapter;
    private String laboratorio;
    public String movimentacao;


    private TextView mErrorMessageDisplay;
    private TextView ncaixas;
    //private ProgressBar mLoadingIndicator;

    public String getLaboratorio() {
        SharedPreferences lab = PreferenceManager.getDefaultSharedPreferences(getContext());
        laboratorio = lab.getString("laboratorio", "nulo");
        return laboratorio;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_listacaixaslab, container, false);

        SQLiteDatabase db = getActivity().openOrCreateDatabase("LabGSEdb", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Historico (poco VARCHAR, tipoAmostra VARCHAR, topo VARCHAR, base VARCHAR, caixa VARCHAR, endereco VARCHAR, data VARCHAR, hora VARCHAR, movimentacao VARCHAR, laboratorio VARCHAR,  localizacao VARCHAR, detalheTestemunho VARCHAR, usuario VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS I33 (poco VARCHAR, tipoAmostra VARCHAR, topo VARCHAR, base VARCHAR, caixa VARCHAR, endereco VARCHAR, data VARCHAR, hora VARCHAR,  localizacao VARCHAR, detalheTestemunho VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS I27 (poco VARCHAR, tipoAmostra VARCHAR, topo VARCHAR, base VARCHAR, caixa VARCHAR, endereco VARCHAR, data VARCHAR, hora VARCHAR,  localizacao VARCHAR, detalheTestemunho VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS AlaE (poco VARCHAR, tipoAmostra VARCHAR, topo VARCHAR, base VARCHAR, caixa VARCHAR, endereco VARCHAR, data VARCHAR, hora VARCHAR,  localizacao VARCHAR, detalheTestemunho VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS P20 (poco VARCHAR, tipoAmostra VARCHAR, topo VARCHAR, base VARCHAR, caixa VARCHAR, endereco VARCHAR, data VARCHAR, hora VARCHAR,  localizacao VARCHAR, detalheTestemunho VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS Parque (poco VARCHAR, tipoAmostra VARCHAR, topo VARCHAR, base VARCHAR, caixa VARCHAR, endereco VARCHAR, data VARCHAR, hora VARCHAR,  localizacao VARCHAR, detalheTestemunho VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS Litoteca (poco VARCHAR, tipoAmostra VARCHAR, topo VARCHAR, base VARCHAR, caixa VARCHAR, endereco VARCHAR, data VARCHAR, hora VARCHAR,  localizacao VARCHAR, detalheTestemunho VARCHAR);");




        itemListaCaixasLab = new String[7];
        listaCaixasLab = new ArrayList<String[]>(1000);
        getLaboratorio();
        switch (laboratorio) {
            case "I33":
                System.out.println("entrou no case i33 -----------------------");
                Cursor c = db.rawQuery("SELECT * FROM I33 LIMIT 1,999999999999999999", null);
                while (c.moveToNext()){
                    itemListaCaixasLab[0] = (c.getString(0)); //poço
                    itemListaCaixasLab[1] = (c.getString(1)); //tipoAmostra
                    itemListaCaixasLab[2] = (c.getString(9).substring(0,3)); //detalheTestemunho. apenas # e número
                    itemListaCaixasLab[3] = (c.getString(4)); //caixa
                    itemListaCaixasLab[4] = (c.getString(6)); //data
                    itemListaCaixasLab[5] = (c.getString(7)); //hora
                    itemListaCaixasLab[6] = (c.getString(8)); //localizacao

                    listaCaixasLab.add(itemListaCaixasLab.clone());

                }
                break;
            case "I27":
                c = db.rawQuery("SELECT * FROM I27 LIMIT 1,999999999999999999", null);
                while (c.moveToNext()){

                    itemListaCaixasLab[0] = (c.getString(0)); //poço
                    itemListaCaixasLab[1] = (c.getString(1)); //tipoAmostra
                    itemListaCaixasLab[2] = (c.getString(9).substring(0,3)); //detalheTestemunho. apenas # e número
                    itemListaCaixasLab[3] = (c.getString(4)); //caixa
                    itemListaCaixasLab[4] = (c.getString(6)); //data
                    itemListaCaixasLab[5] = (c.getString(7)); //hora
                    itemListaCaixasLab[6] = (c.getString(8)); //localizacao

                    listaCaixasLab.add(itemListaCaixasLab.clone());
                }
                break;
            case "AlaE":
                c = db.rawQuery("SELECT * FROM AlaE LIMIT 1,999999999999999999", null);
                while (c.moveToNext()){

                    itemListaCaixasLab[0] = (c.getString(0)); //poço
                    itemListaCaixasLab[1] = (c.getString(1)); //tipoAmostra
                    itemListaCaixasLab[2] = (c.getString(9).substring(0,3)); //detalheTestemunho. apenas # e número
                    itemListaCaixasLab[3] = (c.getString(4)); //caixa
                    itemListaCaixasLab[4] = (c.getString(6)); //data
                    itemListaCaixasLab[5] = (c.getString(7)); //hora
                    itemListaCaixasLab[6] = (c.getString(8)); //localizacao

                    listaCaixasLab.add(itemListaCaixasLab.clone());
                }
                break;
            case "Parque":
                c = db.rawQuery("SELECT * FROM Parque LIMIT 1,999999999999999999", null);
                while (c.moveToNext()){

                    itemListaCaixasLab[0] = (c.getString(0)); //poço
                    itemListaCaixasLab[1] = (c.getString(1)); //tipoAmostra
                    itemListaCaixasLab[2] = (c.getString(9).substring(0,3)); //detalheTestemunho. apenas # e número
                    itemListaCaixasLab[3] = (c.getString(4)); //caixa
                    itemListaCaixasLab[4] = (c.getString(6)); //data
                    itemListaCaixasLab[5] = (c.getString(7)); //hora
                    itemListaCaixasLab[6] = (c.getString(8)); //localizacao

                    listaCaixasLab.add(itemListaCaixasLab.clone());
                }
                break;
            case "Litoteca":
                c = db.rawQuery("SELECT * FROM Litoteca LIMIT 1,999999999999999999", null);
                while (c.moveToNext()){

                    itemListaCaixasLab[0] = (c.getString(0)); //poço
                    itemListaCaixasLab[1] = (c.getString(1)); //tipoAmostra
                    itemListaCaixasLab[2] = (c.getString(9).substring(0,3)); //detalheTestemunho. apenas # e número
                    itemListaCaixasLab[3] = (c.getString(4)); //caixa
                    itemListaCaixasLab[4] = (c.getString(6)); //data
                    itemListaCaixasLab[5] = (c.getString(7)); //hora
                    itemListaCaixasLab[6] = (c.getString(8)); //localizacao

                    listaCaixasLab.add(itemListaCaixasLab.clone());
                }
                break;
            case "P20":
                c = db.rawQuery("SELECT * FROM P20 LIMIT 1,999999999999999999", null);
                while (c.moveToNext()){

                    itemListaCaixasLab[0] = (c.getString(0)); //poço
                    itemListaCaixasLab[1] = (c.getString(1)); //tipoAmostra
                    itemListaCaixasLab[2] = (c.getString(9).substring(0,3)); //detalheTestemunho. apenas # e número com duas casas decimais ou lateral igual a ---
                    itemListaCaixasLab[3] = (c.getString(4)); //caixa
                    itemListaCaixasLab[4] = (c.getString(6)); //data
                    itemListaCaixasLab[5] = (c.getString(7)); //hora
                    itemListaCaixasLab[6] = (c.getString(8)); //localizacao

                    listaCaixasLab.add(itemListaCaixasLab.clone());
                }
                break;
        }



        FloatingActionButton fab_movimentar =  (FloatingActionButton) view.findViewById(R.id.fab_movimentar);
        final FloatingActionButton fab_enviar =  (FloatingActionButton) view.findViewById(R.id.fab_enviar);
        final FloatingActionButton fab_receber =  (FloatingActionButton) view.findViewById(R.id.fab_receber);

        final LinearLayout enviarlayout = (LinearLayout) view.findViewById(R.id.enviar_layout);
        final LinearLayout receberlayout = (LinearLayout) view.findViewById(R.id.receber_layout);
        enviarlayout.setVisibility(View.GONE);
        receberlayout.setVisibility(View.GONE);

        fab_movimentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(enviarlayout.getVisibility()== View.VISIBLE && receberlayout.getVisibility() == View.VISIBLE){
                    enviarlayout.setVisibility(View.GONE);
                    receberlayout.setVisibility(View.GONE);
                }else{
                    enviarlayout.setVisibility(View.VISIBLE);
                    receberlayout.setVisibility(View.VISIBLE);
                }
            }
        });

        fab_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("iniciar fragment entrada de caixas com scan");
                laboratorio = getLaboratorio();
                movimentacao = "Saida";
                Intent intentEnviadora2 = new Intent(getContext(), IOActivity.class);
                intentEnviadora2.putExtra("laboratorio", laboratorio);
                intentEnviadora2.putExtra("movimentacao", movimentacao);

                startActivity(intentEnviadora2);
                enviarlayout.setVisibility(View.GONE);
                receberlayout.setVisibility(View.GONE);
            }
        });
        fab_receber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("iniciar fragment receber caixas com scan");
                laboratorio = getLaboratorio();
                movimentacao = "Entrada";
                Intent intentEnviadora2 = new Intent(getContext(), IOActivity.class);
                intentEnviadora2.putExtra("laboratorio", laboratorio);
                intentEnviadora2.putExtra("movimentacao", movimentacao);
                startActivity(intentEnviadora2);
                receberlayout.setVisibility(View.GONE);
                enviarlayout.setVisibility(View.GONE);
            }
        });

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_listacaixaslab);

        //mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        Collections.reverse(listaCaixasLab);
        mListaCaixasLabAdapter = new ListaCaixasLabAdapter(listaCaixasLab);

        mRecyclerView.setAdapter(mListaCaixasLabAdapter);


        //mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        loadListaCaixasLabData();
        ncaixas = (TextView) view.findViewById(R.id.ncaixas);
        ncaixas.setText("nº de caixas:"+String.valueOf(mListaCaixasLabAdapter.getItemCount()));


        return  view;


    }
    /*public String getLaboratorio() {
        SharedPreferences lab = PreferenceManager.getDefaultSharedPreferences(getContext());
        laboratorio = lab.getString("laboratorio", "nulo");
        return laboratorio;
    }*/


    public void loadListaCaixasLabData() {
        showListaCaixasLabDataView();
        mListaCaixasLabAdapter.setListaCaixasLab_db(listaCaixasLab);
    }

    public int getNcaixas() {
        return mListaCaixasLabAdapter.getItemCount();
    }

    private void showListaCaixasLabDataView() {
        /* First, make sure the error is invisible */
//        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        /* Then, make sure the weather data is visible */
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){

        inflater.inflate(R.menu.listacaixas_menu, menu);
        //menu de filtro rápido por nome do poço
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                ncaixas.setText("nº de caixas:"+String.valueOf(mListaCaixasLabAdapter.getItemCount()));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                mListaCaixasLabAdapter.getFilter().filter(s);

                return false;
            }
        });

        //menu com todos os filtros possíveis
        MenuItem filterlist = menu.findItem(R.id.btn_fiterlist);
        filterlist.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                System.out.println("clicou para filtrar");
                return false;
            }
        });


    }
    public static ListaCaixasLabFragment newInstance() {
        return new ListaCaixasLabFragment();
    }
}

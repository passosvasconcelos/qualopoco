package com.gse.pdep.qualopoco3;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ProgressBar;
import android.support.v7.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class ListaCaixasLab extends AppCompatActivity {
    private ArrayList<String[]> listaCaixasLab;
    private String[] itemListaCaixasLab;
    private RecyclerView mRecyclerView;
    private ListaCaixasLabAdapter mListaCaixasLabAdapter;
    private String laboratorio;

    private TextView mErrorMessageDisplay;
    private TextView ncaixas;

    //private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        SQLiteDatabase db = openOrCreateDatabase("LabGSEdb", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Historico (poco VARCHAR, tipoAmostra VARCHAR, topo VARCHAR, base VARCHAR, caixa VARCHAR, endereco VARCHAR, data VARCHAR, hora VARCHAR, movimentacao VARCHAR, laboratorio VARCHAR,  localizacao VARCHAR, detalheTestemunho VARCHAR, usuario VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS I33 (poco VARCHAR, tipoAmostra VARCHAR, topo VARCHAR, base VARCHAR, caixa VARCHAR, endereco VARCHAR, data VARCHAR, hora VARCHAR,  localizacao VARCHAR, detalheTestemunho VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS I27 (poco VARCHAR, tipoAmostra VARCHAR, topo VARCHAR, base VARCHAR, caixa VARCHAR, endereco VARCHAR, data VARCHAR, hora VARCHAR,  localizacao VARCHAR, detalheTestemunho VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS AlaE (poco VARCHAR, tipoAmostra VARCHAR, topo VARCHAR, base VARCHAR, caixa VARCHAR, endereco VARCHAR, data VARCHAR, hora VARCHAR,  localizacao VARCHAR, detalheTestemunho VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS P20 (poco VARCHAR, tipoAmostra VARCHAR, topo VARCHAR, base VARCHAR, caixa VARCHAR, endereco VARCHAR, data VARCHAR, hora VARCHAR,  localizacao VARCHAR, detalheTestemunho VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS Parque (poco VARCHAR, tipoAmostra VARCHAR, topo VARCHAR, base VARCHAR, caixa VARCHAR, endereco VARCHAR, data VARCHAR, hora VARCHAR,  localizacao VARCHAR, detalheTestemunho VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS Litoteca (poco VARCHAR, tipoAmostra VARCHAR, topo VARCHAR, base VARCHAR, caixa VARCHAR, endereco VARCHAR, data VARCHAR, hora VARCHAR,  localizacao VARCHAR, detalheTestemunho VARCHAR);");


        Bundle parametros = getIntent().getExtras();
        laboratorio = parametros.getString("laboratorio");
        itemListaCaixasLab = new String[7];
        listaCaixasLab = new ArrayList<String[]>(1000);
        System.out.println(laboratorio);

        switch (laboratorio) {
            case "I33":
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



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listacaixaslab);

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview_listacaixaslab);

        //mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        Collections.reverse(listaCaixasLab);
        mListaCaixasLabAdapter = new ListaCaixasLabAdapter(listaCaixasLab);

        mRecyclerView.setAdapter(mListaCaixasLabAdapter);


        //mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        loadListaCaixasLabData();
        ncaixas = (TextView) findViewById(R.id.ncaixas);
        ncaixas.setText("nº de caixas:"+String.valueOf(mListaCaixasLabAdapter.getItemCount()));

    }

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
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.listacaixas_menu, menu);

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

        return true;
    }
}

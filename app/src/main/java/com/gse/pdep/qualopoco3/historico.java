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
import android.widget.ProgressBar;
import android.support.v7.widget.SearchView;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import java.util.ArrayList;

public class historico extends AppCompatActivity {
    private ArrayList<String[]> listahistorico;
    private String[] itemListaHistorico;
    private RecyclerView mRecyclerView;
    private HistoricoAdapter mHistoricoAdapter;

    private TextView mErrorMessageDisplay;

    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        SQLiteDatabase db = openOrCreateDatabase("LabGSEdb", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Historico (poco VARCHAR, tipoAmostra VARCHAR, topo VARCHAR, base VARCHAR, caixa VARCHAR, endereco VARCHAR, data VARCHAR, hora VARCHAR, movimentacao VARCHAR, laboratorio VARCHAR,  localizacao VARCHAR, detalheTestemunho VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS I33 (poco VARCHAR, tipoAmostra VARCHAR, topo VARCHAR, base VARCHAR, caixa VARCHAR, endereco VARCHAR, data VARCHAR, hora VARCHAR,  localizacao VARCHAR, detalheTestemunho VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS I27 (poco VARCHAR, tipoAmostra VARCHAR, topo VARCHAR, base VARCHAR, caixa VARCHAR, endereco VARCHAR, data VARCHAR, hora VARCHAR,  localizacao VARCHAR, detalheTestemunho VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS AlaE (poco VARCHAR, tipoAmostra VARCHAR, topo VARCHAR, base VARCHAR, caixa VARCHAR, endereco VARCHAR, data VARCHAR, hora VARCHAR,  localizacao VARCHAR, detalheTestemunho VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS P20 (poco VARCHAR, tipoAmostra VARCHAR, topo VARCHAR, base VARCHAR, caixa VARCHAR, endereco VARCHAR, data VARCHAR, hora VARCHAR,  localizacao VARCHAR, detalheTestemunho VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS Parque (poco VARCHAR, tipoAmostra VARCHAR, topo VARCHAR, base VARCHAR, caixa VARCHAR, endereco VARCHAR, data VARCHAR, hora VARCHAR,  localizacao VARCHAR, detalheTestemunho VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS Litoteca (poco VARCHAR, tipoAmostra VARCHAR, topo VARCHAR, base VARCHAR, caixa VARCHAR, endereco VARCHAR, data VARCHAR, hora VARCHAR,  localizacao VARCHAR, detalheTestemunho VARCHAR);");



        Cursor c= db.rawQuery("SELECT * FROM Historico ASC LIMIT 1,999999999999999", null);
        listahistorico = new ArrayList<String[]>(1000);
        itemListaHistorico = new String[9];
        while (c.moveToNext()){

            itemListaHistorico[0] = (c.getString(0)); //poço
            itemListaHistorico[1] = (c.getString(1)); //tipoAmostra
            itemListaHistorico[2] = (c.getString(11).substring(0,2)); //detalheTestemunho. apenas # e número
            itemListaHistorico[3] = (c.getString(4)); //caixa
            itemListaHistorico[4] = (c.getString(6)); //data
            itemListaHistorico[5] = (c.getString(7)); //hora
            itemListaHistorico[6] = (c.getString(8)); //movimentacao
            itemListaHistorico[7] = (c.getString(9)); //laboratorio
            itemListaHistorico[8] = (c.getString(10)); //localizacao

            listahistorico.add(itemListaHistorico.clone());


        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview_historico);

        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mHistoricoAdapter = new HistoricoAdapter(listahistorico);

        mRecyclerView.setAdapter(mHistoricoAdapter);

        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        loadHistoricoData();

    }

    private void loadHistoricoData() {
        showHistoricoDataView();
        mHistoricoAdapter.setHistorico_db(listahistorico);
    }

    private void showHistoricoDataView() {
        /* First, make sure the error is invisible */
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
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
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                mHistoricoAdapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }


}

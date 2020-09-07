package com.gse.pdep.qualopoco3;


import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoricoFragment extends Fragment {
    private ArrayList<String[]> listahistorico;
    private String[] itemListaHistorico;
    private RecyclerView mRecyclerView;
    private HistoricoAdapter mHistoricoAdapter;
    private AutoCompleteTextView act_poco;
    private Spinner spinnerTipoAmostra;
    private Spinner spinnerTestemunho;
    private Spinner spinnerCaixa;
    private Spinner spinnerUsuario;
    private String filtroPoco;
    private String filtroTipoAmostra;
    private String filtroTestemunho;
    private String filtroCaixa;
    private String filtroUsuario;
    private String filtroData;
    private Button btnAnularFiltroPoco;
    private Button btnAnularFiltroData;
    private Button btnAnularFiltroTodos;

    private CalendarView mCalendarView;
    private  ArrayList<String> allPocos;
    private ArrayList<String> allTipoAmostra;
    private ArrayList<String> allTestemunho;
    private ArrayList<String> allCaixa;
    private ArrayList<String> allUsuario;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.fragment_historico, container, false);
        SQLiteDatabase db = getActivity().openOrCreateDatabase("LabGSEdb", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Historico (poco VARCHAR, tipoAmostra VARCHAR, topo VARCHAR, base VARCHAR, caixa VARCHAR, endereco VARCHAR, data VARCHAR, hora VARCHAR, movimentacao VARCHAR, laboratorio VARCHAR,  localizacao VARCHAR, detalheTestemunho VARCHAR, usuario VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS I33 (poco VARCHAR, tipoAmostra VARCHAR, topo VARCHAR, base VARCHAR, caixa VARCHAR, endereco VARCHAR, data VARCHAR, hora VARCHAR,  localizacao VARCHAR, detalheTestemunho VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS I27 (poco VARCHAR, tipoAmostra VARCHAR, topo VARCHAR, base VARCHAR, caixa VARCHAR, endereco VARCHAR, data VARCHAR, hora VARCHAR,  localizacao VARCHAR, detalheTestemunho VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS AlaE (poco VARCHAR, tipoAmostra VARCHAR, topo VARCHAR, base VARCHAR, caixa VARCHAR, endereco VARCHAR, data VARCHAR, hora VARCHAR,  localizacao VARCHAR, detalheTestemunho VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS P20 (poco VARCHAR, tipoAmostra VARCHAR, topo VARCHAR, base VARCHAR, caixa VARCHAR, endereco VARCHAR, data VARCHAR, hora VARCHAR,  localizacao VARCHAR, detalheTestemunho VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS Parque (poco VARCHAR, tipoAmostra VARCHAR, topo VARCHAR, base VARCHAR, caixa VARCHAR, endereco VARCHAR, data VARCHAR, hora VARCHAR,  localizacao VARCHAR, detalheTestemunho VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS Litoteca (poco VARCHAR, tipoAmostra VARCHAR, topo VARCHAR, base VARCHAR, caixa VARCHAR, endereco VARCHAR, data VARCHAR, hora VARCHAR,  localizacao VARCHAR, detalheTestemunho VARCHAR);");


        Cursor c = db.rawQuery("SELECT * FROM Historico LIMIT 1,999999999999999", null);
        listahistorico = new ArrayList<String[]>(1000);
        itemListaHistorico = new String[10];
        while (c.moveToNext()) {

            itemListaHistorico[0] = (c.getString(0)); //poço
            itemListaHistorico[1] = (c.getString(1)); //tipoAmostra
            itemListaHistorico[2] = (c.getString(11)); //detalheTestemunho.
            itemListaHistorico[3] = (c.getString(4)); //caixa
            itemListaHistorico[4] = (c.getString(6)); //data
            itemListaHistorico[5] = (c.getString(7)); //hora
            itemListaHistorico[6] = (c.getString(8)); //movimentacao
            itemListaHistorico[7] = (c.getString(9)); //laboratorio
            itemListaHistorico[8] = (c.getString(10)); //localizacao
            itemListaHistorico[9] = (c.getString(12)); //usuario

            listahistorico.add(itemListaHistorico.clone());
        }

        //consulta lista de poços para usar no adapter para filtro na tela histórico
        c = db.rawQuery("SELECT DISTINCT poco FROM Historico", null);
        allPocos = new ArrayList<String>(1000);
        while (c.moveToNext()) {
            allPocos.add(c.getString(0));
        }
        Collections.sort(allPocos);
        act_poco = (AutoCompleteTextView) view.findViewById(R.id.autoCompleteTextView_poco);
        final AutoCompletePocoAdapter adapterPocos = new AutoCompletePocoAdapter(view.getContext(), allPocos);

        act_poco.setAdapter(adapterPocos);

        act_poco.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                act_poco.showDropDown();

                SharedPreferences filtros;
                filtros = PreferenceManager.getDefaultSharedPreferences(view.getContext());
                SharedPreferences.Editor ed = filtros.edit();
                filtroPoco = adapterPocos.getItem(i);
                ed.putString("filtroPoco", filtroPoco);
                ed.apply();
                getDetalheTestemunhos(filtroPoco,filtroTipoAmostra);
                mHistoricoAdapter.getFilter().filter(filtroPoco);
                closeKeyboard();
            }
        });


        //consulta lista de tipoAmostra para usar no adapter para filtro na tela histórico
        c = db.rawQuery("SELECT DISTINCT tipoAmostra FROM Historico LIMIT 1,999999999999999", null);
        allTipoAmostra = new ArrayList<String>(10);

        while (c.moveToNext()) {
            allTipoAmostra.add(c.getString(0));
        }
        Collections.sort(allTipoAmostra);
        allTipoAmostra.add(0,"Tipo de Item");

        ArrayAdapter<String> adapterTipoAmostra = new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_dropdown_item_1line, allTipoAmostra);
        spinnerTipoAmostra = (Spinner) view.findViewById(R.id.spinner_tipoAmostra);
        spinnerTipoAmostra.setAdapter(adapterTipoAmostra);
        spinnerTipoAmostra.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = adapterView.getItemAtPosition(i);
                System.out.println(item.toString());     //prints the text in spinner item.

                if (item.toString().equals("Tipo de Item")){
                    anularFiltro("filtroTipoAmostra");
                    mHistoricoAdapter.getFilter().filter("refiltrar");
                    //do nothing
                }
                else{
                    SharedPreferences filtros;
                    filtros = PreferenceManager.getDefaultSharedPreferences(view.getContext());
                    SharedPreferences.Editor ed = filtros.edit();
                    filtroTipoAmostra = item.toString();
                    ed.putString("filtroTipoAmostra", filtroTipoAmostra);
                    ed.apply();
                    mHistoricoAdapter.getFilter().filter("refiltrar");
                    getDetalheTestemunhos(filtroPoco, filtroTipoAmostra);
                    getCaixas(filtroPoco,filtroTipoAmostra,"---");



                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        c = db.rawQuery("SELECT DISTINCT detalheTestemunho FROM Historico", null);
        allTestemunho = new ArrayList<String>(1000);

        while (c.moveToNext()) {
            allTestemunho.add(c.getString(0));
        }
        Collections.sort(allTestemunho);
        allTestemunho.add(0,"Nº Testemunho");
        System.out.println(allTestemunho);
        ArrayAdapter<String> adapterTestemunho = new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_dropdown_item_1line, allTestemunho);
        spinnerTestemunho = (Spinner) view.findViewById(R.id.spinner_testemunho);
        spinnerTestemunho.setAdapter(adapterTestemunho);
        spinnerTestemunho.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = adapterView.getItemAtPosition(i);
                System.out.println(item.toString());     //prints the text in spinner item.
                if(item.toString().equals("Nº Testemunho")){
                    anularFiltro("filtroTestemunho");
                    getCaixas(filtroPoco, filtroTipoAmostra, filtroTestemunho);
                    mHistoricoAdapter.getFilter().filter("refiltrar");
                    //do nothing
                }else{
                    SharedPreferences filtros;
                    filtros = PreferenceManager.getDefaultSharedPreferences(view.getContext());
                    SharedPreferences.Editor ed = filtros.edit();
                    filtroTestemunho = item.toString();
                    ed.putString("filtroTestemunho", filtroTestemunho);
                    ed.apply();
                    mHistoricoAdapter.getFilter().filter("refiltrar");
                    getCaixas(filtroPoco, filtroTipoAmostra, filtroTestemunho);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //consulta lista de usuarios para usar no adapter para filtro na tela histórico

        c = db.rawQuery("SELECT DISTINCT usuario FROM Historico LIMIT 1,999999999999999", null);
        allUsuario = new ArrayList<String>(10);

        while (c.moveToNext()) {
            allUsuario.add(c.getString(0));
        }
        Collections.sort(allUsuario);
        allUsuario.add(0,"Usuario");
        ArrayAdapter<String> adapterUsuario = new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_dropdown_item_1line, allUsuario);
        spinnerUsuario = (Spinner) view.findViewById(R.id.spinner_usuario);
        spinnerUsuario.setAdapter(adapterUsuario);
        spinnerUsuario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = adapterView.getItemAtPosition(i);
                System.out.println(item.toString());     //prints the text in spinner item.
                if (item.toString()=="Usuario"){
                    anularFiltro("filtroUsuario");
                    mHistoricoAdapter.getFilter().filter("refiltrar");
                    //do nothing
                }else{
                    SharedPreferences filtros;
                    filtros = PreferenceManager.getDefaultSharedPreferences(view.getContext());
                    SharedPreferences.Editor ed = filtros.edit();
                    filtroUsuario = item.toString();
                    ed.putString("filtroUsuario", filtroUsuario);
                    ed.apply();
                    mHistoricoAdapter.getFilter().filter("refiltrar");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//------------------------------------------------------------------------------------

        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerview_historico);

        //mErrorMessageDisplay = (TextView) view.findViewById(R.id.tv_error_message_display);

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        Collections.reverse(listahistorico);
        mHistoricoAdapter = new HistoricoAdapter(listahistorico);

        mRecyclerView.setAdapter(mHistoricoAdapter);

        //mLoadingIndicator = (ProgressBar) view.findViewById(R.id.pb_loading_indicator);
        mCalendarView = (CalendarView) view.findViewById(R.id.calendarView);


        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth + "/" + (month+1) + "/" + year;
                SharedPreferences filtros;
                filtros = PreferenceManager.getDefaultSharedPreferences(view.getContext());
                SharedPreferences.Editor ed = filtros.edit();
                filtroData = date;
                ed.putString("filtroData", filtroData);
                ed.apply();
                mHistoricoAdapter.getFilter().filter(filtroData);

            }
        });
        btnAnularFiltroPoco = (Button) view.findViewById(R.id.btn_anular_filtroPoco);
        btnAnularFiltroPoco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anularFiltro("filtroPoco");
                act_poco.setText("");
            }
        });
        btnAnularFiltroData = (Button) view.findViewById(R.id.btn_anular_filtroData);
        btnAnularFiltroData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anularFiltro("filtroData");
            }
        });
        btnAnularFiltroTodos = (Button) view.findViewById(R.id.btn_anular_filtroTodos);
        btnAnularFiltroTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anularFiltro("filtroPoco");
                anularFiltro("filtroTipoAmostra");
                anularFiltro("filtroTestemunho");
                anularFiltro("filtroCaixa");
                anularFiltro("filtroUsuario");
                anularFiltro("filtroData");
            }
        });




        loadHistoricoData();

        return view;
    }
    private void closeKeyboard(){
        View view = getActivity().getCurrentFocus();
        if (view != null){
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }



    private void getDetalheTestemunhos(String pocoSelect, String tipoAmostraSelect){
        SQLiteDatabase db = getActivity().openOrCreateDatabase("LabGSEdb", Context.MODE_PRIVATE, null);
        if (pocoSelect!=null){
            System.out.println("pocoselect não é nulo. é igual a: "+pocoSelect);
            Cursor c = db.rawQuery("SELECT DISTINCT detalheTestemunho FROM Historico WHERE poco ='"+pocoSelect+"' AND tipoAmostra ='"+tipoAmostraSelect+"'", null);
            allTestemunho = new ArrayList<String>(1000);

            while (c.moveToNext()) {
                allTestemunho.add(c.getString(0));
            }
            Collections.sort(allTestemunho);
            allTestemunho.add(0,"Nº Testemunho");
            ArrayAdapter<String> adapterTestemunho = new ArrayAdapter<String>(getContext(),
                    android.R.layout.simple_dropdown_item_1line, allTestemunho);
            spinnerTestemunho = (Spinner) getView().findViewById(R.id.spinner_testemunho);
            spinnerTestemunho.setAdapter(adapterTestemunho);
            spinnerTestemunho.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    Object item = adapterView.getItemAtPosition(i);
                    System.out.println(item.toString());     //prints the text in spinner item.
                    if(item.toString().equals("Nº Testemunho")){
                        anularFiltro("filtroTestemunho");
                        mHistoricoAdapter.getFilter().filter("refiltrar");
                        //do nothing
                    }else{
                        SharedPreferences filtros;
                        filtros = PreferenceManager.getDefaultSharedPreferences(view.getContext());
                        SharedPreferences.Editor ed = filtros.edit();
                        filtroTestemunho = item.toString();
                        ed.putString("filtroTestemunho", filtroTestemunho);
                        ed.apply();
                        mHistoricoAdapter.getFilter().filter("refiltrar");
                        getCaixas(filtroPoco, filtroTipoAmostra, filtroTestemunho);
                    }


                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }else{
            Cursor c = db.rawQuery("SELECT DISTINCT detalheTestemunho FROM Historico WHERE tipoAmostra ='"+tipoAmostraSelect+"'", null);
            allTestemunho = new ArrayList<String>(1000);

            while (c.moveToNext()) {
                allTestemunho.add(c.getString(0));
            }
            Collections.sort(allTestemunho);
            allTestemunho.add(0,"Nº Testemunho");
            ArrayAdapter<String> adapterTestemunho = new ArrayAdapter<String>(getContext(),
                    android.R.layout.simple_dropdown_item_1line, allTestemunho);
            spinnerTestemunho = (Spinner) getView().findViewById(R.id.spinner_testemunho);
            spinnerTestemunho.setAdapter(adapterTestemunho);
            spinnerTestemunho.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    Object item = adapterView.getItemAtPosition(i);
                    System.out.println(item.toString());     //prints the text in spinner item.
                    if(item.toString().equals("Nº Testemunho")){
                        anularFiltro("filtroTestemunho");
                        mHistoricoAdapter.getFilter().filter("refiltrar");
                        //do nothing
                    }else{
                        SharedPreferences filtros;
                        filtros = PreferenceManager.getDefaultSharedPreferences(view.getContext());
                        SharedPreferences.Editor ed = filtros.edit();
                        filtroTestemunho = item.toString();
                        ed.putString("filtroTestemunho", filtroTestemunho);
                        ed.apply();
                        mHistoricoAdapter.getFilter().filter("refiltrar");
                        getCaixas(filtroPoco, filtroTipoAmostra, filtroTestemunho);
                    }


                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }

    }
    private void getCaixas(String pocoSelect, String tipoAmostraSelect, String testemunhoSelect){
        SQLiteDatabase db = getActivity().openOrCreateDatabase("LabGSEdb", Context.MODE_PRIVATE, null);
        if(pocoSelect!=null){
            Cursor c = db.rawQuery("SELECT DISTINCT caixa FROM Historico WHERE poco ='"+pocoSelect+"'AND detalheTestemunho ='"+testemunhoSelect+"' AND tipoAmostra ='"+tipoAmostraSelect+"'", null);
            allCaixa = new ArrayList<String>(500);

            while (c.moveToNext()) {
                allCaixa.add(c.getString(0));
            }
            Collections.sort(allCaixa);
            allCaixa.add(0,"Caixa");
            ArrayAdapter<String> adapterCaixa = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, allCaixa);
            spinnerCaixa = (Spinner) getView().findViewById(R.id.spinner_caixa);
            spinnerCaixa.setAdapter(adapterCaixa);
            spinnerCaixa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    Object item = adapterView.getItemAtPosition(i);
                    System.out.println(item.toString());     //prints the text in spinner item.
                    if(item.toString().equals("Caixa")){
                        anularFiltro("filtroCaixa");
                        mHistoricoAdapter.getFilter().filter("refiltrar");
                        //do nothing
                    }else{
                        SharedPreferences filtros;
                        filtros = PreferenceManager.getDefaultSharedPreferences(view.getContext());
                        SharedPreferences.Editor ed = filtros.edit();
                        filtroCaixa = item.toString();
                        ed.putString("filtroCaixa", filtroCaixa);
                        ed.apply();
                        mHistoricoAdapter.getFilter().filter("refiltrar");
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }else{
            Cursor c = db.rawQuery("SELECT DISTINCT caixa FROM Historico WHERE detalheTestemunho ='"+testemunhoSelect+"' AND tipoAmostra ='"+tipoAmostraSelect+"'", null);
            allCaixa = new ArrayList<String>(500);

            while (c.moveToNext()) {
                allCaixa.add(c.getString(0));
            }
            Collections.sort(allCaixa);
            allCaixa.add(0,"Caixa");
            ArrayAdapter<String> adapterCaixa = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, allCaixa);
            spinnerCaixa = (Spinner) getView().findViewById(R.id.spinner_caixa);
            spinnerCaixa.setAdapter(adapterCaixa);
            spinnerCaixa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    Object item = adapterView.getItemAtPosition(i);
                    System.out.println(item.toString());     //prints the text in spinner item.
                    if(item.toString().equals("Caixa")){
                        anularFiltro("filtroCaixa");
                        mHistoricoAdapter.getFilter().filter("refiltrar");
                        //do nothing
                    }else{
                        SharedPreferences filtros;
                        filtros = PreferenceManager.getDefaultSharedPreferences(view.getContext());
                        SharedPreferences.Editor ed = filtros.edit();
                        filtroCaixa = item.toString();
                        ed.putString("filtroCaixa", filtroCaixa);
                        ed.apply();
                        mHistoricoAdapter.getFilter().filter("refiltrar");
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
    }
    private void anularFiltro(String filtro){
        SharedPreferences filtros;
        filtros = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor ed = filtros.edit();
        ed.putString(filtro, null);
        ed.apply();
        mHistoricoAdapter.getFilter().filter("novo filtro");

    }
    private void loadHistoricoData() {
        showHistoricoDataView();
        mHistoricoAdapter.setHistorico_db(listahistorico);
    }

    private void showHistoricoDataView() {
        /* First, make sure the error is invisible */
        //mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        /* Then, make sure the weather data is visible */
        mRecyclerView.setVisibility(View.VISIBLE);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){

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
    }



    public static HistoricoFragment newInstance() {
        return new HistoricoFragment();
    }
}

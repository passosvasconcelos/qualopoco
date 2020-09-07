package com.gse.pdep.qualopoco3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDeDados extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "LaboratoriosDB";
    private static final String TABELA = "Historico";
    private static final int VERSAO = 1;


    public BaseDeDados(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

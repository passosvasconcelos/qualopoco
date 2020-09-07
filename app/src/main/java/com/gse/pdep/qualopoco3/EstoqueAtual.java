package com.gse.pdep.qualopoco3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EstoqueAtual extends AppCompatActivity {
    private Button btnAlaI33;
    private Button btnAlaI27;
    private Button btnAlaE01;
    private Button btnPredio20;
    private Button btnTorgua;
    private Button btnParque;
    public String laboratorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estoque_atual);

        btnAlaI33 = (Button) findViewById(R.id.btnAlaI33);
        btnAlaI27 = (Button) findViewById(R.id.btnAlaI27);
        btnAlaE01 = (Button) findViewById(R.id.btnAlaE01);
        btnPredio20 = (Button) findViewById(R.id.btnPredio20);
        btnTorgua = (Button) findViewById(R.id.btnTorgua);
        btnParque = (Button) findViewById(R.id.btnParque);

        btnAlaI33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentEnviadora= new Intent(getApplicationContext(), ListaCaixasLab.class);
                laboratorio = "I33";
                intentEnviadora.putExtra("laboratorio", laboratorio);
                startActivity(intentEnviadora);
            }
        });
        btnAlaI27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentEnviadora= new Intent(getApplicationContext(), ListaCaixasLab.class);
                laboratorio = "I27";
                intentEnviadora.putExtra("laboratorio", laboratorio);
                startActivity(intentEnviadora);
            }
        });
        btnAlaE01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentEnviadora= new Intent(getApplicationContext(), ListaCaixasLab.class);
                laboratorio = "AlaE";
                intentEnviadora.putExtra("laboratorio", laboratorio);
                startActivity(intentEnviadora);
            }
        });
        btnPredio20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentEnviadora= new Intent(getApplicationContext(), ListaCaixasLab.class);
                laboratorio = "P20";
                intentEnviadora.putExtra("laboratorio", laboratorio);
                startActivity(intentEnviadora);
            }
        });
        btnParque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentEnviadora= new Intent(getApplicationContext(), ListaCaixasLab.class);
                laboratorio = "Parque";
                intentEnviadora.putExtra("laboratorio", laboratorio);
                startActivity(intentEnviadora);
            }
        });
        btnTorgua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentEnviadora= new Intent(getApplicationContext(), ListaCaixasLab.class);
                laboratorio = "Litoteca";
                intentEnviadora.putExtra("laboratorio", laboratorio);
                startActivity(intentEnviadora);
            }
        });
    }
}

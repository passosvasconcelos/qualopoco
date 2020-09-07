package com.gse.pdep.qualopoco3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CaixasPorLaboratorioActivity extends AppCompatActivity {
    private Button btnAlaI33;
    private Button btnAlaI27;
    private Button btnAlaE01;
    private Button btnPredio20;
    private Button btnTorgua;
    private Button btnParque;
    public String laboratorio;
    public String bancada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caixas_por_laboratorio);

        btnAlaI33 = (Button) findViewById(R.id.btnAlaI33);
        btnAlaI27 = (Button) findViewById(R.id.btnAlaI27);
        btnAlaE01 = (Button) findViewById(R.id.btnAlaE01);
        btnPredio20 = (Button) findViewById(R.id.btnPredio20);
        btnTorgua = (Button) findViewById(R.id.btnTorgua);
        btnParque = (Button) findViewById(R.id.btnParque);

        btnAlaI33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentEnviadora= new Intent(getApplicationContext(), BancadasI33Activity.class);
                laboratorio = "I33";
                intentEnviadora.putExtra("laboratorio", laboratorio);
                startActivity(intentEnviadora);
            }
        });
        btnAlaI27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentEnviadora= new Intent(getApplicationContext(), BancadasI27Activity.class);
                laboratorio = "I27";
                intentEnviadora.putExtra("laboratorio", laboratorio);
                startActivity(intentEnviadora);
            }
        });
        btnAlaE01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentEnviadora= new Intent(getApplicationContext(), BancadasAlaEActivity.class);
                laboratorio = "AlaE";
                intentEnviadora.putExtra("laboratorio", laboratorio);
                startActivity(intentEnviadora);
            }
        });
        btnPredio20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentEnviadora= new Intent(getApplicationContext(), BancadasP20Activity.class);
                laboratorio = "P20";
                intentEnviadora.putExtra("laboratorio", laboratorio);
                startActivity(intentEnviadora);
            }
        });
        btnParque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentEnviadora= new Intent(getApplicationContext(), BancadasParqueActivity.class);
                laboratorio = "Parque";
                intentEnviadora.putExtra("laboratorio", laboratorio);
                startActivity(intentEnviadora);
            }
        });
        btnTorgua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                laboratorio = "Litoteca";
                bancada = "endereço";
                Intent intentEnviadora= new Intent(getApplicationContext(), IOActivity.class);

                intentEnviadora.putExtra("laboratorio", laboratorio);
                intentEnviadora.putExtra("bancada", bancada);
                startActivity(intentEnviadora);
            }
        });
    }
}

package com.gse.pdep.qualopoco3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BancadasI27Activity extends AppCompatActivity {
    private Button btnEmpilhado;
    private Button btnB1;
    private Button btnB2;
    private Button btnB3;
    private Button btnB4;
    private String bancada;
    private String laboratorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bancadas_i27);
        btnEmpilhado = (Button) findViewById(R.id.Empilhado);
        btnB1 = (Button) findViewById(R.id.B_1);
        btnB2 = (Button) findViewById(R.id.B_2);
        btnB3 = (Button) findViewById(R.id.B_3);
        btnB4 = (Button) findViewById(R.id.B_4);

        btnB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "B-1";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "B-2";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnB3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "B-3";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnB4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "B-4";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnEmpilhado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "Empilhado";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });
    }
}

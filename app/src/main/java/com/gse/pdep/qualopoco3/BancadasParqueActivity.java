package com.gse.pdep.qualopoco3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BancadasParqueActivity extends AppCompatActivity {
    private Button btnEmpilhado;
    private Button btnB1;
    private Button btnB2;
    private Button btnB3;
    private Button btnB4;
    private Button btnB5;
    private Button btnB6;
    private Button btnB7;
    private Button btnB8;
    private Button btnB9;
    private Button btnB10;
    private Button btnB11;
    private Button btnB12;
    private String bancada;
    private String laboratorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bancadas_parque);
        btnEmpilhado = (Button) findViewById(R.id.Empilhado);
        btnB1 = (Button) findViewById(R.id.B_1);
        btnB2 = (Button) findViewById(R.id.B_2);
        btnB3 = (Button) findViewById(R.id.B_3);
        btnB4 = (Button) findViewById(R.id.B_4);
        btnB5 = (Button) findViewById(R.id.B_5);
        btnB6 = (Button) findViewById(R.id.B_6);
        btnB7 = (Button) findViewById(R.id.B_7);
        btnB8 = (Button) findViewById(R.id.B_8);
        btnB9 = (Button) findViewById(R.id.B_9);
        btnB10 = (Button) findViewById(R.id.B_10);
        btnB11 = (Button) findViewById(R.id.B_11);
        btnB12 = (Button) findViewById(R.id.B_12);

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

        btnB5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "B-5";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnB6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "B-6";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnB7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "B-7";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnB8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "B-8";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnB9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "B-9";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnB10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "B-10";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnB11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), IOActivity.class);
                bancada = "B-11";
                intent.putExtra("bancada", bancada);
                startActivity(intent);
            }
        });

        btnB12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "B-12";
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

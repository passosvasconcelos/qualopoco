package com.gse.pdep.qualopoco3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BancadasI33Activity extends AppCompatActivity {
    private String bancada;
    private String laboratorio;

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
    private Button btnB13;
    private Button btnB1I;
    private Button btnB2I;
    private Button btnB3I;
    private Button btnB4I;
    private Button btnB5I;
    private Button btnB6I;
    private Button btnB7I;
    private Button btnB8I;
    private Button btnB9I;
    private Button btnB10I;
    private Button btnB11I;
    private Button btnB12I;
    private Button btnB13I;
    private Button btnE1;
    private Button btnE2;
    private Button btnE3;
    private Button btnE4;
    private Button btnE5;
    private Button btnE6;
    private Button btnE7;
    private Button btnE8;
    private Button btnE9;
    private Button btnE10;
    private Button btnE11;
    private Button btnE12;
    private Button btnE13;
    private Button btnE14;
    private Button btnE15;
    private Button btnE16;
    private Button btnE17;
    private Button btnE18;
    private Button btnE19;
    private Button btnE20;
    private Button btnE21;
    private Button btnE22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bancadas_i33);


        btnEmpilhado = (Button) findViewById(R.id.Empilhado);
        btnB1 = (Button) findViewById(R.id.B_1S);
        btnB2 = (Button) findViewById(R.id.B_2S);
        btnB3 = (Button) findViewById(R.id.B_3S);
        btnB4 = (Button) findViewById(R.id.B_4S);
        btnB5 = (Button) findViewById(R.id.B_5S);
        btnB6 = (Button) findViewById(R.id.B_6S);
        btnB7 = (Button) findViewById(R.id.B_7S);
        btnB8 = (Button) findViewById(R.id.B_8S);
        btnB9 = (Button) findViewById(R.id.B_9S);
        btnB10 = (Button) findViewById(R.id.B_10S);
        btnB11 = (Button) findViewById(R.id.B_11S);
        btnB12 = (Button) findViewById(R.id.B_12S);
        btnB13 = (Button) findViewById(R.id.B_13S);

        btnB1I = (Button) findViewById(R.id.B_1I);
        btnB2I = (Button) findViewById(R.id.B_2I);
        btnB3I = (Button) findViewById(R.id.B_3I);
        btnB4I = (Button) findViewById(R.id.B_4I);
        btnB5I = (Button) findViewById(R.id.B_5I);
        btnB6I = (Button) findViewById(R.id.B_6I);
        btnB7I = (Button) findViewById(R.id.B_7I);
        btnB8I = (Button) findViewById(R.id.B_8I);
        btnB9I = (Button) findViewById(R.id.B_9I);
        btnB10I = (Button) findViewById(R.id.B_10I);
        btnB11I = (Button) findViewById(R.id.B_11I);
        btnB12I = (Button) findViewById(R.id.B_12I);
        btnB13I = (Button) findViewById(R.id.B_13I);

        btnE1 = (Button) findViewById(R.id.E_1);
        btnE2 = (Button) findViewById(R.id.E_2);
        btnE3 = (Button) findViewById(R.id.E_3);
        btnE4 = (Button) findViewById(R.id.E_4);
        btnE5 = (Button) findViewById(R.id.E_5);
        btnE6 = (Button) findViewById(R.id.E_6);
        btnE7 = (Button) findViewById(R.id.E_7);
        btnE8 = (Button) findViewById(R.id.E_8);
        btnE9 = (Button) findViewById(R.id.E_9);
        btnE10 = (Button) findViewById(R.id.E_10);
        btnE11 = (Button) findViewById(R.id.E_11);
        btnE12 = (Button) findViewById(R.id.E_12);
        btnE13 = (Button) findViewById(R.id.E_13);
        btnE14 = (Button) findViewById(R.id.E_14);
        btnE15 = (Button) findViewById(R.id.E_15);
        btnE16 = (Button) findViewById(R.id.E_16);
        btnE17 = (Button) findViewById(R.id.E_17);
        btnE18 = (Button) findViewById(R.id.E_18);
        btnE19 = (Button) findViewById(R.id.E_19);
        btnE20 = (Button) findViewById(R.id.E_20);
        btnE21 = (Button) findViewById(R.id.E_21);
        btnE22 = (Button) findViewById(R.id.E_22);

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

        btnB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "B-1-SUPERIOR";
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
                bancada = "B-2-SUPERIOR";
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
                bancada = "B-3-SUPERIOR";
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
                bancada = "B-4-SUPERIOR";
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
                bancada = "B-5-SUPERIOR";
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
                bancada = "B-6-SUPERIOR";
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
                bancada = "B-7-SUPERIOR";
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
                bancada = "B-8-SUPERIOR";
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
                bancada = "B-9-SUPERIOR";
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
                bancada = "B-10-SUPERIOR";
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
                bancada = "B-11-SUPERIOR";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnB12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "B-12-SUPERIOR";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnB13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "B-13-SUPERIOR";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnB1I.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "B-1-INFERIOR";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnB2I.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "B-2-INFERIOR";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnB3I.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "B-3-INFERIOR";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnB4I.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "B-4-INFERIOR";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnB5I.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "B-5-INFERIOR";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnB6I.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "B-6-INFERIOR";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnB7I.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "B-7-INFERIOR";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnB8I.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "B-8-INFERIOR";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnB9I.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "B-9-INFERIOR";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnB10I.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "B-10-INFERIOR";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnB11I.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "B-11-INFERIOR";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnB12I.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "B-12-INFERIOR";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnB13I.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "B-13-INFERIOR";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnE1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "E-1";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnE2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "E-2";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnE3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "E-3";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnE4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "E-4";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnE5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "E-5";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnE6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "E-6";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnE7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "E-7";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnE8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "E-8";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnE9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "E-9";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnE10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "E-10";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnE11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "E-11";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnE12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "E-12";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnE13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "E-13";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnE14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "E-14";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnE15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "E-15";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnE16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "E-16";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnE17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "E-17";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnE18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "E-18";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnE19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "E-19";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnE20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "E-20";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnE21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "E-21";
                Bundle parametros = getIntent().getExtras();
                laboratorio = parametros.getString("laboratorio");
                Intent intentEnviadora2 = new Intent(getApplicationContext(), IOActivity.class);
                intentEnviadora2.putExtra("bancada", bancada);
                intentEnviadora2.putExtra("laboratorio", laboratorio);

                startActivity(intentEnviadora2);
            }
        });

        btnE22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bancada = "E-22";
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

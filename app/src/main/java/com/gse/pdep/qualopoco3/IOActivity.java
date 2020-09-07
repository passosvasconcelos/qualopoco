package com.gse.pdep.qualopoco3;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.AsyncTask;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Date;
import java.text.DateFormat;


import javax.net.ssl.HttpsURLConnection;


public class IOActivity extends AppCompatActivity {
    private Button btnScan;
    private String scannedData;
    private String poco;
    private String tipoAmostra;
    private String topo;
    private String base;
    private String caixa;
    private String endereco;
    private String detalheTestemunho;
    private String usuario;
    public String movimentacao;
    private String laboratorio;
    private String bancada = "Empilhado";

    private ArrayList<String> caixasMovimentadas = new ArrayList<String>(1000);




    //private static final String PREF_ACCOUNT_NAME = "accountName";

    private TextView titleIO;
    private TextView ncaixas;
    private TextView obs;
    private ProgressDialog mProgress;

    public void setScannedData(String scannedData) {
        this.scannedData = scannedData;
    }

    public void setPoco(String poco) {
        this.poco = poco;
    }

    public void setTipoAmostra(String tipoAmostra) {
        this.tipoAmostra = tipoAmostra;
    }

    public void setTopo(String topo) {
        this.topo = topo;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public void setCaixa(String caixa) {
        this.caixa = caixa;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setDetalheTestemunho(String detalheTestemunho) {
        this.detalheTestemunho = detalheTestemunho;
    }

    public void setMovimentacao(String movimentacao) {
        this.movimentacao = movimentacao;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public void setBancada(String bancada) {
        this.bancada = bancada;
    }

    public String getScannedData() {
        return scannedData;
    }

    public String getPoco() {
        return poco;
    }

    public String getTipoAmostra() {
        return tipoAmostra;
    }

    public String getTopo() {
        return topo;
    }

    public String getBase() {
        return base;
    }

    public String getCaixa() {
        return caixa;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getUsuario(){
        SharedPreferences user = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        usuario = user.getString("usuario", "nulo");
        return usuario;
    }

    public String getDetalheTestemunho() {
        return detalheTestemunho;
    }

    public String getMovimentacao() {
        return movimentacao;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public String getBancada() {
        if (laboratorio.equals("Litoteca")){
            setBancada(getEndereco());
            return bancada;
        }
        else{
            return bancada;
        }
    }

    public String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("d/M/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }
    public String getTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }



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




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_io);
        System.out.println(getDateTime());
        final Activity activity =this;

        titleIO = (TextView) findViewById(R.id.titleIO);
        ncaixas = (TextView) findViewById(R.id.n_caixas_movimentadas);
        obs = (TextView) findViewById(R.id.obs);
        obs.setText("Leia o QRCode da bancada/escaninho para indicar o local de armazenamento. Na litoteca (Torguá) esta seleção é desnecessária, pois a localização é o endereço da caixa");
        ncaixas.setText("caixas movimentadas: "+String.valueOf(caixasMovimentadas.size()));
        mProgress = new ProgressDialog(this);

        Intent intentRecebedora = getIntent();
        Bundle parametros = intentRecebedora.getExtras();

        laboratorio = parametros.getString("laboratorio");
        movimentacao = parametros.getString("movimentacao");

        if (laboratorio.equals("Litoteca")){
            bancada = "endereco";
        }
        titleIO.setText(movimentacao+" em: "+laboratorio+"/"+bancada);

        btnScan = (Button) findViewById(R.id.btn_scan);

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setBeepEnabled(false);
                integrator.setCameraId(0);
                integrator.setBarcodeImageEnabled(false);
                integrator.setOrientationLocked(false);
                integrator.initiateScan();

            }
        });
    }

    public boolean movimentoValido(String laboratorio, String movimentacao, String endereco){
        ArrayList<String> listaCaixasLab = new ArrayList<String>(1000);;
        SQLiteDatabase db = openOrCreateDatabase("LabGSEdb", Context.MODE_PRIVATE, null);

        db.execSQL("CREATE TABLE IF NOT EXISTS Historico (poco VARCHAR, tipoAmostra VARCHAR, topo VARCHAR, base VARCHAR, caixa VARCHAR, endereco VARCHAR, data VARCHAR, hora VARCHAR, movimentacao VARCHAR, laboratorio VARCHAR,  localizacao VARCHAR, detalheTestemunho VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS I33 (poco VARCHAR, tipoAmostra VARCHAR, topo VARCHAR, base VARCHAR, caixa VARCHAR, endereco VARCHAR, data VARCHAR, hora VARCHAR,  localizacao VARCHAR, detalheTestemunho VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS I27 (poco VARCHAR, tipoAmostra VARCHAR, topo VARCHAR, base VARCHAR, caixa VARCHAR, endereco VARCHAR, data VARCHAR, hora VARCHAR,  localizacao VARCHAR, detalheTestemunho VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS AlaE (poco VARCHAR, tipoAmostra VARCHAR, topo VARCHAR, base VARCHAR, caixa VARCHAR, endereco VARCHAR, data VARCHAR, hora VARCHAR,  localizacao VARCHAR, detalheTestemunho VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS P20 (poco VARCHAR, tipoAmostra VARCHAR, topo VARCHAR, base VARCHAR, caixa VARCHAR, endereco VARCHAR, data VARCHAR, hora VARCHAR,  localizacao VARCHAR, detalheTestemunho VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS Parque (poco VARCHAR, tipoAmostra VARCHAR, topo VARCHAR, base VARCHAR, caixa VARCHAR, endereco VARCHAR, data VARCHAR, hora VARCHAR,  localizacao VARCHAR, detalheTestemunho VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS Litoteca (poco VARCHAR, tipoAmostra VARCHAR, topo VARCHAR, base VARCHAR, caixa VARCHAR, endereco VARCHAR, data VARCHAR, hora VARCHAR,  localizacao VARCHAR, detalheTestemunho VARCHAR);");

        Cursor c = db.rawQuery("SELECT endereco FROM "+laboratorio+" ASC LIMIT 1,999999999999999999", null);
        while (c.moveToNext()){
            listaCaixasLab.add(c.getString(0));//adiciona endereços já armazenados no laboratório
        }
        if (movimentacao.equals("Entrada") && listaCaixasLab.contains(endereco)){
            Toast.makeText(getApplicationContext(), "a caixa "+getCaixa()+" já está armazenada no laboratório "+laboratorio,
                    Toast.LENGTH_LONG).show();
            return false;
        } else if(movimentacao.equals("Saida") && !listaCaixasLab.contains(endereco)){
            Toast.makeText(getApplicationContext(), "a caixa "+getCaixa()+" não está armazenada no laboratório "+laboratorio,
                    Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }



    @Override
    protected void onActivityResult(
            int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result!=null){
            scannedData = result.getContents();

            if (scannedData!= null){

                if (scannedData.substring(0,7).equals("bancada")==false && scannedData.substring(0,9).equals("escaninho")==false){
                    setPoco(scannedData.split(";")[0].split(":")[1]);
                    setTipoAmostra(scannedData.split(";")[1].split(":")[1]);
                    switch (tipoAmostra){
                        case "Testemunho":{
                            setTopo(scannedData.split(";")[4].split(":")[1]);
                            setBase(scannedData.split(";")[5].split(":")[1]);
                            setCaixa(scannedData.split(";")[3].split(":")[1]);
                            setEndereco(scannedData.split(";")[6].split(":")[1]);
                            setDetalheTestemunho (scannedData.split(";")[2]);
                            break;
                        }
                        case "Lateral":{
                            setTopo(scannedData.split(";")[2].split(":")[1]);
                            setBase(scannedData.split(";")[3].split(":")[1]);
                            setCaixa(scannedData.split(";")[4].split(":")[1]);
                            setEndereco(scannedData.split(";")[5].split(":")[1]);
                            setDetalheTestemunho ("---");
                            break;
                        }
                        case "Calha":{
                            setTopo(scannedData.split(";")[2].split(":")[1]);
                            setBase(scannedData.split(";")[3].split(":")[1]);
                            setCaixa(scannedData.split(";")[4].split(":")[1]);
                            setEndereco(scannedData.split(";")[5].split(":")[1]);
                            setDetalheTestemunho ("---");//lembrar que a ocorrência de string --- em detalheTestemunho na base de dados restringe a pesquisa por substring neste atributo a no máximo 3 caracteres, o retornará erro ao ler exemplo de lateral (Fora do range)
                            break;
                        }
                        case "Apara de plug":{
                            System.out.println("amostra é do tipo: "+tipoAmostra);
                            System.out.println(scannedData);
                            setTopo(scannedData.split(";")[3].split(":")[1]);
                            setBase(scannedData.split(";")[4].split(":")[1]);
                            setCaixa(scannedData.split(";")[2].split(":")[1]);
                            setEndereco(scannedData.split(";")[5].split(":")[1]);
                            setDetalheTestemunho ("---");//lembrar que a ocorrência de string --- em detalheTestemunho na base de dados restringe a pesquisa por substring neste atributo a no máximo 3 caracteres, o retornará erro ao ler exemplo de lateral (Fora do range)
                            break;
                        }
                        case "Plug":{
                            setTopo(scannedData.split(";")[3].split(":")[1]);
                            setBase(scannedData.split(";")[4].split(":")[1]);
                            setCaixa(scannedData.split(";")[2].split(":")[1]);
                            setEndereco(scannedData.split(";")[5].split(":")[1]);
                            setDetalheTestemunho ("---");//lembrar que a ocorrência de string --- em detalheTestemunho na base de dados restringe a pesquisa por substring neste atributo a no máximo 3 caracteres, o retornará erro ao ler exemplo de lateral (Fora do range)
                            break;
                        }
                    }
                    if (movimentoValido(laboratorio,movimentacao,endereco)==true){
                        //executa abaixo
                        new SendRequest().execute();
                    }
                }
                else{
                    if (scannedData.substring(0,7).equals("bancada")==true || scannedData.substring(0,9).equals("escaninho")==true){
                        setBancada(scannedData.split(":")[1]);
                        titleIO.setText("Armazenando em: "+laboratorio+"/"+bancada);

                        Toast.makeText(getApplicationContext(), "armazenar em "+getBancada()+".",
                                Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "QRCode não reconhecido",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private class SendRequest extends AsyncTask<String, Void, String> {
        protected void onPreExecute(){
            mProgress.setMessage("executanto "+movimentacao+" da caixa "+caixa+" no laboratório "+laboratorio);
            mProgress.show();

        }
        protected String doInBackground(String... arg0) {
            try{
                //Enter script URL Here
                URL url = new URL("https://script.google.com/macros/s/AKfycbw7cQ0Dl_Y1OmxTO-Dfa84yT3FtpLoY9ajuUhr5/exec");
                JSONObject postDataParams = new JSONObject();

                postDataParams.put("data", getDateTime());
                postDataParams.put("time", getTime());
                postDataParams.put("poco", getPoco());
                postDataParams.put("tipoAmostra", getTipoAmostra());
                postDataParams.put("topo", getTopo());
                postDataParams.put("base", getBase());
                postDataParams.put("caixa", getCaixa());
                postDataParams.put("endereco", getEndereco());
                postDataParams.put("detalheTestemunho", getDetalheTestemunho());
                postDataParams.put("usuario", getUsuario());
                postDataParams.put("movimentacao", getMovimentacao());
                postDataParams.put("laboratorio", getLaboratorio());
                postDataParams.put("bancada", getBancada());
                Log.e("params",postDataParams.toString());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode=conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line="";
                    while((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }

                    in.close();
                    ToneGenerator tg = new ToneGenerator(AudioManager.STREAM_NOTIFICATION, (int)(ToneGenerator.MAX_VOLUME * 0.85));
                    if(tg != null){
                        tg.startTone(ToneGenerator.TONE_PROP_BEEP);
                    }
                    //contador+=1;
                    SQLiteDatabase db = openOrCreateDatabase("LabGSEdb", Context.MODE_PRIVATE, null);
                    db.execSQL("INSERT INTO Historico VALUES('"+getPoco()+"','"+getTipoAmostra()+"','"+getTopo()+"','"+getBase()+"','"+getCaixa()+"','"+getEndereco()+"','"+getDateTime()+"','"+getTime()+"','"+getMovimentacao()+"','"+getLaboratorio()+"','"+getBancada()+"','"+getDetalheTestemunho()+"','"+getUsuario()+"');");
                    ArrayList<String> todosOsLaboratorios = new ArrayList<String>(10);
                    todosOsLaboratorios.add("I33");
                    todosOsLaboratorios.add("I27");
                    todosOsLaboratorios.add("AlaE");
                    todosOsLaboratorios.add("Parque");
                    todosOsLaboratorios.add("Litoteca");
                    todosOsLaboratorios.add("P20");
                    if(movimentacao.equals("Entrada")){
                        for (int i=0; i<todosOsLaboratorios.size();i++){
                            String laboratorioSaida = todosOsLaboratorios.get(i);
                            db.execSQL("DELETE FROM " + laboratorioSaida + " WHERE "+ "endereco" + "='"+ getEndereco()+"'");
                        }
                        db.execSQL("INSERT INTO "+laboratorio+" VALUES('"+getPoco()+"','"+getTipoAmostra()+"','"+getTopo()+"','"+getBase()+"','"+getCaixa()+"','"+getEndereco()+"','"+getDateTime()+"','"+getTime()+"','"+getBancada()+"','"+getDetalheTestemunho()+"');");

                    }else if (movimentacao.equals("Saida")){
                        db.execSQL("DELETE FROM " + laboratorio + " WHERE "+ "endereco" + "='"+ getEndereco()+"'");
                    }
                    caixasMovimentadas.add(getEndereco());
                    ncaixas.setText("caixas movimentadas: "+String.valueOf(caixasMovimentadas.size()));
                    return ("Caixa "+caixa+" movimentada com sucesso");


                }
                else {
                    Vibrator rr = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    long milliseconds = 1500;
                    rr.vibrate(milliseconds);
                    //mProgress.hide();
                    return new String("ERRO!, REFAÇA A LEITURA. : "+responseCode);
                }
            }
            catch(Exception e){
                Vibrator rr = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                long milliseconds = 1500;
                rr.vibrate(milliseconds);
                //mProgress.hide();
                return new String("Erro de conexão. REFAÇA A LEITURA: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String result) {
            mProgress.hide();
            Toast.makeText(getApplicationContext(), result,
                    Toast.LENGTH_LONG).show();

        }
    }

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }

}

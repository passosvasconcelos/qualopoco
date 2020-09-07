package com.gse.pdep.qualopoco3;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.googleapis.extensions.android.gms.auth.GooglePlayServicesAvailabilityIOException;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks, NavigationView.OnNavigationItemSelectedListener{
    private String laboratorio;
    GoogleAccountCredential mCredential;
    ProgressDialog mProgress;
    private DrawerLayout drawer;

    static final int REQUEST_ACCOUNT_PICKER = 1000;
    static final int REQUEST_AUTHORIZATION = 1001;
    static final int REQUEST_GOOGLE_PLAY_SERVICES = 1002;
    static final int REQUEST_PERMISSION_GET_ACCOUNTS = 1003;


    private static final String PREF_ACCOUNT_NAME = "accountName";
    private static final String[] SCOPES = { SheetsScopes.SPREADSHEETS_READONLY };

    /**
     * Create the main activity.
     * @param savedInstanceState previously saved instance data.
     */

    private SQLiteDatabase db;

    public void setLaboratorio(String laboratorio) {
        SharedPreferences lab;
        lab = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        SharedPreferences.Editor ed = lab.edit();
        ed.putString("laboratorio_", laboratorio);
        ed.apply();
        this.laboratorio = laboratorio;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer, toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        getSupportActionBar().setTitle("Qual o poço?");
        Fragment telaInicialFragment = TelaInicialFragment.newInstance();
        openFragment(telaInicialFragment);


        mCredential = GoogleAccountCredential.usingOAuth2(
                getApplicationContext(), Arrays.asList(SCOPES))
                .setBackOff(new ExponentialBackOff());

        mProgress = new ProgressDialog(this);
        mProgress.setMessage("Sincronizando com a base de dados...");

        db = openOrCreateDatabase("LabGSEdb",Context.MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Historico (poco VARCHAR, tipoAmostra VARCHAR, topo VARCHAR, base VARCHAR, caixa VARCHAR, endereco VARCHAR, data VARCHAR, hora VARCHAR, movimentacao VARCHAR, laboratorio VARCHAR,  localizacao VARCHAR, detalheTestemunho VARCHAR, usuario VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS I33 (poco VARCHAR, tipoAmostra VARCHAR, topo VARCHAR, base VARCHAR, caixa VARCHAR, endereco VARCHAR, data VARCHAR, hora VARCHAR,  localizacao VARCHAR, detalheTestemunho VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS I27 (poco VARCHAR, tipoAmostra VARCHAR, topo VARCHAR, base VARCHAR, caixa VARCHAR, endereco VARCHAR, data VARCHAR, hora VARCHAR,  localizacao VARCHAR, detalheTestemunho VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS AlaE (poco VARCHAR, tipoAmostra VARCHAR, topo VARCHAR, base VARCHAR, caixa VARCHAR, endereco VARCHAR, data VARCHAR, hora VARCHAR,  localizacao VARCHAR, detalheTestemunho VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS P20 (poco VARCHAR, tipoAmostra VARCHAR, topo VARCHAR, base VARCHAR, caixa VARCHAR, endereco VARCHAR, data VARCHAR, hora VARCHAR,  localizacao VARCHAR, detalheTestemunho VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS Parque (poco VARCHAR, tipoAmostra VARCHAR, topo VARCHAR, base VARCHAR, caixa VARCHAR, endereco VARCHAR, data VARCHAR, hora VARCHAR,  localizacao VARCHAR, detalheTestemunho VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS Litoteca (poco VARCHAR, tipoAmostra VARCHAR, topo VARCHAR, base VARCHAR, caixa VARCHAR, endereco VARCHAR, data VARCHAR, hora VARCHAR,  localizacao VARCHAR, detalheTestemunho VARCHAR);");
        sincronizar();
    }

    @Override
    public void onBackPressed(){
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else{
            super.onBackPressed();
        }

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.mhistorico:{
                getSupportActionBar().setTitle("Histórico");
                Fragment historicoFragment = HistoricoFragment.newInstance();
                openFragment(historicoFragment);
                break;
            }
            case R.id.nav_labi33:{
                getSupportActionBar().setTitle("Lab I33");
                setLaboratorio("I33");
                Fragment listaCaixasLabFragment = ListaCaixasLabFragment.newInstance();
                openFragment(listaCaixasLabFragment);
                break;
            }
            case R.id.nav_labi27:{
                getSupportActionBar().setTitle("Lab I27 (Parceria)");
                setLaboratorio("I27");
                Fragment listaCaixasLabFragment = ListaCaixasLabFragment.newInstance();
                openFragment(listaCaixasLabFragment);
                break;
            }
            case R.id.nav_alaE:{
                getSupportActionBar().setTitle("Ala E (Lab E01)");
                setLaboratorio("AlaE");
                Fragment listaCaixasLabFragment = ListaCaixasLabFragment.newInstance();
                openFragment(listaCaixasLabFragment);
                break;
            }
            case R.id.nav_parque:{
                getSupportActionBar().setTitle("Parque Tecnológico");
                setLaboratorio("Parque");
                Fragment listaCaixasLabFragment = ListaCaixasLabFragment.newInstance();
                openFragment(listaCaixasLabFragment);
                break;
            }
            case R.id.nav_predio20:{
                getSupportActionBar().setTitle("Prédio 20 (Lab 86)");
                setLaboratorio("P20");
                Fragment listaCaixasLabFragment = ListaCaixasLabFragment.newInstance();
                openFragment(listaCaixasLabFragment);
                break;
            }
            case R.id.nav_litoteca:{
                getSupportActionBar().setTitle("Litoteca (Torguá)");
                setLaboratorio("Litoteca");
                Fragment listaCaixasLabFragment = ListaCaixasLabFragment.newInstance();
                openFragment(listaCaixasLabFragment);
                break;
            }
            case R.id.mgraficos:{
                getSupportActionBar().setTitle("Gráficos");
                Fragment graficosFragment = GraficosFragment.newInstance();
                openFragment(graficosFragment);
                break;
            }
            case R.id.msync:{
                getSupportActionBar().setTitle("Sincronizando...");
                sincronizar();
                break;
            }

        }
        drawer.closeDrawer((GravityCompat.START));
        return true;
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();


    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    /**
     * Attempt to call the API, after verifying that all the preconditions are
     * satisfied. The preconditions are: Google Play Services installed, an
     * account was selected and the device currently has online access. If any
     * of the preconditions are not satisfied, the app will prompt the user as
     * appropriate.
     */
    private void sincronizar() {
        if (! isGooglePlayServicesAvailable()) {
            acquireGooglePlayServices();
        } else if (mCredential.getSelectedAccountName() == null) {
            chooseAccount();
        } else if (! isDeviceOnline()) {
            System.out.println("No network connection available");
            //mOutputText.setText("No network connection available.");
        } else {
            new MainActivity.MakeRequestTask(mCredential).execute();
        }
    }

    /**
     * Attempts to set the account used with the API credentials. If an account
     * name was previously saved it will use that one; otherwise an account
     * picker dialog will be shown to the user. Note that the setting the
     * account to use with the credentials object requires the app to have the
     * GET_ACCOUNTS permission, which is requested here if it is not already
     * present. The AfterPermissionGranted annotation indicates that this
     * function will be rerun automatically whenever the GET_ACCOUNTS permission
     * is granted.
     */
    @AfterPermissionGranted(REQUEST_PERMISSION_GET_ACCOUNTS)
    private void chooseAccount() {
        if (EasyPermissions.hasPermissions(
                this, Manifest.permission.GET_ACCOUNTS)) {
            String accountName = getPreferences(Context.MODE_PRIVATE)
                    .getString(PREF_ACCOUNT_NAME, null);
            if (accountName != null) {
                mCredential.setSelectedAccountName(accountName);
                SharedPreferences user;
                user = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor ed = user.edit();
                ed.putString("usuario", mCredential.getSelectedAccountName());
                ed.apply();
                sincronizar();
            } else {
                // Start a dialog from which the user can choose an account
                startActivityForResult(
                        mCredential.newChooseAccountIntent(),
                        REQUEST_ACCOUNT_PICKER);
            }
        } else {
            // Request the GET_ACCOUNTS permission via a user dialog
            EasyPermissions.requestPermissions(
                    this,
                    "This app needs to access your Google account (via Contacts).",
                    REQUEST_PERMISSION_GET_ACCOUNTS,
                    Manifest.permission.GET_ACCOUNTS);
        }

    }

    /**
     * Called when an activity launched here (specifically, AccountPicker
     * and authorization) exits, giving you the requestCode you started it with,
     * the resultCode it returned, and any additional data from it.
     * @param requestCode code indicating which activity result is incoming.
     * @param resultCode code indicating the result of the incoming
     *     activity result.
     * @param data Intent (containing result data) returned by incoming
     *     activity result.
     */
    @Override
    protected void onActivityResult(
            int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case REQUEST_GOOGLE_PLAY_SERVICES:
                if (resultCode != RESULT_OK) {
                    System.out.println("This app requires Google Play Services. Please install Google Play Services on your device and relaunch this app.");
                    /*mOutputText.setText(
                            "This app requires Google Play Services. Please install " +
                                    "Google Play Services on your device and relaunch this app.");*/
                } else {
                    sincronizar();
                }
                break;
            case REQUEST_ACCOUNT_PICKER:
                if (resultCode == RESULT_OK && data != null &&
                        data.getExtras() != null) {
                    String accountName =
                            data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
                    if (accountName != null) {
                        SharedPreferences settings =
                                getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString(PREF_ACCOUNT_NAME, accountName);
                        editor.apply();
                        sincronizar();
                        System.out.println("sincronizando");
                    }
                }
                break;
            case REQUEST_AUTHORIZATION:
                if (resultCode == RESULT_OK) {
                    sincronizar();
                }
                break;
        }
    }

    /**
     * Respond to requests for permissions at runtime for API 23 and above.
     * @param requestCode The request code passed in
     *     requestPermissions(android.app.Activity, String, int, String[])
     * @param permissions The requested permissions. Never null.
     * @param grantResults The grant results for the corresponding permissions
     *     which is either PERMISSION_GRANTED or PERMISSION_DENIED. Never null.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(
                requestCode, permissions, grantResults, this);
    }

    /**
     * Callback for when a permission is granted using the EasyPermissions
     * library.
     * @param requestCode The request code associated with the requested
     *         permission
     * @param list The requested permission list. Never null.
     */
    @Override
    public void onPermissionsGranted(int requestCode, List<String> list) {
        // Do nothing.
    }

    /**
     * Callback for when a permission is denied using the EasyPermissions
     * library.
     * @param requestCode The request code associated with the requested
     *         permission
     * @param list The requested permission list. Never null.
     */
    @Override
    public void onPermissionsDenied(int requestCode, List<String> list) {
        // Do nothing.
    }

    /**
     * Checks whether the device currently has a network connection.
     * @return true if the device has a network connection, false otherwise.
     */
    private boolean isDeviceOnline() {
        ConnectivityManager connMgr =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    /**
     * Check that Google Play services APK is installed and up to date.
     * @return true if Google Play Services is available and up to
     *     date on this device; false otherwise.
     */
    private boolean isGooglePlayServicesAvailable() {
        GoogleApiAvailability apiAvailability =
                GoogleApiAvailability.getInstance();
        final int connectionStatusCode =
                apiAvailability.isGooglePlayServicesAvailable(this);
        return connectionStatusCode == ConnectionResult.SUCCESS;
    }

    /**
     * Attempt to resolve a missing, out-of-date, invalid or disabled Google
     * Play Services installation via a user dialog, if possible.
     */
    private void acquireGooglePlayServices() {
        GoogleApiAvailability apiAvailability =
                GoogleApiAvailability.getInstance();
        final int connectionStatusCode =
                apiAvailability.isGooglePlayServicesAvailable(this);
        if (apiAvailability.isUserResolvableError(connectionStatusCode)) {
            showGooglePlayServicesAvailabilityErrorDialog(connectionStatusCode);
        }
    }


    /**
     * Display an error dialog showing that Google Play Services is missing
     * or out of date.
     * @param connectionStatusCode code describing the presence (or lack of)
     *     Google Play Services on this device.
     */
    void showGooglePlayServicesAvailabilityErrorDialog(
            final int connectionStatusCode) {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        Dialog dialog = apiAvailability.getErrorDialog(
                MainActivity.this,
                connectionStatusCode,
                REQUEST_GOOGLE_PLAY_SERVICES);
        dialog.show();
    }

    /**
     * An asynchronous task that handles the Google Sheets API call.
     * Placing the API calls in their own task ensures the UI stays responsive.
     */
    private class MakeRequestTask extends AsyncTask<Void, Void, List<String>> {
        private com.google.api.services.sheets.v4.Sheets mService = null;
        private Exception mLastError = null;

        MakeRequestTask(GoogleAccountCredential credential) {
            HttpTransport transport = AndroidHttp.newCompatibleTransport();
            JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
            mService = new com.google.api.services.sheets.v4.Sheets.Builder(
                    transport, jsonFactory, credential)
                    .setApplicationName("Qual o poço?")
                    .build();
        }

        /**
         * Background task to call Google Sheets API.
         * @param params no parameters needed for this task.
         */
        @Override
        protected List<String> doInBackground(Void... params) {
            try {
                System.out.println("try to return get data from api");
                return getDataFromApi();
            } catch (Exception e) {
                System.out.println("error getDataFromAPI");
                mLastError = e;
                System.out.println("erro: "+e);
                e.printStackTrace();
                Log.i("Error:  ", "" + e);
                cancel(true);
                return null;
            }
        }

        /**
         * Fetch a list of names and majors of students in a sample spreadsheet:
         * https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit
         * @return List of names and majors
         * @throws IOException
         */
        private List<String> getDataFromApi() throws IOException {
            Cursor c=db.rawQuery("SELECT * FROM Historico", null);
            System.out.println("coluna 0 da tabela sqlite é: "+c.getColumnName(0));
            if (c.moveToFirst()){
                db.execSQL("DELETE FROM Historico");
            }
            c=db.rawQuery("SELECT * FROM I33", null);
            if (c.moveToFirst()){
                db.execSQL("DELETE FROM I33");
            }
            c=db.rawQuery("SELECT * FROM Parque", null);
            if (c.moveToFirst()){
                db.execSQL("DELETE FROM Parque");
            }
            c=db.rawQuery("SELECT * FROM I27", null);
            if (c.moveToFirst()){
                db.execSQL("DELETE FROM I27");
            }
            c=db.rawQuery("SELECT * FROM AlaE", null);
            if (c.moveToFirst()){
                db.execSQL("DELETE FROM AlaE");
            }
            c=db.rawQuery("SELECT * FROM P20", null);
            if (c.moveToFirst()){
                db.execSQL("DELETE FROM P20");
            }
            c=db.rawQuery("SELECT * FROM Litoteca", null);
            if (c.moveToFirst()){
                db.execSQL("DELETE FROM Litoteca");
            }
            c.close();
            List<String> listaHistorico = new ArrayList<String>();
            String spreadsheetId = "1I1qKGxq9iQQlVBuHVg22HJAL9YmRE_zB1keqCKmOQzw";
            String range = "Historico!A1:M";


            ValueRange response = this.mService.spreadsheets().values().get(spreadsheetId, range).execute();

            List<List<Object>> values = response.getValues();
            System.out.println("importando dados da tabela historico");
            if (values != null) {
                for (List row : values) {
                    db.execSQL("INSERT INTO Historico VALUES('"+row.get(0)+"','"+row.get(1)+"','"+row.get(2)+"','"+row.get(3)+"','"+row.get(4)+"','"+row.get(5)+"','"+row.get(6)+"','"+row.get(7)+"','"+row.get(8)+"','"+row.get(9)+"','"+row.get(10)+"','"+row.get(11)+"','"+row.get(12)+"');");

                    listaHistorico.add(row.get(0)+", "+row.get(1)+", "+row.get(2)+", "+row.get(3)+", "+row.get(4)+", "+row.get(5)+", "+row.get(6)+", "+row.get(7)+", "+row.get(8)+", "+row.get(9)+", "+row.get(10)+", "+row.get(11)+", "+row.get(12));

                }
            }

            range = "I33!A1:J";
            response = this.mService.spreadsheets().values()
                    .get(spreadsheetId, range)
                    .execute();
            values = response.getValues();
            if (values != null) {
                for (List row : values) {
                    db.execSQL("INSERT INTO I33 VALUES('"+row.get(0)+"','"+row.get(1)+"','"+row.get(2)+"','"+row.get(3)+"','"+row.get(4)+"','"+row.get(5)+"','"+row.get(6)+"','"+row.get(7)+"','"+row.get(8)+"','"+row.get(9)+"');");
                }
            }

            range = "I27!A1:J";
            response = this.mService.spreadsheets().values()
                    .get(spreadsheetId, range)
                    .execute();
            values = response.getValues();
            if (values != null) {
                for (List row : values) {
                    db.execSQL("INSERT INTO I27 VALUES('"+row.get(0)+"','"+row.get(1)+"','"+row.get(2)+"','"+row.get(3)+"','"+row.get(4)+"','"+row.get(5)+"','"+row.get(6)+"','"+row.get(7)+"','"+row.get(8)+"','"+row.get(9)+"');");

                }

            }

            range = "AlaE!A1:J";
            response = this.mService.spreadsheets().values()
                    .get(spreadsheetId, range)
                    .execute();
            values = response.getValues();
            if (values != null) {
                for (List row : values) {
                    db.execSQL("INSERT INTO AlaE VALUES('"+row.get(0)+"','"+row.get(1)+"','"+row.get(2)+"','"+row.get(3)+"','"+row.get(4)+"','"+row.get(5)+"','"+row.get(6)+"','"+row.get(7)+"','"+row.get(8)+"','"+row.get(9)+"');");
                }
            }

            range = "P20!A1:J";
            response = this.mService.spreadsheets().values()
                    .get(spreadsheetId, range)
                    .execute();
            values = response.getValues();
            if (values != null) {
                for (List row : values) {
                    db.execSQL("INSERT INTO P20 VALUES('"+row.get(0)+"','"+row.get(1)+"','"+row.get(2)+"','"+row.get(3)+"','"+row.get(4)+"','"+row.get(5)+"','"+row.get(6)+"','"+row.get(7)+"','"+row.get(8)+"','"+row.get(9)+"');");
                }
            }

            range = "Parque!A1:J";
            response = this.mService.spreadsheets().values()
                    .get(spreadsheetId, range)
                    .execute();
            values = response.getValues();
            if (values != null) {
                for (List row : values) {
                    db.execSQL("INSERT INTO Parque VALUES('"+row.get(0)+"','"+row.get(1)+"','"+row.get(2)+"','"+row.get(3)+"','"+row.get(4)+"','"+row.get(5)+"','"+row.get(6)+"','"+row.get(7)+"','"+row.get(8)+"','"+row.get(9)+"');");
                }
            }

            range = "Litoteca!A1:J";
            response = this.mService.spreadsheets().values()
                    .get(spreadsheetId, range)
                    .execute();
            values = response.getValues();
            if (values != null) {
                for (List row : values) {
                    db.execSQL("INSERT INTO Litoteca VALUES('"+row.get(0)+"','"+row.get(1)+"','"+row.get(2)+"','"+row.get(3)+"','"+row.get(4)+"','"+row.get(5)+"','"+row.get(6)+"','"+row.get(7)+"','"+row.get(8)+"','"+row.get(9)+"');");
                }
            }

            return listaHistorico;
        }



        @Override
        protected void onPreExecute() {
            //mOutputText.setText("");
            mProgress.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            mProgress.show();
        }

        @Override
        protected void onPostExecute(List<String> output) {
            mProgress.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            mProgress.hide();
            if (output == null || output.size() == 0) {
                //mOutputText.setText("No results returned.");
            } else {
                //output.add(0, "Data retrieved using the Google Sheets API:");
                //mOutputText.setText(TextUtils.join("\n\n", output));
            }
        }

        @Override
        protected void onCancelled() {
            mProgress.hide();
            if (mLastError != null) {
                if (mLastError instanceof GooglePlayServicesAvailabilityIOException) {
                    showGooglePlayServicesAvailabilityErrorDialog(
                            ((GooglePlayServicesAvailabilityIOException) mLastError)
                                    .getConnectionStatusCode());
                } else if (mLastError instanceof UserRecoverableAuthIOException) {
                    startActivityForResult(
                            ((UserRecoverableAuthIOException) mLastError).getIntent(),
                            MainActivity.REQUEST_AUTHORIZATION);
                } else {
                    //mOutputText.setText("The following error occurred:\n"
                            //+ mLastError.getMessage());
                }
            } else {
                //mOutputText.setText("Request cancelled.");
            }
        }
    }


}

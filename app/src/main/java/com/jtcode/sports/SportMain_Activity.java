package com.jtcode.sports;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

public class SportMain_Activity extends AppCompatActivity {

    Adapter adapter;
    ListView lvSport;
    FloatingActionButton fabAcept;
    SharedPreferences sharedPreferences;
    boolean filtredflag;
    char charFilt;
    final String FILT="filt";
    final String CHARFILT="char";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_main_);
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        if(savedInstanceState!=null) {
            charFilt=savedInstanceState.getChar(CHARFILT);
            filtredflag=savedInstanceState.getBoolean(FILT);
            init(filtredflag);

        }
        else{
            init(false);
        }
    }

    private void init(boolean create) {
        adapter = new Adapter(this);

        lvSport=(ListView)findViewById(R.id.listSports);

        fabAcept=(FloatingActionButton)findViewById(R.id.fabOK);

        lvSport.setAdapter(adapter);

        fabAcept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //guargar en las preferencias
                savePrefs();
                Toast.makeText(SportMain_Activity.this,getString(R.string.saveData),Toast.LENGTH_SHORT).show();
            }
        });

        if (create){
           applyFilter();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(FILT,filtredflag);
        outState.putChar(CHARFILT,charFilt);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_search,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       super.onOptionsItemSelected(item);

        if(item.getItemId()==R.id.option_finbyChar){
            final EditText editTextAD;

            AlertDialog.Builder alertD=new AlertDialog.Builder(SportMain_Activity.this);
            alertD.setTitle(getString(R.string.title_ad)).setCancelable(true).setMessage(getString(R.string.admens));

            View v=getLayoutInflater().inflate(R.layout.alert_dialog_custom,null);

            editTextAD=(EditText)v.findViewById(R.id.edtCustomAD);

            alertD.setView(editTextAD);

            alertD.setPositiveButton(getString(R.string.title_ad), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if(editTextAD.getText().toString().trim().length()!=0) {
                        charFilt = editTextAD.getText().toString().charAt(0);
                    }
                    else{
                        charFilt=' ';
                    }
                    applyFilter();
                }
            }).create();
            ViewGroup parent=(ViewGroup)v;
            parent.removeAllViews();
            alertD.show();
        }
        return true;
    }

    private void applyFilter(){

        if (String.valueOf(charFilt).trim().length() != 0) {
            adapter.applyCharFilter(charFilt);
            filtredflag=true;
        } else {
            filtredflag=false;
            adapter.reloadAllSports();
        }
    }

    private void savePrefs(){
        SharedPreferences.Editor editor=sharedPreferences.edit();

        for (int i=0;i< lvSport.getCount();i++) {

            if(adapter.getItem(i).isChecked())

                editor.putString(adapter.getItem(i).getName(),adapter.getItem(i).getName());//añadelo su nombre como clave y su nombre como valor

            else
                editor.remove(adapter.getItem(i).getName());
        }
        //confirmacion
        editor.commit();
    }

}

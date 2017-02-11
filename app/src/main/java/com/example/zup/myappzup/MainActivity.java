package com.example.zup.myappzup;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.AdapterView;
import android.widget.ListView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    public ListView lvFilmes;
    public static FilmesControler mfilmes;
    public FilmeAdapter filmesAdapter;
    public ArrayList<Filmes> listaFilmes;
    public AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvFilmes = (ListView) findViewById(R.id.lvFilmes);

        listaFilmes = new ArrayList<>();
        listaFilmes.clear();



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),AddFilmeActivity.class);
                startActivity(intent);
            }
        });

        lvFilmes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("lvFilmesClick",String.valueOf(listaFilmes.get(position).getId_filme()));

                Intent filmeint = new Intent(getApplicationContext(),FilmeActivity.class);
                filmeint.putExtra("id",listaFilmes.get(position).getId_filme());
                startActivity(filmeint);

            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        mfilmes = new FilmesControler(this);
        listaFilmes = mfilmes.getAll();
        filmesAdapter = new FilmeAdapter(this,listaFilmes);
        lvFilmes.setAdapter(filmesAdapter);
    }







}

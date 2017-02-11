package com.example.zup.myappzup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Anderson Silva on 01/02/2017.
 */

public class FilmesControler extends  Filmes {

    public Context context ;

    public FilmesControler(Context ctx){
        this.context = ctx;
    }

    public long insert(Filmes filme){
        SQLiteDatabase db = new DBHelper(context).getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(KEY_TITLE,filme.getTitle());
        values.put(KEY_YEAR,filme.getYear());
        values.put(KEY_RELEASED,filme.getReleased());
        values.put(KEY_RUNTIME,filme.getRuntime());
        values.put(KEY_GENRE,filme.getGenre());
        values.put(KEY_DIRECTOR,filme.getDirector());
        values.put(KEY_WRITER,filme.getWriter());
        values.put(KEY_ACTORS,filme.getActors());
        values.put(KEY_PLOT,filme.getPlot());
        values.put(KEY_LANGUAGE,filme.getLanguage());
        values.put(KEY_CONTRY,filme.getCountry());
        values.put(KEY_AWARDS,filme.getAwards());
        values.put(KEY_POSTER,filme.getPoster());
        values.put(KEY_IMDB_RATING,filme.getImdb_rating());


        // INSERE ROW NA BASE
        long filme_id = db.insert(TABLE_FILMES,null,values);
        return filme_id;
    }

    public Filmes getFilme(long filme_id){

        SQLiteDatabase db = new DBHelper(context).getWritableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_FILMES + " WHERE " + KEY_ID + " = " + filme_id;
        Cursor c = db.rawQuery(selectQuery,null);
        Log.e("FilmesController",selectQuery);
        if(c != null) {
            c.moveToFirst();
        }

        Filmes f = new Filmes();
        f.setTitle(c.getString(c.getColumnIndex(KEY_TITLE)));
        f.setYear(c.getString(c.getColumnIndex(KEY_YEAR)));
        f.setReleased(c.getString(c.getColumnIndex(KEY_RELEASED)));
        f.setRuntime(c.getString(c.getColumnIndex(KEY_RUNTIME)));
        f.setGenre(c.getString(c.getColumnIndex(KEY_GENRE)));
        f.setDirector(c.getString(c.getColumnIndex(KEY_DIRECTOR)));
        f.setWriter(c.getString(c.getColumnIndex(KEY_WRITER)));
        f.setActors(c.getString(c.getColumnIndex(KEY_ACTORS)));
        f.setPlot(c.getString(c.getColumnIndex(KEY_PLOT)));
        f.setLanguage(c.getString(c.getColumnIndex(KEY_LANGUAGE)));
        f.setCountry(c.getString(c.getColumnIndex(KEY_CONTRY)));
        f.setAwards(c.getString(c.getColumnIndex(KEY_AWARDS)));
        f.setPoster(c.getString(c.getColumnIndex(KEY_POSTER)));
        f.setImdb_rating(c.getString(c.getColumnIndex(KEY_IMDB_RATING)));


        return f;

    }

    public ArrayList<Filmes> getAll(){
        ArrayList<Filmes> lista = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_FILMES;

        Log.e("FilmesController",selectQuery);

        SQLiteDatabase db = new DBHelper(context).getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery,null);

        if(c.moveToFirst()){
            do{
                Filmes f = new Filmes();
                f.setId_filme(c.getInt((c.getColumnIndex(KEY_ID))));
                f.setTitle(c.getString(c.getColumnIndex(KEY_TITLE)));
                f.setYear(c.getString(c.getColumnIndex(KEY_YEAR)));
                f.setReleased(c.getString(c.getColumnIndex(KEY_RELEASED)));
                f.setRuntime(c.getString(c.getColumnIndex(KEY_RUNTIME)));
                f.setGenre(c.getString(c.getColumnIndex(KEY_GENRE)));
                f.setDirector(c.getString(c.getColumnIndex(KEY_DIRECTOR)));
                f.setWriter(c.getString(c.getColumnIndex(KEY_WRITER)));
                f.setActors(c.getString(c.getColumnIndex(KEY_ACTORS)));
                f.setPlot(c.getString(c.getColumnIndex(KEY_PLOT)));
                f.setLanguage(c.getString(c.getColumnIndex(KEY_LANGUAGE)));
                f.setCountry(c.getString(c.getColumnIndex(KEY_CONTRY)));
                f.setAwards(c.getString(c.getColumnIndex(KEY_AWARDS)));
                f.setPoster(c.getString(c.getColumnIndex(KEY_POSTER)));
                f.setImdb_rating(c.getString(c.getColumnIndex(KEY_IMDB_RATING)));


                lista.add(f);

            }while(c.moveToNext());
        }
        return lista;
    }



}

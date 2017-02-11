package com.example.zup.myappzup;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Anderson Silva on 01/02/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    // Versao da base de dados
    private static final int DATABASE_VERSION = 1;

    // Nome da Database
    private static final String DATABASE_NAME = "bdzup";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Filmes.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {



    }

}

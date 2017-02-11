package com.example.zup.myappzup;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zup.myappzup.utils.ImageLoader;

import static com.example.zup.myappzup.AddFilmeActivity.imageLoader;

public class FilmeActivity extends AppCompatActivity {


    public static FilmesControler mfilmes;
    public static Filmes oFilme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filme);

        ImageView imageViewPoster = (ImageView) findViewById(R.id.imageViewPoster);
        TextView  textViewTitle = (TextView) findViewById(R.id.textViewTitle);
        TextView  textViewYear = (TextView) findViewById(R.id.textViewYear);
        TextView  textViewReleased = (TextView) findViewById(R.id.textViewReleased);
        TextView  textViewRuntime = (TextView) findViewById(R.id.textViewRuntime);
        TextView  textViewGenre = (TextView) findViewById(R.id.textViewGenre);
        TextView  textViewDirector = (TextView) findViewById(R.id.textViewDirector);
        TextView  textViewWriter = (TextView) findViewById(R.id.textViewWriter);
        TextView  textViewActors = (TextView) findViewById(R.id.textViewActors);
        TextView  textViewPlot = (TextView) findViewById(R.id.textViewPlot);
        TextView  textViewLanguage = (TextView) findViewById(R.id.textViewLanguage);
        TextView  textViewCountry = (TextView) findViewById(R.id.textViewCountry);
        TextView  textViewAwards = (TextView) findViewById(R.id.textViewAwards);

        imageLoader = new ImageLoader(getApplicationContext());



        mfilmes = new FilmesControler(this);
        oFilme = mfilmes.getFilme(getIntent().getIntExtra("id", 0));

        textViewTitle.setText(oFilme.getTitle());
        textViewYear.setText(oFilme.getYear());
        textViewReleased.setText(oFilme.getReleased());
        textViewRuntime.setText(oFilme.getRuntime());
        textViewGenre.setText(oFilme.getGenre());
        textViewDirector.setText(oFilme.getDirector());
        textViewWriter.setText(oFilme.getWriter());
        textViewActors.setText(oFilme.getActors());
        textViewPlot.setText(oFilme.getPlot());
        textViewLanguage.setText(oFilme.getLanguage());
        textViewCountry.setText(oFilme.getCountry());
        textViewAwards.setText(oFilme.getAwards());


        imageLoader.DisplayImage(oFilme.getPoster(), imageViewPoster, 500);

    }
}

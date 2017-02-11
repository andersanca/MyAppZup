package com.example.zup.myappzup;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zup.myappzup.utils.ImageLoader;
import com.example.zup.myappzup.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

public class AddFilmeActivity extends AppCompatActivity  {

    public EditText edBuscaFilme;
    public static String strBusca;
    public static FilmesControler mfilmes;
    public static TextView tvTituloFilme;
    public static ImageView imageViewFilme;
    public static ImageLoader imageLoader;
    public static Filmes oFilme;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_filme);

        edBuscaFilme = (EditText) findViewById(R.id.edBuscaFilme);
        mfilmes = new FilmesControler(this);
        tvTituloFilme = (TextView) findViewById(R.id.tvTituloFilme);
        imageViewFilme = (ImageView) findViewById(R.id.imageViewFilme);
        imageLoader = new ImageLoader(this);
        mfilmes = new FilmesControler(this);


        findViewById(R.id.buttonBuscar).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                imageViewFilme.setVisibility(View.INVISIBLE);
                strBusca = edBuscaFilme.getText().toString();

                new FilmesTask().execute();

            }
        });

        findViewById(R.id.buttonAdd).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                if(oFilme != null){
                    mfilmes.insert(oFilme);
                    oFilme = null;
                    imageViewFilme.setVisibility(View.INVISIBLE);
                    tvTituloFilme.setText("");
                    Toast.makeText(getApplicationContext(),"Adicionado com sucesso",Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(getApplicationContext(),"Busque por um filme",Toast.LENGTH_SHORT).show();

            }
        });
        findViewById(R.id.buttonVoltar).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // do stuff
                Intent voltar = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(voltar);
                finish();

            }
        });
    }

    public static class FilmesTask extends AsyncTask<Void,Void,JSONObject> {


        @Override
        protected JSONObject doInBackground(Void... params) {


            try {
                URL url = new URL("http://www.omdbapi.com/?t="+strBusca+"&y=&plot=short&r=json");

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                //urlConnection.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
                //urlConnection.setRequestProperty("Accept","*/*");
                urlConnection.setConnectTimeout(5000); //set timeout to 5 seconds
                try{
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    urlConnection.disconnect();
                    return Util.readJsonFromHttpResponse(in);
                }catch(IOException e1){
                    //offline = true;
                    e1.printStackTrace();
                }finally {
                    urlConnection.disconnect();
                }

            }catch (SocketTimeoutException e){
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(JSONObject result) {

            if(result != null){





                JSONObject jfilme = result;

                Log.e("FilmesTask",result.toString());

                try {
                    if(jfilme.getString("Response").equalsIgnoreCase("False")){
                        tvTituloFilme.setText("Filme não encontrado");
                    }else {

                        tvTituloFilme.setText(jfilme.getString("Title"));
                        // ADD FILME
                        imageViewFilme.setVisibility(View.VISIBLE);
                        imageLoader.DisplayImage(jfilme.getString("Poster"), imageViewFilme, 500);

                        oFilme = new Filmes();
                        oFilme.setTitle(jfilme.getString("Title"));
                        oFilme.setYear(jfilme.getString("Year"));
                        oFilme.setReleased(jfilme.getString("Released"));
                        oFilme.setRuntime(jfilme.getString("Runtime"));
                        oFilme.setGenre(jfilme.getString("Genre"));
                        oFilme.setDirector(jfilme.getString("Director"));
                        oFilme.setWriter(jfilme.getString("Writer"));
                        oFilme.setActors(jfilme.getString("Actors"));
                        oFilme.setPlot(jfilme.getString("Plot"));
                        oFilme.setLanguage(jfilme.getString("Language"));
                        oFilme.setCountry(jfilme.getString("Country"));
                        oFilme.setAwards(jfilme.getString("Awards"));
                        oFilme.setPoster(jfilme.getString("Poster"));
                        oFilme.setImdb_rating(jfilme.getString("imdbRating"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else{
                imageViewFilme.setVisibility(View.INVISIBLE);
                tvTituloFilme.setText("Tente novamente");
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tvTituloFilme.setText("Buscando filme, aguarde....");
        }
    }

}

package com.example.zup.myappzup;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zup.myappzup.utils.ImageLoader;

import java.util.ArrayList;

/**
 * Created by Anderson Silva on 02/02/2017.
 */

public class FilmeAdapter extends ArrayAdapter<Filmes> {

    private Context context;
    private ArrayList<Filmes> lista;
    public ImageLoader imageLoader;
    private LayoutInflater inflater;


    public class ViewHolder{
        TextView tvTitle,tvGenre,tvYear;
        ImageView ivImage;

    }

    public FilmeAdapter(Context context, ArrayList<Filmes> lista){

        super(context,0,lista);
        this.context = context;
        this.lista = lista;
        imageLoader = new ImageLoader(context);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Filmes itemFilme = this.lista.get(position);
        ViewHolder viewHolder = new ViewHolder();
        if(convertView==null) {
            LayoutInflater inflater = ((Activity) context)
                    .getLayoutInflater();
            convertView = inflater.inflate(R.layout.row_filme, parent,
                    false);


            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.ivImage = (ImageView) convertView.findViewById(R.id.ivImage);
            viewHolder.tvGenre = (TextView) convertView.findViewById(R.id.tvGenre);
            viewHolder.tvYear = (TextView) convertView.findViewById(R.id.tvYear);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.tvTitle.setText(itemFilme.getTitle());
        viewHolder.tvGenre.setText(itemFilme.getGenre());
        viewHolder.tvYear.setText(itemFilme.getYear());

        imageLoader.DisplayImage(itemFilme.getPoster(), viewHolder.ivImage);

        return convertView;

    }

}

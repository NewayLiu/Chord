package com.thechord.chord.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.thechord.chord.R;
import com.thechord.chord.entity.DouBanMovie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Neway on 2015/10/11.
 */
public class FilmGridViewAdapter extends BaseAdapter {

    private Context context;
    List<DouBanMovie> movieList = new ArrayList<>();

    public FilmGridViewAdapter(Context context){
        this.context = context;
    }


    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public DouBanMovie getItem(int position) {
        return movieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void update(List<DouBanMovie> movieList){
        this.movieList.addAll(movieList);
        notifyDataSetChanged();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_view_file_item,parent,false);
            holder = new ViewHolder();
            holder.imgViewMovie = (ImageView) convertView.findViewById(R.id.imgView_movie_img);
            //holder.txtViewName = (TextView) convertView.findViewById(R.id.txtView_movie_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        DouBanMovie movie = movieList.get(position);
        ImageLoader.getInstance().displayImage(movie.getImage().getLarge(),holder.imgViewMovie);
        //holder.txtViewName.setText(movie.getTitle());
        return convertView;
    }

    private class ViewHolder{
        ImageView imgViewMovie;
        //TextView txtViewName;
    }

}

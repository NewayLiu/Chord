package com.thechord.chord;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.thechord.chord.adapter.FilmGridViewAdapter;
import com.thechord.chord.entity.DouBanMovie;
import com.thechord.chord.net.DouBanAPI;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class MainActivity extends BaseActivity {

    private GridView gridMovies;
    private FilmGridViewAdapter filmGridViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        filmGridViewAdapter = new FilmGridViewAdapter(this);
        gridMovies.setAdapter(filmGridViewAdapter);
        DouBanAPI.getMovieTop250(1, new DouBanAPI.DouBanAPICallback() {
            @Override
            public void onGetDouBanBeanFromServer(List<DouBanMovie> movieList) {
                filmGridViewAdapter.update(movieList);
            }
        });
    }

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        gridMovies = (GridView) findViewById(R.id.grid_movies);
    }

    private void initListener(){

    }

}

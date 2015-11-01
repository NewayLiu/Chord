package com.thechord.chord;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.thechord.chord.adapter.FilmGridViewAdapter;
import com.thechord.chord.entity.DouBanMovie;
import com.thechord.chord.net.DouBanAPI;

import java.util.List;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;


@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @InjectView(R.id.grid_movies)
    private PullToRefreshGridView gridMovies;
    private FilmGridViewAdapter filmGridViewAdapter;
    private int currentPageNum = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        filmGridViewAdapter = new FilmGridViewAdapter(this);
        gridMovies.setAdapter(filmGridViewAdapter);
        DouBanAPI.getMovieTop250(currentPageNum, new DouBanAPI.DouBanAPICallback() {
            @Override
            public void onGetDouBanBeanFromServer(List<DouBanMovie> movieList) {
                filmGridViewAdapter.update(movieList);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        initView();
    }

    protected void initView() {
        gridMovies.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
    }

    @Override
    protected void registerListener(){
        gridMovies.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<GridView>() {
            @Override
            public void onRefresh(final PullToRefreshBase<GridView> pullToRefreshBase) {
                currentPageNum += 1;
                DouBanAPI.getMovieTop250(currentPageNum, new DouBanAPI.DouBanAPICallback() {
                    @Override
                    public void onGetDouBanBeanFromServer(List<DouBanMovie> movieList) {
                        pullToRefreshBase.onRefreshComplete();
                        filmGridViewAdapter.update(movieList);
                    }
                });
            }
        });

        gridMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MovieDetailActivity.class);
                intent.putExtra("movie", filmGridViewAdapter.getItem(position));
                startActivity(intent);
            }
        });
    }

}

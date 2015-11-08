package com.thechord.chord.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.thechord.chord.R;
import com.thechord.chord.adapter.ActorsAdapter;
import com.thechord.chord.entity.Actor;
import com.thechord.chord.entity.DouBanMovie;
import com.thechord.chord.net.DouBanAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import roboguice.inject.InjectView;

/**
 * Created by Neway on 2015/10/12.
 */
public class MovieIntroductionFragment extends BaseFragment {

    private static final String TAG = "MovieIntroduction";

    @InjectView(R.id.imgView_movie_name)
    private TextView txtViewName;

    @InjectView(R.id.imgView_movie_intro_img)
    private ImageView  imgViewMovieImg;

    @InjectView(R.id.imgView_movie_tag)
    private TextView txtViewMovieTag;

    @InjectView(R.id.imgView_movie_year)
    private TextView txtViewYear;

    @InjectView(R.id.txtView_movie_brief)
    private TextView textViewSummery;

    @InjectView(R.id.listView_actors)
    private ListView listViewActors;

    private DouBanMovie movie;
    private ActorsAdapter actorsAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_introduction, container);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listViewActors.setAdapter(actorsAdapter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movie = getActivity().getIntent().getParcelableExtra("movie");
        actorsAdapter = new ActorsAdapter(this.getActivity());
    }

    @Override
    public void onStart() {
        super.onStart();
        DouBanAPI.loadMovieDetail(movie.getId(), new DouBanAPI.DouBanAPICallback() {
            @Override
            public void onGetDouBanBeanFromServer(DouBanMovie douBanMovie) {
                movie = douBanMovie;
                initView(douBanMovie);
            }
        });
    }


    @Override
    protected void registerListener(){
        textViewSummery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textViewSummery.getMaxLines() == Integer.MAX_VALUE) {
                    textViewSummery.setMaxLines(4);
                } else {
                    textViewSummery.setMaxLines(Integer.MAX_VALUE);
                }
            }
        });
    }



    protected void initView(DouBanMovie movie) {
        txtViewName.setText(movie.getTitle());
        ImageLoader.getInstance().displayImage(movie.getImage().getLarge(), imgViewMovieImg);
        txtViewYear.setText(movie.getYear());
        textViewSummery.setText(movie.getSummary());
        StringBuilder sb = new StringBuilder();
        for(String tag : movie.getGenres()) {
            sb.append(tag);
            sb.append(" ");
        }
        txtViewMovieTag.setText(sb.toString());
        List<Actor> actorList = new ArrayList<>();
        for(Actor actor : movie.getCasts()){
            actorList.add(actor);
        }
        actorsAdapter.updateData(actorList);
    }


}

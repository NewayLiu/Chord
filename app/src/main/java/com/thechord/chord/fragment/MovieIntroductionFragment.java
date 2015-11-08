package com.thechord.chord.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.inject.Inject;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.thechord.chord.R;
import com.thechord.chord.entity.DouBanMovie;
import com.thechord.chord.net.DouBanAPI;

import roboguice.fragment.RoboFragment;
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


    private DouBanMovie movie;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_introduction, container);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movie = getActivity().getIntent().getParcelableExtra("movie");
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
    }


}

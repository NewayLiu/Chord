package com.thechord.chord.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.inject.Inject;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.thechord.chord.R;
import com.thechord.chord.entity.DouBanMovie;

import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;

/**
 * Created by Neway on 2015/10/12.
 */
public class MovieIntroductionFragment extends BaseFragment {

    @InjectView(R.id.imgView_movie_name)
    private TextView txtViewName;

    @InjectView(R.id.imgView_movie_intro_img)
    private ImageView  imgViewMovieImg;


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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    protected void initView() {
        txtViewName.setText(movie.getTitle());
        ImageLoader.getInstance().displayImage(movie.getImage().getLarge(),imgViewMovieImg);
    }

}

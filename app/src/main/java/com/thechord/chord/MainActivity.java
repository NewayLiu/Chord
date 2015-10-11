package com.thechord.chord;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
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


    private boolean firstLoad = true;

    private ImageView movieImage;

    private CheckBox checkBox;

    private List<DouBanMovie> mMovieList = new ArrayList<>();

    private int currentPageNumber = 0;

    private int currentIndex = 0;

    private DouBanAPI.DouBanAPICallback apiCallback;

    private Set<String> likeSet = new HashSet<>();

    private int MAX_IMAGE_SIZE = 60;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieImage = (ImageView) this.findViewById(R.id.movieImage);
        checkBox = (CheckBox) this.findViewById(R.id.seenCheckBox);
        apiCallback = new DouBanAPI.DouBanAPICallback() {
            @Override
            public void onGetDouBanBeanFromServer(List<DouBanMovie> movieList) {
                if(mMovieList.size() == MAX_IMAGE_SIZE){
                    for(int i = 0; i< 20 ;i++){
                        mMovieList.remove(0);
                    }
                    if(currentIndex > 19){
                        currentIndex -= 20;
                    }
                    mMovieList.addAll(movieList);
                } else {
                    mMovieList.addAll(movieList);
                    if(firstLoad){
                        ImageLoader.getInstance().displayImage(mMovieList.get(currentIndex++).getImage().getLarge(),movieImage);
                        firstLoad = false;
                    }
                }
            }
        };



        DouBanAPI.getMovieTop250(currentPageNumber,apiCallback);

        movieImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentIndex < mMovieList.size()){
                    ImageLoader.getInstance().displayImage(mMovieList.get(currentIndex++).getImage().getLarge(),movieImage);
                } else {
                    currentPageNumber++;
                    DouBanAPI.getMovieTop250(currentPageNumber,apiCallback);
                }

            }
        });

        initListener();
    }

    private void initListener(){
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    likeSet.add(mMovieList.get(currentIndex).getId());
                } else {
                    likeSet.remove(mMovieList.get(currentIndex).getId());
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

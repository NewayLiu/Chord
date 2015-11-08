package com.thechord.chord.net;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.thechord.chord.entity.DouBanMovie;
import com.thechord.chord.util.JSONUtil;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Neway on 2015/8/19.
 */
public class DouBanAPI {

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static final String HOST = "http://api.douban.com";
    public static final String MOVIE_TOP_250 = "/v2/movie/top250";
    public static final String MOVIE_ITEM_DETAIL = "/v2/movie/subject/";
    public static final int PAGE_SIZE = 21;


    public static String getAbsoluteURL(String path) {
        return HOST + path;
    }

    public static void getMovieTop250(int pageNumber, final DouBanAPICallback callback) {

        int start = pageNumber * PAGE_SIZE;

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("start", String.valueOf(start));
        paramMap.put("count", String.valueOf(PAGE_SIZE));

        get(MOVIE_TOP_250, new RequestParams(paramMap), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    List<DouBanMovie> movieList = JSONUtil.getJSONUtil().getInstanceFromJSONObject(response.getJSONArray("subjects"), new DouBanMovie().getClass());
                    callback.onGetDouBanBeanFromServer(movieList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }


    public static void loadMovieDetail(String movieID, final DouBanAPICallback callback){
        get(MOVIE_ITEM_DETAIL+movieID,null,new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                DouBanMovie douBanMovie = (DouBanMovie) JSONUtil.getJSONUtil().getInstanceFromJsonObject(response,new DouBanMovie().getClass());
                callback.onGetDouBanBeanFromServer(douBanMovie);
            }
        });

    }


    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteURL(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteURL(url), params, responseHandler);
    }

    public static abstract class DouBanAPICallback {

        public void onGetDouBanBeanFromServer(List<DouBanMovie> movieList){

        }

        public void onGetDouBanBeanFromServer(DouBanMovie douBanMovie){

        }
    }
}

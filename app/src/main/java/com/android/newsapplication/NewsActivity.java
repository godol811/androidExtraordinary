package com.android.newsapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private String myDataset [] = {"1","2","3","4"};

    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)


        queue = Volley.newRequestQueue(this);
        getNews();

        //1,화면이 로딩 -> 뉴스 정보를 받아온다
        //2.정보 -> 어댑터에 넘긴다.
        //3.어댑터 -> 셋팅



    }

    public void getNews() {
        // Instantiate the RequestQueue.
        // 발급 받은 주소
        String url ="http://newsapi.org/v2/top-headlines?country=kr&apiKey=bb8ee225a55d4465903b8c797d07ec5f";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("종찬",response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            JSONArray arrayArticles = jsonObject.getJSONArray("articles"); //아티클 값 가져오기


                            List<NewsData> news = new ArrayList<>();

                            for (int i =0 ,j = arrayArticles.length(); i <j; i++)
                            { JSONObject object = arrayArticles.getJSONObject(i);

                                NewsData newsData = new NewsData();


                                //제이슨 정보 가져와오기
                                newsData.setTitle(object.getString("title"));
                                newsData.setUrlToImage(object.getString("urlToImage"));
                                newsData.setContent(object.getString("content"));
                                //가져온거 어레이 리스트에 넣기
                                news.add(newsData);
                            }
                            //   response -> NEWS data 분류



                            mAdapter = new MyAdapter(news,NewsActivity.this);
                            recyclerView.setAdapter(mAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }
                } , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

}
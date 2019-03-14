package com.example.newsreader;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.newsreader.Adapter.SourceAdapter;
import com.example.newsreader.Base.BaseActivity;
import com.example.newsreader.Model.SourcesResponse.SourcesItem;
import com.example.newsreader.Repos.NewsRepository;

import java.util.List;

public class SourceActivity extends BaseActivity {
    protected RecyclerView recyclerView;
    final String lang = "en";
    NewsRepository newsRepository;
    SourceAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source);

        recyclerView = findViewById(R.id.list_source);
        layoutManager = new GridLayoutManager(activity , 2);
        adapter = new SourceAdapter(null);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        newsRepository = new NewsRepository(lang);
        showProgressBar(R.string.please_wait,R.string.loading);
        newsRepository.getNewsSource(onSourcesPreparedListener);

    }

    NewsRepository.OnSourcesPreparedListener onSourcesPreparedListener =
            new NewsRepository.OnSourcesPreparedListener() {
                @Override
                public void onSourcesPrepared(final List<SourcesItem> sources) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            hideProgressBar();
                            adapter = new SourceAdapter(sources);
                            recyclerView.setAdapter(adapter);
                            adapter.setOnTextClickListener(new SourceAdapter.onItemClickListener() {
                                @Override
                                public void onItemClick(int position, SourcesItem sourcesItem) {
                                    Log.e("test","sha8al");
                                    News.source=sourcesItem;
                                    startActivity(new Intent(activity,News.class));
                                }
                            });

                        }
                    });


                }
            };


}


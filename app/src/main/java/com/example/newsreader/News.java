package com.example.newsreader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.newsreader.Adapter.NewsAdapter;
import com.example.newsreader.Base.BaseActivity;
import com.example.newsreader.Model.NewsResponse.ArticlesItem;
import com.example.newsreader.Model.SourcesResponse.SourcesItem;
import com.example.newsreader.Repos.NewsRepository;

import java.util.List;

public class News extends BaseActivity {

    protected RecyclerView listNews;
    final String lang = "en";
    NewsRepository newsRepository;
    NewsAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    static SourcesItem source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_news);
        initView();
        layoutManager = new LinearLayoutManager(activity);
        adapter = new NewsAdapter(null);
        listNews.setLayoutManager(layoutManager);
        listNews.setAdapter(adapter);
        newsRepository = new NewsRepository(lang);
        showProgressBar(R.string.please_wait,R.string.loading);
        newsRepository.getNews(source.getId(),onNewsPreparedListner);
    }

    NewsRepository.OnNewsPreparedListener onNewsPreparedListner = new NewsRepository.OnNewsPreparedListener() {
        @Override
        public void onNewsPrepared(final List<ArticlesItem> articles) {
            //handle recycler view
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    hideProgressBar();
                    adapter.changeData(articles);
                    adapter.setOnNewsClickListener(new NewsAdapter.onItemClickListener() {
                        @Override
                        public void onItemClick(int position, ArticlesItem articlesItem) {
                            Intent intent = new Intent(activity ,ArticleDetails.class);
                            intent.putExtra("url",articlesItem.getUrl());
                            startActivity(intent);
                        }
                    });

                }
            });
        }
    };

    private void initView() {
        listNews = (RecyclerView) findViewById(R.id.list_news);
    }
}

package com.example.newsreader.Repos;


import com.example.newsreader.API.ApiManagerNews;
import com.example.newsreader.DataBase.NewsDataBase;

import com.example.newsreader.Model.NewsResponse.ArticlesItem;
import com.example.newsreader.Model.NewsResponse.NewsResponse;
import com.example.newsreader.Model.SourcesResponse.SourcesItem;
import com.example.newsreader.Model.SourcesResponse.SourcesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {

    String Lang;
    final String APIKEY="17c3229d4c5242758b2a4a079a38c177";

    public NewsRepository(String lang) {
        Lang = lang;
    }

    public void getNewsSource(final OnSourcesPreparedListener onSourcesPreparedListener ){
        ApiManagerNews.getNewsAPIs()
                .getAllSources(Lang,APIKEY)
                .enqueue(new Callback<SourcesResponse>() {
                    @Override
                    public void onResponse(Call<SourcesResponse> call,
                                           Response<SourcesResponse> response) {
                        if(response.isSuccessful()){
                            if(onSourcesPreparedListener!=null){
                                onSourcesPreparedListener.onSourcesPrepared(response.body().getSources());
                                InsertSourcesToDataBase myth =
                                        new InsertSourcesToDataBase(response.body().getSources());
                                myth.start();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<SourcesResponse> call,
                                          Throwable t) {
                        GETSourcesFromDataBase myth =
                                new GETSourcesFromDataBase(onSourcesPreparedListener);
                        myth.start();
                    }
                });

    }

    public void getNews(final String SourceId, final OnNewsPreparedListener onNewsPreparedListener){
        ApiManagerNews.getNewsAPIs()
                .getNewsBySourceId(Lang,APIKEY,SourceId)
                .enqueue(new Callback<NewsResponse>() {
                    @Override
                    public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                        if(response.isSuccessful()){
                            if(onNewsPreparedListener!=null){
                              onNewsPreparedListener.onNewsPrepared(response.body().getArticles());
                            //insert new Articles to DB
                                InsertNewsIntoDBThread th =new InsertNewsIntoDBThread(response.body().getArticles());
                                th.start();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<NewsResponse> call, Throwable t) {
                        //get News from DB
                        GetNewsFromDBThread th = new GetNewsFromDBThread(SourceId,onNewsPreparedListener);
                        th.start();

                    }
                });
    }



    class GetNewsFromDBThread extends Thread{

        String sourceId ;
        OnNewsPreparedListener onNewsPreparedListener;
        public GetNewsFromDBThread(String sourceId,OnNewsPreparedListener onNewsPreparedListener){
            this.sourceId=sourceId;
            this.onNewsPreparedListener=onNewsPreparedListener;
        }

        @Override
        public void run() {
            List<ArticlesItem>  newsList=
                    NewsDataBase.getInstance()
                    .newsDao()
                    .getNewsBySourceId(sourceId);
            onNewsPreparedListener.onNewsPrepared(newsList);

        }
    }
    class InsertNewsIntoDBThread extends Thread{

        List<ArticlesItem> newsList ;
        public InsertNewsIntoDBThread(List<ArticlesItem> items){
            this.newsList=items;
        }

        public void run(){
            /*for(int i=0;i<newsList.size();i++){
                ArticlesItem item=newsList.get(i);
                
            }*/
            for (ArticlesItem item : newsList) {
                item.setSourceId(item.getSource().getId());
                item.setSourceName(item.getSource().getName());
            }
            NewsDataBase.getInstance()
                    .newsDao()
                    .insertArticles(newsList);
        }
    }


    class InsertSourcesToDataBase extends Thread{
        List<SourcesItem> itemsToInsert;

        public InsertSourcesToDataBase(List<SourcesItem> items){
            itemsToInsert = items;
        }

        public void run(){
            NewsDataBase.
                    getInstance()
                    .sourcesDAO().
                    insertSource(itemsToInsert);
        }
    }
    class GETSourcesFromDataBase extends Thread{

        OnSourcesPreparedListener onSourcesPreparedListener;
        public GETSourcesFromDataBase(OnSourcesPreparedListener onSourcesPreparedListener){
            this.onSourcesPreparedListener=onSourcesPreparedListener;
        }

        public void run(){
            List<SourcesItem> mylist = NewsDataBase.
                    getInstance()
                    .sourcesDAO().
                            getAllSources();
            onSourcesPreparedListener.onSourcesPrepared(mylist);
        }
    }



    //CallBack
    public interface OnSourcesPreparedListener{
        void onSourcesPrepared(List<SourcesItem> sources);
    }
    public interface OnNewsPreparedListener{
        void onNewsPrepared(List<ArticlesItem> articles);
    }



}

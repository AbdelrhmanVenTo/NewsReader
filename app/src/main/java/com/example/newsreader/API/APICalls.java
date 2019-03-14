package com.example.newsreader.API;


import com.example.newsreader.Model.IconSource.IconResponse;
import com.example.newsreader.Model.NewsResponse.NewsResponse;
import com.example.newsreader.Model.SourcesResponse.SourcesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APICalls {

    @GET("sources")
    Call<SourcesResponse> getAllSources(@Query("language") String lang,
                                        @Query("apiKey") String apiKey);

    @GET("allicons.json")
    Call<IconResponse> getIcon (@Query ("url") String url);

    @GET("everything")
    Call<NewsResponse> getNewsBySourceId(@Query("language") String lang,
                                         @Query("apiKey") String apiKey,
                                         @Query("sources") String sourceId);
}

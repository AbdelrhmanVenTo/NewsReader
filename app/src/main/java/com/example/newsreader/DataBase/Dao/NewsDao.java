package com.example.newsreader.DataBase.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.newsreader.Model.NewsResponse.ArticlesItem;

import java.util.List;

@Dao
public interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void  insertArticles(List<ArticlesItem> articles);

    @Query("select * from ArticlesItem where sourceId=:sourceId")
    List<ArticlesItem> getNewsBySourceId(String sourceId);
}

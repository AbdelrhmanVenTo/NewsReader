package com.example.newsreader.DataBase.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;


import com.example.newsreader.Model.SourcesResponse.SourcesItem;

import java.util.List;

@Dao
public interface SourcesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSource(List<SourcesItem> sourcesItems);


    @Query("select * from SourcesItem;")
    List<SourcesItem>getAllSources();
}

package com.example.newsreader.DataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


import com.example.newsreader.DataBase.Dao.NewsDao;
import com.example.newsreader.DataBase.Dao.SourcesDAO;
import com.example.newsreader.Model.NewsResponse.ArticlesItem;
import com.example.newsreader.Model.SourcesResponse.SourcesItem;

@Database(entities = {SourcesItem.class, ArticlesItem.class},
        version = 3,exportSchema = false)
public abstract class NewsDataBase extends RoomDatabase {
    private static NewsDataBase myDataBase;
    private static final String DBName="NEWSDATABASE";
    public  abstract SourcesDAO sourcesDAO();
    public abstract NewsDao newsDao();

    public static NewsDataBase init(Context context){

        if (myDataBase==null){//create object
            myDataBase =
                    Room.databaseBuilder(context,NewsDataBase.class,DBName)
                 //   .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return myDataBase;
    }

    public static NewsDataBase getInstance(){
        return myDataBase;
    }
}

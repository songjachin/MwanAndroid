package com.songjachin.mwanandroid.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.songjachin.mwanandroid.model.Constant;

/**
 * Created by matthew
 */
public class DBHelper extends SQLiteOpenHelper {


    public DBHelper(@Nullable Context context) {
        super(context, Constant.DB_NAME, null, Constant.DB_VERSION_CODE);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //String getAuthor();
        //    String getTitle();
        //    String getLink
        //time

        String historyTbSql = "create table " + Constant.HISTORY_TB_NAME +
                "("+ Constant.HISTORY_ID + " integer primary key autoincrement, "+
                Constant.HISTORY_ARTICLE_ID+" integer, "+
                Constant.HISTORY_AUTHOR+" text, "+
                Constant.HISTORY_TITLE+ " text, "+
                Constant.HISTORY_LINK  +" text, "+
                Constant.HISTORY_TIME+" varchar "+")";
        db.execSQL(historyTbSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

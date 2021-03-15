package com.songjachin.mwanandroid.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.songjachin.mwanandroid.base.BaseApplication;
import com.songjachin.mwanandroid.model.Constant;
import com.songjachin.mwanandroid.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matthew
 */
public class HistoryDao implements IHistoryDao {

    private IHistoryDaoCallback mDaoCallback;
    private final DBHelper mDbHelper;
    private Object mLock = new Object();

    private HistoryDao() {
        mDbHelper = new DBHelper(BaseApplication.getAppContext());
    }

    private static class HistoryDaoHolder {
        private static final HistoryDao sInstance = new HistoryDao();
    }

    public static HistoryDao getInstance() {
        return HistoryDaoHolder.sInstance;
    }

    @Override
    public void setCallback(IHistoryDaoCallback callback) {
        mDaoCallback = callback;
    }

    @Override
    public void addHistory(HistoryArticle article) {
        synchronized (mLock) {

            SQLiteDatabase db = null;
            boolean isSuccess = false;
            try {
                db = mDbHelper.getWritableDatabase();
                db.beginTransaction();
                int delete = db.delete(Constant.HISTORY_TB_NAME, Constant.HISTORY_ARTICLE_ID + "=?",
                        new String[]{article.getArticleId() + ""});
                ContentValues values = new ContentValues();
                values.put(Constant.HISTORY_ARTICLE_ID, article.getArticleId());
                values.put(Constant.HISTORY_AUTHOR, article.getAuthor());
                values.put(Constant.HISTORY_TITLE, article.getTitle());
                values.put(Constant.HISTORY_LINK, article.getLink());
                values.put(Constant.HISTORY_TIME, article.getTime());

                db.insert(Constant.HISTORY_TB_NAME, null, values);
                db.setTransactionSuccessful();
                isSuccess = true;
            } catch (Exception e) {
                isSuccess = false;
                e.printStackTrace();
            } finally {
                if (db != null) {
                    db.endTransaction();
                    db.close();
                }
                if (mDaoCallback != null) {
                    mDaoCallback.onHistoryAdd(isSuccess);
                }
            }
        }
    }

    @Override
    public void delHistory(HistoryArticle article) {
        synchronized (mLock){
            SQLiteDatabase db = null;
            boolean isDelSuccess =false;
            try{
                db = mDbHelper.getWritableDatabase();
                db.beginTransaction();
                int delete = db.delete(Constant.HISTORY_TB_NAME, Constant.HISTORY_ARTICLE_ID+"=?",
                        new String[]{article.getArticleId() + ""});
                db.setTransactionSuccessful();
                isDelSuccess =true;
            }catch (Exception e){
                e.printStackTrace();
                isDelSuccess =false;
            }finally {
                if (db != null) {
                    db.endTransaction();
                    db.close();
                }
                if (mDaoCallback != null) {
                    mDaoCallback.onHistoryDel(isDelSuccess);
                }
            }
        }

    }

    @Override
    public void clearHistory() {
        synchronized (mLock){
            SQLiteDatabase db =null;
            boolean isSuccess =false ;
            try{
                db = mDbHelper.getWritableDatabase();
                db.beginTransaction();
                db.delete(Constant.HISTORY_TB_NAME,null,null);
                db.setTransactionSuccessful();
                isSuccess = true;
            }catch (Exception e){
                e.printStackTrace();
                isSuccess =false;
            }finally {
                if (db != null) {
                    db.endTransaction();
                    db.close();
                }

                if (mDaoCallback != null) {
                    mDaoCallback.onHistoriesClean(isSuccess);
                }
            }
        }
    }

    @Override
    public void listHistories() {
        synchronized (mLock){
            SQLiteDatabase db = null;
            List<HistoryArticle> histories = new ArrayList<>();
            try{
                db = mDbHelper.getReadableDatabase();
                db.beginTransaction();
                Cursor cursor = db.query(Constant.HISTORY_TB_NAME, null,
                        null, null, null, null,
                        "_id desc");
                while(cursor.moveToNext()){
                    //private int articleId;
                    //    private String author;
                    //    private String title;
                    //    private String link;
                    //    private String time;
                    HistoryArticle article = new HistoryArticle();
                    int articleId = cursor.getInt(cursor.getColumnIndex(Constant.HISTORY_ARTICLE_ID));
                    String author = cursor.getString(cursor.getColumnIndex(Constant.HISTORY_AUTHOR));
                    String title = cursor.getString(cursor.getColumnIndex(Constant.HISTORY_TITLE));
                    String link = cursor.getString(cursor.getColumnIndex(Constant.HISTORY_LINK));
                    String time = cursor.getString(cursor.getColumnIndex(Constant.HISTORY_TIME));
                    article.setArticleId(articleId);
                    article.setAuthor(author);
                    article.setTitle(title);
                    article.setLink(link);
                    article.setTime(time);
                    histories.add(article);
                }
                db.setTransactionSuccessful();

            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if (db != null) {
                    db.endTransaction();
                    db.close();
                }
                LogUtils.d(this,"histories----->"+histories.size());
                if (mDaoCallback != null&&histories.size()!=0) {
                    mDaoCallback.onHistoriesLoaded(histories);
                }
            }
        }
    }
}

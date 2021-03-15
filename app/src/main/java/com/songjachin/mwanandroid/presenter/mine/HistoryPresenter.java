package com.songjachin.mwanandroid.presenter.mine;

import com.songjachin.mwanandroid.base.BaseApplication;
import com.songjachin.mwanandroid.database.HistoryArticle;
import com.songjachin.mwanandroid.database.HistoryDao;
import com.songjachin.mwanandroid.database.IHistoryDaoCallback;
import com.songjachin.mwanandroid.model.Constant;
import com.songjachin.mwanandroid.utils.LogUtils;
import com.songjachin.mwanandroid.view.mine.IHistoryCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by matthew
 */
public class HistoryPresenter implements IHistoryPresenter, IHistoryDaoCallback {
    private List<IHistoryCallback> mHistoryCallbacks= new ArrayList<>();
    private final HistoryDao mHistoryDao;
    private List<HistoryArticle> mCurrentHistories = new ArrayList<>();
    private HistoryArticle mCurrentAddArticle = null;

    public static ExecutorService threadPool = Executors.newSingleThreadExecutor();

    private HistoryPresenter(){
        mHistoryDao = HistoryDao.getInstance();
        mHistoryDao.setCallback(this);
    }

    private static volatile HistoryPresenter sInstance = null;

    public static HistoryPresenter getInstance(){
        if (sInstance == null) {
            synchronized (HistoryPresenter.class){
                if (sInstance == null) {
                    sInstance = new HistoryPresenter();
                }
            }
        }

        return sInstance;
    }

    @Override
    public void listHistories() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (mHistoryDao != null) {
                    mHistoryDao.listHistories();
                }
            }
        }).start();
        //被观察者创建
//        Observable.create(emitter -> {
//            if(mHistoryDao != null) {
//                mHistoryDao.listHistories();
//            }
//        }).subscribeOn(Schedulers.io()).
//                subscribe();

//        threadPool.execute(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
    }

    private  boolean isDoDelAsOutOfSize = false;
    @Override
    public void addHistory(HistoryArticle article) {
        //需要去判断是否>=100条记录
        if(mCurrentHistories != null && mCurrentHistories.size() >= Constant.MAX_HISTORY_COUNT) {
            isDoDelAsOutOfSize = true;
            this.mCurrentAddArticle = article;
            //先不能添加，先删除最前的一条记录，再添加
            delHistory(mCurrentHistories.get(mCurrentHistories.size() - 1));
        } else {
            LogUtils.d(this,"history------->to add");
            doAddHistory(article);
        }
    }

    private void doAddHistory(final HistoryArticle article) {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                if(mHistoryDao != null) {
                    mHistoryDao.addHistory(article);
                }
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }

    @Override
    public void delHistory(HistoryArticle article) {
        /**匿名内部类引用局部变量
         * 局部变量应加上final，方法入栈出栈生命周期小于堆里面的方法，如果改变的话，对象引用错误
         */
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                if(mHistoryDao != null) {
                    mHistoryDao.delHistory(article);
                }
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }

    @Override
    public void cleanHistories() {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                if(mHistoryDao != null) {
                    mHistoryDao.clearHistory();
                }
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }

    @Override
    public void registerViewCallback(IHistoryCallback callback) {
        if (mHistoryCallbacks != null&& !mHistoryCallbacks.contains(callback)) {
            mHistoryCallbacks.add(callback);
        }
    }

    @Override
    public void unregisterViewCallback(IHistoryCallback callback) {
        if (mHistoryCallbacks != null) {
            mHistoryCallbacks.remove(callback);
        }
    }

    @Override
    public void onHistoryAdd(boolean isSuccess) {
        listHistories();
    }

    @Override
    public void onHistoryDel(boolean isSuccess) {
        if(isDoDelAsOutOfSize && mCurrentAddArticle != null) {
            isDoDelAsOutOfSize = false;
            //添加当前的数据进到数据库里
            addHistory(mCurrentAddArticle);
        } else {
            listHistories();
        }
    }

    @Override
    public void onHistoriesLoaded(List<HistoryArticle> articles) {
        //此时在IO子线程
        this.mCurrentHistories = articles;


        //通知UI更新数据
        BaseApplication.getHandler().post(new Runnable() {
            @Override
            public void run() {
                for(IHistoryCallback callback : mHistoryCallbacks) {
                    callback.onHistoriesLoaded(articles);
                }
            }
        });
    }

    @Override
    public void onHistoriesClean(boolean isSuccess) {
        listHistories();
    }
}

package com.songjachin.mwanandroid.ui.mine;

import com.songjachin.mwanandroid.base.BaseApplication;
import com.songjachin.mwanandroid.utils.FileUtil;

<<<<<<< HEAD
=======
import java.io.IOException;
import java.io.ObjectInputStream;
>>>>>>> 7f475a1... finish the most
import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * Created by matthew
 */
public class User implements Serializable {
    private static final String TAG = "User";
    private static final long serialVersionUID = 1L;

    private User(){
    }

    private static volatile User instance;

    public static User getInstance(){
        if (instance == null) {
            synchronized (User.class){
                if (instance == null) {
                    Object object = FileUtil.restoreObject(BaseApplication.getAppContext()
                            , TAG);
                    if(object == null){
                        object = new User();
                        FileUtil.saveObject(BaseApplication.getAppContext(),TAG,object);

                    }
                    instance = (User) object;
                }
            }
        }

        return instance;
    }

    private String mUsername;//用户名
    private String mPassword;//密码
<<<<<<< HEAD
    private  int level;
    private int rankNum;
    private int rank;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getRankNum() {
        return rankNum;
    }

    public void setRankNum(int rankNum) {
        this.rankNum = rankNum;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    private boolean mLoginStatus = false;//登陆状态
=======
    private boolean mLoginStatus;//登陆状态
>>>>>>> 7f475a1... finish the most

    /**
     * 退出登录时使用
     */
    public void reset() {
<<<<<<< HEAD
        mUsername = "";
        mPassword = "";
=======
        mUsername = null;
        mPassword = null;
>>>>>>> 7f475a1... finish the most
        mLoginStatus = false;
        save();
    }

    public void save() {
        FileUtil.saveObject(BaseApplication.getAppContext(), TAG, this);
    }


    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public boolean isLoginStatus() {
        return mLoginStatus;
    }

    public void setLoginStatus(boolean loginStatus) {
        mLoginStatus = loginStatus;
    }

    // -----------以下方法防止反序列化时重新生成对象-----------------
    public User readResolve() throws ObjectStreamException, CloneNotSupportedException {
        instance = (User) this.clone();
        return instance;
    }

<<<<<<< HEAD
//    private void readObject(ObjectInputStream os) throws IOException, ClassNotFoundException {
//        os.defaultReadObject();
//    }
=======
    private void readObject(ObjectInputStream os) throws IOException, ClassNotFoundException {
        os.defaultReadObject();
    }
>>>>>>> 7f475a1... finish the most

    public Object Clone() throws CloneNotSupportedException {
        return super.clone();
    }

}

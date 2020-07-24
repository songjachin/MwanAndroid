package com.songjachin.mwanandroid.model.domain;

/**
 * Created by matthew
 */

/**
 * data : {"coinCount":1310,"level":14,"rank":"652","userId":48273,"username":"s**gjachin"}
 */

public class Ranking {
    /**
     * coinCount : 1310
     * level : 14
     * rank : 652
     * userId : 48273
     * username : s**gjachin
     */

    private int coinCount;
    private int level;
    private String rank;
    private int userId;
    private String username;

    public int getCoinCount() {
        return coinCount;
    }

    public void setCoinCount(int coinCount) {
        this.coinCount = coinCount;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

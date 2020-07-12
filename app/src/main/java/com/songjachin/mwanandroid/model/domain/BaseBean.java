package com.songjachin.mwanandroid.model.domain;

import com.google.gson.Gson;
import com.songjachin.mwanandroid.utils.JsonFormatUtils;

import java.io.Serializable;

/**
 * Created by matthew
 */
public class BaseBean implements Serializable {

    public String toJson() {
        return new Gson().toJson(this);
    }

    public String toFormatJson() {
        return JsonFormatUtils.format(toJson());
    }
}

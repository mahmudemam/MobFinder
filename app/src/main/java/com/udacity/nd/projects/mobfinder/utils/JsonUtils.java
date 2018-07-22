package com.udacity.nd.projects.mobfinder.utils;

import com.google.gson.Gson;
import com.udacity.nd.projects.mobfinder.data.Mobile;

/**
 * Created by noname on 7/22/18.
 */

public class JsonUtils {
    public static String mobileToJson(Mobile mobile) {
        return new Gson().toJson(mobile);
    }

    public static Mobile jsonToMobile(String jsonStr) {
        return new Gson().fromJson(jsonStr, Mobile.class);
    }
}

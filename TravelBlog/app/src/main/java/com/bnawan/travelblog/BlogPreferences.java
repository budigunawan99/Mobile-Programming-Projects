package com.bnawan.travelblog;

import android.content.Context;
import android.content.SharedPreferences;

public class BlogPreferences {
    private static final String KEY_STATE_LOGIN = "key_login_state";
    private SharedPreferences preferences;

    BlogPreferences(Context context) {
        preferences = context.getSharedPreferences("travel-blog", Context.MODE_PRIVATE);
    }

    public boolean isLoggedIn() {
        return preferences.getBoolean(KEY_STATE_LOGIN, false);
    }

    public void setLoggedIn(boolean loggedIn) {
        preferences.edit().putBoolean(KEY_STATE_LOGIN, true).apply();
    }
}

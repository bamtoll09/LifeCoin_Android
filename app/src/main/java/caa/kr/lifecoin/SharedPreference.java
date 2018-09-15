package caa.kr.lifecoin;

import android.app.Activity;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreference {

    private Activity mActivity;
    private String mName;

    public SharedPreference(Activity activity, String name) {
        mActivity = activity;
        mName = name;
    }

    // 값 불러오기
    String getPreferences(String key){
        SharedPreferences pref = mActivity.getSharedPreferences(mName, MODE_PRIVATE);
        return pref.getString(key, "none");
    }

    // 값 저장하기
    void savePreferences(String key, String value){
        SharedPreferences pref = mActivity.getSharedPreferences(mName, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    // 값(Key Data) 삭제하기
    void removePreferences(String key){
        SharedPreferences pref = mActivity.getSharedPreferences(mName, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(key);
        editor.commit();
    }

    // 값(ALL Data) 삭제하기
    void removeAllPreferences(){
        SharedPreferences pref = mActivity.getSharedPreferences(mName, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }
}

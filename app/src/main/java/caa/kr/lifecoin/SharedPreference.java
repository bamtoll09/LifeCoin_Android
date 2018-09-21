package caa.kr.lifecoin;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreference {

    private Activity mActivity;
    private String mName;
    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;

    public SharedPreference(Activity activity) {
        mPref = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
        mEditor = mPref.edit();
    }

    // 값 불러오기
    String getPreferences(String key){
        return mPref.getString(key, "none");
    }

    // 값 저장하기
    void savePreferences(String key, String value){
        mEditor.putString(key, value);
        mEditor.commit();
    }

    // 값(Key Data) 삭제하기
    void removePreferences(String key){
        SharedPreferences.Editor editor = mPref.edit();
        editor.remove(key);
        editor.commit();
    }

    // 값(ALL Data) 삭제하기
    void removeAllPreferences(){
        SharedPreferences.Editor editor = mPref.edit();
        editor.clear();
        editor.commit();
    }
}

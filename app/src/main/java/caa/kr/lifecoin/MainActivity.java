package caa.kr.lifecoin;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    public static SharedPreference SP;
    private int mPosition = 0;
    private LinearLayout mFragmentContainer;
    private LinearLayout mLoadingLayout;
    private FragmentTransaction mFragmentTransaction;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (mPosition == 0) return false;
                    LoadingScreenSwitch(1);
                    replaceFragment(HomeFragment.getInstance());
                    mFragmentContainer.setBackgroundColor(getColor(R.color.colorBackground));
                    mPosition = 0;
                    return true;

                case R.id.navigation_camera:
                    if (mPosition == 1) {
                        CameraFragment.getInstance().refresh();
                        return false;
                    }
                    LoadingScreenSwitch(1);
                    replaceFragment(CameraFragment.getInstance());
                    mFragmentContainer.setBackgroundColor(getColor(R.color.colorBackground));
                    mPosition = 1;
                    return true;

                case R.id.navigation_settings:
                    if (mPosition == 2) return false;
                    LoadingScreenSwitch(1);
                    replaceFragment(SettingsFragment.getInstance());
                    mFragmentContainer.setBackgroundColor(Color.WHITE);
                    mPosition = 2;
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        mFragmentContainer = (LinearLayout) findViewById(R.id.fragment_container);
        mLoadingLayout = (LinearLayout) findViewById(R.id.layout_qr_reader_loading);
        SP = new SharedPreference(this, "lifecoin");

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mFragmentTransaction = getFragmentManager().beginTransaction();
        mFragmentTransaction.add(R.id.fragment_container, HomeFragment.getInstance()).commit();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment).commit();
    }


    protected void LoadingScreenSwitch(int num) {
        switch (num) {
            case 0:
                mLoadingLayout.setVisibility(View.GONE);
                break;
            case 1:
                mLoadingLayout.setVisibility(View.VISIBLE);
                break;
        }
    }


}

package caa.kr.lifecoin;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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
                    if (mPosition == 0) {
                        HomeFragment.getInstance().loadBlocks();
                        return false;
                    }
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
        SP = new SharedPreference(this);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mFragmentTransaction = getFragmentManager().beginTransaction();
        mFragmentTransaction.add(R.id.fragment_container, HomeFragment.getInstance()).commit();

        if (SP.getPreferences("name").equals("none")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

            final EditText et = new EditText(MainActivity.this);
            et.setSingleLine(true);
            builder.setView(et)
                    .setTitle(R.string.input_name)
                    .setPositiveButton(R.string.submit, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (!et.getText().toString().trim().equals("")) {
                                SP.savePreferences("name", et.getText().toString().trim());
                            }

                            dialog.dismiss();
                        }
                    })
                    .setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            if (SP.getPreferences("name").equals("none"))
                                HomeFragment.getInstance().setName(getResources().getString(R.string.settings_name));
                            else
                                HomeFragment.getInstance().setName(SP.getPreferences("name"));
                        }
                    })
                    .show();
        }
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

package caa.kr.lifecoin;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

public class SettingsFragment extends PreferenceFragment {

    private static SettingsFragment SF = null;
    public static SettingsFragment getInstance() {
        if (SF == null) SF = new SettingsFragment();

        return SF;
    }

    private final String SAVE_FOLDER = "/LifeCoin/Profile";
    private final int GALLERY_CODE=1122;
    private Preference mImageSelectPreference;
    private Preference mEditNamePreference;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        addPreferencesFromResource(R.xml.pref_settings);

        mImageSelectPreference = findPreference("profile_image");
        mEditNamePreference = findPreference("name");

        mImageSelectPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_CODE);
                return true;
            }
        });
        
        if (MainActivity.SP.getPreferences("name").equals("none"))
            mEditNamePreference.setSummary(getResources().getString(R.string.home_user));
        else
            mEditNamePreference.setSummary(MainActivity.SP.getPreferences("name"));

        mEditNamePreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                String nameString = newValue.toString().trim();
                if (nameString.equals("")) {
                    MainActivity.SP.savePreferences("name", "none");
                    preference.setSummary(getResources().getString(R.string.home_user));
                }
                else {
                    preference.setSummary(nameString);
                    MainActivity.SP.savePreferences("name", nameString);
                    HomeFragment.getInstance().setName(nameString);
                }
                return true;
            }
        });

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_CODE) {
                processImage(data.getData());
            }
        }
    }

    private void processImage(Uri imageUri) {

        String imagePath = getRealPathFromURI(imageUri);

        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);

        String savePath = Environment.getExternalStorageDirectory().toString() + SAVE_FOLDER;

        File dir = new File(savePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        Date day = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.KOREA);
        String fileName = String.valueOf(sdf.format(day));

        String localPath = savePath + "/" + fileName + ".jpg";
        File file = new File(localPath);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MainActivity.SP.savePreferences("profile_image_path", localPath);
        ((ImageSelectPreference) mImageSelectPreference).changeProfileImage(localPath);
    }

    private String getRealPathFromURI(Uri contentUri) {
        int column_index = 0;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(contentUri, proj, null, null, null);

        if (cursor.moveToFirst()) {
            column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        }

        return cursor.getString(column_index);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((MainActivity) getActivity()).LoadingScreenSwitch(0);
    }
}

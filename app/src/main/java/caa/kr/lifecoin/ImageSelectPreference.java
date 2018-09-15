package caa.kr.lifecoin;

import android.content.Context;
import android.media.ExifInterface;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

public class ImageSelectPreference extends Preference {

    private ImageView mProfileImage;

    public ImageSelectPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected View onCreateView(final ViewGroup parent) {
        super.onCreateView(parent);

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.item_change_profile_img, parent, false);

        mProfileImage = (ImageView) v.findViewById(R.id.image_profile);

        Picasso.get().load(new File(MainActivity.SP.getPreferences("profile_image_path"))).fit().centerCrop().transform(new CircleTransform()).placeholder(R.drawable.ic_launcher_foreground).into(mProfileImage);

        return v;
    }

    protected void changeProfileImage(String imagePath) {

        ExifInterface exif = null;

        try {
            exif = new ExifInterface(imagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
        int exifDegree = exifOrientationToDegrees(exifOrientation);

        Picasso.get().load(new File(MainActivity.SP.getPreferences("profile_image_path"))).fit().centerCrop().transform(new CircleTransform()).rotate(exifDegree).placeholder(R.drawable.ic_launcher_foreground).into(mProfileImage);
    }

    private int exifOrientationToDegrees(int exifOrientation) {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            return 180;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270;
        }
        return 0;
    }
}

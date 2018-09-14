package caa.kr.lifecoin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private static HomeFragment HF;
    public static HomeFragment getInstance() {
        if (HF == null) HF = new HomeFragment();

        return HF;
    }

    private ImageView mProfileImage;
    private RecyclerView mSpecRecycler;
    private SpecAdapter mSpecAdapter;

    private ArrayList<SpecItem> mSpecData = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.layout_home, container, false);

        mProfileImage = (ImageView) v.findViewById(R.id.image_profile);
        mSpecRecycler = (RecyclerView) v.findViewById(R.id.recyler_specs);

        Picasso.get().load("http://bamtoll.me:3000/shops/5b8c7a998b2b0012ddb46e37/images/1.jpeg").fit().centerCrop().transform(new CircleTransform()).placeholder(R.drawable.ic_launcher_foreground).into(mProfileImage);

        mSpecData.add(new SpecItem("HyconHacks", "2018.09.14", hexStringToByteArray("A459CF39CD65D4B56CFC8E35B361D4B160A7A8490B39D525AE655ADF538C7C7B")));
        mSpecAdapter = new SpecAdapter(mSpecData);
        mSpecRecycler.setAdapter(mSpecAdapter);

        return v;
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }

        return data;
    }
}

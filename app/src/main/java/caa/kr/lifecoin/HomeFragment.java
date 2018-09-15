package caa.kr.lifecoin;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import caa.kr.lifecoin.retrofit.APIInterface;
import caa.kr.lifecoin.retrofit.APIUtils;
import caa.kr.lifecoin.retrofit.BlockResource;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private static HomeFragment HF;
    public static HomeFragment getInstance() {
        if (HF == null) HF = new HomeFragment();

        return HF;
    }

    private APIInterface mService;
    private ImageView mProfileImage;
    private TextView mNameText;
    private TextView mMySpecsText;
    private RecyclerView mSpecRecycler;
    private SpecAdapter mSpecAdapter;
    private String[] tests = {"HyconHacks", "AppJam", "Smart Tour App Contest", "Elecsthon", "Samsung Tomorrow Solutions"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.layout_home, container, false);

        mService = APIUtils.getAPIService();

        mProfileImage = (ImageView) v.findViewById(R.id.image_profile);
        mNameText = (TextView) v.findViewById(R.id.text_name);
        mMySpecsText = (TextView) v.findViewById(R.id.text_my_specs);
        mSpecRecycler = (RecyclerView) v.findViewById(R.id.recyler_specs);

        if (!MainActivity.SP.getPreferences("name").equals("none"))
            if (!MainActivity.SP.getPreferences("name").equals(mNameText.getText().toString()))
                mNameText.setText(MainActivity.SP.getPreferences("name"));

        Picasso.get().load(new File(MainActivity.SP.getPreferences("profile_image_path"))).fit().centerCrop().transform(new CircleTransform()).placeholder(R.drawable.ic_launcher_foreground).into(mProfileImage);

        loadBlocks();

        return v;
    }

    protected void loadBlocks() {
        mService.getBlocks().enqueue(new Callback<List<BlockResource>>() {
            @Override
            public void onResponse(Call<List<BlockResource>> call, Response<List<BlockResource>> response) {
                if (response.isSuccessful()) {
                    String[] specData = resourcesToStringArray(response.body());
                    mSpecAdapter = new SpecAdapter(specData);
                    mSpecRecycler.setAdapter(mSpecAdapter);
                    OverScrollDecoratorHelper.setUpOverScroll(mSpecRecycler, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
                    mMySpecsText.setText(getResources().getString(R.string.home_spec_count) + specData.length);
                } else {
                    int statusCode = response.code();
                    Log.d("tagtag", statusCode + ": occured error");
                }
            }

            @Override
            public void onFailure(Call<List<BlockResource>> call, Throwable t) {
                Toast.makeText(getContext(), "Please Check Your Device Is Connected On Internet", Toast.LENGTH_SHORT).show();

                Log.d("tagtag", t.getMessage());
            }
        });
    }

    private String[] resourcesToStringArray(List<BlockResource> resources) {
        String[] strings = new String[resources.size()];
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < strings.length; ++i) {
            sb.append("제 ").append((strings.length - (i + 1)) / 5 + 1).append("회 ").append(tests[i % 5]);
            strings[i] = sb.toString();
            sb.delete(0, sb.length());
        }

        return strings;
    }

    protected void setName(String name) {
        if (name == null) {
            if (MainActivity.SP.getPreferences("name").equals("none")) mNameText.setText(getResources().getString(R.string.home_user));
            else mNameText.setText(MainActivity.SP.getPreferences("name"));
        }
        else mNameText.setText(name);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((MainActivity) getActivity()).LoadingScreenSwitch(0);
    }

    /*public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }

        return data;
    }*/
}

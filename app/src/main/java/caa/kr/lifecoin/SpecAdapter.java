package caa.kr.lifecoin;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SpecAdapter extends RecyclerView.Adapter<SpecViewHolder> {

    private String[] mSpecData;

    public SpecAdapter(String[] specData) {
        mSpecData = specData;
    }

    @Override
    public SpecViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spec, parent, false);
        SpecViewHolder vh = new SpecViewHolder(parent.getContext(), parent);
        return vh;
    }

    @Override
    public void onBindViewHolder(SpecViewHolder holder, int position) {
        Date day = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(day);
        if (position > 4) c.add(Calendar.DATE, -position / 5);
        day = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
        String date = String.valueOf(sdf.format(day));

        holder.nameText.setText(mSpecData[position]);
        holder.dateText.setText(date);
    }

    @Override
    public int getItemCount() {
        return mSpecData.length;
    }
}

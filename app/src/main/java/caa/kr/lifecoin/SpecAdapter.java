package caa.kr.lifecoin;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class SpecAdapter extends RecyclerView.Adapter<SpecViewHolder> {

    private List<SpecItem> mSpecData;

    public SpecAdapter(List<SpecItem> specData) {
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
        holder.nameText.setText(mSpecData.get(position).getName());
        holder.dateText.setText(mSpecData.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return mSpecData.size();
    }
}

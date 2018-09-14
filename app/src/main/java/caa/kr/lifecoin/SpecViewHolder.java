package caa.kr.lifecoin;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

public class SpecViewHolder extends RecyclerView.ViewHolder {

    protected CardView backgroundCard;
    protected TextView nameText;
    protected TextView dateText;

    public SpecViewHolder(Context context, ViewGroup parent) {
        super(LayoutInflater.from(context).inflate(R.layout.item_spec, parent, false));

        backgroundCard = (CardView) itemView.findViewById(R.id.card_background);
        nameText = (TextView) itemView.findViewById(R.id.text_spec_name);
        dateText = (TextView) itemView.findViewById(R.id.text_spec_date);
    }
}

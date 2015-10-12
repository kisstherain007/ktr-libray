package com.ktr.ui.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ktr.ktr_libray.R;

import java.util.List;

/**
 * Created by kisstherain on 2015/10/11.
 */
public class KRecyclerAdapter extends RecyclerView.Adapter<KRecyclerAdapter.KViewHolder> {

    Context mContext;

    LayoutInflater mLayoutInflater;

    List<Integer> datas;

    public KRecyclerAdapter(Context context){

        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void refreshAdapter(List<Integer> datas){

        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public KViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = mLayoutInflater.inflate(R.layout.home_grid_item, viewGroup, false);
        KViewHolder kViewHolder = new KViewHolder(view);
        return kViewHolder;
    }

    @Override
    public void onBindViewHolder(KViewHolder kViewHolder, int i) {

        kViewHolder.item_center_imageView.setImageResource(datas.get(i));
    }

    @Override
    public int getItemCount() {

        return datas == null ? 0 : datas.size();
    }

    class KViewHolder extends RecyclerView.ViewHolder{

        TextView item_top_textView;

        ImageView item_center_imageView;

        public KViewHolder(View itemView) {
            super(itemView);

            item_top_textView = (TextView) itemView.findViewById(R.id.item_bottom_textView);
            item_center_imageView = (ImageView) itemView.findViewById(R.id.item_center_imageView);
        }

    }
}

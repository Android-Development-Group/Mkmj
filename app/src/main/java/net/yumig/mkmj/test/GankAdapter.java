package net.yumig.mkmj.test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import net.yumig.mkmj.R;
import net.yumig.mkmj.test.entity.resulte.GankResulte;

import java.util.List;

/**
 * Created by T5-Jusenr on 2017/4/6.
 */

public class GankAdapter extends RecyclerView.Adapter<GankAdapter.GankViewHolder> {

    private Context context;
    private List<GankResulte> datas;

    public GankAdapter(Context context, List<GankResulte> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public GankViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GankViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_gank_item, parent, false));
    }

    @Override
    public void onBindViewHolder(GankViewHolder holder, int position) {
        Glide.with(context).load(datas.get(position).getUrl()+"?imageView2/0/w/600").into(holder.img);
        holder.who.setText(datas.get(position).getWho());
//        holder.desc.setText(datas.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class GankViewHolder extends ViewHolder {

        ImageView img;
        TextView who;
        TextView desc;

        public GankViewHolder(View view) {
            super(context, view);
            img = (ImageView) view.findViewById(R.id.iv_tejia_img);
            who = (TextView) view.findViewById(R.id.tv_tejia_name);
            desc = (TextView) view.findViewById(R.id.tv_tejia_price);
        }
    }
}

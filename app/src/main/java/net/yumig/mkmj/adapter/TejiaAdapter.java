package net.yumig.mkmj.adapter;

import android.content.Context;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import net.yumig.mkmj.R;
import net.yumig.mkmj.bean.TejiaBean;

import java.util.List;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : www.yumig.net
 * Author     : Gaoxichao
 * Email      : 1024003167@qq.com
 * Date       : 2017/3/27 11:07
 */

public class TejiaAdapter extends CommonAdapter<TejiaBean> {

    public TejiaAdapter(Context context, int layoutId, List datas) {
        super(context, layoutId, datas);
    }
    @Override
    protected void convert(ViewHolder holder, TejiaBean tejiaBean, int position) {

        holder.setImageResource(R.id.iv_tejia_img, tejiaBean.getImg());
        holder.setText(R.id.tv_tejia_name, tejiaBean.getName());
        holder.setText(R.id.tv_tejia_price, tejiaBean.getPrice());
        holder.setText(R.id.tv_tejia_jifen, tejiaBean.getJifen());
    }

    @Override
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        super.setOnItemClickListener(onItemClickListener);
    }
}

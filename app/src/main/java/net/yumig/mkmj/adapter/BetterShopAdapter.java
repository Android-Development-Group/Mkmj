package net.yumig.mkmj.adapter;

import android.content.Context;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import net.yumig.mkmj.R;
import net.yumig.mkmj.bean.BetterShopBean;

import java.util.List;

/**
 * Description: 店铺 本周优选
 * Copyright  : Copyright (c) 2016
 * Company    : www.yumig.net
 * Author     : Gaoxichao
 * Email      : 1024003167@qq.com
 * Date       : 2017/3/31 08:50
 */

public class BetterShopAdapter extends CommonAdapter<BetterShopBean> {

    public BetterShopAdapter(Context context, int layoutId, List datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, BetterShopBean betterShopBean, int position) {

        holder.setImageResource(R.id.iv_shopImg, betterShopBean.getImg());
        holder.setText(R.id.tv_shopName, betterShopBean.getName());
        holder.setText(R.id.shopDis, betterShopBean.getDistance());
        holder.setText(R.id.shoptype, betterShopBean.getType());
        holder.setText(R.id.shopPrice, betterShopBean.getPrice());
    }

    @Override
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        super.setOnItemClickListener(onItemClickListener);
    }
}

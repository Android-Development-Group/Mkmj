package net.yumig.mkmj.adapter;

import android.content.Context;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import net.yumig.mkmj.bean.HotSellBean;
import net.yumig.mkmj.R;

import java.util.List;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : www.yumig.net
 * Author     : Gaoxichao
 * Email      : 1024003167@qq.com
 * Date       : 2017/3/27 11:07
 */

public class HotSellAdapter extends CommonAdapter<HotSellBean> {

    public HotSellAdapter(Context context, int layoutId, List datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, HotSellBean hotSellBean, int position) {

        holder.setImageResource(R.id.iv_hotSell_img, hotSellBean.getImg());
        holder.setText(R.id.tv_hotSell_name, hotSellBean.getName());
        holder.setText(R.id.tv_hotSell_price, hotSellBean.getPrice());
        holder.setText(R.id.tv_hotSell_jifen, hotSellBean.getJifen());
    }


}

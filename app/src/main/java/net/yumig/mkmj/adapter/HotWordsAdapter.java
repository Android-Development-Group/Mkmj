package net.yumig.mkmj.adapter;

import android.content.Context;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

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

public class HotWordsAdapter extends CommonAdapter<String> {


    public HotWordsAdapter(Context context, int layoutId, List<String> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, String hotWords, int position) {
        holder.setText(R.id.tv_hotWords, hotWords);
    }
}

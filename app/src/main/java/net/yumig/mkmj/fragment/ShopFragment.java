package net.yumig.mkmj.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import net.yumig.mkmj.R;
import net.yumig.mkmj.activity.MainActivity;
import net.yumig.mkmj.activity.SearchActivity;
import net.yumig.mkmj.adapter.HotSellAdapter;
import net.yumig.mkmj.adapter.TejiaAdapter;
import net.yumig.mkmj.bean.HotSellBean;
import net.yumig.mkmj.bean.TejiaBean;
import com.currency.library.widget.recycler.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    :
 * Author     : Gaoxichao
 * Date       : 2017/3/24 15:26
 */

public class ShopFragment extends Fragment {

    Integer[] images = new Integer[]{R.drawable.img_lunbao, R.drawable.img_lunboo, R.drawable.img_lunbooo};
    private List<TejiaBean>   mTejiaList   = new ArrayList<TejiaBean>();
    private List<HotSellBean> mHotSellList = new ArrayList<HotSellBean>();

    private Banner       banner;
    private View         view;
    private RecyclerView mRvTejia;
    private RecyclerView mRvHotSell;

    private TejiaAdapter   mJifenAdapter;
    private HotSellAdapter mHotSellAdapter;
    private MainActivity   mContext;
    private RelativeLayout rlSearch;
    private TextView       tvSaoyiSao;
    private TextView       tvMessage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = (MainActivity) this.getActivity();
        view = View.inflate(mContext, R.layout.fragment_shopp, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        for (int i = 1; i < 10; i++) {
            TejiaBean tejiaBean = new TejiaBean();
            HotSellBean hotSellBean = new HotSellBean();
            tejiaBean.setImg(R.drawable.img_sp);
            tejiaBean.setJifen(i + "000积分");
            tejiaBean.setName("肌初赋原液" + i);
            tejiaBean.setPrice("¥100" + i);
            mTejiaList.add(tejiaBean);
            hotSellBean.setImg(R.drawable.img_xj);
            hotSellBean.setJifen(i + "666积分");
            hotSellBean.setName("LOMO拍立得相机" + i);
            hotSellBean.setPrice("¥888" + i);
            mHotSellList.add(hotSellBean);
        }
        mRvTejia.setAdapter(mJifenAdapter);
        mJifenAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

                Toast.makeText(mContext, "Click: 特价" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        mRvHotSell.setAdapter(mHotSellAdapter);
        mHotSellAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Toast.makeText(mContext, "Click: 热卖" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

    }

    private void initView() {
        rlSearch = (RelativeLayout) view.findViewById(R.id.rl_search);
        tvSaoyiSao = (TextView) view.findViewById(R.id.tv_titlebar_saoyisao);
        tvMessage = (TextView) view.findViewById(R.id.tv_titlebar_xiaoxi);
        rlSearch.setOnClickListener(listener);
        tvSaoyiSao.setOnClickListener(listener);
        tvMessage.setOnClickListener(listener);
        //tablayout
        TabLayout tabTitle = (TabLayout) view.findViewById(R.id.tabTitle);
        tabTitle.addTab(tabTitle.newTab().setText("推荐"));
        tabTitle.addTab(tabTitle.newTab().setText("美食"));
        tabTitle.addTab(tabTitle.newTab().setText("家居"));
        tabTitle.addTab(tabTitle.newTab().setText("数码"));
        tabTitle.addTab(tabTitle.newTab().setText("护理"));
        tabTitle.addTab(tabTitle.newTab().setText("全部"));
        //        LinearLayout tabStrip = (LinearLayout) tabBar.getChildAt(0);
        //        for (int i = 0; i < tabStrip.getChildCount(); i++) {
        //            View tabView = tabStrip.getChildAt(i);
        //            if (tabView != null) {
        //                tabView.setClickable(false);
        //            }
        //        }
        //banner
        banner = (Banner) view.findViewById(R.id.banner);
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(Arrays.asList(images));
        banner.setDelayTime(5000);
        banner.start();
        //rv tejia
        mRvTejia = (RecyclerView) view.findViewById(R.id.rv_tejia);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvTejia.setLayoutManager(linearLayoutManager);
        mJifenAdapter = new TejiaAdapter(mContext, R.layout.rvlist_item_tejia, mTejiaList);

        //rv hotsell
        mRvHotSell = (RecyclerView) view.findViewById(R.id.rv_hotSell);
        mRvHotSell.setLayoutManager(new GridLayoutManager(mContext, 2));
        mHotSellAdapter = new HotSellAdapter(mContext, R.layout.rvlist_item_hotsell, mHotSellList);
        mRvHotSell.addItemDecoration(new DividerGridItemDecoration(mContext));

    }


    @Override
    public void onStart() {
        super.onStart();
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }


    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.rl_search:
                    startActivity(new Intent(mContext, SearchActivity.class));
                    break;
                case R.id.tv_titlebar_saoyisao:
                    Toast.makeText(mContext, "saoyisao", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tv_titlebar_xiaoxi:
                    Toast.makeText(mContext, "message", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    private class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }


}

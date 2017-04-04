package net.yumig.mkmj.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.currency.library.controller.BaseFragment;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import net.yumig.mkmj.R;
import net.yumig.mkmj.activity.SearchActivity;
import net.yumig.mkmj.adapter.HotSellAdapter;
import net.yumig.mkmj.adapter.TejiaAdapter;
import net.yumig.mkmj.bean.HotSellBean;
import net.yumig.mkmj.bean.TejiaBean;
import net.yumig.mkmj.util.DialogUtil;
import net.yumig.mkmj.widget.LoadStateView;
import net.yumig.mkmj.widget.MyScrollview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    : www.yumig.net
 * Author     : Gaoxichao
 * Email      : 1024003167@qq.com
 * Date       : 2017/3/28 14:02
 */

public class Shop1Fragment extends BaseFragment {
    @BindView(R.id.tv_titlebar_saoyisao)
    TextView       tvTitlebarSaoyisao;
    @BindView(R.id.rl_search)
    RelativeLayout rlSearch;
    @BindView(R.id.tv_titlebar_xiaoxi)
    TextView       tvTitlebarXiaoxi;
    @BindView(R.id.tabTitle)
    TabLayout      tabTitle;
    @BindView(R.id.llbar)
    LinearLayout   llbar;
    @BindView(R.id.banner)
    Banner         banner;
    @BindView(R.id.tv_myjifen)
    TextView       tvMyjifen;
    @BindView(R.id.tvjifen)
    TextView       tvjifen;
    @BindView(R.id.iv_jifen)
    ImageView      ivJifen;
    @BindView(R.id.tv_tejia)
    TextView       tvTejia;
    @BindView(R.id.rv_tejia)
    RecyclerView   rvTejia;
    @BindView(R.id.tv_hotSell)
    TextView       tvHotSell;
    //    @BindView(R.id.view)
    //    ImageView      view;
    @BindView(R.id.rv_hotSell)
    RecyclerView   rvHotSell;
    //    @BindView(R.id.load_state_view)
    //    LoadStateView  mLoadStateView;
    @BindView(R.id.sv_home)
    MyScrollview   myScrollview;


    Integer[] images = new Integer[]{R.drawable.img_lunbao, R.drawable.img_lunboo, R.drawable.img_lunbooo};
    private List<TejiaBean>   mTejiaList   = new ArrayList<TejiaBean>();
    private List<HotSellBean> mHotSellList = new ArrayList<HotSellBean>();
    private TejiaAdapter   tejiaAdapter;
    private HotSellAdapter hotSellAdapter;


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shopp;
    }

    @Override
    public void onViewCreatedFinish(Bundle savedInstanceState) {

        //        mLoadStateView.setEmptyMessage("空的！！！");
        //        mLoadStateView.setOnRetryListener(onRetryListener);
        //         initData();
        tabTitle.addTab(tabTitle.newTab().setText("推荐"));
        tabTitle.addTab(tabTitle.newTab().setText("美食"));
        tabTitle.addTab(tabTitle.newTab().setText("家居"));
        tabTitle.addTab(tabTitle.newTab().setText("数码"));
        tabTitle.addTab(tabTitle.newTab().setText("护理"));
        tabTitle.addTab(tabTitle.newTab().setText("全部"));

        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(Arrays.asList(images));
        banner.setDelayTime(5000);
        banner.start();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //        FullyLinearLayoutManager fullyLinearLayoutManager = new FullyLinearLayoutManager(mActivity);
        //        fullyLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvTejia.setLayoutManager(linearLayoutManager);
        rvTejia.setHasFixedSize(true);
        tejiaAdapter = new TejiaAdapter(mActivity, R.layout.rvlist_item_tejia, mTejiaList);
        tejiaAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                //                ToastUtils.showToastShort(mActivity, "You Click " + position);
                DialogUtil.showToast(mActivity, "You Click " + position, 0);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        rvTejia.setAdapter(tejiaAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity, 2);
        //        FullyGridLayoutManager fullyGridLayoutManager = new FullyGridLayoutManager(mActivity, 2);
        rvHotSell.setLayoutManager(gridLayoutManager);
        rvTejia.setHasFixedSize(true);
        //        rvHotSell.addItemDecoration(new DividerGridItemDecoration(mActivity));
        hotSellAdapter = new HotSellAdapter(mActivity, R.layout.rvlist_item_hotsell, mHotSellList);
        hotSellAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                //                ToastUtils.showToastShort(mActivity, "You Click " + position);
                DialogUtil.showToast(mActivity, "You Click " + position, 0);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        rvHotSell.setAdapter(hotSellAdapter);
        myScrollview.smoothScrollTo(0, 20);  //解决Scrollview不能顶层显示


    }

    @Override
    protected String[] getRequestUrls() {
        return new String[0];
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
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

    private void initData() {
        mTejiaList.clear();
        mHotSellList.clear();
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

        hotSellAdapter.notifyDataSetChanged();
        tejiaAdapter.notifyDataSetChanged();
        //        if (tejiaAdapter != null) {
        //            tejiaAdapter.notifyDataSetChanged();
        //        } else {
        //            tejiaAdapter = new TejiaAdapter(mActivity, R.layout.rvlist_item_tejia, mTejiaList);
        //            tejiaAdapter.notifyDataSetChanged();
        //        }
        //
        //        if (hotSellAdapter != null) {
        //            hotSellAdapter.notifyDataSetChanged();
        //        } else {
        //            hotSellAdapter = new HotSellAdapter(mActivity, R.layout.rvlist_item_hotsell, mHotSellList);
        //            hotSellAdapter.notifyDataSetChanged();
        //        }
    }

    private class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }

    @OnClick({R.id.tv_titlebar_saoyisao, R.id.tv_titlebar_xiaoxi, R.id.rl_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_titlebar_saoyisao:
                DialogUtil.showToast(mActivity, "saoyisao", 0);
                break;
            case R.id.tv_titlebar_xiaoxi:
                DialogUtil.showToast(mActivity, "message", 0);
                break;
            case R.id.rl_search:
                startActivity(SearchActivity.class);
                break;
        }
    }

    private LoadStateView.OnRetryListener onRetryListener = new LoadStateView.OnRetryListener() {
        @Override
        public void onRetry(View v) {
            initData();
        }
    };

}

package net.yumig.mkmj.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.currency.library.controller.BaseFragment;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import net.yumig.mkmj.R;
import net.yumig.mkmj.adapter.BetterShopAdapter;
import net.yumig.mkmj.adapter.GridViewAdapter;
import net.yumig.mkmj.adapter.ViewPagerAdapter;
import net.yumig.mkmj.bean.BetterShopBean;
import net.yumig.mkmj.bean.LeibieBean;
import net.yumig.mkmj.util.DialogUtil;
import net.yumig.mkmj.widget.LoadingLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    :
 * Author     : Gaoxichao
 * Date       : 2017/3/24 15:26
 */

public class NearFragment extends BaseFragment {
    @BindView(R.id.tv_location)
    TextView       tvLocation;
    @BindView(R.id.tv_titlebar_search)
    TextView       tvTitlebarSearch;
    @BindView(R.id.rl_search)
    RelativeLayout rlSearch;
    @BindView(R.id.tv_xiaoxi)
    TextView       tvXiaoxi;
    @BindView(R.id.title)
    LinearLayout   title;
    @BindView(R.id.banner)
    Banner         banner;
    @BindView(R.id.viewpager)
    ViewPager      mPager;
    @BindView(R.id.ll_dot)
    LinearLayout   mLlDot;
    @BindView(R.id.rv_youxuan)
    RecyclerView   mRvYouxuan;

    private LoadingLayout loadingLayout;


    Integer[] images = new Integer[]{R.drawable.img_lunbao, R.drawable.img_lunboo, R.drawable.img_lunbooo};

    private List<View> mPagerList;
    private List<LeibieBean>     mDatas = new ArrayList<LeibieBean>();
    private List<BetterShopBean> mShops = new ArrayList<BetterShopBean>();
    private LayoutInflater inflater;


    //总的页数
    private int pageCount;
    //每一页显示的个数
    private int pageSize = 8;
    //当前显示的是第几页
    private int curIndex = 0;
    private ViewPagerAdapter  viewPagerAdapter;
    private BetterShopAdapter betterShopAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_near;

    }

    private void initData() {
        mDatas.clear();
        mShops.clear();
        for (int i = 0; i < 13; i++) {
            BetterShopBean betterShopBean = new BetterShopBean();
            //动态获取资源ID，第一个参数是资源名，第二个参数是资源类型例如drawable，string等，第三个参数包名
            //            int imageId = getResources().getIdentifier("ic_category_" + i, "mipmap", getPackageName());
            //            mDatas.add(new Model(titles[i], imageId));
            mDatas.add(new LeibieBean(R.drawable.leibie_meishi, "美食"));
            betterShopBean.setImg(R.drawable.wellshop);
            betterShopBean.setArea("南大街");
            betterShopBean.setDistance("7.3KM");
            betterShopBean.setType("自助餐");
            betterShopBean.setPrice("¥50");
            betterShopBean.setName("椰园东南亚风情餐厅");
            mShops.add(betterShopBean);
        }
        if (viewPagerAdapter != null) {
            viewPagerAdapter.notifyDataSetChanged();
        }
        if (betterShopAdapter != null) {
            betterShopAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onViewCreatedFinish(Bundle savedInstanceState) {
        //        loadingLayout = LoadingLayout.wrap(textView);
        //        loadingLayout.setRetryListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                DialogUtil.showToast(mActivity, "retry", 0);
        //                showLoading();
        //                new Handler().postDelayed(new Runnable() {
        //                    @Override
        //                    public void run() {
        //                        showContent();
        //
        //                    }
        //                }, 2000);
        //
        //            }
        //        });

        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(Arrays.asList(images));
        banner.setDelayTime(5000);
        banner.start();
        initData();
        inflater = LayoutInflater.from(mActivity);
        //总的页数=总数/每页数量，并取整
        pageCount = (int) Math.ceil(mDatas.size() * 1.0 / pageSize);
        mPagerList = new ArrayList<View>();
        for (int i = 0; i < pageCount; i++) {
            //每个页面都是inflate出一个新实例
            GridView gridView = (GridView) inflater.inflate(R.layout.gridview, mPager, false);
            gridView.setAdapter(new GridViewAdapter(mActivity, mDatas, i, pageSize));
            mPagerList.add(gridView);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int pos = position + curIndex * pageSize;
                    Toast.makeText(mActivity, mDatas.get(pos).getName() + pos, Toast.LENGTH_SHORT).show();
                }
            });
        }
        viewPagerAdapter = new ViewPagerAdapter(mPagerList);
        //设置适配器
        mPager.setAdapter(viewPagerAdapter);
        //设置圆点
        setOvalLayout();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity, 2);
        mRvYouxuan.setLayoutManager(gridLayoutManager);
        mRvYouxuan.setHasFixedSize(true);
        betterShopAdapter = new BetterShopAdapter(mActivity, R.layout.rvlist_item_wellshopl, mShops);
        mRvYouxuan.setAdapter(betterShopAdapter);
        betterShopAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                BetterShopBean shopBean = mShops.get(position);
                DialogUtil.showToast(mActivity, shopBean.getName() + position, Toast.LENGTH_SHORT);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });


    }


    @Override
    public void onResume() {
        super.onResume();
        initData();
        //        showLoading();
        //        new Handler().postDelayed(new Runnable() {
        //            @Override
        //            public void run() {
        //                showError();
        //            }
        //        }, 2000);

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

    private void showError() {
        loadingLayout.setErrorImage(R.drawable.error);
        loadingLayout.showError();
    }

    private void showContent() {
        loadingLayout.showContent();
    }

    private void showEmpty() {
        loadingLayout.setEmptyImage(R.drawable.empty);
        loadingLayout.showEmpty();
    }

    private void showLoading() {
        loadingLayout.showLoading();
    }

    @Override
    protected String[] getRequestUrls() {
        return new String[0];
    }


    @OnClick({R.id.tv_location, R.id.tv_titlebar_search, R.id.tv_xiaoxi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_location:
                break;
            case R.id.tv_titlebar_search:
                break;
            case R.id.tv_xiaoxi:
                break;
        }
    }

    private class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }

    /**
     * 设置圆点
     */
    public void setOvalLayout() {
        for (int i = 0; i < pageCount; i++) {
            mLlDot.addView(inflater.inflate(R.layout.dot, null));
        }
        // 默认显示第一页
        mLlDot.getChildAt(0).findViewById(R.id.v_dot)
                .setBackgroundResource(R.drawable.dot_selected);
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageSelected(int position) {
                // 取消圆点选中
                mLlDot.getChildAt(curIndex)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.dot_normal);
                // 圆点选中
                mLlDot.getChildAt(position)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.dot_selected);
                curIndex = position;
            }

            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }
}

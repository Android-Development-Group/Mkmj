package net.yumig.mkmj.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.currency.library.controller.BaseFragment;

import net.yumig.mkmj.R;
import net.yumig.mkmj.activity.SettingActivity;
import net.yumig.mkmj.widget.MyScrollview;
import com.currency.library.widget.image.RoundImageView;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Description: 个人中心模块
 * Copyright  : Copyright (c) 2016
 * Company    : www.Yumig.net
 * Author     : Gaoxichao
 * Date       : 2017/3/24 15:26
 */

public class MyFragment extends BaseFragment {


    @BindView(R.id.iv_user_icon)
    RoundImageView ivUserIcon;       //头像
    @BindView(R.id.tv_user_nickname)
    TextView       tvUserNickname;  //用户昵称
    @BindView(R.id.ll_user)
    LinearLayout   llUser;
    @BindView(R.id.rl_user_head_icon)
    RelativeLayout rlUserHeadIcon;
    @BindView(R.id.tv_me_integral)
    TextView       tvMeIntegral;     //积分
    @BindView(R.id.ll_integral)
    LinearLayout   llIntegral;
    @BindView(R.id.tv_me_m_collection)
    TextView       tvMeMCollection;    //收藏
    @BindView(R.id.ll_collection)
    LinearLayout   llCollection;
    @BindView(R.id.tv_me_m_concern)
    TextView       tvMeMConcern;         //关注
    @BindView(R.id.ll_concern)
    LinearLayout   llConcern;
    @BindView(R.id.tv_nick_name)
    TextView       tvMore;
    @BindView(R.id.rl_my_order)
    RelativeLayout rlMyOrder;
    @BindView(R.id.tv_pending_payment)
    TextView       tvPendingPayment;
    @BindView(R.id.tv_wait_for_delivery)
    TextView       tvWaitForDelivery;
    @BindView(R.id.tv_receiving_goods)
    TextView       tvReceivingGoods;
    @BindView(R.id.tv_pending_evaluation)
    TextView       tvPendingEvaluation;
    @BindView(R.id.tv_sale_refund)
    TextView       tvSaleRefund;
    @BindView(R.id.tv_wodeMessage)
    TextView       tvWodeMessage;
    @BindView(R.id.tv_wodeLocation)
    TextView       tvWodeLocation;
    @BindView(R.id.tv_wodeTuijian)
    TextView       tvWodeTuijian;
    @BindView(R.id.tv_wodeKefu)
    TextView       tvWodeKefu;
    @BindView(R.id.tv_wodeSetting)
    TextView       tvWodeSetting;
    @BindView(R.id.ll_me)
    LinearLayout   llMe;
    @BindView(R.id.sv_me)
    MyScrollview   svMe;
    Unbinder unbinder;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    public void onViewCreatedFinish(Bundle savedInstanceState) {
        initData();

    }

    private void initData() {

    }


    @OnClick({R.id.iv_user_icon, R.id.ll_integral, R.id.ll_collection, R.id.ll_concern, R.id.tv_nick_name, R.id.tv_pending_payment, R.id.tv_wait_for_delivery, R.id.tv_receiving_goods, R.id.tv_pending_evaluation, R.id.tv_sale_refund, R.id.tv_wodeMessage, R.id.tv_wodeLocation, R.id.tv_wodeTuijian, R.id.tv_wodeKefu, R.id.tv_wodeSetting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_user_icon:
                //头像（个人中心）
                break;
            case R.id.ll_integral:
                //我的积分
                break;
            case R.id.ll_collection:
                //商品收藏
                break;
            case R.id.ll_concern:
                //商户关注
                break;
            case R.id.tv_nick_name:
                //查看更多订单
                break;
            case R.id.tv_pending_payment:
                //待付款
                break;
            case R.id.tv_wait_for_delivery:
                //待发货
                break;
            case R.id.tv_receiving_goods:
                //待收货
                break;
            case R.id.tv_pending_evaluation:
                //待评价
                break;
            case R.id.tv_sale_refund:
                //退款售后
                break;
            case R.id.tv_wodeMessage:
                //消息
                break;
            case R.id.tv_wodeLocation:
                //我的地址
                break;
            case R.id.tv_wodeTuijian:
                //推荐APP
                break;
            case R.id.tv_wodeKefu:
                //客服
                break;
            case R.id.tv_wodeSetting:
                //设置
                startActivity(new Intent(mActivity, SettingActivity.class));
                break;
        }
    }

    @Override
    protected String[] getRequestUrls() {
        return new String[0];
    }


    @Override
    public void onResume() {
        super.onResume();
        //        initData();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

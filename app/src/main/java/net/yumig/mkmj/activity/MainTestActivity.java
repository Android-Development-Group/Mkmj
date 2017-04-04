package net.yumig.mkmj.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.yumig.mkmj.fragment.MyFragment;
import net.yumig.mkmj.fragment.NearFragment;
import net.yumig.mkmj.R;
import net.yumig.mkmj.fragment.ShopCarFragment;
import net.yumig.mkmj.fragment.ShopFragment;

public class MainTestActivity extends FragmentActivity implements View.OnClickListener {

    private ShopFragment    shopFragment;
    private NearFragment    nearFragment;
    private ShopCarFragment shopCarFragment;
    private MyFragment      myFragment;

    private Fragment[]       mFragmentList;
    private RelativeLayout[] mRelativeLayoutList;
    private TextView[]       mTextviews;

    private int index;
    private int currentTabIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintest);
        initView();
    }


    private void initView() {
        //tab rlbtn list
        mRelativeLayoutList = new RelativeLayout[4];
        mRelativeLayoutList[0] = (RelativeLayout) findViewById(R.id.rl_shop);
        mRelativeLayoutList[1] = (RelativeLayout) findViewById(R.id.rl_near);
        mRelativeLayoutList[2] = (RelativeLayout) findViewById(R.id.rl_shopcar);
        mRelativeLayoutList[3] = (RelativeLayout) findViewById(R.id.rl_my);
        mRelativeLayoutList[0].setSelected(true);   //default selected
        mRelativeLayoutList[0].setOnClickListener(this);
        mRelativeLayoutList[1].setOnClickListener(this);
        mRelativeLayoutList[2].setOnClickListener(this);
        mRelativeLayoutList[3].setOnClickListener(this);
        //tab textview list
        mTextviews = new TextView[4];
        mTextviews[0] = (TextView) findViewById(R.id.tv_shop);
        mTextviews[1] = (TextView) findViewById(R.id.tv_near);
        mTextviews[2] = (TextView) findViewById(R.id.tv_shopcar);
        mTextviews[3] = (TextView) findViewById(R.id.tv_my);
        mTextviews[0].setTextColor(Color.RED);   //default selected
        //tab fragment
        shopFragment = new ShopFragment();
        nearFragment = new NearFragment();
        shopCarFragment = new ShopCarFragment();
        myFragment = new MyFragment();
        mFragmentList = new Fragment[]{shopFragment, nearFragment, shopCarFragment, myFragment};
        //add the first fragment and hide other.
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, shopFragment)
                .add(R.id.fragment_container, nearFragment)
                .add(R.id.fragment_container, shopCarFragment)
                .add(R.id.fragment_container, myFragment)
                .hide(nearFragment).hide(shopCarFragment)
                .hide(myFragment).show(shopFragment).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_shop:
                index = 0;
                break;
            case R.id.rl_near:
                index = 1;
                break;
            case R.id.rl_shopcar:
                index = 2;
                break;
            case R.id.rl_my:
                index = 3;
                break;
        }
        if (currentTabIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager()
                    .beginTransaction();
            trx.hide(mFragmentList[currentTabIndex]);
            if (!mFragmentList[index].isAdded()) {
                trx.add(R.id.fragment_container, mFragmentList[index]);
            }
            trx.show(mFragmentList[index]).commit();
        }
        mRelativeLayoutList[currentTabIndex].setSelected(false);
        // hold current tab selected
        mRelativeLayoutList[index].setSelected(true);
        mTextviews[currentTabIndex].setTextColor(Color.GRAY);
        mTextviews[index].setTextColor(Color.RED);
        currentTabIndex = index;
    }


    public void switchFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment).commit();
    }

}

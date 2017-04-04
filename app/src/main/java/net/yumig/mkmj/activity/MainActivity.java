package net.yumig.mkmj.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.currency.library.controller.ActivityManager;
import com.currency.library.controller.BaseActivity;

import net.yumig.mkmj.R;
import net.yumig.mkmj.fragment.MyFragment;
import net.yumig.mkmj.fragment.NearFragment;
import net.yumig.mkmj.fragment.Shop1Fragment;
import net.yumig.mkmj.fragment.ShopCarFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;
    @BindView(R.id.ib_shop)
    ImageView ibShop;
    @BindView(R.id.tv_shop)
    TextView tvShop;
    @BindView(R.id.rl_shop)
    RelativeLayout rlShop;
    @BindView(R.id.ib_near)
    ImageView ibNear;
    @BindView(R.id.tv_near)
    TextView tvNear;
    @BindView(R.id.rl_near)
    RelativeLayout rlNear;
    @BindView(R.id.ib_shopcar)
    ImageView ibShopcar;
    @BindView(R.id.tv_shopcar)
    TextView tvShopcar;
    @BindView(R.id.rl_shopcar)
    RelativeLayout rlShopcar;
    @BindView(R.id.ib_my)
    ImageView ibMy;
    @BindView(R.id.tv_my)
    TextView tvMy;
    @BindView(R.id.rl_my)
    RelativeLayout rlMy;

    private ArrayList<Fragment> mFragments;
    private RelativeLayout[] mRelativeLayoutList;
    private Shop1Fragment shop1Fragment;
    private NearFragment nearFragment;
    private ShopCarFragment shopCarFragment;
    private MyFragment myFragment;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onViewCreatedFinish(Bundle saveInstanceState) {
        initView();
    }

    @Override
    protected String[] getRequestUrls() {
        return new String[0];
    }

    private void initView() {
        addFragments();
    }

    private void addFragments() {
        //        mFragments = new SparseArray<>();
        //        mFragments.put(0, Fragment.instantiate(mContext, Shop1Fragment.class.getName()));
        //        mFragments.put(1, Fragment.instantiate(mContext, NearFragment.class.getName()));
        //        mFragments.put(2, Fragment.instantiate(mContext, ShopCarFragment.class.getName()));
        //        mFragments.put(3, Fragment.instantiate(mContext, MyFragment.class.getName()));


        shop1Fragment = new Shop1Fragment();
        nearFragment = new NearFragment();
        shopCarFragment = new ShopCarFragment();
        myFragment = new MyFragment();
        mFragments = new ArrayList<>();
        mFragments.add(shop1Fragment);
        mFragments.add(nearFragment);
        mFragments.add(shopCarFragment);
        mFragments.add(myFragment);

        mRelativeLayoutList = new RelativeLayout[4];
        mRelativeLayoutList[0] = rlShop;
        mRelativeLayoutList[1] = rlNear;
        mRelativeLayoutList[2] = rlShopcar;
        mRelativeLayoutList[3] = rlMy;
        mRelativeLayoutList[0].setSelected(true);   //default selected
        //add the first fragment and hide other.
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, shop1Fragment)
                .add(R.id.fragment_container, nearFragment)
                .add(R.id.fragment_container, shopCarFragment)
                .add(R.id.fragment_container, myFragment)
                .hide(nearFragment).hide(shopCarFragment)
                .hide(myFragment).show(shop1Fragment).commit();

    }

    private int index;
    private int currentTabIndex;

    @OnClick({R.id.rl_shop, R.id.rl_near, R.id.rl_shopcar, R.id.rl_my})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
            trx.hide(mFragments.get(currentTabIndex));
            if (!mFragments.get(index).isAdded()) {
                trx.add(R.id.fragment_container, mFragments.get(index));
            }
            trx.show(mFragments.get(index)).commit();
        }
        mRelativeLayoutList[currentTabIndex].setSelected(false);
        // hold current tab selected
        mRelativeLayoutList[index].setSelected(true);
        currentTabIndex = index;
    }


    //记录用户首次点击返回键的时间
    private long firstTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - firstTime > 2000) {
                Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                firstTime = System.currentTimeMillis();
            } else {
                ActivityManager.getInstance().finishAllActivity();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}

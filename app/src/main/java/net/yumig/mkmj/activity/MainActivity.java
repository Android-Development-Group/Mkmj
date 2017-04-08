package net.yumig.mkmj.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.currency.library.controller.ActivityManager;
import com.currency.library.controller.BaseActivity;
import com.currency.library.utils.ToastUtils;
import com.currency.library.widget.view.DragPointView;
import com.currency.library.widget.viewpager.UnScrollableViewPager;

import net.yumig.mkmj.R;
import net.yumig.mkmj.fragment.MyFragment;
import net.yumig.mkmj.fragment.NearFragment;
import net.yumig.mkmj.fragment.Shop1Fragment;
import net.yumig.mkmj.fragment.ShopCarFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity
        implements ViewPager.OnPageChangeListener,
        DragPointView.OnDragListencer {

    @BindView(R.id.main_viewpager)
    UnScrollableViewPager mMainViewpager;
    @BindView(R.id.tab_img_chats)
    ImageView mTabImgChats;
    @BindView(R.id.tab_text_chats)
    TextView mTabTextChats;
    @BindView(R.id.seal_num)
    DragPointView mSealNum;
    @BindView(R.id.tab_img_contact)
    ImageView mTabImgContact;
    @BindView(R.id.tab_text_contact)
    TextView mTabTextContact;
    @BindView(R.id.tab_img_find)
    ImageView mTabImgFind;
    @BindView(R.id.tab_text_find)
    TextView mTabTextFind;
    @BindView(R.id.tab_img_me)
    ImageView mTabImgMe;
    @BindView(R.id.tab_text_me)
    TextView mTabTextMe;
    @BindView(R.id.mine_red)
    ImageView mMineRed;

    private List<Fragment> mFragment = new ArrayList<>();

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onViewCreatedFinish(Bundle saveInstanceState) {
        changeTextViewColor();
        changeSelectedTabState(0);
        initMainViewPager();

    }

    @Override
    protected String[] getRequestUrls() {
        return new String[0];
    }

    private void initMainViewPager() {
        mFragment.add(new Shop1Fragment());
        mFragment.add(new NearFragment());
        mFragment.add(new ShopCarFragment());
        mFragment.add(new MyFragment());
        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            @Override
            public int getCount() {
                return mFragment.size();
            }
        };
        mMainViewpager.setAdapter(fragmentPagerAdapter);
        mMainViewpager.setOffscreenPageLimit(4);
        mMainViewpager.setOnPageChangeListener(this);
    }

    private void changeTextViewColor() {
        mTabImgChats.setBackgroundDrawable(getResources().getDrawable(R.drawable.tabbar_shangcheng));
        mTabImgContact.setBackgroundDrawable(getResources().getDrawable(R.drawable.tabbar_fujin));
        mTabImgFind.setBackgroundDrawable(getResources().getDrawable(R.drawable.tabbar_gouwuche));
        mTabImgMe.setBackgroundDrawable(getResources().getDrawable(R.drawable.tabbar_wode));
        mTabTextChats.setTextColor(Color.parseColor("#abadbb"));
        mTabTextContact.setTextColor(Color.parseColor("#abadbb"));
        mTabTextFind.setTextColor(Color.parseColor("#abadbb"));
        mTabTextMe.setTextColor(Color.parseColor("#abadbb"));
    }

    private void changeSelectedTabState(int position) {
        switch (position) {
            case 0:
                mTabTextChats.setTextColor(getResources().getColor(R.color.appRed));
                mTabImgChats.setBackgroundDrawable(getResources().getDrawable(R.drawable.tabbar_shangchen_pre));
                break;
            case 1:
                mTabTextContact.setTextColor(getResources().getColor(R.color.appRed));
                mTabImgContact.setBackgroundDrawable(getResources().getDrawable(R.drawable.tabbar_fujin_pre));
                break;
            case 2:
                mTabTextFind.setTextColor(getResources().getColor(R.color.appRed));
                mTabImgFind.setBackgroundDrawable(getResources().getDrawable(R.drawable.tabbar_gouwuche_pre));
                break;
            case 3:
                mTabTextMe.setTextColor(getResources().getColor(R.color.appRed));
                mTabImgMe.setBackgroundDrawable(getResources().getDrawable(R.drawable.tabbar_wode_pre));
                break;
        }
    }

    long firstClick = 0;
    long secondClick = 0;

    @OnClick({R.id.seal_chat, R.id.seal_contact_list, R.id.seal_find, R.id.seal_me})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.seal_chat:
                if (mMainViewpager.getCurrentItem() == 0) {
                    if (firstClick == 0) {
                        firstClick = System.currentTimeMillis();
                    } else {
                        secondClick = System.currentTimeMillis();
                    }
                    Log.i("MainActivity", "time = " + (secondClick - firstClick));
                    if (secondClick - firstClick > 0 && secondClick - firstClick <= 800) {
//                        mConversationListFragment.focusUnreadItem();
                        firstClick = 0;
                        secondClick = 0;
                    } else if (firstClick != 0 && secondClick != 0) {
                        firstClick = 0;
                        secondClick = 0;
                    }
                }
                mMainViewpager.setCurrentItem(0, false);
                break;
            case R.id.seal_contact_list:
                mMainViewpager.setCurrentItem(1, false);
                break;
            case R.id.seal_find:
                mMainViewpager.setCurrentItem(2, false);
                break;
            case R.id.seal_me:
                mMainViewpager.setCurrentItem(3, false);
                mMineRed.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        changeTextViewColor();
        changeSelectedTabState(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onDragOut() {
        mSealNum.setVisibility(View.GONE);
        ToastUtils.showToastShort(mContext, "清除成功");

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.getBooleanExtra("systemconversation", false)) {
            mMainViewpager.setCurrentItem(0, false);
        }
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

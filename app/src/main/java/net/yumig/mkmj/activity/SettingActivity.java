package net.yumig.mkmj.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.currency.library.widget.image.RoundImageView;
import com.currency.library.widget.scroll.SupportScrollView;

import net.yumig.mkmj.R;
import net.yumig.mkmj.TestActivity;
import net.yumig.mkmj.base.TitleActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingActivity extends TitleActivity {

    @BindView(R.id.iv_header_icon)
    RoundImageView ivHeaderIcon;
    @BindView(R.id.rl_header_icon)
    RelativeLayout rlHeaderIcon;
    @BindView(R.id.tv_nick_name)
    TextView tvNickName;
    @BindView(R.id.rl_nick_name)
    RelativeLayout rlNickName;
    @BindView(R.id.tv_man)
    TextView tvMan;
    @BindView(R.id.tv_woman)
    TextView tvWoman;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.rl_area)
    RelativeLayout rlArea;
    @BindView(R.id.tv_phoneNumber)
    TextView tvPhoneNumber;
    @BindView(R.id.tv_resetPhone)
    TextView tvResetPhone;
    @BindView(R.id.tv_password)
    TextView tvPassword;
    @BindView(R.id.tv_resetPwd)
    TextView tvResetPwd;
    @BindView(R.id.sv_setting)
    SupportScrollView svSetting;
    @BindView(R.id.btn_loginout)
    Button btnLoginout;


    @Override
    public void setContentView() {
        setContentView(R.layout.activity_setting);
    }

    @Override
    protected void onViewCreatedFinish(Bundle saveInstanceState) {
        addNavigation();
    }

    @Override
    protected String[] getRequestUrls() {
        return new String[0];
    }

    @OnClick({R.id.rl_header_icon, R.id.rl_nick_name, R.id.tv_man, R.id.tv_woman, R.id.rl_area, R.id.tv_resetPhone, R.id.tv_resetPwd, R.id.btn_loginout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_header_icon:
                //更改头像
                startActivity(new Intent(SettingActivity.this, ChoseHeadImgActivity.class));
                break;
            case R.id.rl_nick_name:
                startActivity(new Intent(SettingActivity.this, SetNickNameActivity.class));
                //更改昵称
                break;
            case R.id.tv_man:
                //性别
                break;
            case R.id.tv_woman:
                //性别
                break;
            case R.id.rl_area:
                //地区
                break;
            case R.id.tv_resetPhone:
                //更改手机号
                break;
            case R.id.tv_resetPwd:
                //更改密码
                break;
            case R.id.btn_loginout:
                //注销
                startActivity(TestActivity.class);
                break;
        }
    }
}

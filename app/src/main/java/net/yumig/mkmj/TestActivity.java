package net.yumig.mkmj;

import android.os.Bundle;

import com.currency.library.controller.BaseActivity;
import com.currency.library.http.callback.SimpleFastJsonCallback;
import com.currency.library.utils.ToastUtils;

import net.yumig.mkmj.api.UserApi;
import net.yumig.mkmj.user.UserInfoBean;

public class TestActivity extends BaseActivity {

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_test);
    }

    @Override
    protected void onViewCreatedFinish(Bundle saveInstanceState) {

//        getData();
    }

    private void getData() {
        networkRequest(UserApi.getUserInfo(), new SimpleFastJsonCallback<UserInfoBean>(UserInfoBean.class, null) {
            @Override
            public void onSuccess(String url, final UserInfoBean result) {

//                        tv_user_nickname.setText(result.getNick_name());
            }

            @Override
            public void onFailure(String url, int statusCode, String msg) {
                super.onFailure(url, statusCode, msg);

                ToastUtils.showToastShort(mContext, msg);

            }
        }, false);
    }

    @Override
    protected String[] getRequestUrls() {
        return new String[0];
    }


}

package net.yumig.mkmj.test;

import android.os.Bundle;

import com.currency.library.controller.BaseActivity;

import net.yumig.mkmj.R;

import rx.Subscription;

public class TestActivity extends BaseActivity {

    protected Subscription subscription;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_test);
    }

    @Override
    protected void onViewCreatedFinish(Bundle saveInstanceState) {

        getData();
    }

    private void getData() {

    }

    @Override
    protected String[] getRequestUrls() {
        return new String[0];
    }


}

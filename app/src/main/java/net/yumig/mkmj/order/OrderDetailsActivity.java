package net.yumig.mkmj.order;

import android.os.Bundle;

import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.listener.HttpOnNextListener;

import net.yumig.mkmj.R;
import net.yumig.mkmj.base.TitleActivity;

public class OrderDetailsActivity extends TitleActivity implements HttpOnNextListener {

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_order_details);
    }

    @Override
    protected void onViewCreatedFinish(Bundle saveInstanceState) {
        addNavigation();


    }

    @Override
    protected String[] getRequestUrls() {
        return new String[0];
    }


    @Override
    public void onNext(String resulte, String method) {

    }

    @Override
    public void onError(ApiException e) {

    }
}

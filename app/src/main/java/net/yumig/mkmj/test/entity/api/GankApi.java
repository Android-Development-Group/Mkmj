package net.yumig.mkmj.test.entity.api;

import com.wzgiceman.rxretrofitlibrary.retrofit_rx.Api.BaseApi;

import net.yumig.mkmj.test.HttpService;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by T5-Jusenr on 2017/4/4.
 */

public class GankApi extends BaseApi {

    public GankApi() {
        setShowProgress(true);
        setMethod("福利/10/1");
        setCache(true);
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpService httpService = retrofit.create(HttpService.class);
        return httpService.getGank();
    }
}


package net.yumig.mkmj.test.entity.api;

import com.wzgiceman.rxretrofitlibrary.retrofit_rx.Api.BaseApi;

import net.yumig.mkmj.api.TotalBaseApi;
import net.yumig.mkmj.test.HttpService;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by T5-Jusenr on 2017/4/4.
 */

public class GankApi extends BaseApi {

    public GankApi() {
        setBaseUrl(TotalBaseApi.GANK_IO_URL);
        setShowProgress(true);
        setMethod(TotalBaseApi.Url.URL_FULI_10_1);
        setCache(true);
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpService httpService = retrofit.create(HttpService.class);
        return httpService.getGank();
    }
}


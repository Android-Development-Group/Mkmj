package net.yumig.mkmj.test;

import android.database.Observable;

import net.yumig.mkmj.api.BaseApi;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by T5-Jusenr on 2017/4/4.
 */

public interface GankApi {

    @GET(BaseApi.Url.URL_ANDROID_10_1)
    Observable<RetrofitBean<GankBean>> getUpgradeInfo(@Query("version") String version, @Query("appid") String appid);

    @GET(BaseApi.Url.URL_ALL_20_2)
    Observable<RetrofitBean<List<GankBean>>> getChildInfo(@Url String url);

    @GET(BaseApi.Url.URL_FULI_10_1)
    Observable<List<GankBean>> getGank();

    @GET(BaseApi.Url.URL_FULI_10_1)
    Observable<RetrofitBean<GankBean>> getChildIndex(@QueryMap Map<String, String> map);


    @FormUrlEncoded
    @POST(BaseApi.Url.URL_CARD_MYCARD)
    Observable<RetrofitBean> createServiceRelation(@Field("token") String token,
                                                   @Field("uid") String uid,
                                                   @Field("service_id") String service_id,
                                                   @Field("deviceid") String deviceid,
                                                   @Field("appid") String appid,
                                                   @Field("code") String code);

    @FormUrlEncoded
    @POST(BaseApi.Url.URL_CARD_MYCARD2)
    Observable<RetrofitBean<List<GankBean>>> getUserInfo(@FieldMap Map<String, String> map);


    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(BaseApi.Url.URL_CARD_MYCARD2)
    Observable<String> requestRxDeviceInfoNew(
            @Body RequestBody body);
}


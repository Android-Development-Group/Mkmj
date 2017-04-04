package net.yumig.mkmj.api;

import com.currency.library.http.request.FormEncodingRequestBuilder;

/**
 * 继承固定请求参数
 * Created by guchenkai on 2015/11/26.
 */
public class PTWDRequestHelper {
    //===================request key================================
    public static final String REQUEST_KEY_UID = "uid";
    public static final String REQUEST_KEY_TOKEN = "token";
    public static final String REQUEST_KEY_DEVICE_ID = "device_id";
    public static final String REQUEST_KEY_APP_ID = "appid";

    public static final String REQUEST_KEY_START_DEVICE_NAME = "device_name";


    /**
     * 封装固定请求参数(商城使用)
     *
     * @return Request实例
     */
    public static FormEncodingRequestBuilder store() {
        return FormEncodingRequestBuilder.newInstance()
                .addParam(PTWDRequestHelper.REQUEST_KEY_UID, "")
                .addParam(PTWDRequestHelper.REQUEST_KEY_TOKEN, "")
                .addParam(PTWDRequestHelper.REQUEST_KEY_APP_ID, "")
                .addParam(PTWDRequestHelper.REQUEST_KEY_DEVICE_ID, "");
    }

    /**
     * 封装固定请求参数(用户使用)
     *
     * @return Request实例
     */
    public static FormEncodingRequestBuilder user() {
        return FormEncodingRequestBuilder.newInstance()
                .addParam(PTWDRequestHelper.REQUEST_KEY_UID, "")
                .addParam(PTWDRequestHelper.REQUEST_KEY_TOKEN, "")
                .addParam(PTWDRequestHelper.REQUEST_KEY_APP_ID, "")
                .addParam(PTWDRequestHelper.REQUEST_KEY_DEVICE_ID, "");
    }

    /**
     * 封装固定请求参数(购物车使用)
     *
     * @return Request实例
     */
    public static FormEncodingRequestBuilder shopCar() {
        return FormEncodingRequestBuilder.newInstance()
                .addParam(PTWDRequestHelper.REQUEST_KEY_UID, "")
                .addParam(PTWDRequestHelper.REQUEST_KEY_TOKEN, "")
                .addParam(PTWDRequestHelper.REQUEST_KEY_APP_ID, "")
                .addParam(PTWDRequestHelper.REQUEST_KEY_DEVICE_ID, "");
    }
}

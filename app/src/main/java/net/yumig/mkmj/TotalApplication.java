package net.yumig.mkmj;

import com.currency.library.controller.ActivityManager;
import com.currency.library.controller.BaseApplication;
import com.currency.library.http.OkHttpManager;
import com.currency.library.http.interceptor.CacheStrategyInterceptor;
import com.currency.library.http.interceptor.HeaderInfoInterceptor;
import com.currency.library.http.interceptor.NetworkInterceptor;
import com.currency.library.http.interceptor.ResponseInfoInterceptor;
import com.currency.library.utils.AppUtils;
import com.currency.library.utils.Logger;
import com.currency.library.utils.SDCardUtils;

import net.yumig.mkmj.api.BaseApi;

import java.io.File;

import okhttp3.OkHttpClient;

/**
 * Description: TotalApplication
 * Copyright  : Copyright (c) 2017
 * Email      : jusenr@163.com
 * Author     : Jusenr
 * Date       : 2017/04/04 15:12.
 */
public class TotalApplication extends BaseApplication {
    private static final String TAG = "MkMj_Log";

    @Override
    public void onCreate() {
        super.onCreate();
        //创建储存文件夹
        File appDir = new File(getSdCardPath());
        if (!appDir.exists()) {
            boolean isSuccess = appDir.mkdirs();
            System.out.println(TAG + "-Create-" + getSdCardPath() + ":===================>" + isSuccess);
        }


    }

    @Override
    protected void initEnvironment() {
        //初始化Service Api
        BaseApi.init(BaseApi.HOST_TEST);
    }

    @Override
    public String appDeviceId() {
        return null;
    }

    @Override
    protected boolean isDebug() {
        //根据需求更改
        return BaseApi.isInnerEnvironment();
    }

    @Override
    protected String getBuglyKey() {

        return "ad473298bc";
    }

    @Override
    public String getPackageName() {
        return "net.yumig.mkmj";
    }

    @Override
    protected String getLogTag() {
        return "mkmj";
    }

    @Override
    protected String getSdCardPath() {
        return SDCardUtils.getSDCardPath() + File.separator + getLogTag();
    }

    @Override
    protected String getNetworkCacheDirectoryPath() {
        return sdCardPath + File.separator + "http_cache";
    }

    @Override
    protected int getNetworkCacheSize() {
        return 20 * 1024 * 1024;
    }

    @Override
    protected int getNetworkCacheMaxAgeTime() {
        return 0;
    }

    @Override
    public OkHttpClient initOkHttpClient() {
        return OkHttpManager.getInstance(getNetworkCacheDirectoryPath(), getNetworkCacheSize())
                .addInterceptor(new NetworkInterceptor())
                .addInterceptor(new ResponseInfoInterceptor())
                .addInterceptor(new CacheStrategyInterceptor())
                .addInterceptor(new HeaderInfoInterceptor(AppUtils.getVersionName(this)))
                .build();
    }

    /**
     * 捕捉到异常就退出App
     *
     * @param ex 异常信息
     */
    @Override
    protected void onCrash(Throwable ex) {
        Logger.e("APP崩溃了,错误信息是" + ex.getMessage());
        ex.printStackTrace();
        ActivityManager.getInstance().finishAllActivity();
    }
}

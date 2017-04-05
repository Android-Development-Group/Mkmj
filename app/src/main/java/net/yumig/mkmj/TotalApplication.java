package net.yumig.mkmj;

import com.currency.library.BaseApplication;
import com.currency.library.controller.ActivityManager;
import com.currency.library.utils.Logger;
import com.currency.library.utils.SDCardUtils;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.RxRetrofitApp;

import net.yumig.mkmj.api.Base1Api;

import java.io.File;

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

        RxRetrofitApp.init(this);

    }

    @Override
    protected void initEnvironment() {
        //初始化Service Api
        Base1Api.init(Base1Api.HOST_TEST);
    }

    @Override
    public String appDeviceId() {
        return null;
    }

    @Override
    protected boolean isDebug() {
        //根据需求更改
        return Base1Api.isInnerEnvironment();
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
        ActivityManager.getInstance().killProcess(this);
    }
}

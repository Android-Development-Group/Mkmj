package net.yumig.mkmj.api;

/**
 * Description: API管理
 * Copyright  : Copyright (c) 2017
 * Email      : jusenr@163.com
 * Author     : Jusenr
 * Date       : 2017/04/04 15:12.
 */

public abstract class BaseApi {

    public static final int HOST_FORMAL = 1;//正式环境
    public static final int HOST_DEV = 2;//开发环境
    public static final int HOST_TEST = 3;//测试环境

    public static int HOST_NOW;//当前环境

    //e.g.
    public static String GANK_IO_URL = "";

    public static String QRCODE_BASE_URL = "";
    public static String STORE_BASE_URL = "";


    /**
     * environment: 1，外网 2，开发内网，3，测试内网
     */
    public static void init(int environment) {
        HOST_NOW = environment;
        switch (environment) {
            case 1:
                GANK_IO_URL = "http://gank.io/api/data/";
                QRCODE_BASE_URL = "";
                STORE_BASE_URL = "";
                break;
            case 2:
                GANK_IO_URL = "http://gank.io/api/data/";
                QRCODE_BASE_URL = "";
                STORE_BASE_URL = "";
                break;
            case 3:
                GANK_IO_URL = "http://gank.io/api/data/";
                QRCODE_BASE_URL = "";
                STORE_BASE_URL = "";
                break;
        }
    }


    public static boolean isInnerEnvironment() {
        return HOST_NOW == HOST_DEV || HOST_NOW == HOST_TEST;
    }


    /*所有的相对URL*/
    public static class Url {
        //=====================AccountApi ========================//
        public static final String URL_QR_SERVER_HANDLER = "api/register";            //注册
        public static final String URL_CARD_MYCARD = "api/register";
        public static final String URL_CARD_MYCARD2 = "api/register";
        public static final String URL_CARD_MYCARD3 = "api/register";
        public static final String URL_GET_VERSION = "api/register";


        /**
         * Gank.io
         */
        public static final String URL_ANDROID_10_1 = "Android/10/1";
        public static final String URL_ALL_20_2 = "all/20/2";
        public static final String URL_FULI_10_1 = "福利/10/1";
    }

}

package net.yumig.mkmj.api;

import com.currency.library.http.request.RequestMethod;

import okhttp3.Request;

/**
 * 用户接口
 * Created by guchenkai on 2015/12/8.
 */
public class UserApi {
    private static final String REQUEST_NICK_NAME = "nick_name";//昵称
    private static final String REQUEST_PROFILE = "profile";//简介
    private static final String REQUEST_EXT = "ext";
    private static final String REQUEST_UID = "uid";
    private static final String REQUEST_FILENAME = "file_name";
    private static final String REQUEST_FILEHASH = "hash";
    private static final String REQUEST_HEAD_ICON = "userProfilePhoto";//头像
    private static final String REQUEST_PAGE = "page";//页码
    private static final String REQUEST_TYPE = "type";//类型
    private static final String REQUEST_MSG = "message";//提问问题
    public static final String HAS_DEVICE = "has_device";//是否已经添加设备

    private static final String BABY_ID = "baby_id";//孩子ID
    private static final String BABY_NAME = "baby_name";//孩子昵称
    private static final String RELATION = "relation";//与孩子关系
    private static final String SEX = "sex";//孩子性别
    private static final String BIRTHDAY = "birthday";//孩子生日


    private static final String REQUEST_NICKNAME = "nickName";

    public static void install(String base_url) {
//        WEIDU_BASE_URL = base_url;
    }


    /**
     * 提交葡萄籽问题
     *
     * @param nickName         昵称
     * @param userProfilePhoto 头像
     * @param msg              问题
     */
    public static Request questionAdd(String msg, String nickName, String userProfilePhoto) {
        return PTWDRequestHelper.user()
                .addParam(REQUEST_MSG, msg).addParam(REQUEST_NICKNAME, nickName)
                .addParam(REQUEST_HEAD_ICON, userProfilePhoto).
                        build(RequestMethod.POST, BaseApi.Url.URL_CARD_MYCARD);
    }




    /**
     * 查询孩子信息
     */
    public static Request getUserInfo() {
        return PTWDRequestHelper.user()
                .build(RequestMethod.POST, BaseApi.Url.URL_CARD_MYCARD2);
    }

    /**
     * 保存孩子信息
     */
    public static Request setChildInfo(String baby_id, String baby_name, String relation, String sex, String birthday) {
        return PTWDRequestHelper.user()
                .addParam(BABY_ID, baby_id)
                .addParam(BABY_NAME, baby_name)
                .addParam(RELATION, relation)
                .addParam(SEX, sex)
                .addParam(BIRTHDAY, birthday)
                .build(RequestMethod.POST, BaseApi.Url.URL_CARD_MYCARD3);
    }

}

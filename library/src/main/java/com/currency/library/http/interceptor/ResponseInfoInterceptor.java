package com.currency.library.http.interceptor;

import android.os.Handler;
import android.os.Looper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.currency.library.controller.eventbus.EventBusHelper;
import com.currency.library.http.HttpCode;
import com.currency.library.http.HttpConstants;
import com.currency.library.utils.Logger;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * reponse信息拦截器
 * Created by guchenkai on 2016/1/22.
 */
public class ResponseInfoInterceptor implements Interceptor {
    private static final String TAG = ResponseInfoInterceptor.class.getSimpleName();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        final Response response = chain.proceed(request);
        int code = response.code();
        if (code == HttpCode.HTTP_SUCCESS_CODE) {
            ResponseBody responseBody = response.body();
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE);

            Charset charset = Charset.forName("UTF-8");
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                try {
                    charset = contentType.charset(charset);
                } catch (UnsupportedCharsetException e) {
                    return response;
                }
            }
            Buffer buffer = source.buffer();
            String bufferString = buffer.clone().readString(charset);

            JSONObject jsonObject = null;
            try {
                jsonObject = JSON.parseObject(bufferString);
                if (jsonObject != null) {
                    final Integer httpCode = jsonObject.getInteger("http_code");
                    final String msg = jsonObject.getString("msg");
                    if (httpCode != null && httpCode != 200) {
                        Logger.dSave(TAG, jsonObject.toJSONString());
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                switch (httpCode) {

                                    case HttpCode.HTTP_LOGIN_EXPIRE_CODE://登录过期
                                        EventBusHelper.post(HttpConstants.TEXT_USER_LOGIN_EXPIRE,
                                                HttpConstants.EVENT_USER_LOGIN_EXPIRE);
                                        break;

                                    case HttpCode.HTTP_LOGIN_KICKED_CODE://账号在其他设备上登录

                                        break;
                                }
                            }
                        });
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return response;
            }
        }
        return response;
    }
}

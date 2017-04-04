package net.yumig.mkmj.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

/**
 * App信息工具类
 *
 * @author widebluesky
 */
public class AppInfoUtil {

    private AppInfoUtil() {

    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     * @throws Exception
     */
    public static int getVersionCode(Context context) {
        PackageManager manager = context.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(context.getPackageName(), PackageManager.GET_CONFIGURATIONS);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        int versionCode = info.versionCode;
        return versionCode;
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     * @throws Exception
     */
    public String getVersionName(Context context) throws Exception {
        PackageManager manager = context.getPackageManager();
        PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
        String versionName = info.versionName;
        return versionName;
    }

}

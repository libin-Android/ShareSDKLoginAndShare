package com.example.administrator.day40;

import android.app.Application;

import com.umeng.comm.core.CommunitySDK;
import com.umeng.comm.core.impl.CommunityFactory;
import com.umeng.comm.core.sdkmanager.LocationSDKManager;
import com.umeng.community.location.DefaultLocationImpl;
import com.umeng.socialize.PlatformConfig;

/**
 * Created by Administrator on 2016/7/21.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CommunitySDK mCommSDK = CommunityFactory.getCommSDK(this);
        mCommSDK.initSDK(this);
        //代码中通过如下代码注入高德定位SDK即可:
        LocationSDKManager.getInstance().addAndUse(new DefaultLocationImpl());
        //第三方分享和登录的注入
        PlatformConfig.setWeixin("wx96110a1e3af63a39", "c60e3d3ff109a5d17013df272df99199");
        PlatformConfig.setSinaWeibo("275392174", "d96fb6b323c60a42ed9f74bfab1b4f7a");
        PlatformConfig.setQQZone("1104606393", "X4BAsJAVKtkDQ1zQ");
    }
}

package com.weex.app;

import android.app.Application;
import android.os.Build;
import android.os.StrictMode;

import com.alibaba.weex.plugin.loader.WeexPluginContainer;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;
import com.weex.app.extend.ImageAdapter;
import com.weex.app.extend.WXEventModule;
import com.weex.app.image.ImagePickerModule;
import com.weex.app.util.AppConfig;

public class WXApplication extends Application {

    private static WXApplication mInstance;

    public static WXApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        WXSDKEngine.addCustomOptions("appName", "WXSample");
        WXSDKEngine.addCustomOptions("appGroup", "WXApp");
        if (Build.VERSION.SDK_INT >= 18) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            builder.detectFileUriExposure();
        }
        WXSDKEngine.initialize(this,
                new InitConfig.Builder().setImgAdapter(new ImageAdapter()).build()
        );
        try {
            WXSDKEngine.registerModule("event", WXEventModule.class);
            WXSDKEngine.registerModule("imagePicker", ImagePickerModule.class);
        } catch (WXException e) {
            e.printStackTrace();
        }
        AppConfig.init(this);
        WeexPluginContainer.loadAll(this);
    }
}

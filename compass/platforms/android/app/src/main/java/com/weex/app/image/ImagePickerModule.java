package com.weex.app.image;

import android.app.Activity;
import android.content.Intent;

import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import com.weex.app.photo.PhotoWallActivity;
import com.weex.app.util.LogUtil;

/**
 * Created by manji
 * Date：2018/10/9 下午3:42
 * Desc：
 */
public class ImagePickerModule extends WXModule {

    private static final int REQUEST_PIC_CODE = 0x101;
    private static final int REQUEST_TAKE_PIC = 0x110;

    private JSCallback mJsCallBack;

    @JSMethod
    public void selectPhoto(String photoNum, JSCallback callback) {
        //选择相册图片
        mJsCallBack = callback;
        ((Activity) mWXSDKInstance.getContext()).startActivityForResult(getPhotoIntent(Integer.valueOf(photoNum)
                , false), REQUEST_PIC_CODE);
    }

    @JSMethod
    public void takePhoto(JSCallback callback) {
        //拍照
        LogUtil.e("进入拍照");
        mJsCallBack = callback;
        ((Activity) mWXSDKInstance.getContext()).startActivityForResult(getPhotoIntent(1
                , true), REQUEST_TAKE_PIC);
    }

    private Intent getPhotoIntent(int photoNum, boolean isOnlyFromCamera) {
        Intent intent = new Intent(mWXSDKInstance.getContext(), PhotoWallActivity.class);
        intent.putExtra(PhotoWallActivity.EXTRA_MEDIA_TYPE, PhotoWallActivity.TYPE_PICTURE);
        intent.putExtra(PhotoWallActivity.EXTRA_IS_MUTIPLE, true);
        intent.putExtra(PhotoWallActivity.EXTRA_MAX_PHOTO, photoNum);
        intent.putExtra(PhotoWallActivity.EXTRA_IS_LIMIT_MAX, true);
        intent.putExtra(PhotoWallActivity.IS_ONLY_FROM_CAMERA, isOnlyFromCamera);
        return intent;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && null != data) {
            if (requestCode == REQUEST_TAKE_PIC) {
                LogUtil.e(data.getStringArrayListExtra("path").get(0) + "");
                mJsCallBack.invoke(data.getStringArrayListExtra("path").get(0));
            } else if (requestCode == REQUEST_PIC_CODE) {
                LogUtil.e(data.getStringArrayListExtra("path") + "");
                mJsCallBack.invoke(data.getStringArrayListExtra("path"));
            }
        }
    }
}
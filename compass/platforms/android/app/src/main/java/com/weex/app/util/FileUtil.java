package com.weex.app.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;


import com.weex.app.BuildConfig;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * 作者：yang
 * 时间：17/7/3
 * 邮箱：yangyang@maihaoche.com
 */
public class FileUtil {


    public static final String FILE_PROVIDER_NAME =
            BuildConfig.DEBUG ?
                    "com.weex.app.debug.android.fileprovider"
                    : "com.weex.app.android.fileprovider";

    public static final String FILE_PROVIDER_NAME1 = "com.weex.app.android.fileprovider";

    /**
     * 获取外部的文件路径
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String getExternalFilePath(Context context, String fileName) {
        File file = context.getExternalFilesDir(null);
        if (file != null && file.exists()) {
            return file.getAbsolutePath() + File.separator + fileName;
        } else {
            file = context.getFilesDir();
            return file.getAbsolutePath() + File.separator + fileName;
        }
    }

    /**
     * 安装某个目录路径下的apk文件
     *
     * @param context
     * @param filePath
     * @return
     */
    public static boolean installApk(Context context, String filePath) {
        if (TextUtils.isEmpty(filePath)) return false;
        File file = new File(filePath);
        if (!file.exists()) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_INSTALL_PACKAGE);
            intent.setData(FileProvider.getUriForFile(context, FileUtil.FILE_PROVIDER_NAME1, file));
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            context.startActivity(intent);
        } else {
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            context.startActivity(intent);
        }
//        Intent intent = new Intent();
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.setAction(Intent.ACTION_VIEW);
//        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
//        context.startActivity(intent);
        return true;
    }

    /**
     * 检查一个文件的md5是否与目标路径的文件一致
     */
    public static boolean isTheSameMD5File(String filePath, String exceptedMD5) {
        if (TextUtils.isEmpty(filePath)) {
            return false;
        }
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            return false;
        }
        MessageDigest digest;
        FileInputStream in;
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        String md5 = bigInt.toString(16);
        //不满足32位补0
        if (md5.length() < 32) {
            for (int i = 0; i < 32 - md5.length(); i++) {
                md5 = "0" + md5;
            }
        }
        return md5.equals(exceptedMD5);

    }
}

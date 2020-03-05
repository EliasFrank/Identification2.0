package com.example.identification20.myserver;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import com.example.identification20.myactivity.MainActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyCrop {
    private static final int CROP_PHOTO=4;
    private static final int CROP_PHOTO_1=3;

    /**
     * 调用裁剪功能
     * @param uri
     * @param mainActivity
     * @param requestCode
     */
    public static void openCorp(Uri uri, MainActivity mainActivity,int requestCode){
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        mainActivity.startActivityForResult(intent, requestCode);
    }

    /**
     * 根据请求码保存裁剪后的图片地址
     * @param name
     * @param bmp
     * @param requestCode
     */
    public static void saveImage(String name, Bitmap bmp,int requestCode) {
        File appDir = new File(Environment.getExternalStorageDirectory().getPath());
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = name + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
            if(requestCode ==CROP_PHOTO ) {
                //保存裁剪后的图片路径
                MyCamera.setPath(file.getAbsolutePath());
                MyResult.setImagePath(file.getAbsolutePath());
            }else if(requestCode == CROP_PHOTO_1) {
                MyPhoto.setPath(file.getAbsolutePath());
                MyResult.setImagePath(file.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

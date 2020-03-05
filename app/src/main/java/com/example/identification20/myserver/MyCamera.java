package com.example.identification20.myserver;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import java.io.File;
import java.io.IOException;

public class MyCamera {
    //相机
    private static final int TAKE_CAMERA=1;
    private static Uri imageUri=null;
    private AppCompatActivity myActivity;
    private static File outputImage;
    private static String imagePath;
    public MyCamera( AppCompatActivity  myActivity){
        this.myActivity = myActivity;
    }
    public void initCamera(){
         outputImage = new File(myActivity.getExternalCacheDir(), "output_image.jpg");
        try {
            if(outputImage.exists()){
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(Build.VERSION.SDK_INT >= 24){
            //File对象转换为Uri标识对象
            imageUri = FileProvider.getUriForFile(myActivity.getApplicationContext(),
                    "com.example.identification20.fileprovider2", outputImage);
        }else{
            //指定图片的输出地址
            imageUri = Uri.fromFile(outputImage);
        }
        //隐式Intent，启动相机程序
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        myActivity.startActivityForResult(intent,TAKE_CAMERA);
    }
    //通过这个方法调用相机
    public void openCamera(){
        if (ContextCompat.checkSelfPermission(myActivity.getApplicationContext(), android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(myActivity,
                    new String[]{Manifest.permission.CAMERA}, 1000);

        } else {
            initCamera();
        }
    }
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1000:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(myActivity.getApplicationContext(), "相机权限已申请", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(myActivity.getApplicationContext(), "相机权限已被禁止,请在设置中打开", Toast.LENGTH_SHORT).show();

                }
                break;
            default:
        }
    }
    public static Uri getImageUri(){
        return imageUri;
    }
    public static void setPath(String Path){
        imagePath=Path;
    }



}

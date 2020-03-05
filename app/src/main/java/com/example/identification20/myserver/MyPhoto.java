package com.example.identification20.myserver;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MyPhoto  {
    private static final int CHOOSE_PHOTO= 2;
    private AppCompatActivity myActivity;
    private  static String imagePath;
    public MyPhoto( AppCompatActivity  myActivity){
        this.myActivity = myActivity;
    }
    //通过这个方法调用图册
    public void openPhoto(){
        if (ContextCompat.checkSelfPermission(myActivity.getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(myActivity,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            openAlbum();
        }
    }
    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        myActivity.startActivityForResult(intent,CHOOSE_PHOTO);
    }
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(myActivity.getApplicationContext(), "你已经拒绝了该权限", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    public static void setPath(String Path){
        imagePath=Path;
    }
}

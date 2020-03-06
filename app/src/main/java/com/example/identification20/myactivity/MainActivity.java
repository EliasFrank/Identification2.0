package com.example.identification20.myactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import com.example.identification20.R;
import com.example.identification20.myfragment.PreventFragment;
import com.example.identification20.myfragment.WatchFragment;
import com.example.identification20.myserver.MyCamera;
import com.example.identification20.myserver.MyCrop;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity  {
    private static final int CHOOSE_PHOTO= 2;
    private static final int TAKE_CAMERA=1;
    private static final int CROP_PHOTO=4;
    private static final int CROP_PHOTO_1=3;
    private BottomBar bottomBar;
    private Fragment oneFragment,nowFragment;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //保证可以获取到该活动的context
        //加载布局
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //相机相册，获取实例，设置监听

        //获取初始的碎片
        oneFragment = new WatchFragment();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.other,oneFragment);
        fragmentTransaction.commit();
        nowFragment = oneFragment;
        //设置菜单栏事件监听
        bottomBar = findViewById(R.id.bottombar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            public void onTabSelected(int tabId) {
                switch (tabId) {
                    case R.id.tab1:
                        //切换到识别界面
                        replaceFragment(new WatchFragment());
                        break;
                    case R.id.tab2:
                        //切换到预防界面
                        replaceFragment(new PreventFragment());
                        break;
                    case R.id.tab3:
                        break;
                    case R.id.tab4:
                        break;
                }
            }
        });
    }

    /**
     * 用于替换碎片，达到切换页面的效果
     * @param fragment
     */
    public void replaceFragment(Fragment fragment){
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if (fragment.isAdded()) {
                fragmentTransaction.hide(nowFragment).show(fragment);
            } else {
                fragmentTransaction.hide(nowFragment).add(R.id.other,fragment);
            }
            nowFragment = fragment;
            fragmentTransaction.commit();
    }
    //lpxnp
    //调用相机和相册后的操作
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode ==RESULT_OK) {
            switch (requestCode){
                case CHOOSE_PHOTO:
                    //从相册选取图片后调用裁剪功能
                    MyCrop.openCorp(data.getData(),MainActivity.this,CROP_PHOTO_1);
                    break;
                case TAKE_CAMERA:
                    MyCrop.openCorp(MyCamera.getImageUri(),MainActivity.this,CROP_PHOTO);
                    break;
                case CROP_PHOTO_1:
                case CROP_PHOTO:
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        //在这里获得了剪裁后的Bitmap对象，可以用于上传
                        Bitmap image = bundle.getParcelable("data");
                        //设置到ImageView上
                        WatchFragment.getImageView().setImageBitmap(image);
                        //也可以进行一些保存、压缩等操作后上传
                        MyCrop.saveImage("crop", image,requestCode);
                    }
                    break;
            }
        }
    }
}

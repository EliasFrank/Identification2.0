package com.example.identification20.myfragment;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.identification20.R;
import com.example.identification20.myactivity.MainActivity;
import com.example.identification20.myserver.MyCamera;
import com.example.identification20.myserver.MyPhoto;

public class MeFragment extends Fragment implements View.OnClickListener{
    LinearLayout linearLayout1,linearLayout2,linearLayout3,linearLayout4;
    private static ImageView cricleImageView;
    private static ImageView imageView;
    private static final int CHOOSE_PHOTO= 5;
    private static final int TAKE_CAMERA=6;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.me_fragment, container, false);
        linearLayout1 = view.findViewById(R.id.part1);
        linearLayout2 = view.findViewById(R.id.part2);
        linearLayout3 = view.findViewById(R.id.part3);
        linearLayout4 = view.findViewById(R.id.part4);
        cricleImageView = view.findViewById(R.id.picture_small);
        imageView = view.findViewById(R.id.picture_big);
        this.registerForContextMenu(cricleImageView);
        return view;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        linearLayout1.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        linearLayout3.setOnClickListener(this);
        linearLayout4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.part1:
            case R.id.part2:
            case R.id.part3:
            case R.id.part4:
                Toast.makeText(getActivity(),"此功能暂未开放",Toast.LENGTH_LONG).show();
            break;
        }
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                MyPhoto myPhoto = new MyPhoto((MainActivity)getActivity(),CHOOSE_PHOTO);
                myPhoto.openPhoto();
                break;
            case 1:
                MyCamera myCamera = new MyCamera((MainActivity) getActivity(),TAKE_CAMERA);
                myCamera.openCamera();
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("头像选取");
        menu.add(0, 0, Menu.NONE, "相册选取头像");
        menu.add(0, 1, Menu.NONE, "拍照选取头像");
    }
    public static ImageView getImageView(){
        return imageView;
    }
    public static ImageView getCricleImageView(){
        return cricleImageView;
    }

}

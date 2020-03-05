package com.example.identification20.myfragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.identification20.R;
import com.example.identification20.myactivity.LoadingActivity;
import com.example.identification20.myactivity.MainActivity;
import com.example.identification20.myactivity.ResultActivity;
import com.example.identification20.myserver.MyCamera;
import com.example.identification20.myserver.MyPhoto;
import com.example.identification20.myserver.MyResult;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class WatchFragment extends Fragment {
     private ImageButton button_camera;
     private ImageButton button_photo;
    private static Button watch_button;
    private static ImageView picture ;
    private static  ProgressDialog progressDialog;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.watch_fragment, container, false);
        button_camera = view.findViewById(R.id.camera_button);
        button_photo = view.findViewById(R.id.photo_button);
        watch_button = view.findViewById(R.id.watch_button);
        picture = view.findViewById(R.id.picture);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        button_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyCamera myCamera = new MyCamera((MainActivity) getActivity());
                myCamera.openCamera();
            }
        });
        button_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyPhoto myPhoto = new MyPhoto((MainActivity)getActivity());
                myPhoto.openPhoto();
            }
        });
        watch_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("vvvvvvvvvvvvvvvvvvvvvvvv");
                System.out.println(MyResult.getImagePath());
                progressDialog = ProgressDialog.show(getContext(), "请稍等...", "识别中...", false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            OkHttpClient client = new OkHttpClient();
                            File file = new File(MyResult.getImagePath());
                            MultipartBody.Builder builder = new MultipartBody.Builder()
                                    .setType(MultipartBody.FORM)
                                    .addFormDataPart("name","imageName");
                            builder.addFormDataPart("headimg",file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
                            RequestBody requestBody =builder.build();
                            Request request = new Request.Builder()
                                    .url("http://121.40.174.157:8000/users/views/")
                                    .post(requestBody)
                                    .build();
                            Response response = client.newCall(request).execute();
                            MyResult.setResult(response.body().string());
                            progressDialog.dismiss();
                            Intent intent = new Intent(getActivity(), ResultActivity.class);
                            startActivity(intent);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }
    public static ImageView getImageView(){
        return picture;
    }
    public static ProgressDialog getDialog(){
        return progressDialog;
    }

}

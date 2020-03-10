package com.example.identification20.myfragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.identification20.R;
import com.example.identification20.evaluation.EvaluationAdapter;
import com.example.identification20.evaluation.bean.Avater;
import com.example.identification20.evaluation.bean.Evaluation;
import com.example.identification20.evaluation.bean.EvaluationItem;
import com.example.identification20.evaluation.bean.EvaluationPic;
import com.example.identification20.evaluation.bean.EvaluationReply;
import com.lzy.ninegrid.NineGridView;

import org.apache.http.params.HttpParams;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Request;
import okhttp3.Response;

public class CommunityFragment extends Fragment {
//    @BindView(R.id.ptr)
//    PtrClassFrameLayout ptr;

    @BindView(R.id.listView)
    ListView listView;

    private List<EvaluationItem> evaluationItemList = new ArrayList<>();
    Unbinder unbinder;

    private EvaluationAdapter mAdapter;
    private ArrayList<EvaluationItem> data;
    private int page = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_evaluation, container, false);
//        unbinder = ButterKnife.bind(this, view);


//        View emptyView = View.inflate(getActivity(), R.layout.item_empty, null);
        ButterKnife.bind(this, view);

//        listView.setEmptyView(emptyView);
        initEvaluation();
        mAdapter = new EvaluationAdapter(getContext(), evaluationItemList);
        System.out.println(mAdapter.getCount());
//        ListView listView = (ListView) view.findViewById(R.id.listView);
        NineGridView.setImageLoader(new GlideImageLoader());
        listView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private class GlideImageLoader implements NineGridView.ImageLoader{

        @Override
        public void onDisplayImage(Context context, ImageView imageView, String url) {
            Glide.with(context).load(url)
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.loading)
                    .into(imageView);
        }

        @Override
        public Bitmap getCacheImage(String url) {
            return null;
        }
    }

    private void initEvaluation(){
        EvaluationItem evaluationItem = new EvaluationItem();

        Avater avater = new Avater();
        avater.setMongoId("1");
        avater.setPicUrl("http://superlpx.top/wordpress/wp-content/uploads/2019/08/head.jpg");
        avater.setSmallPicUrl("http://superlpx.top/wordpress/wp-content/uploads/2019/08/head.jpg");
        evaluationItem.setAvatar(avater);
        evaluationItem.setAnonymous(0);

        List<EvaluationPic> PicList = new ArrayList<>();
        EvaluationPic evaluationPic = new EvaluationPic();
        evaluationPic.setImageId("1");
        evaluationPic.setImageUrl("http://superlpx.top/wordpress/wp-content/uploads/2019/08/head.jpg");
        evaluationPic.setSmallImageUrl("http://superlpx.top/wordpress/wp-content/uploads/2019/08/head.jpg");
        for (int i = 0; i < 9; i++) {
            evaluationPic.setAttachmentId(i);
            PicList.add(evaluationPic);
        }
        evaluationItem.setAttachments(PicList);

        evaluationItem.setContent("一念起，天涯咫尺。一念灭，咫尺天涯。\n\n\n\n\n\t\t----缥缈孤鸿影");

        evaluationItem.setCreatTime("2020-3-9 20:55");

        evaluationItem.setEvaluationId(0);

        evaluationItem.setGrade(5);

        evaluationItem.setOrderId("1");

        evaluationItem.setSid(1);

        evaluationItem.setUserName("陈豪");

        List<EvaluationReply> evaluationReplies = new ArrayList<>();
        EvaluationReply evaluationReply = new EvaluationReply();
        evaluationReply.setErid(0);
        evaluationReply.setErReplyuser("黄雷");
        evaluationReply.setErContent("就这？陈豪给爷爬！！！");
        evaluationReply.setErReplytime("2020-3-9 21:00");
        evaluationReplies.add(evaluationReply);
        evaluationItem.setEvaluatereplys(evaluationReplies);
        evaluationItemList.add(evaluationItem);

    }

}

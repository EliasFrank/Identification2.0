package com.example.identification20.evaluation;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.identification20.R;
import com.example.identification20.evaluation.bean.EvaluationReply;

import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentsAdapter extends BaseAdapter {
    private Context context;
    private List<EvaluationReply> evaluationReplies;

    public CommentsAdapter(Context context, @NonNull List<EvaluationReply> evaluationReplies){
        this.context = context;
        this.evaluationReplies = evaluationReplies;
    }

    @Override
    public int getCount() {
        return evaluationReplies.size();
    }

    @Override
    public EvaluationReply getItem(int position) {
        return evaluationReplies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_evaluatereply, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        EvaluationReply replyItem = getItem(position);
        SpannableString msp = new SpannableString(replyItem.erReplyuser + ": " + replyItem.erContent);
        msp.setSpan(new ForegroundColorSpan(0xff6b8747), 0, replyItem.erReplyuser.length() + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.reply.setText(msp);
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_reply)
        TextView reply;

        public ViewHolder(View convertView){
            ButterKnife.bind(this, convertView);
        }
    }
}

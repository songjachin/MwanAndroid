package com.songjachin.mwanandroid.ui.adapters;

import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.songjachin.mwanandroid.R;
import com.songjachin.mwanandroid.customview.RoundRectImageView;
import com.songjachin.mwanandroid.model.domain.KnowArticleBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by matthew
 */
public class KnowArticleAdapter extends RecyclerView.Adapter<KnowArticleAdapter.InnerHolder> {
    private RecyclerView mRecyclerView;

    private OnItemClickListener mItemClickListener;
    private List<KnowArticleBean.DataBean.DatasBean> mData = new ArrayList<>();
    private int mBottom = -1;


    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        setRecyclerView(recyclerView);
    }
    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_know_article_list,parent,false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {

        KnowArticleBean.DataBean.DatasBean datasBean = mData.get(position);
        holder.setData(datasBean,position);
        if(mBottom == position){
            this.getRecyclerView().post(new Runnable() {
                @Override
                public void run() {
                    if (mItemClickListener != null) {
                        mItemClickListener.toLoad();
                    }
                }
            });
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(datasBean);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<KnowArticleBean.DataBean.DatasBean> datas) {
        mBottom = -1;
        mData.clear();
        mData.addAll(datas);
        mBottom += datas.size();
        notifyDataSetChanged();
    }

    public void addData(List<KnowArticleBean.DataBean.DatasBean> datas) {
        int oldSize = mData.size();
        mData.addAll(datas);
        mBottom += datas.size();
        notifyItemRangeChanged(oldSize,mData.size());
    }

    public void setCollectStatus(int position, boolean b) {
        mData.get(position).setCollect(b);
        notifyItemChanged(position);
    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_author)
        TextView textAuthor;

        @BindView(R.id.tv_time)
        TextView textTime;

        @BindView(R.id.tv_title)
        TextView textTitle;


        @BindView(R.id.tv_chapter_name)
        TextView textChapterName;

        @BindView(R.id.cv_collect)
        ImageView collectView;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setData(KnowArticleBean.DataBean.DatasBean data, int position) {
            if(!TextUtils.isEmpty(data.getAuthor())){
                textAuthor.setText(data.getAuthor());
            }else{
                String str = "["+data.getShareUser()+"]分享";
                textAuthor.setText(str);
            }
            textTime.setText(data.getNiceDate());
            textTitle.setText(Html.fromHtml(data.getTitle()));
            textChapterName.setText(Html.fromHtml(formatChapterName(data.getSuperChapterName(), data.getChapterName())));

            if (data.isCollect()) {
                collectView.setImageResource(R.mipmap.ic_collect);
            } else {
                collectView.setImageResource(R.mipmap.ic_uncollect);
            }

            collectView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onCollectClick(data, position);
                }
            });
        }

        private String formatChapterName(String... names) {
            StringBuilder format = new StringBuilder();
            for (String name : names) {
                if (!TextUtils.isEmpty(name)) {
                    if (format.length() > 0) {
                        format.append("·");
                    }
                    format.append(name);
                }
            }
            return format.toString();
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mItemClickListener = listener;
    }
    public interface OnItemClickListener{
        void onItemClick( KnowArticleBean.DataBean.DatasBean data);

        void toLoad();

        void onCollectClick(KnowArticleBean.DataBean.DatasBean data, int position);
    }
}

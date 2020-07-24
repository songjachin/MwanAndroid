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

import com.bumptech.glide.Glide;
import com.songjachin.mwanandroid.R;
import com.songjachin.mwanandroid.model.domain.IBaseArticleInfo;
import com.songjachin.mwanandroid.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by matthew
 */
public class ProjectRightAdapter extends RecyclerView.Adapter<ProjectRightAdapter.InnerHolder> {
    private RecyclerView mRecyclerView;

    private List<IBaseArticleInfo> mData = new ArrayList<>();
    private OnItemClickListener mItemClickListener;
    private int mPosition = -1;

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
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project_content ,parent,false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        IBaseArticleInfo data = mData.get(position);
        holder.setData(data,position);
        if(mPosition == position){
            this.getRecyclerView().post(new Runnable() {
                @Override
                public void run() {
                    if (mItemClickListener != null) {
                        mItemClickListener.toLoad();
                    }
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<? extends IBaseArticleInfo> data) {
        mPosition = -1;
        mData.clear();

        mData.addAll(data);
        mPosition += data.size();
        notifyDataSetChanged();
    }

    public void addData(List<? extends IBaseArticleInfo> data) {
        int oldSize = mData.size();
        mData.addAll(data);
        mPosition += data.size();
        notifyItemRangeChanged(oldSize,mData.size());
    }

    public void setCollectStatus(int position, boolean b) {
        mData.get(position).setCollect(b);
        notifyItemChanged(position);
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        //@bindview
        @BindView(R.id.project_image)
        ImageView ivImage;
        @BindView(R.id.tv_author)
        TextView textAuthor;
        @BindView(R.id.tv_title)
        TextView textTitle;
        @BindView(R.id.tv_desc)
        TextView textDescription;
        @BindView(R.id.tv_time)
        TextView textTime;
        @BindView(R.id.collect_image)
        ImageView collectView;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setData(IBaseArticleInfo data,int position){
            if(!TextUtils.isEmpty(data.getAuthor())){
                textAuthor.setText(data.getAuthor());
            }else{
                String str = "["+data.getShareUser()+"]分享";
                textAuthor.setText(str);
            }
            textTime.setText(data.getNiceDate());
            if (!TextUtils.isEmpty(data.getEnvelopePic())) {
                Glide.with(itemView.getContext()).load(data.getEnvelopePic()).into(ivImage);
                ivImage.setVisibility(View.VISIBLE);
                ivImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mItemClickListener.onItemTitleClick(data);
                    }
                });
            } else {
                ivImage.setVisibility(View.GONE);
            }
            textTitle.setText(Html.fromHtml(data.getTitle()));
            if (TextUtils.isEmpty(data.getDesc())) {
                textDescription.setVisibility(View.GONE);
                textTitle.setSingleLine(false);
                textTitle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mItemClickListener.onItemTitleClick(data);
                    }
                });
            } else {
                textDescription.setVisibility(View.VISIBLE);
                textTitle.setSingleLine(true);
                String desc = Html.fromHtml(data.getDesc()).toString();
                desc = StringUtils.removeAllBank(desc, 2);
                textDescription.setText(desc);
                textDescription.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mItemClickListener.onItemTitleClick(data);
                    }
                });
            }

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

    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mItemClickListener = listener;
    }

    public interface OnItemClickListener{
        void onItemTitleClick(IBaseArticleInfo data);

        void toLoad();
        void onCollectClick(IBaseArticleInfo data, int position);
    }
}

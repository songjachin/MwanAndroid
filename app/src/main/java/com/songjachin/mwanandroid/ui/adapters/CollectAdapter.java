package com.songjachin.mwanandroid.ui.adapters;

import android.content.Context;
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
import com.songjachin.mwanandroid.customview.RoundRectImageView;
import com.songjachin.mwanandroid.model.domain.IBaseArticleInfo;
import com.songjachin.mwanandroid.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by matthew
 */
public class CollectAdapter extends RecyclerView.Adapter<CollectAdapter.InnerHolder> {

    private List<IBaseArticleInfo> mData = new ArrayList<>();

    private OnItemClickListener mItemClickListener;

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mData.size()==0) return null;
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_home_list, parent, false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        if(mData.size()==0) return;
        IBaseArticleInfo data = mData.get(position);
        holder.setData(data, position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void remove(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }

    public void setData(List<? extends IBaseArticleInfo> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_new)
        TextView textNew;
        @BindView(R.id.tv_author)
        TextView textAuthor;
        @BindView(R.id.tv_tag)
        TextView textTag;
        @BindView(R.id.tv_time)
        TextView textTime;
        @BindView(R.id.tv_title)
        TextView textTitle;
        @BindView(R.id.tv_desc)
        TextView textDescription;
        @BindView(R.id.iv_img)
        RoundRectImageView ivImage;
        @BindView(R.id.tv_top)
        TextView textTop;
        @BindView(R.id.tv_chapter_name)
        TextView textChapterName;
        @BindView(R.id.cv_collect)
        ImageView collectView;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        public void setData(IBaseArticleInfo data, int position) {
            Context context = itemView.getContext();
            if (data.isFresh()) {
                textNew.setVisibility(View.VISIBLE);
            } else {
                textNew.setVisibility(View.GONE);
            }
            if (data.isTop()) {
                textTop.setVisibility(View.VISIBLE);
            } else {
                textTop.setVisibility(View.GONE);
            }
            if (!TextUtils.isEmpty(data.getAuthor())) {
                textAuthor.setText(data.getAuthor());
            } else {
                String str = "[" + data.getShareUser() + "]分享";
                textAuthor.setText(str);
            }
            if (data.getTags() != null && data.getTags().size() > 0) {
                textTag.setText(data.getTags().get(0).getName());
                textTag.setVisibility(View.VISIBLE);
//                textTag.setOnClickListener(new OnClickListener2() {
//                    @Override
//                    public void onClick2(View v) {
//                        KnowledgeArticleActivity.start(v.getContext(), item.getTags().get(0));
//                    }
//                });
            } else {
                textTag.setVisibility(View.GONE);
            }
            textTime.setText(data.getNiceDate());
            if (!TextUtils.isEmpty(data.getEnvelopePic())) {
                Glide.with(context).load(data.getEnvelopePic()).into(ivImage);
                ivImage.setVisibility(View.VISIBLE);
            } else {
                ivImage.setVisibility(View.GONE);
            }

            textTitle.setText(Html.fromHtml(data.getTitle()));
            textTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemTitleClick(data);
                }
            });
            if (TextUtils.isEmpty(data.getDesc())) {
                textDescription.setVisibility(View.GONE);
                textTitle.setSingleLine(false);
            } else {
                textDescription.setVisibility(View.VISIBLE);
                textTitle.setSingleLine(true);
                String desc = Html.fromHtml(data.getDesc()).toString();
                desc = StringUtils.removeAllBank(desc, 2);
                textDescription.setText(desc);
            }
            textDescription.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemTitleClick(data);
                }
            });
            textChapterName.setText(Html.fromHtml(formatChapterName(data.getSuperChapterName(), data.getChapterName())));


            if (data.isCollect()) {
                collectView.setImageResource(R.mipmap.ic_collect);
            } else {
                collectView.setImageResource(R.mipmap.ic_uncollect);
            }

            collectView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int size = mData.size();
                    mItemClickListener.onCollectClick(data, position,size);
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


        public void setData(List<? extends IBaseArticleInfo> data) {
            mData.clear();
            mData.addAll(data);
            notifyDataSetChanged();
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemTitleClick(IBaseArticleInfo data);

        void onCollectClick(IBaseArticleInfo data, int position,int size);
    }
}

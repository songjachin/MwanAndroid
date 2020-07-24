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
import com.songjachin.mwanandroid.model.domain.Article;
import com.songjachin.mwanandroid.model.domain.Articles;
import com.songjachin.mwanandroid.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by matthew
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.InnerHolder> {

    private OnItemClickListener mItemClickListener;
    private List<Article> mData = new ArrayList<>();

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_search,parent,false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        Article article = mData.get(position);
        holder.setData(article,position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addData(Articles data) {
        int oldSize = mData.size();
        mData.addAll(data.getDatas());
        notifyItemRangeChanged(oldSize,data.getDatas().size());
    }

    public void setData(Articles data) {
        List<Article> datas = data.getDatas();
        mData.clear();
        mData.addAll(datas);
        notifyDataSetChanged();
    }

    public void setCollectStatus(int position, boolean b) {
        mData.get(position).setCollect(b);
        notifyItemChanged(position);
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

        @BindView(R.id.tv_chapter_name)
        TextView textChapterName;

        @BindView(R.id.cv_collect)
        ImageView collectView;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setData(Article data,int position) {
            Context context = itemView.getContext();
            if (data.isFresh()) {
                textNew.setVisibility(View.VISIBLE);
            } else {
                textNew.setVisibility(View.GONE);
            }

            if (!TextUtils.isEmpty(data.getAuthor())) {
                textAuthor.setText(data.getAuthor());
            } else {
                textAuthor.setVisibility(View.GONE);
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
                    mItemClickListener.onItemClick(data);
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
                    mItemClickListener.onItemClick(data);
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
                    mItemClickListener.onCollectClick(data,position);
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
        void onItemClick(Article article);

        void onCollectClick(Article article,int position);
    }
}

package com.songjachin.mwanandroid.ui.adapters;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.songjachin.mwanandroid.R;
import com.songjachin.mwanandroid.database.HistoryArticle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by matthew
 */
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.InnerHolder> {

    private OnItemClickListener mListener;
    private List<HistoryArticle> mData = new ArrayList<>();

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_history,parent,false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        HistoryArticle article = mData.get(position);
        if(!TextUtils.isEmpty(article.getAuthor())){
            holder.author.setText(article.getAuthor());
        }
        if(!TextUtils.isEmpty(article.getTime())){
            holder.time.setText(article.getTime());
        }
        if(!TextUtils.isEmpty(article.getTitle())){
            holder.title.setText(article.getTitle());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(article);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<HistoryArticle> articles) {
        mData.clear();
        mData.addAll(articles);
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_author)
        TextView author;
        @BindView(R.id.tv_time)
        TextView time;
        @BindView(R.id.tv_title)
        TextView title;
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }
    public interface OnItemClickListener{
        void onItemClick(HistoryArticle article);
    }
}

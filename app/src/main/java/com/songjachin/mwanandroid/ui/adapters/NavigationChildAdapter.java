package com.songjachin.mwanandroid.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexboxLayout;
import com.songjachin.mwanandroid.R;
import com.songjachin.mwanandroid.model.domain.NaviBean;
import com.songjachin.mwanandroid.ui.navigation.NavigationChildFragment;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by matthew
 */
public class NavigationChildAdapter extends RecyclerView.Adapter<NavigationChildAdapter.InnerHolder> {

    private OnItemClickListener mOnItemClickListener;
    private List<NaviBean.DataBean> mData = new ArrayList<>();

    private LayoutInflater mInflater = null;
    private Queue<TextView> mFlexItemTextViewCaches = new LinkedList<>();
    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_knowledge,parent,false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        NaviBean.DataBean dataBean = mData.get(position);
        holder.setData(dataBean);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<NaviBean.DataBean> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }



    public class InnerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.knowledge_category)
        TextView textKnowledge;
        @BindView(R.id.flex_box_layout)
        FlexboxLayout mFlex;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setData(NaviBean.DataBean item) {
            textKnowledge.setText(item.getName());
            mFlex.removeAllViews();
            List<NaviBean.DataBean.ArticlesBean> articles = item.getArticles();
            for (NaviBean.DataBean.ArticlesBean article : articles) {
                TextView childTextView = createOrGetCacheFlexItemTextView(mFlex);
                childTextView.setText(article.getTitle());
                childTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onItemClick(article);
                    }
                });
                mFlex.addView(childTextView);
            }
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mOnItemClickListener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(NaviBean.DataBean.ArticlesBean article);
    }

    private TextView createOrGetCacheFlexItemTextView(FlexboxLayout fbl) {
        TextView tv = mFlexItemTextViewCaches.poll();
        if (tv != null) {
            return tv;
        }
        return createFlexItemTextView(fbl);
    }

    private TextView createFlexItemTextView(FlexboxLayout fbl) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(fbl.getContext());
        }
        return (TextView) mInflater.inflate(R.layout.rv_item_knowledge_child, fbl, false);
    }
}

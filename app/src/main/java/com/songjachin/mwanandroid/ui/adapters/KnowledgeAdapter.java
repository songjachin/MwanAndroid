package com.songjachin.mwanandroid.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexboxLayout;
import com.songjachin.mwanandroid.R;
import com.songjachin.mwanandroid.customview.TextFlowLayout;
import com.songjachin.mwanandroid.model.domain.KnowledgeBean;
import com.songjachin.mwanandroid.utils.LogUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by matthew
 */
public class KnowledgeAdapter extends RecyclerView.Adapter<KnowledgeAdapter.InnerHolder> {
    private static final String TAG = "KnowledgeAdapter";

    private LayoutInflater mInflater = null;
    private Queue<TextView> mFlexItemTextViewCaches = new LinkedList<>();

    private OnItemClickListener mOnItemClickListener;
    private List<KnowledgeBean.DataBean> mList = new ArrayList<>();

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_knowledge,parent,false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        KnowledgeBean.DataBean item = mList.get(position);
        holder.setData(item);
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setData(List<KnowledgeBean.DataBean> data) {
        mList.clear();
        mList.addAll(data);
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

        public void setData(KnowledgeBean.DataBean item) {
            textKnowledge.setText(item.getName());
            //textList.clear();
            mFlex.removeAllViews();
            List<KnowledgeBean.DataBean.ChildrenBean> children = item.getChildren();
            for(int i = 0; i < children.size();i++){
                KnowledgeBean.DataBean.ChildrenBean child = children.get(i);
                TextView childTextView = createOrGetCacheFlexItemTextView(mFlex);
                int id = child.getId();
                childTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onItemClick(id);
                    }
                });
                childTextView.setText(child.getName());
                mFlex.addView(childTextView);
            }
            //LogUtils.d(TAG,"flow text size"+textList.size()+textList.toString());

        }
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mOnItemClickListener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick( int target);
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

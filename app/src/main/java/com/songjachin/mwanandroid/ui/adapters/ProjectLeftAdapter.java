package com.songjachin.mwanandroid.ui.adapters;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.songjachin.mwanandroid.R;
import com.songjachin.mwanandroid.model.domain.ProjectCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matthew
 */
public class ProjectLeftAdapter extends RecyclerView.Adapter<ProjectLeftAdapter.InnerHolder> {

    private int mCurrentSelectedPosition = 0;
    private OnLeftItemClickListener mItemClickListener = null;
    private List<ProjectCategory.DataBean> mData = new ArrayList<>();

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project_category, parent, false);
        return new InnerHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        TextView itemTv = holder.itemView.findViewById(R.id.left_category_tv);
        if (mCurrentSelectedPosition == position) {
            itemTv.setBackgroundColor(itemTv.getResources().getColor(R.color.colorEEEEEE, null));
        } else {
            itemTv.setBackgroundColor(itemTv.getResources().getColor(R.color.white, null));
        }
        itemTv.setText(mData.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mItemClickListener != null&&mCurrentSelectedPosition!=position){
                    mCurrentSelectedPosition = position;
                    mItemClickListener.onLeftItemClick(mData.get(position));
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(ProjectCategory category) {
        mData.clear();
        mData.addAll(category.getData());
        notifyDataSetChanged();
        if (mData.size() > 0) {
            mItemClickListener.onLeftItemClick(mData.get(mCurrentSelectedPosition));
        }
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    public void setOnLeftItemClickListener(OnLeftItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    public interface OnLeftItemClickListener {
        void onLeftItemClick(ProjectCategory.DataBean item);
    }
}

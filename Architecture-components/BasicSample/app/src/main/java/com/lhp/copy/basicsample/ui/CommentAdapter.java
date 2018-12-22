package com.lhp.copy.basicsample.ui;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.lhp.copy.basicsample.R;
import com.lhp.copy.basicsample.databinding.CommentItemBinding;
import com.lhp.copy.basicsample.model.Comment;

import java.util.List;
import java.util.Objects;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    private List<? extends Comment> mCommentList;
    @Nullable
    private final CommentClickCallback mCommentClickCallback;
    public CommentAdapter(@Nullable CommentClickCallback commentClickCallback){
        mCommentClickCallback = commentClickCallback;
    }

    public void setCommentList(final List<? extends Comment> comments){
        if(null == mCommentList){
            mCommentList = comments;
            notifyItemRangeInserted(0,comments.size());
        }else {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mCommentList.size();
                }

                @Override
                public int getNewListSize() {
                    return comments.size();
                }

                @Override
                public boolean areItemsTheSame(int i, int i1) {
                    Comment old = mCommentList.get(i);
                    Comment comment = comments.get(i1);
                    return old.getId() == comment.getId();
                }

                @Override
                public boolean areContentsTheSame(int i, int i1) {
                    Comment old = mCommentList.get(i);
                    Comment comment = comments.get(i1);
                    return old.getId() == comment.getId()
                            && old.getPostedAt() == comment.getPostedAt()
                            && old.getProductId() == comment.getProductId()
                            && Objects.equals(old.getText(),comment.getText());
                }
            });
            mCommentList =comments;
            diffResult.dispatchUpdatesTo(this);
        }
    }
    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CommentItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),R.layout.comment_item,viewGroup,false);
        binding.setCallback(mCommentClickCallback);
        return new CommentViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder commentViewHolder, int i) {
        commentViewHolder.binding.setComment(mCommentList.get(i));
        commentViewHolder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mCommentList == null ? 0:mCommentList.size();
    }

    static class CommentViewHolder extends RecyclerView.ViewHolder{
        CommentItemBinding binding;
        CommentViewHolder(CommentItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

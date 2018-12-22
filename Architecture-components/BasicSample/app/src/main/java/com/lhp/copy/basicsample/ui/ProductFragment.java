package com.lhp.copy.basicsample.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lhp.copy.basicsample.R;
import com.lhp.copy.basicsample.databinding.ProductFragmentBinding;
import com.lhp.copy.basicsample.db.entity.CommentEntity;
import com.lhp.copy.basicsample.db.entity.ProductEntity;
import com.lhp.copy.basicsample.model.Comment;
import com.lhp.copy.basicsample.viewmodel.ProductViewModel;

import java.util.List;

/**
 * @author lianghp
 * @Date 2018/10/31
 **/
public class ProductFragment extends Fragment {
    private static final String KEY_PRODUCT_ID = "product_id";
    private ProductFragmentBinding mBinding;
    private CommentAdapter mCommentAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,R.layout.product_fragment,container,false);
        mCommentAdapter = new CommentAdapter(mCommentClickCallback);
        mBinding.commentLsit.setAdapter(mCommentAdapter);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ProductViewModel.Factory factory = new ProductViewModel.Factory(getActivity().getApplication(),getArguments().getInt(KEY_PRODUCT_ID));
        final ProductViewModel model = ViewModelProviders.of(this,factory).get(ProductViewModel.class);
        mBinding.setProductViewModel(model);
        subscribeToModel(model);
    }
    private void subscribeToModel(final ProductViewModel model){
        model.getObservableProduct().observe(this, new Observer<ProductEntity>() {
            @Override
            public void onChanged(@Nullable ProductEntity productEntity) {
                model.setProduct(productEntity);
            }
        });
        model.getComments().observe(this, new Observer<List<CommentEntity>>() {
            @Override
            public void onChanged(@Nullable List<CommentEntity> commentEntities) {
                if(null != commentEntities){
                    mBinding.setIsLoading(false);
                    mCommentAdapter.setCommentList(commentEntities);
                }else {
                    mBinding.setIsLoading(true);
                }
            }
        });
    }

    public static ProductFragment forProduct(int productId){
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_PRODUCT_ID,productId);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    private CommentClickCallback mCommentClickCallback = new CommentClickCallback() {
        @Override
        public void onClick(Comment comment) {

        }
    };
}

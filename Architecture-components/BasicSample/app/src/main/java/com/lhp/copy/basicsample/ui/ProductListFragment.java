package com.lhp.copy.basicsample.ui;

import android.arch.lifecycle.Lifecycle;
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
import com.lhp.copy.basicsample.databinding.ListFragmentBinding;
import com.lhp.copy.basicsample.db.entity.ProductEntity;
import com.lhp.copy.basicsample.model.Product;
import com.lhp.copy.basicsample.viewmodel.ProductListViewModel;

import java.util.List;
import java.util.Objects;

/**
 * @author lianghp
 * @date 2018/12/22
 */
public class ProductListFragment extends Fragment {
    public static final String TAG = "ProductListViewModel";
    private ListFragmentBinding mBinding;
    private ProductAdapter mProductAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,R.layout.list_fragment,container,false);
        mProductAdapter = new ProductAdapter(mProductClickCallBack);
        mBinding.productsList.setAdapter(mProductAdapter);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final ProductListViewModel viewModel = ViewModelProviders.of(this).get(ProductListViewModel.class);
        subscribeUi(viewModel);
    }
    private void subscribeUi(ProductListViewModel viewModel){
        viewModel.getProducts().observe(this, new Observer<List<ProductEntity>>() {
            @Override
            public void onChanged(@Nullable List<ProductEntity> productEntities) {
                if(productEntities != null){
                    mBinding.setIsLoading(false);
                    mProductAdapter.setProductList(productEntities);
                }else {
                    mBinding.setIsLoading(true);
                }
                mBinding.executePendingBindings();
            }
        });
    }
    private final ProductClickCallback mProductClickCallBack = new ProductClickCallback() {
        @Override
        public void onClick(Product product) {
            if(getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)){
                ((MainActivity)Objects.requireNonNull(getActivity())).show(product);
            }
        }
    };
}

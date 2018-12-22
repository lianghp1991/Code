package com.lhp.copy.basicsample.ui;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.lhp.copy.basicsample.R;
import com.lhp.copy.basicsample.model.Product;
import com.lhp.copy.basicsample.databinding.ProductItemBinding;

import java.util.List;
import java.util.Objects;


/**
 * @author lianghp
 * @Date 2018/10/30
 **/
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    List<? extends Product> mProductList;
    @Nullable
    private final ProductClickCallback mProductClickCallback;
    public ProductAdapter(@Nullable ProductClickCallback productClickCallback){
        mProductClickCallback = productClickCallback;
    }
    public void setProductList(List<? extends Product> productList){
        if(mProductList ==null){
            mProductList = productList;
            notifyItemRangeInserted(0,productList.size());
        }else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mProductList.size();
                }

                @Override
                public int getNewListSize() {
                    return productList.size();
                }

                @Override
                public boolean areItemsTheSame(int i, int i1) {
                    return mProductList.get(i) == productList.get(i1);
                }

                @Override
                public boolean areContentsTheSame(int i, int i1) {
                    Product newProduct = productList.get(i1);
                    Product oldProduct = mProductList.get(i);
                    return newProduct.getId() == oldProduct.getId()
                            && Objects.equals(newProduct.getDescription(),oldProduct.getDescription())
                            && Objects.equals(newProduct.getName(),oldProduct.getName())
                            && newProduct.getPrice() == oldProduct.getPrice();
                }
            });
            mProductList = productList;
            result.dispatchUpdatesTo(this);
        }
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ProductItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.product_item,viewGroup,false);
        binding.setCallback(mProductClickCallback);
        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i) {
        productViewHolder.binding.setProduct(mProductList.get(i));
        productViewHolder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mProductList == null ? 0 : mProductList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        ProductItemBinding binding;
        public ProductViewHolder(ProductItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

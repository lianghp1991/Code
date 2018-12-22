package com.lhp.copy.basicsample.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.lhp.copy.basicsample.BasicApp;
import com.lhp.copy.basicsample.DataRepository;
import com.lhp.copy.basicsample.db.entity.CommentEntity;
import com.lhp.copy.basicsample.db.entity.ProductEntity;

import java.util.List;

/**
 * @author lianghp
 * @Date 2018/10/29
 **/
public class ProductViewModel extends AndroidViewModel {
    private final LiveData<ProductEntity> mObservableProduct;

    public ObservableField<ProductEntity> product = new ObservableField<>();
    private final int mProductId;

    private final LiveData<List<CommentEntity>> mObservableComments;
    public ProductViewModel(@NonNull Application application, DataRepository dataRepository,
                            final int productId) {
        super(application);
        mProductId = productId;
        mObservableProduct = dataRepository.loadProduct(mProductId);
        mObservableComments = dataRepository.loadComments(mProductId);
    }
    public LiveData<List<CommentEntity>> getComments(){
        return mObservableComments;
    }
    public LiveData<ProductEntity> getObservableProduct(){
        return mObservableProduct;
    }
    public LiveData<ProductEntity> getProduct(){
        return mObservableProduct;
    }

    public void setProduct(ProductEntity product) {
        this.product.set(product);
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory{

        @NonNull
        private final Application mApplication;

        private final int mProductId;

        private final DataRepository mDataRepository;

        public Factory(@Nullable Application application, int productId){
            mApplication = application;
            mProductId = productId;
            mDataRepository = ((BasicApp)application).getRepository();
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new ProductViewModel(mApplication,mDataRepository,mProductId);
        }
    }
}

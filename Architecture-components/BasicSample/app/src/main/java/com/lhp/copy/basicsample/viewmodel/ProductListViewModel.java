package com.lhp.copy.basicsample.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;

import com.lhp.copy.basicsample.BasicApp;
import com.lhp.copy.basicsample.db.entity.ProductEntity;

import java.util.List;

/**
 * @author lianghp
 * @Date 2018/10/30
 **/
public class ProductListViewModel extends AndroidViewModel {
    private final MediatorLiveData<List<ProductEntity>> mObservablePeoducts;
    public ProductListViewModel(@NonNull Application application) {
        super(application);
        mObservablePeoducts = new MediatorLiveData<>();
        mObservablePeoducts.setValue(null);

        LiveData<List<ProductEntity>> products = ((BasicApp)application).getRepository().getProducts();
        mObservablePeoducts.addSource(products,mObservablePeoducts::setValue);
    }
    public LiveData<List<ProductEntity>> getProducts(){
        return mObservablePeoducts;
    }
}

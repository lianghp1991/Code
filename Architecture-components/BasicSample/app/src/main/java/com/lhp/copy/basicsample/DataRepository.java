package com.lhp.copy.basicsample;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.persistence.room.TypeConverter;

import com.lhp.copy.basicsample.db.AppDatabase;
import com.lhp.copy.basicsample.db.entity.CommentEntity;
import com.lhp.copy.basicsample.db.entity.ProductEntity;

import java.util.Date;
import java.util.List;

/**
 * @author lianghp
 * @Date 2018/10/23
 **/
public class DataRepository {

    private static DataRepository sInstance;

    private final AppDatabase mDatabase;
    private MediatorLiveData<List<ProductEntity>> mObservableProducts;

    private DataRepository(final AppDatabase appDatabase){
        mDatabase = appDatabase;
        mObservableProducts = new MediatorLiveData<>();
        mObservableProducts.addSource(mDatabase.productDao().loadAllProducts(),productEntities -> {
            if (mDatabase.getDatabaseCreated().getValue() != null){
                mObservableProducts.postValue(productEntities);
            }
        });
    }
    public static DataRepository getsInstance(final AppDatabase database){
        if (sInstance == null){
            synchronized (DataRepository.class){
                if (sInstance == null){
                    sInstance = new DataRepository(database);
                }
            }
        }
        return sInstance;
    }
    public LiveData<List<ProductEntity>> getProducts(){
        return mObservableProducts;
    }
    public LiveData<ProductEntity> loadProduct(final int productId){
        return mDatabase.productDao().loadProduct(productId);
    }
    public LiveData<List<CommentEntity>> loadComments(final int productId){
        return mDatabase.commentDao().loadComments(productId);
    }
}

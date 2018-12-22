package com.lhp.copy.basicsample.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.lhp.copy.basicsample.AppExecutors;
import com.lhp.copy.basicsample.db.converter.DataConverter;
import com.lhp.copy.basicsample.db.dao.CommentDao;
import com.lhp.copy.basicsample.db.dao.ProductDao;
import com.lhp.copy.basicsample.db.entity.CommentEntity;
import com.lhp.copy.basicsample.db.entity.ProductEntity;
import com.lhp.copy.basicsample.model.Comment;

import java.util.List;

/**
 * @author lianghp
 * @Date 2018/10/23
 **/
@Database(entities = {ProductEntity.class,CommentEntity.class},version = 1)
@TypeConverters(DataConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase sInstance;
    @VisibleForTesting
    public static final String DATABASE_NAME = "basic-sample-db";

    public abstract ProductDao productDao();
    public abstract CommentDao commentDao();

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    public static AppDatabase getsInstance(final Context context, final AppExecutors executors){
        if (sInstance == null){
            synchronized (AppDatabase.class){
                if (sInstance == null){
                    sInstance = buildDatabase(context.getApplicationContext(),executors);
                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    private static AppDatabase buildDatabase(final Context appContext, final AppExecutors executors){
        return Room.databaseBuilder(appContext,AppDatabase.class,DATABASE_NAME).addCallback(new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                executors.diskIO().execute(()->{
                    addDelay();
                    AppDatabase database = AppDatabase.getsInstance(appContext,executors);
                    List<ProductEntity> products = DataGenerator.generateProducts();
                    List<CommentEntity> comments = DataGenerator.generateCommentsForProducts(products);
                    insertData(database,products,comments);
                    database.setDatabaseCreated();
                });
            }

            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
            }
        }).build();
    }
    private void updateDatabaseCreated(final Context context){
        if (context.getDatabasePath(DATABASE_NAME).exists()){
            setDatabaseCreated();
        }
    }
    private void setDatabaseCreated(){
        mIsDatabaseCreated.postValue(true);
    }

    private static void insertData(final AppDatabase database, final List<ProductEntity> products, final List<CommentEntity> comments){
        database.runInTransaction(()->{
            database.productDao().insertAll(products);
            database.commentDao().insertAll(comments);
        });
    }

    private static void addDelay(){
        try{
            Thread.sleep(4000);
        }catch (InterruptedException ignore){

        }
    }

    public LiveData<Boolean> getDatabaseCreated(){
        return mIsDatabaseCreated;
    }
}

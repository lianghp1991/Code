package com.lhp.copy.basicsample.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.lhp.copy.basicsample.db.entity.CommentEntity;

import java.util.List;

/**
 * @author lianghp
 * @Date 2018/10/23
 **/
@Dao
public interface CommentDao {
    @Query("SELECT * FROM comments where productId = :productId")
    LiveData<List<CommentEntity>> loadComments(int productId);
    @Query("select * from comments where productId = :productId")
    List<CommentEntity> loadCommentsSync(int productId);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<CommentEntity> comments);
}

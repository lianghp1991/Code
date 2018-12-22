package com.lhp.copy.basicsample.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.lhp.copy.basicsample.model.Comment;

import java.util.Date;

/**
 * @author lianghp
 * @Date 2018/10/23
 **/
@Entity(tableName = "comments", foreignKeys = {
        @ForeignKey(entity = ProductEntity.class,
                parentColumns = "id",
                childColumns = "productId",
                onDelete = ForeignKey.CASCADE)
},
        indices = {@Index(value = "productId")})
public class CommentEntity implements Comment {
    @PrimaryKey(autoGenerate = true)

    private int id;
    private int productId;
    @NonNull
    private String text;
    private Date postedAt;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    @NonNull
    public String getText() {
        return text;
    }

    public void setText(  @NonNull String text) {
        this.text = text;
    }

    @Override
    @NonNull
    public Date getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(Date postedAt) {
        this.postedAt = postedAt;
    }

    public CommentEntity() {
    }

    public CommentEntity(int id, int productId,  @NonNull String text, Date postedAt) {
        this.id = id;
        this.productId = productId;
        this.text = text;
        this.postedAt = postedAt;
    }
}

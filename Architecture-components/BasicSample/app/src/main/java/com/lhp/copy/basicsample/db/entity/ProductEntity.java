package com.lhp.copy.basicsample.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.lhp.copy.basicsample.model.Product;

/**
 * @author lianghp
 * @Date 2018/10/23
 **/
@Entity(tableName = "products")
public class ProductEntity implements Product {
    @PrimaryKey
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String description;
    private int price;

    @Override
    public int getId() {
        return id;
    }
    public void setId( int id){
        this.id = id;
    }

    @Override
    @NonNull
    public String getName() {
        return name;
    }

    public void setName( @NonNull String name) {
        this.name = name;
    }

    @Override
    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ProductEntity(){}
    public ProductEntity(int id,@NonNull String name,@NonNull String description, int price){
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }
    public ProductEntity(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
    }
}

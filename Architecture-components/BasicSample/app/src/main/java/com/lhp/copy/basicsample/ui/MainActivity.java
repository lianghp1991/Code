package com.lhp.copy.basicsample.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.lhp.copy.basicsample.R;
import com.lhp.copy.basicsample.model.Product;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null){
            ProductListFragment fragment = new ProductListFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,fragment,ProductListFragment.TAG).commit();
        }
    }
    public void show(Product product){
        ProductFragment productFragment = ProductFragment.forProduct(product.getId());
        getSupportFragmentManager().beginTransaction().addToBackStack("product").replace(R.id.fragment_container,productFragment,null).commit();
    }
}

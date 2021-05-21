package com.example.parcial3.entities.products;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.parcial3.R;
import java.util.List;

public class ProductAdapter extends BaseAdapter {
    public List<ProductView> products;
    public Context context;

    public  ProductAdapter(Context context, List<ProductView> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ProductView product = products.get(i);
        view =  LayoutInflater.from(this.context).inflate(R.layout.item_product_list, null);
        TextView name = view.findViewById(R.id.itemProductName);
        TextView provider = view.findViewById(R.id.itemProductProvider);
        TextView category = view.findViewById(R.id.itemProductCategory);

        name.setText(product.name);
        provider.setText(product.provider);
        category.setText(product.category);
        return view;
    }
}
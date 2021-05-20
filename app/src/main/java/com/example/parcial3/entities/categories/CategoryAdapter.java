package com.example.parcial3.entities.categories;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.parcial3.R;
import java.util.List;

public class CategoryAdapter extends BaseAdapter {
    public List<Category> categories;
    public Context context;
    public TextView name;

    public  CategoryAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int i) {
        return categories.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Category category = categories.get(i);
        view =  LayoutInflater.from(this.context).inflate(R.layout.item_category_list, null);
        TextView name = view.findViewById(R.id.listItemCategoryName);
        name.setText(category.name);
        return view;
    }
}
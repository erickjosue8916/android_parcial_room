package com.example.parcial3.entities.providers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.parcial3.R;

import org.w3c.dom.Text;

import java.util.List;

public class ProviderAdapter extends BaseAdapter {
    public List<Provider> providers;
    public Context context;

    public  ProviderAdapter(Context context, List<Provider> providers) {
        this.context = context;
        this.providers = providers;
    }

    @Override
    public int getCount() {
        return providers.size();
    }

    @Override
    public Object getItem(int i) {
        return providers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Provider provider = providers.get(i);
        view =  LayoutInflater.from(this.context).inflate(R.layout.item_provider_list, null);
        TextView name = view.findViewById(R.id.listProvidersName);
        name.setText(provider.name);
        return view;
    }
}

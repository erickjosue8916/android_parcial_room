package com.example.parcial3;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parcial3.database.StoreDatabase;
import com.example.parcial3.entities.categories.Category;
import com.example.parcial3.entities.categories.CategoryAdapter;
import com.example.parcial3.entities.products.Product;
import com.example.parcial3.entities.providers.Provider;
import com.example.parcial3.entities.providers.ProviderAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductDetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "productId";
    private static final String ARG_PARAM2 = "category";
    private static final String ARG_PARAM3 = "provider";

    // TODO: Rename and change types of parameters
    private int productId;
    private String category;
    private String provider;

    TextView productName;
    Button btnCreateProduct;
    Button btnDeleteProduct;

    List<Category> categories;
    CategoryAdapter categoryAdapter;
    ListView listCategories;
    Category categorySelected;
    TextView lblSelectedCategory;

    List<Provider> providers;
    ProviderAdapter providerAdapter;
    ListView listProviders;
    Provider providerSelected;
    TextView lblSelectedProvider;

    StoreDatabase store;
    View view;
    Context context;

    Product selectedProduct;

    public ProductDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductDetailsFragment newInstance(String param1, String param2) {
        ProductDetailsFragment fragment = new ProductDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            productId = getArguments().getInt(ARG_PARAM1);
            category = getArguments().getString(ARG_PARAM2);
            provider = getArguments().getString(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_product_details, container, false);
        context = getContext();
        store = StoreDatabase.getInstance(context);
        listProviders = view.findViewById(R.id.newProductProviderOptions);
        listCategories = view.findViewById(R.id.newProductCategoryOptions);
        lblSelectedCategory = view.findViewById(R.id.lblSelectedCategory);
        lblSelectedProvider = view.findViewById(R.id.lblSelectedProvider);
        productName = view.findViewById(R.id.inputProductName);
        btnCreateProduct = view.findViewById(R.id.btnCreateProduct);
        btnDeleteProduct = view.findViewById(R.id.btnProductDelete);

        btnCreateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });

        btnDeleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });

        this.loadProviders();
        this.loadCategories();
        this.loadProduct();
        return view;
    }

    private void loadProduct () {
        selectedProduct = store.db.products().getById(productId);
        productName.setText(selectedProduct.name);
        // Toast.makeText(context, String.valueOf(productId), Toast.LENGTH_SHORT).show();
        // show category selected
        lblSelectedCategory.setText(category);

        // show provider selected
        lblSelectedProvider.setText(provider);
    }

    private void loadCategories() {
        categories = store.db.categories().getAll();
        categoryAdapter = new CategoryAdapter(context, categories);
        listCategories.setAdapter(categoryAdapter);

        ProductDetailsFragment instance = this;

        listCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                categorySelected = categories.get(i);
                selectedProduct.categoryId = categorySelected.id;
                lblSelectedCategory.setText(categorySelected.name);
            }
        });
    }

    private void loadProviders() {
        providers = store.db.provider().getAll();
        providerAdapter = new ProviderAdapter(context, providers);
        listProviders.setAdapter(providerAdapter);

        listProviders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                providerSelected = providers.get(i);
                selectedProduct.providerId = providerSelected.id;
                lblSelectedProvider.setText(providerSelected.name);
            }
        });
    }

    private void update() {
        selectedProduct.name = productName.getText().toString();
        store.db.products().update(selectedProduct);
        Toast.makeText(context, "Product Updated!!", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(view).navigate(R.id.action_productDetailsFragment_to_productsFragment);
    }

    private void delete() {
        store.db.products().delete(selectedProduct);
        Toast.makeText(context, "Product deleted!!", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(view).navigate(R.id.action_productDetailsFragment_to_productsFragment);
    }
}
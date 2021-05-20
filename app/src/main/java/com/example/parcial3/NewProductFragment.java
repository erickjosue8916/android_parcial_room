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
 * Use the {@link NewProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewProductFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView productName;
    Button btnCreateProduct;

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



    public NewProductFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewProductFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewProductFragment newInstance(String param1, String param2) {
        NewProductFragment fragment = new NewProductFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_new_product, container, false);
        context = getContext();
        store = StoreDatabase.getInstance(context);
        listProviders = view.findViewById(R.id.newProductProviderOptions);
        listCategories = view.findViewById(R.id.newProductCategoryOptions);
        lblSelectedCategory = view.findViewById(R.id.lblSelectedCategory);
        lblSelectedProvider = view.findViewById(R.id.lblSelectedProvider);
        productName = view.findViewById(R.id.inputProductName);
        btnCreateProduct = view.findViewById(R.id.btnCreateProduct);

        btnCreateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert();
            }
        });

        this.loadProviders();
        this.loadCategories();
        return view;
    }

    private void loadCategories() {
        categories = store.db.categories().getAll();
        categoryAdapter = new CategoryAdapter(context, categories);
        listCategories.setAdapter(categoryAdapter);

        NewProductFragment instance = this;

        listCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                categorySelected = categories.get(i);
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
                lblSelectedProvider.setText(providerSelected.name);
            }
        });
    }

    private void insert() {
        Product product = new Product();
        product.name = productName.getText().toString();
        product.providerId = providerSelected.id;
        product.categoryId = categorySelected.id;
        long res = store.db.products().insert(product);
        Toast.makeText(context, "Nueva producto agregada", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(view).navigate(R.id.action_newCategoryFragment_to_categoriesFragment);
    }
}
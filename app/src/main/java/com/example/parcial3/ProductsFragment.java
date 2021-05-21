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

import com.example.parcial3.database.StoreDatabase;
import com.example.parcial3.entities.products.Product;
import com.example.parcial3.entities.products.ProductAdapter;
import com.example.parcial3.entities.products.ProductView;
import com.example.parcial3.entities.providers.Provider;
import com.example.parcial3.entities.providers.ProviderAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    List<ProductView> products;
    ProductAdapter adapter;
    ListView listProducts;
    ProductView productSelected;
    StoreDatabase store;
    View view;
    Context context;
    Button btnNewProduct;

    public ProductsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductsFragment newInstance(String param1, String param2) {
        ProductsFragment fragment = new ProductsFragment();
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
        view = inflater.inflate(R.layout.fragment_products, container, false);
        btnNewProduct = view.findViewById(R.id.btnNewProduct);
        context = getContext();
        store = StoreDatabase.getInstance(context);

        listProducts = view.findViewById(R.id.listProducts);
        this.load();
        btnNewProduct.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_productsFragment_to_newProductFragment));
        Button btnHome = view.findViewById(R.id.btnHome);
        btnHome.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_productsFragment_to_mainFragment));
        return view;
    }

    private void load(){
        products = store.db.products().getProductView();
        adapter = new ProductAdapter(context, products);
        listProducts.setAdapter(adapter);
        View principalView = view;
        listProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                productSelected = products.get(i);
                Bundle bundle = new Bundle();
                bundle.putInt("productId", productSelected.id);
                bundle.putString("provider", productSelected.provider);
                bundle.putString("category", productSelected.category);
                Navigation.findNavController(principalView).navigate(R.id.action_productsFragment_to_productDetailsFragment, bundle);
            }
        });
    }
}
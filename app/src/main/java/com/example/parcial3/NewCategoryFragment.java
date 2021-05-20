package com.example.parcial3;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parcial3.database.StoreDatabase;
import com.example.parcial3.entities.categories.Category;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewCategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewCategoryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private StoreDatabase store;
    Button btnSaveCategory;
    TextView inputCategoryName;
    Context context;
    View view;

    public NewCategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewCategoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewCategoryFragment newInstance(String param1, String param2) {
        NewCategoryFragment fragment = new NewCategoryFragment();
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
        view = inflater.inflate(R.layout.fragment_new_category, container, false);
        context = getContext();
        store = StoreDatabase.getInstance(context);
        inputCategoryName = view.findViewById(R.id.inputCategoryName);
        btnSaveCategory = view.findViewById(R.id.btnCreateCategory);

        NewCategoryFragment instance = this;

        btnSaveCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                instance.insertCategory();
            }
        });


        return view;
    }

    private void insertCategory() {
        Category category = new Category();
        category.name = inputCategoryName.getText().toString();
        long res = store.db.categories().insert(category);
        Toast.makeText(context, "Nueva categoria agregada", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(view).navigate(R.id.action_newCategoryFragment_to_categoriesFragment);
    }
}
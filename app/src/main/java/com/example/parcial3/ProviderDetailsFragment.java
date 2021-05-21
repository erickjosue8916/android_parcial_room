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
import com.example.parcial3.entities.providers.Provider;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProviderDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProviderDetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "providerId";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int providerId;
    private String mParam2;

    Button btnSaveProvider;
    Button btnDeleteProvider;
    TextView inputProviderName;

    Provider selectedProvider;
    StoreDatabase store;
    View view;
    Context context;

    public ProviderDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProviderDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProviderDetailsFragment newInstance(String param1, String param2) {
        ProviderDetailsFragment fragment = new ProviderDetailsFragment();
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
            providerId = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_provider_details, container, false);
        context = getContext();
        store = StoreDatabase.getInstance(context);
        inputProviderName = view.findViewById(R.id.inputProviderName);
        btnSaveProvider = view.findViewById(R.id.btnCreateProvider);
        btnDeleteProvider = view.findViewById(R.id.btnProviderDelete);

        btnSaveProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });

        btnDeleteProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });
        this.loadProvider();
        return view;
    }

    private void loadProvider () {
        selectedProvider = store.db.provider().getById(providerId);
        inputProviderName.setText(selectedProvider.name);
    }

    private void update() {
        selectedProvider.name = inputProviderName.getText().toString();
        store.db.provider().update(selectedProvider);
        Toast.makeText(context, "New provider updated!!", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(view).navigate(R.id.action_providerDetailsFragment_to_providersFragment);
    }

    private void delete() {
        store.db.provider().delete(selectedProvider);
        Toast.makeText(context, "Provider deleted!!", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(view).navigate(R.id.action_providerDetailsFragment_to_providersFragment);
    }
}
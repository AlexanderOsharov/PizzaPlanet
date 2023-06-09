package com.shurik.pizzaplanet.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shurik.pizzaplanet.R;
import com.shurik.pizzaplanet.adapters.OrganizationAdapter;
import com.shurik.pizzaplanet.databinding.FragmentCustomerBinding;
import com.shurik.pizzaplanet.databinding.FragmentSupplierBinding;

import java.util.ArrayList;

public class SupplierFragment extends Fragment {

    public static boolean isSupplier = false;

    // binding
    private FragmentSupplierBinding binding;

    // recylerView для заказчиков
    private RecyclerView recyclerViewUser;

//    // адаптер для заказчиков
//    private SupplierAdapter userAdapter;

    // layoutManager
    private LinearLayoutManager layoutManager;

    public SupplierFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // инициализация binding - а
        binding = FragmentSupplierBinding.inflate(inflater, container, false);

        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerViewUser = binding.userRecyclerview;
        recyclerViewUser.setLayoutManager(layoutManager);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        userAdapter = new SupplierAdapter(getActivity(), new ArrayList<>());
//        recyclerViewUser.setAdapter(userAdapter);

    }

}

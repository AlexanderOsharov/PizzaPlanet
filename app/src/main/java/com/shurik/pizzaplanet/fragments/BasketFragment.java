package com.shurik.pizzaplanet.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shurik.pizzaplanet.adapters.BasketAdapter;
import com.shurik.pizzaplanet.databinding.FragmentBasketBinding;
import com.shurik.pizzaplanet.model.Pizza;
import com.shurik.pizzaplanet.product_database.PizzaDAO;
import com.shurik.pizzaplanet.product_database.PizzaDatabaseApplication;

import java.util.ArrayList;
import java.util.List;

public class BasketFragment extends Fragment {

    private FragmentBasketBinding binding;
    private RecyclerView recyclerView;

    public static BasketAdapter basketAdapter;
    LinearLayoutManager layoutManager;

    private PizzaDAO pizzaDAO  = PizzaDatabaseApplication.getPizzaDatabase().pizzaDAO();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBasketBinding.inflate(inflater, container, false);
        recyclerView = binding.pizzaRecyclerview;
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), layoutManager.getOrientation()));
        recyclerView.setLayoutManager(layoutManager);

        // Initialize basketAdapter with an empty list of pizzas
        basketAdapter = new BasketAdapter(new ArrayList<>());
        recyclerView.setAdapter(basketAdapter);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}

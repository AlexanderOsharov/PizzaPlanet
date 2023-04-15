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

import java.util.ArrayList;
import java.util.List;

public class BasketFragment extends Fragment {

    private FragmentBasketBinding binding;
    private RecyclerView recyclerView;

    private BasketAdapter basketAdapter;
    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBasketBinding.inflate(inflater, container, false);
        recyclerView = binding.pizzaRecyclerview;
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), layoutManager.getOrientation()));
        recyclerView.setLayoutManager(layoutManager);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Pizza> pizzaList = new ArrayList<>();
        pizzaList.add(new Pizza("Маргаритта", "сыр с помидорчиками", "https://e0.edimdoma.ru/data/posts/0002/1429/21429-ed4_wide.jpg?1631194036","360"));
        pizzaList.add(new Pizza("Боржоми", "сыр с огурчиками", "https://www.edimdoma.ru/system/images/contents/0001/3568/wide/85419-original.jpg?1628278420","800"));
        pizzaList.add(new Pizza("1", "0000 u 1111", "https://avatars.mds.yandex.net/get-altay/2887807/2a000001744e74154b4e0274fa3024dacdf3/XXL","500"));
        pizzaList.add(new Pizza("2", "0110", "https://chef.ru/wp-content/uploads/iz-abhazii-s-lyubovyu-uv-3_tilda5808565.jpg","200"));

        updateUIWithPizzaVenues(pizzaList);
    }

    private void updateUIWithPizzaVenues(List<Pizza> pizzaVenuesList) {
        basketAdapter = new BasketAdapter(pizzaVenuesList);
        recyclerView.setAdapter(basketAdapter);
    }
}
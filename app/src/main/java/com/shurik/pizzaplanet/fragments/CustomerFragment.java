package com.shurik.pizzaplanet.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shurik.pizzaplanet.R;
import com.shurik.pizzaplanet.adapters.NewsAdapter;
import com.shurik.pizzaplanet.databinding.FragmentCustomerBinding;
import com.shurik.pizzaplanet.fragments.geolocation.Geolocation;
import com.shurik.pizzaplanet.model.New;
import com.shurik.pizzaplanet.model.Organization;
import com.shurik.pizzaplanet.adapters.OrganizationAdapter;
import com.shurik.pizzaplanet.model.Pizza;
import com.shurik.pizzaplanet.pizzasearch.OrganizationParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomerFragment extends Fragment {

    // binding
    private FragmentCustomerBinding binding;

    // recylerView для организаций
    private RecyclerView recyclerViewOrganization;

    // адаптер для организаций
    private OrganizationAdapter organizationAdapter;

    // список новостей
    private final ArrayList<New> news = new ArrayList<>();

    // адаптер для новостей
    private NewsAdapter newsAdapter;

    // layoutManager
    private LinearLayoutManager layoutManager;

    Geolocation geolocation;

    public CustomerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // инициализация binding - а
        binding = FragmentCustomerBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // напполняем список новостей
        setInitialData();

        // инициализируем адаптер
        newsAdapter = new NewsAdapter(news);

        // ставим адаптер viewPager
        binding.viewPager2.setAdapter(newsAdapter);

        // устанавливаем layoutManager recyclerViewOrganization
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewOrganization = binding.organizationRecyclerview;
        recyclerViewOrganization.setLayoutManager(layoutManager);

        geolocation = new Geolocation(getActivity());

        // возвращаем представление фрагмента (view)
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        Location location = geolocation.getUserLocation();
//        ArrayList<Organization> organizationList = OrganizationParser.getOrganizations(location);
//        updateUIWithPizzaVenues(organizationList);

        new Thread(() -> {
            Location location = geolocation.getUserLocation();
            try {
                ArrayList<Organization> organizationList = OrganizationParser.getOrganizations(location);
                getActivity().runOnUiThread(() -> updateUIWithPizzaVenues(organizationList));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();

    }

    private void updateUIWithPizzaVenues(List<Organization> pizzaVenuesList) {
        Location location = geolocation.getUserLocation();

        organizationAdapter = new OrganizationAdapter(getContext(), pizzaVenuesList, location.getLatitude(), location.getLongitude());
        recyclerViewOrganization.setAdapter(organizationAdapter);
    }

    private void setInitialData() {
        /**
         * Здес мы напполняем список новостей
         */
        news.add(new New(
                "2 пиццы",
                R.drawable.pizza_ham,
                R.drawable.pizza_icon_1,
                "Скидка 30%"
        ));
        news.add(new New(
                "Мяснйо комбо",
                R.drawable.pizza_ham,
                R.drawable.pizza_icon_2,
                "Напиток в подарок!"));
        news.add(new New(
                "2 пиццы",
                R.drawable.pizza_ham,
                R.drawable.pizza_icon_3,
                " Скидка 30%"));
        news.add(new New(
                "2 пиццы",
                R.drawable.pizza_ham,
                R.drawable.pizza_icon_4,
                " Скидка 30%"));
        news.add(new New(
                "2 пиццы",
                R.drawable.pizza_ham,
                R.drawable.pizza_icon_5,
                ""));
    }

}

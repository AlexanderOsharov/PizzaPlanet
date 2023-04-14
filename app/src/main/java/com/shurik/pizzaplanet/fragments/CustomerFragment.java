package com.shurik.pizzaplanet.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.shurik.pizzaplanet.Constants;
import com.shurik.pizzaplanet.databinding.FragmentCustomerBinding;
import com.shurik.pizzaplanet.pizzasearch.Organization;
import com.shurik.pizzaplanet.pizzasearch.OrganizationAdapter;
import com.shurik.pizzaplanet.pizzasearch.Pizza;
import com.shurik.pizzaplanet.pizzasearch.PizzaAdapter;
import com.shurik.pizzaplanet.pizzasearch.PizzaVenue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CustomerFragment extends Fragment {

    private FragmentCustomerBinding binding;
    private RecyclerView recyclerViewOrganization;
    private OrganizationAdapter organizationAdapter;
    private List<Organization> organizationList = new ArrayList<>();;
    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

    public CustomerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCustomerBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewOrganization = binding.organizationRecyclerview;
        recyclerViewOrganization.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<Pizza> pizzaArrayList = new ArrayList<>();
        pizzaArrayList.add(new Pizza("Маргаритта", "сыр с помидорчиками", "https://e0.edimdoma.ru/data/posts/0002/1429/21429-ed4_wide.jpg?1631194036","360"));
        pizzaArrayList.add(new Pizza("Боржоми", "сыр с огурчиками", "https://www.edimdoma.ru/system/images/contents/0001/3568/wide/85419-original.jpg?1628278420","800"));

        Organization organization = new Organization("Шикарная ул., дом 9, Москва", "PizzaPlanet", "1", pizzaArrayList, "55.996596", "37.220086");

        ArrayList<Pizza> pizzaArrayList_2 = new ArrayList<>();
        pizzaArrayList_2.add(new Pizza("1", "0000 u 1111", "https://e0.edimdoma.ru/data/posts/0002/1429/21429-ed4_wide.jpg?1631194036","500"));
        pizzaArrayList_2.add(new Pizza("2", "0110", "https://www.edimdoma.ru/system/images/contents/0001/3568/wide/85419-original.jpg?1628278420","200"));

        Organization organization_2 = new Organization("Замечательная ул., дом 13, Москва", "PizzaPlanet", "1", pizzaArrayList_2, "55.996596", "37.220086");

        organizationList.add(organization);
        organizationList.add(organization_2);

        updateUIWithPizzaVenues(organizationList);
    }

    private void updateUIWithPizzaVenues(List<Organization> pizzaVenuesList) {
        organizationAdapter = new OrganizationAdapter(getContext(), pizzaVenuesList, "55.798292", "37.512366");
        recyclerViewOrganization.setAdapter(organizationAdapter);
    }

}

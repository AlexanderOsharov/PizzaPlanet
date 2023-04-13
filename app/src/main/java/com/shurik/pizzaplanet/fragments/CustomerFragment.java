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

public class CustomerFragment extends Fragment implements PizzaAdapter.OnItemClickListener {

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
        Organization organization = new Organization("Шикарная ул., дом 9, Москва", "PizzaPlanet", "1", "", pizzaArrayList);

        ArrayList<Pizza> pizzaArrayList_2 = new ArrayList<>();
        pizzaArrayList_2.add(new Pizza("1", "0000 u 1111", "https://e0.edimdoma.ru/data/posts/0002/1429/21429-ed4_wide.jpg?1631194036","500"));
        pizzaArrayList_2.add(new Pizza("2", "0110", "https://www.edimdoma.ru/system/images/contents/0001/3568/wide/85419-original.jpg?1628278420","200"));
        Organization organization_2 = new Organization("Замечательная ул., дом 13, Москва", "PizzaPlanet", "1", "", pizzaArrayList_2);

        organizationList.add(organization);
        organizationList.add(organization_2);

        updateUIWithPizzaVenues(organizationList);
    }

    private void updateUIWithPizzaVenues(List<Organization> pizzaVenuesList) {
        // Передать информацию о пиццериях в RecyclerView или другой виджет для отображения данных пользователю
        // Чтобы работать с картами Yandex, установите и импортируйте нужные зависимости (см. https://yandex.ru/dev/maps/mapkit/doc/android-ref/full/index.html)
        // Интегрируйте карту Yandex с приложением, используя информацию из списка pizzaVenuesList для отображения маркеров
        organizationAdapter = new OrganizationAdapter(getContext(), pizzaVenuesList);
        recyclerViewOrganization.setAdapter(organizationAdapter);
    }

    public static JSONObject searchPizzaVenues(String latitude, String longitude, String clientId, String clientSecret)
            throws IOException, JSONException {

        String url = "https://api.foursquare.com/v2/venues/search?v=20190425"
                + "&ll=" + latitude + "," + longitude
                + "&intent=browse&radius=500&query=pizza"
                + "&client_id=" + clientId
                + "&client_secret=" + clientSecret;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            throw new IOException("Ошибка сервера: " + response);
        }

        String json = response.body().string();

        return new JSONObject(json);
    }

    public static JSONObject getVenueDetails(String venueId, String clientId, String clientSecret)
            throws IOException, JSONException {
        String url = "https://api.foursquare.com/v2/venues/" + venueId
                + "?v=20190425"
                + "&client_id=" + clientId
                + "&client_secret=" + clientSecret;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        String json = response.body().string();

        return new JSONObject(json);
    }

    @NonNull
    @Override
    public CreationExtras getDefaultViewModelCreationExtras() {
        return super.getDefaultViewModelCreationExtras();
    }

    @Override
    public void onItemClick(Pizza pizzaVenue) {
        MapDialogFragment dialogFragment = new MapDialogFragment();
        dialogFragment.show(getFragmentManager(), "dialog");
    }
}

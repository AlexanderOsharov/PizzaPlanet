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
import com.shurik.pizzaplanet.R;
import com.shurik.pizzaplanet.databinding.FragmentCustomerBinding;
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
    private RecyclerView recyclerView;
    private PizzaAdapter pizzaAdapter;
    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

    public CustomerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCustomerBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView = binding.pizzaVenueRecyclerview;
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            // Вызываем метод searchPizzaVenues для получения списка пиццерий
            JSONObject pizzaVenues = searchPizzaVenues("55.790199", "37.531649",
                    Constants.FOURSQUARE_ClientId, Constants.FOURSQUARE_ClientSecret);

            // Обработка JSON-объекта и извлечение информации о пиццериях
            JSONObject response = pizzaVenues.getJSONObject("response");
            JSONArray venues = response.getJSONArray("venues");

            // Создаем список объектов класса PizzaVenue, которые будут содержать информацию о каждой пиццерии
            List<PizzaVenue> pizzaVenuesList = new ArrayList<PizzaVenue>();
            for (int i = 0; i < venues.length(); i++) {
                JSONObject venue = venues.getJSONObject(i);
                String venueId = venue.getString("id");
                String name = venue.getString("name");
                JSONObject venueDetails = getVenueDetails(venueId, Constants.FOURSQUARE_ClientId, Constants.FOURSQUARE_ClientSecret);
                String address = venue.getJSONObject("location").getString("address");

                // Получить информацию о пицце и изображении из API
                String pizzaName = "", pizzaComposition = "", imageUrl = "";

                // Для примера мы предполагаем, что информация о пицце находится в первой записи меню.
                if (venueDetails.has("response") && venueDetails.getJSONObject("response").has("venues") &&
                        venueDetails.getJSONObject("response").getJSONArray("venues").length() > 0) {
                    JSONObject menu = venueDetails.getJSONObject("response").getJSONArray("venues").getJSONObject(0);
                    if (menu.has("name")) {
                        pizzaName = menu.getString("name");
                    }
                }

                PizzaVenue pizza_Venue = new PizzaVenue(name, address, pizzaName, pizzaComposition, imageUrl);
                pizzaVenuesList.add(pizza_Venue);
            }


            // Обновление пользовательского интерфейса с информацией о пиццериях
            updateUIWithPizzaVenues(pizzaVenuesList);

        } catch (IOException | JSONException e) {
            e.printStackTrace();
            //handle connection error
            Toast.makeText(getContext(), "Ошибка подключения к серверу. Проверьте соединение и повторите запрос.", Toast.LENGTH_SHORT).show();
        }

    }

    private void updateUIWithPizzaVenues(List<PizzaVenue> pizzaVenuesList) {
        // Передать информацию о пиццериях в RecyclerView или другой виджет для отображения данных пользователю
        // Чтобы работать с картами Yandex, установите и импортируйте нужные зависимости (см. https://yandex.ru/dev/maps/mapkit/doc/android-ref/full/index.html)
        // Интегрируйте карту Yandex с приложением, используя информацию из списка pizzaVenuesList для отображения маркеров
        pizzaAdapter = new PizzaAdapter(getContext(), pizzaVenuesList);
        pizzaAdapter.setOnItemClickListener((PizzaAdapter.OnItemClickListener) getContext());
        recyclerView.setAdapter(pizzaAdapter);
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

    @Override
    public void onItemClick(PizzaVenue pizzaVenue) {
        MapDialogFragment dialogFragment = new MapDialogFragment();
        dialogFragment.show(getFragmentManager(), "dialog");
    }

    @NonNull
    @Override
    public CreationExtras getDefaultViewModelCreationExtras() {
        return super.getDefaultViewModelCreationExtras();
    }
}

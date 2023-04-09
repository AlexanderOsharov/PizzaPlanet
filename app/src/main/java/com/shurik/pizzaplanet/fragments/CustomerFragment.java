package com.shurik.pizzaplanet.fragments;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shurik.pizzaplanet.Constants;
import com.shurik.pizzaplanet.R;
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

    public CustomerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_supplier, container,	false);

        try {
            // Вызываем метод searchPizzaVenues для получения списка пиццерий
            JSONObject pizzaVenues = searchPizzaVenues("55.790199", "37.531649",
                    Constants.FOURSQUARE_ClientId, Constants.FOURSQUARE_ClientId);

            // Обработка JSON-объекта и извлечение информации о пиццериях
            JSONObject response = pizzaVenues.getJSONObject("response");
            JSONArray venues = response.getJSONArray("venues");

            // Создаем список объектов класса PizzaVenue, которые будут содержать информацию о каждой пиццерии
            List<PizzaVenue> pizzaVenuesList = new ArrayList<PizzaVenue>();
            for (int i = 0; i < venues.length(); i++) {
                JSONObject venue = venues.getJSONObject(i);
                String name = venue.getString("name");
                String address = venue.getJSONObject("location").getString("address");

                // Добавить данные о пицце и изображении (эти данные должны быть предоставлены вашим API-бэкэндом или парситься с сайта)
                String pizzaName = "Sample Pizza Name";
                String pizzaComposition = "Sample Pizza Composition";
                String imageUrl = "https://example.com/sample_pizza_image.jpg";

                PizzaVenue pizzaVenue = new PizzaVenue(name, address, pizzaName, pizzaComposition, imageUrl);
                pizzaVenuesList.add(pizzaVenue);

                // Добавляем маркеры на карту (интеграция с Yandex Maps вместо Google Maps)
            }

            // Обновление пользовательского интерфейса с информацией о пиццериях
            updateUIWithPizzaVenues(pizzaVenuesList);

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return view;
    }

    private void updateUIWithPizzaVenues(List<PizzaVenue> pizzaVenuesList) {
        // Передать информацию о пиццериях в RecyclerView или другой виджет для отображения данных пользователю
        // Чтобы работать с картами Yandex, установите и импортируйте нужные зависимости (см. https://yandex.ru/dev/maps/mapkit/doc/android-ref/full/index.html)
        // Интегрируйте карту Yandex с приложением, используя информацию из списка pizzaVenuesList для отображения маркеров
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
        String json = response.body().string();

        return new JSONObject(json);
    }
}

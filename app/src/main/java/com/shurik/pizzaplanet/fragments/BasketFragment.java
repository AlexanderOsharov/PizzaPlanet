package com.shurik.pizzaplanet.fragments;

import android.os.AsyncTask;
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
import com.shurik.pizzaplanet.product_database.PizzaDatabase;
import com.shurik.pizzaplanet.product_database.PizzaEntity;

import java.util.ArrayList;
import java.util.List;

public class BasketFragment extends Fragment {

    private FragmentBasketBinding binding;
    private RecyclerView recyclerView;

    public static BasketAdapter basketAdapter;
    LinearLayoutManager layoutManager;

    private PizzaDatabase database;
    public static PizzaDAO pizzaDao;

    private List<Pizza> pizzaList = new ArrayList<>();
    private List<PizzaEntity> pizzaListAdapter = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBasketBinding.inflate(inflater, container, false);
        recyclerView = binding.pizzaRecyclerview;
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), layoutManager.getOrientation()));
        recyclerView.setLayoutManager(layoutManager);

        // Инициализация
        basketAdapter = new BasketAdapter(new ArrayList<>());
        recyclerView.setAdapter(basketAdapter);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Создать и получить доступ к базе данных
        database = PizzaDatabase.getInstance(getActivity());

        // Получить доступ к Dao
        pizzaDao = database.pizzaDao();

        // Получить все пиццы из базы данных и добавить их в адаптер
        new Thread(() -> {
            List<PizzaEntity> pizzaEntities = pizzaDao.getAllPizzas();
            for (PizzaEntity pizzaEntity : pizzaEntities) {
                Pizza pizza = new Pizza(
                pizzaEntity.getId(),
                pizzaEntity.getTitle(),
                pizzaEntity.getDesciption(),
                pizzaEntity.getPic(),
                pizzaEntity.getFee(),
                pizzaEntity.getQuantity());
                getActivity().runOnUiThread(() -> basketAdapter.addPizza(pizza));
            }
        }).start();
    }


    private class GetPizzasAsyncTask extends AsyncTask<Void, Void, List<PizzaEntity>> {
        private PizzaDAO pizzaDao;

        public GetPizzasAsyncTask(PizzaDAO pizzaDao) {
            this.pizzaDao = pizzaDao;
        }

        @Override
        protected List<PizzaEntity> doInBackground(Void... voids) {
            return pizzaDao.getAllPizzas();
        }

        @Override
        protected void onPostExecute(List<PizzaEntity> pizzas) {
            super.onPostExecute(pizzas);
            // Здесь можно обработать полученный список пицц, например, передать его в адаптер RecyclerView
            // pizzaListAdapter.submitList(pizzas);
        }
    }
}

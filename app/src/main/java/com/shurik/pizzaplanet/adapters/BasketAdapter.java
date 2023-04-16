package com.shurik.pizzaplanet.adapters;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shurik.pizzaplanet.R;
import com.shurik.pizzaplanet.fragments.BasketFragment;
import com.shurik.pizzaplanet.model.Pizza;
import com.shurik.pizzaplanet.product_database.PizzaEntity;

import java.util.List;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.BasketViewHolder> {

    private List<Pizza> pizzaList;

    public BasketAdapter(List<Pizza> pizzaList) {
        this.pizzaList = pizzaList;
    }

    @NonNull
    @Override
    public BasketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.basket_item, parent, false);
        return new BasketViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull BasketViewHolder holder, int position) {
        Pizza pizza = pizzaList.get(position);
        holder.pizzaTitle.setText(pizza.getTitle());
        int sum = Integer.parseInt(pizza.getFee().replaceAll("\\D+", "")) * pizza.getQuantity();
        holder.price.setText(String.valueOf(sum) + pizza.getFee().replaceAll("\\d", ""));
        holder.quantity.setText(String.valueOf(pizza.getQuantity()));
        Glide.with(holder.pizzaImage.getContext())
                .load(pizza.getPic())
                .into(holder.pizzaImage);
    }

    @Override
    public int getItemCount() {
        return pizzaList.size();
    }

    class BasketViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView pizzaImage;
        TextView pizzaTitle;
        TextView price;
        ImageView minus;
        TextView quantity;
        ImageView plus;
        ImageButton close;

        BasketViewHolder(View view) {
            super(view);
            pizzaImage = view.findViewById(R.id.pizza_image);
            pizzaTitle = view.findViewById(R.id.pizza_title);
            price = view.findViewById(R.id.price);
            minus = view.findViewById(R.id.minus);
            minus.setOnClickListener(this);
            quantity = view.findViewById(R.id.quantity);
            plus = view.findViewById(R.id.plus);
            plus.setOnClickListener(this);
            close = view.findViewById(R.id.close);
            close.setOnClickListener(this);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onClick(View v) {
            // get the pizza item at the clicked position
            Pizza item = pizzaList.get(getAdapterPosition());

            switch (v.getId()) {
                case R.id.minus:
                    int qty = 1;
                    if(!TextUtils.isEmpty(quantity.getText().toString())) {
                        qty = Integer.parseInt(quantity.getText().toString());
                    }
                    int sum = 0;
                    if (!TextUtils.isEmpty(price.getText().toString())) {
                        String digitsOnly = price.getText().toString().replaceAll("\\D+", "");
                        sum = Integer.parseInt(digitsOnly);
                    }
                    if (qty > 1) {
                        qty--;
                        String priceString = item.getFee();
                        String digitsOnly = priceString.replaceAll("\\D+", "");
                        sum -= Integer.parseInt(digitsOnly);

                        quantity.setText(String.valueOf(qty));
                        price.setText(String.valueOf(sum) + item.getFee().replaceAll("\\d", ""));
                    }
                    break;

                case R.id.plus:
                    int qty1 = 1;
                    if(!TextUtils.isEmpty(quantity.getText().toString())) {
                        qty1 = Integer.parseInt(quantity.getText().toString());
                    }
                    int sum1 = 0;
                    if (!TextUtils.isEmpty(price.getText().toString())) {
                        String digitsOnly1 = price.getText().toString().replaceAll("\\D+", "");
                        sum1 = Integer.parseInt(digitsOnly1);
                    }
                    qty1++;
                    String priceString1 = item.getFee();
                    String digitsOnly1 = priceString1.replaceAll("\\D+", "");
                    sum1 += Integer.parseInt(digitsOnly1);

                    quantity.setText(String.valueOf(qty1));
                    price.setText(String.valueOf(sum1) + item.getFee().replaceAll("\\d", ""));
                    break;

                case R.id.close:
                    // Удалить PizzaEntity из базы данных
                    new Thread(() -> {
                        PizzaEntity pizzaToDelete = new PizzaEntity();
                        pizzaToDelete.setDesciption(item.getDescription());
                        pizzaToDelete.setPic(item.getPic());
                        pizzaToDelete.setFee(item.getFee());
                        pizzaToDelete.setQuantity(item.getQuantity());
                        BasketFragment.pizzaDao.delete(pizzaToDelete);
                    }).start();

                    removePizza(getAdapterPosition());
                    break;

            }
        }
    }

    public void addPizza(Pizza pizza) {
        pizzaList.add(pizza);
        notifyItemInserted(pizzaList.indexOf(pizza));
    }

    public void removePizza(int position) {
        pizzaList.remove(position);
        notifyItemRemoved(position);
    }
}

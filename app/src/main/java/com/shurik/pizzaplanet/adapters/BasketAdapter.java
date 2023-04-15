package com.shurik.pizzaplanet.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shurik.pizzaplanet.R;
import com.shurik.pizzaplanet.model.Pizza;

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

    @Override
    public void onBindViewHolder(@NonNull BasketViewHolder holder, int position) {
        Pizza pizza = pizzaList.get(position);
        holder.pizzaTitle.setText(pizza.getTitle());
        holder.price.setText(pizza.getFee());
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
        }

        @Override
        public void onClick(View v) {
            // get the pizza item at the clicked position
            Pizza item = pizzaList.get(getAdapterPosition());

            switch (v.getId()) {
                case R.id.minus:
                    int qty = Integer.parseInt(quantity.getText().toString());
                    if (qty > 1) {
                        qty--;
                        quantity.setText(String.valueOf(qty));
                    }
                    break;

                case R.id.plus:
                    int qty1 = Integer.parseInt(quantity.getText().toString());
                    qty1++;
                    quantity.setText(String.valueOf(qty1));
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

package com.shurik.pizzaplanet.adapters;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shurik.pizzaplanet.R;
import com.shurik.pizzaplanet.fragments.BasketFragment;
import com.shurik.pizzaplanet.model.Pizza;

import java.util.List;

public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.ViewHolder> {

    private static List<Pizza> mPizzaVenues;
    private Context mContext;

    public PizzaAdapter(Context context, List<Pizza> pizzaVenues) {
        mContext = context;
        mPizzaVenues = pizzaVenues;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pizza_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pizza pizzaVenue = mPizzaVenues.get(position);

        holder.pizzaFee.setText(pizzaVenue.getFee());
        loadImageIntoImageView(mContext, pizzaVenue.getPic(), holder.pizzaPic);
        holder.pizzaTitle.setText(pizzaVenue.getTitle());

        // Действие при нажатии добавление в корзину
        holder.basketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO прописать функционал добавления в корзину
            }
        });

        // Действие при нажатии на картинку
        holder.pizzaPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPizzaDetailsDialog(pizzaVenue);
            }
        });

        // Изменение счетчика
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty1 = Integer.parseInt(holder.quantity.getText().toString());
                qty1++;
                holder.quantity.setText(String.valueOf(qty1));
            }
        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty = Integer.parseInt(holder.quantity.getText().toString());
                if (qty > 1) {
                    qty--;
                    holder.quantity.setText(String.valueOf(qty));
                }
            }
        });

        // Действие при нажатии на корзинку
        holder.basketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BasketFragment.basketAdapter.addPizza(pizzaVenue);
            }
        });
    }

    // кол - во пицц
    @Override
    public int getItemCount() {
        return mPizzaVenues.size();
    }

    // viewHolder для пиццы
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView pizzaFee;
        public ImageView pizzaPic;
        public TextView pizzaTitle;
        public ImageButton basketButton;
        public ImageView plus;
        public TextView quantity;
        public ImageView minus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pizzaFee = itemView.findViewById(R.id.fee);
            pizzaPic = itemView.findViewById(R.id.pic);
            pizzaTitle = itemView.findViewById(R.id.title);
            basketButton = itemView.findViewById(R.id.addBtn);
            minus = itemView.findViewById(R.id.minus);
            quantity = itemView.findViewById(R.id.numberOrderTxt);
            plus = itemView.findViewById(R.id.plus);
        }
    }

    // загружаем картинку
    private void loadImageIntoImageView(Context context, String imageUrl, ImageView imageView) {
        Glide.with(context).load(imageUrl).centerCrop().into(imageView);
    }

    private void showPizzaDetailsDialog(Pizza pizza) {
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.pizza_details);

        TextView pizzaTitleDetails = dialog.findViewById(R.id.titleText);
        ImageView pizzaPicDetails = dialog.findViewById(R.id.picFood);
        TextView pizzaDescriptionDetails = dialog.findViewById(R.id.descriptionTxt);
        TextView pizzaFeeDetails = dialog.findViewById(R.id.feeTxt);
        ImageButton closeDetails = dialog.findViewById(R.id.crest);
        ImageView plus = dialog.findViewById(R.id.plus);
        TextView qnt = dialog.findViewById(R.id.numberOrderTxt);

        pizzaTitleDetails.setText(pizza.getTitle());
        loadImageIntoImageView(mContext, pizza.getPic(), pizzaPicDetails);
        pizzaDescriptionDetails.setText(pizza.getDesciption());
        pizzaFeeDetails.setText(pizza.getFee());

        // удаление окошка
        closeDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty1 = Integer.parseInt(qnt.getText().toString());
                qty1++;
                qnt.setText(String.valueOf(qty1));

            }
        });
        dialog.show();
    }

    public void addPizza(Pizza pizza) {
        mPizzaVenues.add(pizza);
        notifyItemInserted(mPizzaVenues.indexOf(pizza));
    }

    public void removePizza(int position) {
        mPizzaVenues.remove(position);
        notifyItemRemoved(position);
    }
}
package com.shurik.pizzaplanet.pizzasearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shurik.pizzaplanet.R;
import com.shurik.pizzaplanet.pizzasearch.PizzaVenue;

import java.util.List;

public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.ViewHolder> {

    private List<Pizza> mPizzaVenues;
    private Context mContext;
    private OnItemClickListener mListener;

    public PizzaAdapter(Context context, List<Pizza> pizzaVenues) {
        mContext = context;
        mPizzaVenues = pizzaVenues;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pizza_venue_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pizza pizzaVenue = mPizzaVenues.get(position);

        holder.pizzaVenuePrice.setText(pizzaVenue.getPrice());
        loadImageIntoImageView(mContext, pizzaVenue.getImageUrl(), holder.pizzaImage);
        holder.pizzaName.setText(pizzaVenue.getPizzaName());
        holder.pizzaComposition.setText(pizzaVenue.getPizzaComposition());

        // Действие при нажатии
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(pizzaVenue);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPizzaVenues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView pizzaVenuePrice;
        public ImageView pizzaImage;
        public TextView pizzaName;
        public TextView pizzaComposition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pizzaVenuePrice = itemView.findViewById(R.id.pizza_venue_price);
            pizzaImage = itemView.findViewById(R.id.pizza_image);
            pizzaName = itemView.findViewById(R.id.pizza_name);
            pizzaComposition = itemView.findViewById(R.id.pizza_composition);
        }
    }

    private void loadImageIntoImageView(Context context, String imageUrl, ImageView imageView) {
        Glide.with(context)
                .load(imageUrl)
                .centerCrop()
                .into(imageView);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(Pizza pizzaVenue);
    }

}

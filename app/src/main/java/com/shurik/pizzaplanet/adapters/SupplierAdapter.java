package com.shurik.pizzaplanet.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shurik.pizzaplanet.R;

//public class SupplierAdapter extends RecyclerView.Adapter<SupplierAdapter.ViewHolder> {
//
//    private List<User> users;
//    private Context context;
//
//    public SupplierAdapter(Context context, List<User> users) {
//        this.users = users;
//        this.context = context;
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.supplier_item, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        User user = users.get(position);
//
//        holder.pizzaTitle.setText(user.getName());
//        holder.phone.setText(user.getPhone());
//
//        Glide.with(context)
//                .load(user.getImageUrl())
//                .into(holder.user);
//    }
//
//    @Override
//    public int getItemCount() {
//        return users.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//
//        ImageView user;
//        TextView pizzaTitle;
//        TextView phone;
//        Button button;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//
//            user = itemView.findViewById(R.id.user);
//            pizzaTitle = itemView.findViewById(R.id.pizza_title);
//            phone = itemView.findViewById(R.id.phone);
//            button = itemView.findViewById(R.id.button);
//        }
//    }
//}

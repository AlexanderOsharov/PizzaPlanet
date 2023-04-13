package com.shurik.pizzaplanet.pizzasearch;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shurik.pizzaplanet.R;

import java.util.List;

public class OrganizationAdapter extends RecyclerView.Adapter<OrganizationAdapter.ViewHolder> {
    private Context context;
    private List<Organization> organizationList;
    private PizzaAdapter pizzaAdapter;

    public OrganizationAdapter(Context context, List<Organization> organizationList) {
        this.context = context;
        this.organizationList = organizationList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_organization, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Organization organization = organizationList.get(position);
        holder.organizationName.setText(organization.getName());
        holder.organizationAddress.setText(organization.getAddress());

        holder.itemView.setOnClickListener(view -> {
            RecyclerView pizzaRecyclerView = ((Activity) context).findViewById(R.id.pizza_venue_recyclerview);
            pizzaAdapter = new PizzaAdapter(context, organization.getPizzaList());
            pizzaRecyclerView.setAdapter(pizzaAdapter);
            pizzaRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        });
    }

    @Override
    public int getItemCount() {
        return organizationList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView organizationName;
        TextView organizationAddress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            organizationName = itemView.findViewById(R.id.organization_name);
            organizationAddress = itemView.findViewById(R.id.organization_address);
        }
    }
}

package com.shurik.pizzaplanet.pizzasearch;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shurik.pizzaplanet.R;
import com.shurik.pizzaplanet.fragments.MapDialogFragment;
import com.yandex.mapkit.geometry.Geo;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;

import java.util.List;

public class OrganizationAdapter extends RecyclerView.Adapter<OrganizationAdapter.ViewHolder> {
    private Context context;
    private List<Organization> organizationList;
    private PizzaAdapter pizzaAdapter;
    private String latitude;
    private String longitude;

    public OrganizationAdapter(Context context, List<Organization> organizationList) {
        this.context = context;
        this.organizationList = organizationList;
    }

    public OrganizationAdapter(Context context, List<Organization> organizationList, String latitude, String longitude) {
        this.context = context;
        this.organizationList = organizationList;
        this.latitude = latitude;
        this.longitude = longitude;
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

        holder.organizationName.setOnClickListener(view -> {
            RecyclerView pizzaRecyclerView = ((Activity) context).findViewById(R.id.pizza_venue_recyclerview);
            pizzaAdapter = new PizzaAdapter(context, organization.getPizzaList());
            pizzaRecyclerView.setAdapter(pizzaAdapter);
            pizzaRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        });

        holder.organizationAddress.setOnClickListener(view -> {
            MapDialogFragment mapDialogFragment = MapDialogFragment.newInstance(
                    new Point(Double.parseDouble(latitude), Double.parseDouble(longitude)),
                    new Point(Double.parseDouble(organization.getLatitude()), Double.parseDouble(organization.getLongitude()))
            );

            mapDialogFragment.show(((AppCompatActivity) context).getSupportFragmentManager(), "map_dialog");
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

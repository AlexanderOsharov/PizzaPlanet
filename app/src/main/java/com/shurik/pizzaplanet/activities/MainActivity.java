package com.shurik.pizzaplanet.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.shurik.pizzaplanet.model.Pizza;
import com.shurik.pizzaplanet.other.Constants;
import com.shurik.pizzaplanet.R;
import com.shurik.pizzaplanet.fragments.SupplierFragment;
import com.shurik.pizzaplanet.fragments.CustomerFragment;
import com.shurik.pizzaplanet.fragments.BasketFragment;
import com.shurik.pizzaplanet.fragments.UserFragment;
import com.shurik.pizzaplanet.fragments.UserSettingsFragment;
import com.shurik.pizzaplanet.fragments.geolocation.Geolocation;
import com.yandex.mapkit.MapKitFactory;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Запрос на разрешение полученя данных о текущем местоположении
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);

        // Инициализация Yandex-MapKit
        MapKitFactory.setApiKey(Constants.YANDEX_MapKitSDK);
        MapKitFactory.initialize(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        loadFragment(new SupplierFragment());
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment;

            switch (item.getItemId()) {
                case R.id.nav_customer_fragment:
                    selectedFragment = new CustomerFragment();
                    break;
                case R.id.nav_supplier_fragment:
                    if (SupplierFragment.isSupplier) selectedFragment = new SupplierFragment();
                    else {
                        selectedFragment = new SupplierFragment();
                    }
                    break;
                case R.id.nav_basket_fragment:
                    selectedFragment = new BasketFragment();
                    break;
                case R.id.nav_user_settings_fragment:
                    selectedFragment = new UserSettingsFragment();
                    break;
                case R.id.user_fragment:
                    selectedFragment = new UserFragment();
                    break;
                default:
                    selectedFragment = new SupplierFragment();
                    break;
            }
            loadFragment(selectedFragment);
            return true;
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (TutorialActivity.whatIsIt == 1) {
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        } else if (TutorialActivity.whatIsIt == 2) {
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Разрешение получено, можно вызывать метод getUserLocation() из любого другого класса
                Geolocation geolocation = new Geolocation(this);
                Location location = geolocation.getUserLocation();
            }
        }
    }

    private void showPizzaDetailsDialog(Pizza pizza) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.pizza_details);

        TextView pizzaTitleDetails = dialog.findViewById(R.id.titleText);
        ImageView pizzaPicDetails = dialog.findViewById(R.id.picFood);
        TextView pizzaDescriptionDetails = dialog.findViewById(R.id.descriptionTxt);
        TextView pizzaFeeDetails = dialog.findViewById(R.id.feeTxt);
        ImageButton closeDetails = dialog.findViewById(R.id.crest);

        pizzaTitleDetails.setText(pizza.getTitle());
        pizzaDescriptionDetails.setText(pizza.getDesciption());
        pizzaFeeDetails.setText(pizza.getFee());

        // удаление окошка
        closeDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
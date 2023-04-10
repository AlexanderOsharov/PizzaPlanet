package com.shurik.pizzaplanet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.shurik.pizzaplanet.fragments.GeolocationFragment;
import com.shurik.pizzaplanet.fragments.SupplierFragment;
import com.shurik.pizzaplanet.fragments.CustomerFragment;
import com.shurik.pizzaplanet.fragments.BasketFragment;
import com.shurik.pizzaplanet.fragments.UserSettingsFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        loadFragment(new SupplierFragment());
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment;

            switch (item.getItemId()) {
                case R.id.nav_customer_fragment:
                    selectedFragment = new GeolocationFragment();
                    break;
                case R.id.nav_supplier_fragment:
                    selectedFragment = new SupplierFragment();
                    break;
                case R.id.nav_basket_fragment:
                    selectedFragment = new BasketFragment();
                    break;
                case R.id.nav_user_settings_fragment:
                    selectedFragment = new UserSettingsFragment();
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
}

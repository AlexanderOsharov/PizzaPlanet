package com.shurik.pizzaplanet.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shurik.pizzaplanet.R;
import com.shurik.pizzaplanet.adapters.ReglogAdapter;
import com.shurik.pizzaplanet.databinding.FragmentLoginBinding;
import com.shurik.pizzaplanet.databinding.FragmentUserBinding;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentLoginBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
}
package com.shurik.pizzaplanet.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.shurik.pizzaplanet.MainActivity;
import com.shurik.pizzaplanet.R;
import com.shurik.pizzaplanet.database.UserDao;
import com.shurik.pizzaplanet.database.UserDatabase;
import com.shurik.pizzaplanet.database.UserEntity;
import com.shurik.pizzaplanet.databinding.FragmentLoginBinding;
import com.shurik.pizzaplanet.databinding.FragmentRegistrationBinding;

import java.util.Objects;

public class RegistrationFragment extends Fragment {

    private FragmentRegistrationBinding binding;
    private Handler handler;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentRegistrationBinding.inflate(inflater, container, false);

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                return false;
            }
        });
        binding.register.setOnClickListener(v -> {
            UserEntity userEntity = new UserEntity();
            userEntity.setName(binding.editName.getText().toString());
            userEntity.setPhone(binding.editPhone.getText().toString());
            userEntity.setMail(binding.editMail.getText().toString());
            userEntity.setPassword(binding.editPass.getText().toString());
            if (validateInput(userEntity)) {
                UserDatabase database = UserDatabase.getUserDatabase(requireActivity().getApplicationContext());
                UserDao userDao = database.userDao();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        userDao.registerUser(userEntity);
                        handler.sendEmptyMessage(1);
                    }
                }).start();
            } else {
                //
            }
        });
        return binding.getRoot();
    }

    private boolean validateInput(UserEntity userEntity) {
        if (userEntity.getName().isEmpty() ||
                userEntity.getPhone().isEmpty() ||
                userEntity.getPassword().isEmpty()) {
            return false;
        }
        return true;
    }
}


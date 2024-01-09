package org.depinfo.chictype.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.depinfo.chictype.databinding.ActivityMainBinding;
import org.depinfo.chictype.network.http.RetrofitUtil;
import org.depinfo.chictype.network.http.Service;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private Service service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        service = RetrofitUtil.get();

        setContentView(binding.getRoot());

        setupBtnRandom();
    }

    private void setupBtnRandom() {
        binding.btnRandom.setOnClickListener(v -> callRandom());
    }

    private void callRandom() {
        service.getRandomNumber().enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(@NonNull Call<Integer> call, @NonNull Response<Integer> response) {
                if (response.isSuccessful()) {
                    Integer resultat = response.body();
                    Log.i("RETROFIT", "Mon nombre aléatoire : " + resultat);
                    binding.tvRandom.setText(String.valueOf(resultat));
                } else {
                    Log.i("RETROFIT", "J'ai une erreur : " + response.code());
                    Toast.makeText(MainActivity.this, "Y'a un problème!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Integer> call, @NonNull Throwable t) {
                Log.i("RETROFIT", "J'ai une grosse erreur!" + t.getMessage());
                Toast.makeText(MainActivity.this, "Y'a un gros problème!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
package com.example.mireamobile82;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity {

    private ImageView imageDog;
    private Button btnLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageDog = findViewById(R.id.imageDog);
        btnLoad = findViewById(R.id.btnLoad);

        btnLoad.setOnClickListener(v -> {
            loadDogImage();
        });
    }

    private void loadDogImage() {
        Toast.makeText(this, "Загрузка изображения. . .", Toast.LENGTH_SHORT).show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://random.dog/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DogApiService service = retrofit.create(DogApiService.class);

        service.getRandomDog().enqueue(new Callback<DogResponse>() {
            @Override
            public void onResponse(Call<DogResponse> call, Response<DogResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String imageUrl = response.body().url;
                    Glide.with(MainActivity.this)
                            .load(imageUrl)
                            .into(imageDog);
                } else {
                    Toast.makeText(MainActivity.this, "Ошибка загрузки",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DogResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Ошибка: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    static class DogResponse {
        int fileSizeBytes;
        String url;
    }

    interface DogApiService {
        @GET("woof.json")
        Call<DogResponse> getRandomDog();
    }
}























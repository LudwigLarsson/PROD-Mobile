package com.example.finalprodproject.feature_user.data.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
    private Retrofit retrofit;

    public HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return loggingInterceptor;
    }


    public Retrofit create(String BASE_URL) {
        OkHttpClient.Builder client = new OkHttpClient.Builder().addInterceptor(getHttpLoggingInterceptor());

        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(client.build())
                .build();
    }

    public Retrofit getInstance(String BASE_URL) {
        if (retrofit == null) retrofit = create(BASE_URL);
        return retrofit;
    }
}
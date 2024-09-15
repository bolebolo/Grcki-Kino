package com.app.grckikino.retrofit;

import static com.app.grckikino.utils.KeysAndConstants.API_BASE_URL;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCreator {

    private static final Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());
    private static final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public static RetrofitAPI createService() {
        builder.client(httpClient.build());
        Retrofit retrofit = builder.build();
        return retrofit.create(RetrofitAPI.class);
    }

}

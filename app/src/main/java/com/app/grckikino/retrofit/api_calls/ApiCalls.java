package com.app.grckikino.retrofit.api_calls;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.app.grckikino.models.RoundsModel;
import com.app.grckikino.retrofit.RetrofitCreator;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiCalls {


    public ApiCalls() {
    }

    public static void getUpcomingRounds(MutableLiveData<ArrayList<RoundsModel>> liveData, int gameId, int number) {
        Call<ArrayList<RoundsModel>> call = RetrofitCreator.createService().getUpcomingRounds(gameId, number);
        call.enqueue(new Callback<ArrayList<RoundsModel>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<RoundsModel>> call, @NonNull Response<ArrayList<RoundsModel>> response) {
                if (response.body() != null) {
                    liveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<RoundsModel>> call, @NonNull Throwable t) {
                Log.e("onFailure", "onFailure retrofit call: " + t.getMessage());
            }
        });
    }

}

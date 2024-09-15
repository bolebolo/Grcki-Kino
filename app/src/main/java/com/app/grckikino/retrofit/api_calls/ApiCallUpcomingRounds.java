package com.app.grckikino.retrofit.api_calls;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.app.grckikino.models.UpcomingRoundsModel;
import com.app.grckikino.retrofit.RetrofitCreator;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiCallUpcomingRounds {


    public ApiCallUpcomingRounds() {
    }

    public static void getUpcomingRounds(MutableLiveData<ArrayList<UpcomingRoundsModel>> liveData, int gameId, int number) {
        Call<ArrayList<UpcomingRoundsModel>> call = RetrofitCreator.createService().getUpcomingRounds(gameId, number);
        call.enqueue(new Callback<ArrayList<UpcomingRoundsModel>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<UpcomingRoundsModel>> call, @NonNull Response<ArrayList<UpcomingRoundsModel>> response) {
                if (response.body() != null) {
                    liveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<UpcomingRoundsModel>> call, @NonNull Throwable t) {
                Log.e("onFailure", "onFailure retrofit call: " + t.getMessage());
            }
        });
    }

}

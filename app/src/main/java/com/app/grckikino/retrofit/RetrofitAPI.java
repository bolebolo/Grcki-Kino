package com.app.grckikino.retrofit;

import com.app.grckikino.models.UpcomingRoundsModel;

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitAPI {



        @GET("{gameId}/upcoming/{number}")
        Call<ArrayList<UpcomingRoundsModel>> getUpcomingRounds(@Path("gameId") int gameId, @Path("number") int number);

        //    @Headers("Content-Type: application/json")
//        @POST("braintree_checkout")
//        Call<TransactionModel> checkout(@Body JSONObject nonce);


}

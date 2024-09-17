package com.app.grckikino.retrofit;

import com.app.grckikino.models.RoundsHistoryModel;
import com.app.grckikino.models.RoundsModel;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitAPI {



        @GET("{gameId}/upcoming/{number}")
        Call<ArrayList<RoundsModel>> getUpcomingRounds(@Path("gameId") int gameId, @Path("number") int number);


        @GET("{gameId}/{drawId}")
        Call<RoundsModel> getExactRound(@Path("gameId") int gameId, @Path("drawId") long number);

        @GET("{gameId}/draw-date/{fromDate}/{toDate}")
        Call<RoundsHistoryModel> getResultsHistory(@Path("gameId") int gameId, @Path("fromDate") String fromDate, @Path("toDate") String toDate);


}

package com.app.grckikino.retrofit.api_calls

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.app.grckikino.models.RoundsHistoryModel
import com.app.grckikino.models.RoundsModel
import com.app.grckikino.retrofit.RetrofitCreator
import com.app.grckikino.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


fun getHistoryResults(
    liveData: MutableLiveData<RoundsHistoryModel>,
    gameId: Int,
    timestamp: Long
) {
    val call = RetrofitCreator.createService().getResultsHistory(
        gameId,
        Utils.getDateString(timestamp),
        Utils.getDateString(timestamp)
    )
    call.enqueue(object : Callback<RoundsHistoryModel> {
        override fun onResponse(
            call: Call<RoundsHistoryModel>,
            response: Response<RoundsHistoryModel>
        ) {
            response.body()?.let { roundsHistoryModel ->
                liveData.postValue(roundsHistoryModel)
            }
        }

        override fun onFailure(call: Call<RoundsHistoryModel>, t: Throwable) {
            Log.e("onFailure", "onFailure retrofit call: ${t.message}")
        }
    })
}

fun getExactRounds(
    liveData: MutableLiveData<RoundsModel>,
    gameId: Int,
    drawID: Long
) {
    val call = RetrofitCreator.createService().getExactRound(gameId, drawID)
    call.enqueue(object : Callback<RoundsModel> {
        override fun onResponse(call: Call<RoundsModel>, response: Response<RoundsModel>) {
            response.body()?.let { roundsHistoryModel ->
                liveData.postValue(roundsHistoryModel)
            }
        }

        override fun onFailure(call: Call<RoundsModel>, t: Throwable) {
            Log.e("onFailure", "onFailure retrofit call: ${t.message}")
        }
    })
}
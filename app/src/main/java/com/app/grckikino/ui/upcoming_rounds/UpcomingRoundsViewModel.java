package com.app.grckikino.ui.upcoming_rounds;

import static com.app.grckikino.utils.KeysAndConstants.GAME_ID;
import static com.app.grckikino.utils.KeysAndConstants.LOAD_UPCOMING_ROUNDS;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.grckikino.models.RoundsModel;
import com.app.grckikino.retrofit.api_calls.ApiCalls;

import java.util.ArrayList;


public class UpcomingRoundsViewModel extends ViewModel {

    private MutableLiveData<ArrayList<RoundsModel>> upcomingDrawsArrayList;


    public UpcomingRoundsViewModel() {
        upcomingDrawsArrayList = new MutableLiveData<>();
    }


    public void initList() {
        ApiCalls.getUpcomingRounds(upcomingDrawsArrayList, GAME_ID, LOAD_UPCOMING_ROUNDS);
    }


    public LiveData<ArrayList<RoundsModel>> getUpcomingDrawsList() {
        return upcomingDrawsArrayList;
    }


    public void removeItem(long id, int position) { // kada istekne vreme
        ArrayList<RoundsModel> currentList = upcomingDrawsArrayList.getValue();
        if (currentList != null && position >= 0 && position < currentList.size()) {
            if (currentList.get(position).getDrawId() == id) {
                currentList.remove(position);
                upcomingDrawsArrayList.setValue(currentList);
            }
        }
        new Handler().postDelayed(this::initList, 1500);
    }

    public void removeAllExpired() {
        ArrayList<RoundsModel> arrayList = new ArrayList<>();
        if (upcomingDrawsArrayList.getValue() != null) {
            for (RoundsModel item : upcomingDrawsArrayList.getValue()) {
                if (item.getDrawTime() - System.currentTimeMillis() > 5000) {
                    arrayList.add(item);
                }
            }
            upcomingDrawsArrayList.setValue(arrayList);
            if (arrayList.size() < LOAD_UPCOMING_ROUNDS) {
                initList();
            }
        }
    }

}
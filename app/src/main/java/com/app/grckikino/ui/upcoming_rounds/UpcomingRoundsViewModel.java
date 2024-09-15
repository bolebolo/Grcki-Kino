package com.app.grckikino.ui.upcoming_rounds;

import static com.app.grckikino.utils.KeysAndConstants.GAME_ID;
import static com.app.grckikino.utils.KeysAndConstants.LOAD_UPCOMING_ROUNDS;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.grckikino.models.UpcomingRoundsModel;
import com.app.grckikino.retrofit.api_calls.ApiCallUpcomingRounds;

import java.util.ArrayList;


public class UpcomingRoundsViewModel extends ViewModel {

    private MutableLiveData<ArrayList<UpcomingRoundsModel>> upcomingDrawsArrayList;


    public UpcomingRoundsViewModel() {
        upcomingDrawsArrayList = new MutableLiveData<>();
    }


    public void initList() {
        ApiCallUpcomingRounds.getUpcomingRounds(upcomingDrawsArrayList, GAME_ID, LOAD_UPCOMING_ROUNDS);
    }


    public LiveData<ArrayList<UpcomingRoundsModel>> getUpcomingDrawsList() {
        return upcomingDrawsArrayList;
    }


    public void removeItem(long id, int position) { // kada istekne vreme
        ArrayList<UpcomingRoundsModel> currentList = upcomingDrawsArrayList.getValue();
        if (currentList != null && position >= 0 && position < currentList.size()) {
            if (currentList.get(position).getDrawId() == id) {
                currentList.remove(position);
                upcomingDrawsArrayList.setValue(currentList);
            }
        }
        initList();
    }

    public void removeAllExpired() {
        ArrayList<UpcomingRoundsModel> arrayList = new ArrayList<>();
        if (upcomingDrawsArrayList.getValue() != null) {
            for (UpcomingRoundsModel item : upcomingDrawsArrayList.getValue()) {
                if (item.getDrawTime() - System.currentTimeMillis() > 5000) {
                    arrayList.add(item);
                }
            }
            upcomingDrawsArrayList.setValue(arrayList);
            if (arrayList.size() < LOAD_UPCOMING_ROUNDS) {// da bi uvek u listi bilo 20
                initList();
            }
        }
    }


    private ArrayList<UpcomingRoundsModel> loadItems(int x) {
        ArrayList<UpcomingRoundsModel> arrayList = new ArrayList<>();
        for (int i = 0; i < x; i++) {
//            if (getCurrentTime(i) - System.currentTimeMillis() >5){
            arrayList.add(new UpcomingRoundsModel(i + 5, getCurrentTime(i)));
        }
//        }
        return arrayList;
    }

    private long getCurrentTime(int i) {
        return System.currentTimeMillis() + (long) i * 20 * 1000;
    }

}
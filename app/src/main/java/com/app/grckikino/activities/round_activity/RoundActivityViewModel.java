package com.app.grckikino.activities.round_activity;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.grckikino.models.RoundsModel;

public class RoundActivityViewModel extends ViewModel {

    private MutableLiveData<RoundsModel> roundModel;

    public RoundActivityViewModel() {
        roundModel = new MutableLiveData<>();
    }


    public MutableLiveData<RoundsModel> getRound() {
        return roundModel;
    }

    public void setRound(RoundsModel roundsModel) {
        roundModel.setValue(roundsModel);
    }
}
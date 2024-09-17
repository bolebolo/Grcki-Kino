package com.app.grckikino.ui.results_history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.grckikino.models.RoundsHistoryModel


class ResultsHistoryViewModelKt : ViewModel() {


    private val _emptytext = MutableLiveData<String>()
    private val _roundsHistory = MutableLiveData<RoundsHistoryModel>()

    val emptyText: MutableLiveData<String> get() = _emptytext
    val roundsHistory: MutableLiveData<RoundsHistoryModel> get() = _roundsHistory
    init {
        setText( "Nema Nicega za Prikaz!")
    }
    fun setText(text: String) {
        _emptytext.value = text
    }

    fun setRoundsHistory(roundsHistory: RoundsHistoryModel) {
        _roundsHistory.value = roundsHistory
    }
}
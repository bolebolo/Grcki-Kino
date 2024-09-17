package com.app.grckikino.ui.round_result

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.grckikino.models.RoundsModel

class ResultRoundViewModelKt : ViewModel() {


    private val _emptyText = MutableLiveData<String>()
    private val _roundResult = MutableLiveData<RoundsModel>()


    val emptyText: MutableLiveData<String> get() = _emptyText
    val roundResult: MutableLiveData<RoundsModel> get() = _roundResult
    init {
        setEmptyText( "Kolo jos nije pocelo")
    }
    fun setEmptyText(text: String) {
        _emptyText.value = text
    }

    fun setRoundResult(roundsHistory: RoundsModel) {
        _roundResult.value = roundsHistory
    }
}
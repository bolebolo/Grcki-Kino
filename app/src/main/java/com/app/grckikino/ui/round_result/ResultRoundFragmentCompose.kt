package com.app.grckikino.ui.round_result

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.app.grckikino.compose_ui_elements.emptyScr
import com.app.grckikino.compose_ui_elements.oneResultItem


@Composable
fun ResultsRoundFragmentCompose(viewModel: ResultRoundViewModelKt) {
    val resultRound = viewModel.roundResult.observeAsState().value
    val emptyText = viewModel.emptyText.observeAsState().value

    if(resultRound!=null && resultRound.winningNumbers!=null){
        oneResultItem(resultRound)
    }else{
        emptyText?.let { emptyScr(it) }
    }
}
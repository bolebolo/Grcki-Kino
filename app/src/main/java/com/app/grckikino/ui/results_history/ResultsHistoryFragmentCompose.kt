package com.app.grckikino.ui.results_history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.grckikino.compose_ui_elements.emptyScr
import com.app.grckikino.compose_ui_elements.oneResultItem
import com.app.grckikino.models.RoundsHistoryModel



@Composable
fun ResultsHistoryFragmentCompose(viewModel: ResultsHistoryViewModelKt) {
    val resultsHistory = viewModel.roundsHistory.observeAsState().value
    val emptyText = viewModel.emptyText.observeAsState().value
    resultsHistory?.let {
        listResultItem(resultsHistory)
    } ?: run {
        emptyText?.let { emptyScr(it) }
    }
}

@Composable
fun listResultItem(resultsHistory: RoundsHistoryModel) {
    val resultsNumb = resultsHistory.content.size
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(resultsNumb) { index ->
            val round = resultsHistory.content[index]
            oneResultItem(round)
        }
    }
}



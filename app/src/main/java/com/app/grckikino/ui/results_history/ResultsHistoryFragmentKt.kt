package com.app.grckikino.ui.results_history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.app.grckikino.retrofit.api_calls.getHistoryResults
import com.app.grckikino.theming_compose.AppTheme
import com.app.grckikino.utils.KeysAndConstants.GAME_ID


class ResultsHistoryFragmentKt : Fragment() {


    private val viewModel: ResultsHistoryViewModelKt by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme {
                    getHistoryResults(viewModel.roundsHistory, GAME_ID, System.currentTimeMillis())
                   ResultsHistoryFragmentCompose(viewModel = viewModel)
                }
            }
        }
    }
}
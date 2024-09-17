package com.app.grckikino.ui.round_result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.app.grckikino.activities.round_activity.RoundActivityViewModel
import com.app.grckikino.models.RoundsModel
import com.app.grckikino.retrofit.api_calls.getExactRounds
import com.app.grckikino.theming_compose.AppTheme
import com.app.grckikino.utils.KeysAndConstants.GAME_ID


class ResultRoundFragmentKt : Fragment() {


    private val viewModel: ResultRoundViewModelKt by viewModels()

    private lateinit var viewModelActivity: RoundActivityViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme {
                    ResultsRoundFragmentCompose(viewModel = viewModel)
                    viewModelActivity =
                        ViewModelProvider(requireActivity())[RoundActivityViewModel::class.java]
                    val roundData: RoundsModel? = viewModelActivity.round.value
                    roundData?.let {
                        viewModel.setRoundResult(it)
                        getExactRounds(viewModel.roundResult, GAME_ID, it.drawId)
                    }
                }
            }
        }
    }
}
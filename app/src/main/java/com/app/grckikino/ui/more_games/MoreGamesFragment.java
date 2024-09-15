package com.app.grckikino.ui.more_games;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.app.grckikino.R;
import com.app.grckikino.databinding.FragmentMoreGamesBinding;

public class MoreGamesFragment extends Fragment {

    private FragmentMoreGamesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MoreGamesViewModel dashboardViewModel =
                new ViewModelProvider(this).get(MoreGamesViewModel.class);

        binding = FragmentMoreGamesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDashboard;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        dashboardViewModel.setText(getString(R.string.page_under_constraction));
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}